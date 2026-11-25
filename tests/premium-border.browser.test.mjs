import assert from "node:assert/strict";
import test from "node:test";

// Start docs:dev (or serve docs/.vuepress/dist) and set SITE_TEST_URL.
test(
  "premium card border and spotlight",
  {
    skip: !process.env.SITE_TEST_URL,
    timeout: 90000,
  },
  async (t) => {
    const { chromium } = await import(
      process.env.PLAYWRIGHT_MODULE || "playwright"
    );
    const browser = await chromium.launch({
      channel: "chrome",
      headless: true,
    });
    try {
      for (const path of ["/", "/en/"]) {
        await t.test(path, async () => {
          const page = await browser.newPage({
            viewport: { width: 1440, height: 1000 },
            deviceScaleFactor: path === "/en/" ? 2 : 1,
            locale: path === "/" ? "zh-CN" : "en-US",
          });
          const errors = [];
          page.on("pageerror", (error) => errors.push(error.message));
          page.on("console", (message) => {
            if (
              /Failed to resolve component: (PremiumBorder|ElectricBorder)/.test(
                message.text(),
              )
            )
              errors.push(message.text());
          });
          await page.route("**/*", (route) =>
            new URL(route.request().url()).origin ===
            new URL(process.env.SITE_TEST_URL).origin
              ? route.continue()
              : route.abort(),
          );
          await page.goto(new URL(path, process.env.SITE_TEST_URL).href, {
            waitUntil: "networkidle",
          });
          if (await page.locator(".redirect-modal-mask").isVisible())
            await page
              .getByRole("button", { name: "Cancel", exact: true })
              .click();
          if (await page.locator(".vp-notice-wrapper .close-icon").isVisible())
            await page.locator(".vp-notice-wrapper .close-icon").click();
          const card = page.locator(".paid-version");
          const border = card.locator(".premium-border");
          const trail = border.locator(".premium-border-trail");
          await border.waitFor({ state: "attached" });
          assert.equal(
            await card.locator("electricborder, premiumborder").count(),
            0,
          );
          assert.equal(
            await card.locator(".electric-border, feTurbulence").count(),
            0,
          );
          assert.equal(
            await page.locator(".compare-wrapper .tip .premium-border").count(),
            0,
          );
          assert.equal(await border.getAttribute("aria-hidden"), "true");
          assert.equal(
            await border.evaluate((el) => getComputedStyle(el).pointerEvents),
            "none",
          );
          assert.equal(
            await trail.evaluate(
              (el) => getComputedStyle(el).animationDuration,
            ),
            "10s",
          );
          assert.equal(
            await trail.evaluate((el) => getComputedStyle(el).strokeWidth),
            "2px",
          );
          const track = border.locator(".premium-border-track");
          assert.equal(
            await track.evaluate((el) => getComputedStyle(el).strokeWidth),
            "2px",
          );
          assert.ok(
            await track.evaluate(
              (el) => Number(getComputedStyle(el).opacity) >= 0.6,
            ),
          );
          assert.match(
            await track.evaluate((el) => getComputedStyle(el).stroke),
            /url\(/,
          );
          assert.equal(
            await border
              .locator(".premium-border-outline")
              .evaluate((el) => getComputedStyle(el).zIndex),
            "1",
          );
          assert.equal(
            await trail.evaluate((el) => getComputedStyle(el).strokeDasharray),
            "120px, 380px",
          );
          assert.equal(
            await trail.evaluate(
              (el) => getComputedStyle(el).animationPlayState,
            ),
            "paused",
          );

          await card.evaluate((el) =>
            window.scrollTo(
              0,
              window.scrollY + el.getBoundingClientRect().top - 130,
            ),
          );
          await page.waitForFunction(() =>
            document
              .querySelector(".premium-border")
              ?.classList.contains("is-animated"),
          );
          const before = await trail.evaluate(
            (el) => getComputedStyle(el).strokeDashoffset,
          );
          await page.waitForTimeout(200);
          assert.notEqual(
            await trail.evaluate((el) => getComputedStyle(el).strokeDashoffset),
            before,
          );
          const rect = await card.boundingBox();
          // Check rendered edge pixels, rather than only the animation's CSS.
          // The edge must remain visible even without hover or a passing comet.
          const clip = {
            x: rect.x - 4,
            y: rect.y + 30,
            width: 12,
            height: 160,
          };
          const visibleEdge = await page.screenshot({
            clip,
            animations: "disabled",
          });
          await border.evaluate((el) => {
            el.style.visibility = "hidden";
          });
          const hiddenEdge = await page.screenshot({
            clip,
            animations: "disabled",
          });
          await border.evaluate((el) => {
            el.style.removeProperty("visibility");
          });
          const contrast = await page.evaluate(
            async (images) => {
              const pixels = await Promise.all(
                images.map(async (image) => {
                  const blob = await (
                    await fetch(`data:image/png;base64,${image}`)
                  ).blob();
                  const bitmap = await createImageBitmap(blob);
                  const canvas = document.createElement("canvas");
                  canvas.width = bitmap.width;
                  canvas.height = bitmap.height;
                  const context = canvas.getContext("2d");
                  context.drawImage(bitmap, 0, 0);
                  bitmap.close();
                  return context.getImageData(0, 0, canvas.width, canvas.height)
                    .data;
                }),
              );
              let visible = 0;
              for (let index = 0; index < pixels[0].length; index += 4) {
                if (
                  [0, 1, 2].some(
                    (channel) =>
                      Math.abs(
                        pixels[0][index + channel] - pixels[1][index + channel],
                      ) >= 24,
                  )
                )
                  visible++;
              }
              return visible / (pixels[0].length / 4);
            },
            [visibleEdge.toString("base64"), hiddenEdge.toString("base64")],
          );
          assert.ok(contrast >= 0.08, `Border was too faint: ${contrast}`);
          await page.mouse.move(rect.x + 12, rect.y + 180);
          await page.waitForFunction(() =>
            document
              .querySelector(".premium-border")
              ?.classList.contains("is-highlighted"),
          );
          const coordinates = () =>
            border.evaluate((el) =>
              ["--spotlight-x", "--spotlight-y"].map((key) =>
                parseFloat(el.style.getPropertyValue(key)),
              ),
            );
          const [x, y] = await coordinates();
          assert.ok(Math.abs(x - 12) < 2 && Math.abs(y - 180) < 2);

          if (process.env.PREMIUM_SCREENSHOT_DIR) {
            await page.addStyleTag({
              content: ".site-cursor { visibility: hidden; }",
            });
            await page.waitForTimeout(300);
            await page.screenshot({
              path: `${process.env.PREMIUM_SCREENSHOT_DIR}/premium-${path === "/" ? "zh" : "en"}.png`,
            });
          }
          await page.evaluate(() => window.scrollBy(0, 200));
          await page.waitForFunction(
            (oldY) =>
              parseFloat(
                document
                  .querySelector(".premium-border")
                  .style.getPropertyValue("--spotlight-y"),
              ) >
              oldY + 195,
            y,
          );
          const stable = await coordinates();
          await page.waitForTimeout(300);
          assert.deepEqual(await coordinates(), stable);

          await page.mouse.move(1400, 400);
          await page.waitForFunction(
            () =>
              !document
                .querySelector(".premium-border")
                ?.classList.contains("is-highlighted"),
          );
          const buy = card.locator(".buyBtn");
          await buy.focus();
          await page.waitForFunction(() =>
            document
              .querySelector(".premium-border")
              ?.classList.contains("is-highlighted"),
          );
          await buy.click({ trial: true });
          await page.evaluate(() => {
            window.open = (url) => {
              window.lastOpenedUrl = url;
              return null;
            };
          });
          await buy.click();
          assert.equal(
            await page.evaluate(() => window.lastOpenedUrl),
            "https://plugins.jetbrains.com/plugin/16988/pricing",
          );

          await page.emulateMedia({ reducedMotion: "reduce" });
          await page.waitForFunction(
            () =>
              !document
                .querySelector(".premium-border")
                ?.classList.contains("is-animated"),
          );
          assert.equal(
            await trail.evaluate((el) => getComputedStyle(el).animationName),
            "none",
          );
          assert.notEqual(
            await track.evaluate((el) => getComputedStyle(el).display),
            "none",
          );
          assert.equal(
            await border
              .locator(".premium-border-spotlight")
              .evaluate((el) => getComputedStyle(el).display),
            "none",
          );
          await page.emulateMedia({ reducedMotion: "no-preference" });
          await page.waitForFunction(() =>
            document
              .querySelector(".premium-border")
              ?.classList.contains("is-animated"),
          );
          await page.evaluate(() => {
            Object.defineProperty(document, "hidden", {
              configurable: true,
              value: true,
            });
            document.dispatchEvent(new Event("visibilitychange"));
          });
          await page.waitForFunction(
            () =>
              !document
                .querySelector(".premium-border")
                ?.classList.contains("is-animated"),
          );
          await page.evaluate(() => {
            delete document.hidden;
            document.dispatchEvent(new Event("visibilitychange"));
          });
          await page.waitForFunction(() =>
            document
              .querySelector(".premium-border")
              ?.classList.contains("is-animated"),
          );
          await page.evaluate(() => {
            document.activeElement?.blur();
            window.scrollTo(0, 0);
          });
          await page.waitForFunction(
            () =>
              !document
                .querySelector(".premium-border")
                ?.classList.contains("is-animated"),
          );
          const handle = await border.elementHandle();
          await page.locator(".vp-hero-action.primary").click();
          await page.waitForURL((url) => url.pathname !== path);
          assert.equal(await handle.evaluate((el) => el.isConnected), false);
          await page.mouse.move(400, 400);
          assert.deepEqual(errors, []);
          await page.close();
        });
      }
    } finally {
      await browser.close();
    }
  },
);
