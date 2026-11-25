<script setup>
import { computed } from "vue";
import { withBase } from "vuepress/client";
import { getGuideHref } from "./features.js";

const props = defineProps({
  feature: { type: Object, required: true },
  lang: { type: String, default: "zh" },
  caption: { type: String, default: "" },
  icon: { type: String, default: "" },
  active: { type: Boolean, default: false },
});
defineEmits(["preview"]);
const label = computed(() => props.feature.label[props.lang === "en" ? 1 : 0]);
</script>

<template>
  <a
    class="guide-action"
    :class="{ 'is-active': active, 'has-caption': caption }"
    :href="withBase(getGuideHref(feature, lang))"
    :aria-label="label"
    :title="label"
    :data-feature="feature.id"
    @mouseenter="$emit('preview', feature, $event)"
    @focus="$emit('preview', feature, $event)"
  >
    <img
      :src="withBase(`/img/ui-guide/${icon || feature.icon}.svg`)"
      width="22"
      height="22"
      alt=""
      no-view
    />
    <span v-if="caption">{{ caption }}</span>
  </a>
</template>

<style scoped>
.guide-action {
  display: inline-flex;
  position: relative;
  align-items: center;
  justify-content: center;
  flex: 0 0 auto;
  box-sizing: border-box;
  min-width: 36px;
  min-height: 38px;
  padding: 7px;
  border: 1px solid transparent;
  border-radius: 5px;
  color: #dfe1e5;
  text-decoration: none !important;
  transition:
    background 120ms,
    border-color 120ms;
}
.guide-action img {
  flex-shrink: 0;
  margin: 0;
  object-fit: contain;
}
.guide-action:hover,
.guide-action.is-active {
  border-color: #637594;
  background: #3a4354;
  color: #fff;
}
.guide-action:focus-visible {
  outline: 2px solid #88aaff;
  outline-offset: 2px;
}
.guide-action.has-caption {
  justify-content: flex-start;
  gap: 9px;
  padding-inline: 12px;
  font-size: 0.875rem;
}
@media (prefers-reduced-motion: reduce) {
  .guide-action {
    transition: none;
  }
}
</style>
