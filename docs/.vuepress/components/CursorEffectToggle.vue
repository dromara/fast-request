<script setup lang="ts">
import { computed } from "vue";
import { usePageLang } from "vuepress/client";
import { useCursorPreference } from "../composables/useCursorPreference.js";

const { enabled, toggle } = useCursorPreference();
const lang = usePageLang();
const english = computed(() => lang.value.startsWith("en"));
const label = computed(() => (english.value ? "Mouse effects" : "鼠标特效"));
const title = computed(() =>
  english.value
    ? `${enabled.value ? "Disable" : "Enable"} mouse effects`
    : `${enabled.value ? "关闭" : "开启"}鼠标特效`,
);
</script>

<template>
  <button
    type="button"
    class="cursor-effect-toggle"
    :class="{ 'is-enabled': enabled }"
    :aria-label="label"
    :aria-pressed="enabled"
    :title="title"
    @click.stop="toggle"
    @keydown.enter.stop
    @keydown.space.stop
  >
    <svg
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      stroke-width="1.7"
      stroke-linecap="round"
      stroke-linejoin="round"
      aria-hidden="true"
      focusable="false"
    >
      <path d="m4 3 6 16 3-6 6-3L4 3Z" />
      <path v-if="enabled" d="M19 2v4m-2-2h4M4 16v4m-2-2h4" />
      <path v-else d="m3 21 18-18" />
    </svg>
  </button>
</template>

<style scoped>
.cursor-effect-toggle {
  display: inline-flex;
  flex-shrink: 0;
  align-items: center;
  justify-content: center;
  width: 2rem;
  height: 2rem;
  margin-inline-end: 0.625rem;
  padding: 0;
  border: 1px solid transparent;
  border-radius: 50%;
  background: transparent;
  color: var(--vp-c-text-mute, #9ca3af);
  cursor: pointer;
  transition:
    color 150ms ease,
    background 150ms ease;
}

.cursor-effect-toggle svg {
  width: 1.25rem;
  height: 1.25rem;
}

.cursor-effect-toggle.is-enabled {
  border-color: rgb(33 215 137 / 25%);
  background: rgb(33 215 137 / 10%);
  color: #21d789;
}

.cursor-effect-toggle:hover {
  background: rgb(33 215 137 / 18%);
}

.cursor-effect-toggle:focus-visible {
  outline: 2px solid #21d789;
  outline-offset: 3px;
}

@media (prefers-reduced-motion: reduce) {
  .cursor-effect-toggle {
    transition: none;
  }
}
</style>
