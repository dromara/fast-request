import assert from "node:assert/strict";
import { afterEach, test } from "node:test";
import { createSSRApp } from "vue";
import {
  createCursorPreference,
  cursorPreferenceKey,
  useCursorPreference,
} from "../docs/.vuepress/composables/useCursorPreference.js";

afterEach(() => {
  delete globalThis.window;
});
const key = "fast-request:mouse-effects";
function storage(initial) {
  const values = new Map(initial === undefined ? [] : [[key, initial]]);
  globalThis.window = {
    localStorage: {
      getItem: (key) => values.get(key) ?? null,
      setItem: (key, value) => values.set(key, value),
    },
  };
  return values;
}
test("defaults to gyroscope and isolates app instances", () => {
  const first = createCursorPreference(),
    second = createCursorPreference();
  first.setEffect("glow");
  assert.equal(first.effect.value, "glow");
  assert.equal(second.effect.value, "gyroscope");
});
test("restores choices and migrates the previous switch", () => {
  for (const [value, expected] of [
    [undefined, "gyroscope"],
    ["true", "gyroscope"],
    ["false", "off"],
    ["off", "off"],
    ["glow", "glow"],
    ["gyroscope", "gyroscope"],
    ["invalid", "gyroscope"],
  ]) {
    storage(value);
    const preference = createCursorPreference();
    preference.restore();
    assert.equal(preference.effect.value, expected);
  }
});
test("persists selections and rejects invalid values", () => {
  const values = storage();
  const preference = createCursorPreference();
  for (const value of ["glow", "off", "gyroscope"]) {
    preference.setEffect(value);
    assert.equal(values.get(key), value);
    const reloaded = createCursorPreference();
    reloaded.restore();
    assert.equal(reloaded.effect.value, value);
  }
  preference.setEffect("invalid");
  assert.equal(preference.effect.value, "gyroscope");
  assert.equal(values.get(key), "gyroscope");
});
test("works when storage is unavailable", () => {
  globalThis.window = {
    get localStorage() {
      throw new Error("unavailable");
    },
  };
  const preference = createCursorPreference();
  preference.restore();
  preference.setEffect("off");
  assert.equal(preference.effect.value, "off");
});
test("injects the preference and reports a missing provider", (t) => {
  const app = createSSRApp({ render: () => null });
  const preference = createCursorPreference();
  app.provide(cursorPreferenceKey, preference);
  assert.equal(app.runWithContext(useCursorPreference), preference);
  t.mock.method(console, "warn", () => {});
  assert.throws(
    () =>
      createSSRApp({ render: () => null }).runWithContext(useCursorPreference),
    /Mouse effect preference is unavailable/,
  );
});
