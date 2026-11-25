import assert from "node:assert/strict";
import { readFileSync } from "node:fs";
import test from "node:test";
import { fileURLToPath } from "node:url";
import { runInNewContext } from "node:vm";

const scriptUrl = new URL(
  "../docs/.vuepress/public/html/image-preview.js",
  import.meta.url,
);
const script = readFileSync(scriptUrl, "utf8");

function setup({ autoPlay = true, standalone = false } = {}) {
  const handlers = {};
  const messages = [];
  const timers = [];
  const calls = { stop: 0, play: 0, focus: 0 };
  const parent = { postMessage: (...args) => messages.push(args) };
  const trigger = {
    addEventListener: (type, handler) => (handlers[type] = handler),
    focus: () => calls.focus++,
  };
  const carousel = {
    currentIndex: 4,
    interval: 5000,
    isAutoPlaying: autoPlay,
    items: Array.from({ length: 7 }, (_, index) => ({
      querySelector: () => ({ textContent: ` Feature ${index} ` }),
    })),
    stopAutoPlay: () => calls.stop++,
    autoPlay: () => calls.play++,
  };
  const window = {
    parent,
    location: { origin: "https://example.com" },
    addEventListener: (type, handler) => (handlers[type] = handler),
  };
  if (standalone) window.parent = window;
  runInNewContext(
    `${script}\ninitImagePreview(carousel);`,
    {
      carousel,
      window,
      document: { querySelector: () => trigger },
      setTimeout: (callback, delay) => {
        timers.push({ callback, delay });
        return timers.length;
      },
    },
    { filename: fileURLToPath(scriptUrl) },
  );
  const close = (overrides = {}) =>
    handlers.message({
      origin: window.location.origin,
      source: parent,
      data: { type: "home-advantage:close" },
      ...overrides,
    });
  return { handlers, messages, timers, calls, carousel, close };
}

test("opens the current image and pauses the carousel only once", () => {
  const { handlers, messages, calls } = setup();
  handlers.click();
  handlers.click();
  assert.equal(calls.stop, 1);
  assert.equal(messages.length, 1);
  assert.equal(messages[0][0].type, "home-advantage:preview");
  assert.equal(messages[0][0].index, 4);
  assert.equal(messages[0][0].title, "Feature 4");
  assert.equal(messages[0][1], "https://example.com");
});

test("restores focus and resumes autoplay after the full interval", () => {
  const { handlers, close, timers, calls, carousel } = setup();
  handlers.click();
  close();
  assert.equal(calls.focus, 1);
  assert.equal(timers.length, 1);
  assert.equal(carousel.timer, 1);
  assert.equal(timers[0].delay, 5000);
  assert.equal(calls.play, 0);
  timers[0].callback();
  assert.equal(calls.play, 1);
  close();
  assert.equal(timers.length, 1);
  handlers.click();
  assert.equal(calls.stop, 2);
});

test("keeps a manually selected slide paused after closing", () => {
  const { handlers, close, timers, calls } = setup({ autoPlay: false });
  handlers.click();
  close();
  assert.equal(calls.focus, 1);
  assert.equal(timers.length, 0);
});

test("ignores messages from other origins, windows and unrelated payloads", () => {
  const { handlers, close, timers, calls } = setup();
  handlers.click();
  close({ origin: "https://other.example" });
  close({ source: {} });
  close({ data: null });
  close({ data: { type: "other" } });
  assert.equal(calls.focus, 0);
  assert.equal(timers.length, 0);
  close();
  assert.equal(calls.focus, 1);
});

test("ignores closing before opening and leaves standalone autoplay running", () => {
  const { close, timers, calls } = setup();
  close();
  assert.equal(calls.focus, 0);
  assert.equal(timers.length, 0);
  const standalone = setup({ standalone: true });
  standalone.handlers.click();
  assert.equal(standalone.calls.stop, 0);
  assert.equal(standalone.messages.length, 0);
});
