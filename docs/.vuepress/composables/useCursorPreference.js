import { inject, readonly, ref } from "vue";

/** @type {import("vue").InjectionKey<ReturnType<typeof createCursorPreference>>} */
export const cursorPreferenceKey = Symbol("cursor-preference");
const storageKey = "fast-request:mouse-effects";

export function createCursorPreference() {
  const enabled = ref(true);

  function restore() {
    try {
      enabled.value = window.localStorage.getItem(storageKey) !== "false";
    } catch {
      // Storage can be unavailable in private or restricted browsing contexts.
    }
  }

  function toggle() {
    enabled.value = !enabled.value;
    try {
      window.localStorage.setItem(storageKey, String(enabled.value));
    } catch {
      // The switch still works for this visit when preferences cannot be saved.
    }
  }

  return { enabled: readonly(enabled), restore, toggle };
}

export function useCursorPreference() {
  const preference = inject(cursorPreferenceKey);
  if (!preference) throw new Error("Mouse effect preference is unavailable");
  return preference;
}
