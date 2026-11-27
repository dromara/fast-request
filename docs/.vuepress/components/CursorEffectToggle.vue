<script setup lang="ts">
import { computed, onMounted, onUnmounted, ref } from "vue";
import { usePageLang } from "vuepress/client";
import { useCursorPreference } from "../composables/useCursorPreference.js";

const { effect, setEffect } = useCursorPreference();
const lang = usePageLang();
const english = computed(() => lang.value.startsWith("en"));
const open = ref(false);
const root = ref<HTMLElement | null>(null);
const trigger = ref<HTMLButtonElement | null>(null);
const options = computed(() => [
  {
    value: "gyroscope",
    label: english.value ? "Gyroscope cursor" : "旋转光标",
  },
  { value: "glow", label: english.value ? "Smoke trail" : "烟雾轨迹" },
  { value: "off", label: english.value ? "Effects off" : "关闭特效" },
]);

function select(value: string) {
  setEffect(value);
  open.value = false;
  trigger.value?.focus();
}

function outside(event: PointerEvent) {
  if (event.target instanceof Node && !root.value?.contains(event.target))
    open.value = false;
}

function focusout(event: FocusEvent) {
  if (
    event.relatedTarget instanceof Node &&
    !root.value?.contains(event.relatedTarget)
  )
    open.value = false;
}

function keydown(event: KeyboardEvent) {
  if (event.key === "Escape" && open.value) {
    event.preventDefault();
    open.value = false;
    trigger.value?.focus();
  }
}

onMounted(() => {
  document.addEventListener("pointerdown", outside);
  document.addEventListener("keydown", keydown);
});
onUnmounted(() => {
  document.removeEventListener("pointerdown", outside);
  document.removeEventListener("keydown", keydown);
});
</script>

<template>
  <div ref="root" class="cursor-menu" @focusout="focusout">
    <button
      ref="trigger"
      type="button"
      class="cursor-menu__trigger"
      :class="{ 'is-active': open }"
      :aria-label="english ? 'Mouse effects' : '鼠标特效'"
      :title="english ? 'Mouse effects' : '鼠标特效'"
      :aria-expanded="open"
      aria-controls="cursor-effect-options"
      @click.stop="open = !open"
    >
      <svg
        viewBox="0 0 24 24"
        fill="none"
        stroke="currentColor"
        stroke-width="2.4"
        stroke-linecap="round"
        stroke-linejoin="round"
        aria-hidden="true"
      >
        <path d="M5 3 11 20l3-6 6-3L5 3Z" />
        <path v-if="effect === 'off'" d="m3 21 18-18" />
      </svg>
    </button>
    <div
      v-if="open"
      id="cursor-effect-options"
      class="cursor-menu__panel"
      :aria-label="english ? 'Mouse effects' : '鼠标特效'"
    >
      <button
        v-for="option in options"
        :key="option.value"
        type="button"
        class="cursor-menu__option"
        :class="{ 'is-selected': effect === option.value }"
        :aria-pressed="effect === option.value"
        @click="select(option.value)"
      >
        <span>{{ option.label }}</span>
        <svg
          v-if="effect === option.value"
          viewBox="0 0 16 16"
          fill="none"
          stroke="currentColor"
          stroke-width="2"
          aria-hidden="true"
        >
          <path d="m3 8 3 3 7-7" />
        </svg>
      </button>
    </div>
  </div>
</template>

<style scoped>
.cursor-menu {
  position: relative;
  flex-shrink: 0;
  margin-inline-end: 0.625rem;
}
.cursor-menu__trigger {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 2rem;
  height: 2rem;
  padding: 0;
  border: 1px solid rgb(8 126 87 / 25%);
  border-radius: 50%;
  background: #fff;
  color: #087e57;
  cursor: pointer;
  transition:
    color 150ms ease,
    background 150ms ease,
    border-color 150ms ease;
}
.cursor-menu__trigger:hover,
.cursor-menu__trigger.is-active {
  border-color: currentColor;
  background: #e7f8f0;
}
.cursor-menu__trigger svg {
  width: 1.25rem;
  height: 1.25rem;
}
.cursor-menu__trigger:focus-visible,
.cursor-menu__option:focus-visible {
  outline: 2px solid #21d789;
  outline-offset: 2px;
}
.cursor-menu__panel {
  position: absolute;
  top: calc(100% + 0.5rem);
  right: 0;
  min-width: 9rem;
  padding: 0.35rem;
  border: 1px solid var(--vp-c-border, #d1d5db);
  border-radius: 0.75rem;
  background: var(--vp-c-bg, #fff);
  box-shadow: 0 8px 24px rgb(0 0 0 / 18%);
}
.cursor-menu__option {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 1rem;
  width: 100%;
  padding: 0.5rem 0.65rem;
  border: 0;
  border-radius: 0.45rem;
  background: transparent;
  color: var(--vp-c-text, #333);
  font: inherit;
  font-size: 0.875rem;
  text-align: left;
  white-space: nowrap;
  cursor: pointer;
}
.cursor-menu__option:hover,
.cursor-menu__option.is-selected {
  color: #21d789;
  background: rgb(33 215 137 / 12%);
}
.cursor-menu__option svg {
  width: 1rem;
  height: 1rem;
  flex-shrink: 0;
}
@media (prefers-reduced-motion: reduce) {
  .cursor-menu__trigger {
    transition: none;
  }
}
</style>
