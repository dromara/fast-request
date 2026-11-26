<script setup>
import { useId } from "vue";

const props = defineProps({
  items: { type: Array, required: true },
  modelValue: { type: String, required: true },
  label: { type: String, required: true },
  secondary: { type: Boolean, default: false },
  windowTabs: { type: Boolean, default: false },
});
const emit = defineEmits(["update:modelValue", "preview"]);
const id = `guide-tabs-${useId()}`;

function onKeydown(event, index) {
  let next;
  switch (event.key) {
    case "ArrowRight":
      next = (index + 1) % props.items.length;
      break;
    case "ArrowLeft":
      next = (index + props.items.length - 1) % props.items.length;
      break;
    case "Home":
      next = 0;
      break;
    case "End":
      next = props.items.length - 1;
      break;
    default:
      return;
  }
  event.preventDefault();
  emit("update:modelValue", props.items[next].id);
  event.currentTarget.parentElement
    .querySelectorAll('[role="tab"]')
    [next].focus();
}
</script>

<template>
  <div
    class="guide-tabs"
    :class="{ 'is-secondary': secondary, 'is-window': windowTabs }"
  >
    <div class="guide-tabs-header">
      <div class="guide-tab-list" role="tablist" :aria-label="label">
        <button
          v-for="(item, index) in items"
          :id="`${id}-${item.id}`"
          :key="item.id"
          type="button"
          role="tab"
          :class="[item.class, { 'is-current': modelValue === item.id }]"
          :aria-selected="modelValue === item.id"
          :aria-controls="`${id}-panel`"
          :tabindex="modelValue === item.id ? 0 : -1"
          :data-window-tab="windowTabs ? item.id : undefined"
          @click="emit('update:modelValue', item.id)"
          @keydown="onKeydown($event, index)"
          @mouseenter="emit('preview', item, $event)"
          @focus="emit('preview', item, $event)"
        >
          <slot name="tab" :item="item">{{ item.label }}</slot>
        </button>
      </div>
      <slot name="actions" />
    </div>
    <div
      :id="`${id}-panel`"
      role="tabpanel"
      :aria-labelledby="`${id}-${modelValue}`"
      tabindex="0"
    >
      <slot />
    </div>
  </div>
</template>

<style scoped>
.guide-tabs-header {
  display: flex;
  flex-wrap: wrap;
  padding: 0 7px;
  border-bottom: 1px solid #1e1f22;
}
.guide-tab-list {
  display: flex;
  flex-wrap: wrap;
  gap: 1px;
}
.guide-tab-list button {
  min-height: 49px;
  padding: 10px 12px;
  border: 0;
  border-bottom: 3px solid transparent;
  border-radius: 0;
  background: transparent;
  color: #dfe1e5;
  font: inherit;
  font-size: 0.875rem;
  cursor: pointer;
}
.guide-tab-list button:hover {
  background: #3a4354;
}
.guide-tab-list button.is-current {
  border-bottom-color: #548af7;
}
.guide-tab-list button:focus-visible,
[role="tabpanel"]:focus-visible {
  outline: 2px solid #88aaff;
  outline-offset: -3px;
}
.guide-tab-list .script {
  color: #e5a778;
}
.guide-tab-list .response {
  color: #57cba2;
}
.guide-tab-list .console {
  color: #ed8e99;
}
.is-window > .guide-tabs-header {
  align-items: center;
  justify-content: space-between;
  padding: 5px 5px 0;
  border-radius: 10px 10px 0 0;
  background: #25262a;
}
.is-window > .guide-tabs-header > .guide-tab-list button {
  display: inline-flex;
  align-items: center;
  gap: 8px;
  min-height: 48px;
  padding-inline: 10px;
  font-weight: 550;
}
.is-window > .guide-tabs-header > .guide-tab-list button.is-current {
  background: #32353b;
}
.is-window > [role="tabpanel"] {
  min-height: 420px;
}
.is-secondary > .guide-tabs-header {
  padding: 5px 7px;
}
.is-secondary > .guide-tabs-header > .guide-tab-list {
  gap: 4px;
}
.is-secondary > .guide-tabs-header > .guide-tab-list button {
  min-height: 36px;
  padding: 6px 10px;
  border: 1px solid transparent;
  border-radius: 5px;
}
.is-secondary > .guide-tabs-header > .guide-tab-list button.is-current {
  border-color: #375995;
  background: #25324d;
}
@media (max-width: 640px) {
  .guide-tab-list button {
    padding-inline: 9px;
  }
}
</style>
