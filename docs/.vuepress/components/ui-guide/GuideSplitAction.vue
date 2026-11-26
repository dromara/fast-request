<script setup>
import { computed } from "vue";
import GuideAction from "./GuideAction.vue";

const props = defineProps({
  features: { type: Array, required: true },
  lang: { type: String, default: "zh" },
  active: { type: String, default: "" },
});
const emit = defineEmits(["preview"]);
const preview = (...args) => emit("preview", ...args);
const index = computed(() => (props.lang === "en" ? 1 : 0));
</script>

<template>
  <div class="guide-split-action">
    <GuideAction
      :feature="features[0]"
      :lang="lang"
      :active="active === features[0].id"
      @preview="preview"
    />
    <details
      v-if="features.length > 1"
      class="guide-dropdown"
      name="guide-toolbar-menu"
      @keydown.esc.prevent="$event.currentTarget.open = false"
    >
      <summary
        :aria-label="`${features[0].label[index]} · ${lang === 'en' ? 'More actions' : '更多操作'}`"
      >
        <span class="guide-chevron" />
      </summary>
      <div class="guide-dropdown-panel">
        <GuideAction
          v-for="feature in features"
          :key="feature.id"
          :feature="feature"
          :lang="lang"
          :caption="feature.label[index]"
          @preview="preview"
        />
      </div>
    </details>
  </div>
</template>

<style scoped>
.guide-split-action {
  display: inline-flex;
  align-items: center;
  position: relative;
}
.guide-dropdown summary {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 16px;
  height: 32px;
  border-radius: 4px;
  list-style: none;
  cursor: pointer;
}
.guide-dropdown summary::-webkit-details-marker {
  display: none;
}
.guide-dropdown summary:hover,
.guide-dropdown[open] summary {
  background: #414652;
}
.guide-dropdown summary:focus-visible {
  outline: 2px solid #88aaff;
  outline-offset: 1px;
}
.guide-chevron {
  display: inline-block;
  width: 7px;
  height: 7px;
  border-right: 1.5px solid #b3b9c6;
  border-bottom: 1.5px solid #b3b9c6;
  transform: rotate(45deg) translateY(-2px);
}
.guide-dropdown-panel {
  display: grid;
  position: absolute;
  top: calc(100% + 5px);
  right: 0;
  z-index: 10;
  width: max-content;
  min-width: 190px;
  max-width: 280px;
  padding: 5px;
  border: 1px solid #5a6272;
  border-radius: 7px;
  background: #30343b;
  box-shadow: 0 10px 32px #0006;
}
.guide-dropdown-panel :deep(.guide-action) {
  width: 100%;
  white-space: normal;
}
@media (max-width: 640px) {
  .guide-split-action {
    position: static;
  }
  .guide-dropdown-panel {
    right: auto;
    left: 0;
    max-width: calc(100vw - 80px);
  }
}
</style>
