import assert from "node:assert/strict";
import test from "node:test";

// Start docs:dev (or serve docs/.vuepress/dist), then set SITE_TEST_URL.
test(
  "white smoke cursor and its navbar switch",
  {
    skip: !process.env.SITE_TEST_URL,
    timeout: 180000,
  },
  async (t) => {
    const { chromium } = await import(
      process.env.PLAYWRIGHT_MODULE || "playwright"
    );
    const browser = await chromium.launch({
      channel: "chrome",
      headless: true,
    });
    const errors = [];
    const newPage = async (options = {}) => {
      const page = await browser.newPage({
        viewport: { width: 1440, height: 1000 },
        locale: "zh-CN",
        ...options,
      });
      page.on("pageerror", (error) => errors.push(error.message));
      await page.route("**/*", (route) =>
        new URL(route.request().url()).origin ===
        new URL(process.env.SITE_TEST_URL).origin
          ? route.continue()
          : route.abort(),
      );
      return page;
    };
    try {
      const page = await newPage();
      await page.addInitScript(() => {
        window.cursorStats = {
          frames: 0,
          colors: {},
          deletedTextures: 0,
          deletedPrograms: 0,
        };
        const proto = WebGL2RenderingContext.prototype;
        const names = new WeakMap();
        const location = proto.getUniformLocation;
        proto.getUniformLocation = function (program, name) {
          const value = location.call(this, program, name);
          if (value) names.set(value, name);
          return value;
        };
        const uniform = proto.uniform3f;
        proto.uniform3f = function (location, r, g, b) {
          const name = names.get(location);
          if (
            this.canvas.classList.contains("glow-cursor__canvas") &&
            ["uColor", "uSecondary"].includes(name)
          )
            window.cursorStats.colors[name] = [r, g, b];
          return uniform.call(this, location, r, g, b);
        };
        const draw = proto.drawArrays;
        proto.drawArrays = function (...args) {
          draw.apply(this, args);
          if (
            !this.canvas.classList.contains("glow-cursor__canvas") ||
            this.getParameter(this.FRAMEBUFFER_BINDING)
          )
            return;
          window.cursorStats.frames++;
          if (!window.captureCursorPixels) return;
          window.captureCursorPixels = false;
          const pixels = new Uint8Array(
            this.canvas.width * this.canvas.height * 4,
          );
          this.readPixels(
            0,
            0,
            this.canvas.width,
            this.canvas.height,
            this.RGBA,
            this.UNSIGNED_BYTE,
            pixels,
          );
          let visible = 0;
          let colored = 0;
          for (let i = 0; i < pixels.length; i += 4) {
            if (pixels[i + 3] < 8) continue;
            visible++;
            if (
              Math.max(pixels[i], pixels[i + 1], pixels[i + 2]) -
                Math.min(pixels[i], pixels[i + 1], pixels[i + 2]) >
              2
            )
              colored++;
          }
          window.cursorStats.pixels = { visible, colored };
        };
        for (const [method, counter] of [
          ["deleteTexture", "deletedTextures"],
          ["deleteProgram", "deletedPrograms"],
        ]) {
          const original = proto[method];
          proto[method] = function (...args) {
            if (this.canvas.classList.contains("glow-cursor__canvas"))
              window.cursorStats[counter]++;
            return original.apply(this, args);
          };
        }
      });
      await page.goto(process.env.SITE_TEST_URL, { waitUntil: "networkidle" });
      if (await page.locator(".vp-notice-wrapper .close-icon").isVisible())
        await page.locator(".vp-notice-wrapper .close-icon").click();
      const canvas = page.locator(".site-cursor canvas");
      const toggle = page.getByRole("button", {
        name: "鼠标特效",
        exact: true,
      });
      const labels = {
        gyroscope: "旋转光标",
        glow: "烟雾轨迹",
        off: "关闭特效",
      };
      const selectEffect = async (value) => {
        await toggle.click();
        await page
          .getByRole("button", { name: labels[value], exact: true })
          .click();
      };
      const selectedEffect = () =>
        page.evaluate(
          () =>
            localStorage.getItem("fast-request:mouse-effects") || "gyroscope",
        );
      const frames = () => page.evaluate(() => window.cursorStats.frames);
      const waitForRest = async () => {
        await page.waitForFunction(
          () => {
            const current = window.cursorStats.frames;
            const unchanged = current === window.lastCursorFrames;
            window.lastCursorFrames = current;
            return unchanged;
          },
          {},
          { polling: 500, timeout: 15000 },
        );
        const before = await frames();
        await page.waitForTimeout(400);
        assert.equal(await frames(), before);
      };
      assert.equal(await selectedEffect(), "gyroscope");
      await page.mouse.move(300, 400);
      await page.mouse.move(500, 400, { steps: 5 });
      await page.locator(".gyroscope-cursor.is-visible").waitFor();
      await page.mouse.move(500, 30);
      assert.equal(
        await page.evaluate(() => {
          const cursor = document
            .querySelector(".gyroscope-cursor svg")
            .getBoundingClientRect();
          return (
            document
              .elementFromPoint(
                cursor.left + cursor.width / 2,
                cursor.top + cursor.height / 2,
              )
              ?.closest(".site-cursor") !== null
          );
        }),
        false,
      ); // The overlay must never intercept pointer events.
      assert.ok(
        await page
          .locator(".site-cursor")
          .evaluate(
            (el) =>
              Number(getComputedStyle(el).zIndex) >
              Number(
                getComputedStyle(document.querySelector(".vp-navbar")).zIndex ||
                  0,
              ),
          ),
      );
      assert.equal(
        await page.evaluate(() => getComputedStyle(document.body).cursor),
        "none",
      );
      await page.mouse.down();
      assert.equal(await page.locator(".gyroscope-ripple").count(), 1);
      await page.mouse.up();
      await selectEffect("glow");
      await canvas.waitFor();

      await t.test(
        "white and gray smoke actually renders without the old ribbons",
        async () => {
          assert.equal(
            await page
              .locator(".ribbons, .ribbons-canvas, .site-ribbons")
              .count(),
            0,
          );
          const control = await toggle.boundingBox();
          const music = await page.locator(".sound-toggle").boundingBox();
          assert.ok(control.x + control.width <= music.x);
          assert.equal(await selectedEffect(), "glow");
          assert.equal(
            await page
              .locator(".site-cursor")
              .evaluate((el) => getComputedStyle(el).pointerEvents),
            "none",
          );
          await page.mouse.move(300, 400);
          await page.mouse.move(1000, 500, { steps: 30 });
          await page.evaluate(() => {
            window.captureCursorPixels = true;
          });
          await page.waitForFunction(
            () => window.cursorStats.pixels?.visible > 50,
          );
          const stats = await page.evaluate(() => window.cursorStats);
          assert.deepEqual(stats.colors.uColor, [1, 1, 1]);
          assert.deepEqual(stats.colors.uSecondary, [
            155 / 255,
            155 / 255,
            155 / 255,
          ]);
          assert.equal(stats.pixels.colored, 0);
          if (process.env.CURSOR_SCREENSHOT)
            await page.screenshot({ path: process.env.CURSOR_SCREENSHOT });
          await page.locator(".vp-hero-action.primary").click({ trial: true });
        },
      );

      await t.test(
        "switch releases GPU resources immediately and remembers the choice",
        async () => {
          const music = page.locator(".sound-toggle");
          const musicState = await music.getAttribute("aria-pressed");
          await selectEffect("off");
          await canvas.waitFor({ state: "detached" });
          assert.equal(await music.getAttribute("aria-pressed"), musicState);
          const stats = await page.evaluate(() => window.cursorStats);
          assert.ok(stats.deletedTextures > 0 && stats.deletedPrograms >= 11);
          const before = await frames();
          await page.mouse.move(400, 400, { steps: 10 });
          await page.waitForTimeout(300);
          assert.equal(await frames(), before);
          await page.reload({ waitUntil: "networkidle" });
          assert.equal(await selectedEffect(), "off");
          assert.equal(await canvas.count(), 0);
          await selectEffect("glow");
          await canvas.waitFor();
          assert.equal(await selectedEffect(), "glow");
        },
      );

      await t.test(
        "idle and leaving the window stop rendering, then movement wakes it",
        async () => {
          await page.mouse.move(300, 500);
          await page.mouse.move(1100, 300, { steps: 15 });
          await waitForRest();
          const before = await frames();
          await page.mouse.move(600, 600, { steps: 10 });
          await page.waitForFunction(
            (count) => window.cursorStats.frames > count,
            before,
          );
          await page.evaluate(() =>
            document.documentElement.dispatchEvent(
              new PointerEvent("pointerleave"),
            ),
          );
          await waitForRest();
          const afterLeave = await frames();
          await page.evaluate(() => {
            window.dispatchEvent(
              new PointerEvent("pointermove", {
                pointerType: "touch",
                clientX: 500,
                clientY: 500,
              }),
            );
            window.dispatchEvent(
              new PointerEvent("pointerdown", {
                pointerType: "touch",
                clientX: 500,
                clientY: 500,
              }),
            );
          });
          await page.waitForTimeout(300);
          assert.equal(await frames(), afterLeave);
        },
      );

      await t.test(
        "background pages and reduced motion pause or remove the effect",
        async () => {
          await page.mouse.move(900, 400, { steps: 10 });
          await page.evaluate(() => {
            Object.defineProperty(document, "hidden", {
              configurable: true,
              value: true,
            });
            document.dispatchEvent(new Event("visibilitychange"));
          });
          const before = await frames();
          await page.waitForTimeout(400);
          assert.equal(await frames(), before);
          await page.evaluate(() => {
            delete document.hidden;
            document.dispatchEvent(new Event("visibilitychange"));
          });
          await page.mouse.move(300, 600, { steps: 10 });
          await page.waitForFunction(
            (count) => window.cursorStats.frames > count,
            before,
          );
          await page.emulateMedia({ reducedMotion: "reduce" });
          await canvas.waitFor({ state: "detached" });
          assert.equal(await selectedEffect(), "glow");
          await page.emulateMedia({ reducedMotion: "no-preference" });
          await canvas.waitFor();
          assert.equal(await canvas.count(), 1);
        },
      );

      await t.test(
        "page navigation retains the setting and resizing keeps a single canvas",
        async () => {
          await selectEffect("off");
          await page.locator(".vp-hero-action.primary").click();
          await page.waitForURL((url) => url.pathname !== "/");
          assert.equal(await canvas.count(), 0);
          assert.equal(await selectedEffect(), "off");
          await selectEffect("glow");
          await canvas.waitFor();
          await page.setViewportSize({ width: 1200, height: 800 });
          await page.waitForFunction(
            () => document.querySelector(".site-cursor canvas")?.width === 1200,
          );
          assert.equal(await canvas.evaluate((el) => el.height), 800);
          assert.equal(await canvas.count(), 1);
        },
      );

      await t.test(
        "context loss stops drawing and the switch can create a fresh renderer",
        async () => {
          await canvas.evaluate((el) =>
            el
              .getContext("webgl2")
              .getExtension("WEBGL_lose_context")
              .loseContext(),
          );
          await page.waitForTimeout(100);
          const before = await frames();
          await page.mouse.move(500, 400, { steps: 10 });
          await page.waitForTimeout(300);
          assert.equal(await frames(), before);
          await selectEffect("off");
          await selectEffect("glow");
          await canvas.waitFor();
          await page.mouse.move(800, 500, { steps: 10 });
          await page.waitForFunction(
            (count) => window.cursorStats.frames > count,
            before,
          );
        },
      );

      await t.test(
        "English and touch devices preserve the navbar control",
        async () => {
          const english = await newPage({
            locale: "en-US",
            deviceScaleFactor: 2,
          });
          await english.goto(new URL("/en/", process.env.SITE_TEST_URL).href, {
            waitUntil: "networkidle",
          });
          if (
            await english.locator(".vp-notice-wrapper .close-icon").isVisible()
          )
            await english.locator(".vp-notice-wrapper .close-icon").click();
          const control = english.getByRole("button", {
            name: "Mouse effects",
            exact: true,
          });
          await control.waitFor();
          await control.click();
          await english.getByRole("button", { name: "Smoke trail" }).click();
          await english.waitForFunction(
            () => document.querySelector(".site-cursor canvas")?.width === 2160,
          );
          await control.click();
          await english.getByRole("button", { name: "Effects off" }).click();
          assert.equal(await english.locator(".site-cursor").count(), 0);
          await english.close();
          const mobile = await newPage({
            isMobile: true,
            hasTouch: true,
            viewport: { width: 390, height: 844 },
          });
          await mobile.goto(process.env.SITE_TEST_URL, {
            waitUntil: "networkidle",
          });
          assert.equal(await mobile.locator(".site-cursor").count(), 0);
          await mobile.close();
        },
      );

      await t.test(
        "WebGL unavailability leaves controls and navigation usable",
        async () => {
          await page.addInitScript(() => {
            const original = HTMLCanvasElement.prototype.getContext;
            HTMLCanvasElement.prototype.getContext = function (type, ...args) {
              return type === "webgl2"
                ? null
                : original.call(this, type, ...args);
            };
          });
          await page.goto(process.env.SITE_TEST_URL, {
            waitUntil: "networkidle",
          });
          await canvas.waitFor();
          await selectEffect("off");
          assert.equal(await canvas.count(), 0);
          await page.locator(".vp-hero-action.primary").click();
          await page.waitForURL((url) => url.pathname !== "/");
        },
      );
      assert.deepEqual(errors, []);
    } finally {
      await browser.close();
    }
  },
);
