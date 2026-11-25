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

function storage(initial) {
  const values = new Map(
    initial === undefined ? [] : [["fast-request:mouse-effects", initial]],
  );
  globalThis.window = {
    localStorage: {
      getItem: (key) => values.get(key) ?? null,
      setItem: (key, value) => values.set(key, value),
    },
  };
  return values;
}

test("defaults on and isolates preferences between app instances", () => {
  const first = createCursorPreference();
  const second = createCursorPreference();
  assert.equal(first.enabled.value, true);
  first.toggle();
  assert.equal(first.enabled.value, false);
  assert.equal(second.enabled.value, true);
});

test("restores explicit choices and defaults on for missing or invalid preferences", () => {
  for (const [value, expected] of [
    [undefined, true],
    ["true", true],
    ["false", false],
    ["invalid", true],
  ]) {
    storage(value);
    const preference = createCursorPreference();
    preference.restore();
    assert.equal(preference.enabled.value, expected);
  }
});

test("saves both off and on and restores the choice in a new app", () => {
  const values = storage();
  const preference = createCursorPreference();
  preference.toggle();
  assert.equal(values.get("fast-request:mouse-effects"), "false");
  const reloaded = createCursorPreference();
  reloaded.restore();
  assert.equal(reloaded.enabled.value, false);
  reloaded.toggle();
  assert.equal(values.get("fast-request:mouse-effects"), "true");
});

test("keeps working when storage reads and writes fail", () => {
  globalThis.window = {
    get localStorage() {
      throw new Error("Storage unavailable");
    },
  };
  const preference = createCursorPreference();
  preference.restore();
  assert.equal(preference.enabled.value, true);
  preference.toggle();
  assert.equal(preference.enabled.value, false);
  preference.toggle();
  assert.equal(preference.enabled.value, true);
});

test("injects the same preference into controls and the renderer", () => {
  const app = createSSRApp({ render: () => null });
  const preference = createCursorPreference();
  app.provide(cursorPreferenceKey, preference);
  assert.equal(app.runWithContext(useCursorPreference), preference);
});

test("reports a missing preference provider clearly", (t) => {
  t.mock.method(console, "warn", () => {});
  const app = createSSRApp({ render: () => null });
  assert.throws(
    () => app.runWithContext(useCursorPreference),
    /Mouse effect preference is unavailable/,
  );
});
