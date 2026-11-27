import { inject, readonly, ref } from "vue";

/** @type {import("vue").InjectionKey<ReturnType<typeof createCursorPreference>>} */
export const cursorPreferenceKey = Symbol("cursor-preference");
const storageKey = "fast-request:mouse-effects";

export function createCursorPreference() {
  const effect = ref("gyroscope");

  function restore() {
    try {
      const saved = window.localStorage.getItem(storageKey);
      effect.value =
        saved === "false" || saved === "off"
          ? "off"
          : saved === "glow"
            ? "glow"
            : "gyroscope";
    } catch {
      // Storage can be unavailable in private or restricted browsing contexts.
    }
  }

  function setEffect(value) {
    if (!["gyroscope", "glow", "off"].includes(value)) return;
    effect.value = value;
    try {
      window.localStorage.setItem(storageKey, value);
    } catch {
      // The switch still works for this visit when preferences cannot be saved.
    }
  }

  return { effect: readonly(effect), restore, setEffect };
}

export function useCursorPreference() {
  const preference = inject(cursorPreferenceKey);
  if (!preference) throw new Error("Mouse effect preference is unavailable");
  return preference;
}
