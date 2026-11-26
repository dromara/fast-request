<script setup>
import { withBase } from "vuepress/client";
import GuideAction from "./GuideAction.vue";
import GuideSplitAction from "./GuideSplitAction.vue";

defineProps({
  actions: { type: Array, required: true },
  lang: { type: String, default: "zh" },
  toggles: { type: Array, default: () => [] },
  pressed: { type: Array, default: () => [] },
});
const emit = defineEmits(["preview", "action"]);
const preview = (...args) => emit("preview", ...args);
function activate(action) {
  preview(action, { type: "focus" });
  emit("action", action.id);
}
</script>

<template>
  <div class="guide-panel-toolbar" role="toolbar">
    <template
      v-for="action in actions"
      :key="Array.isArray(action) ? action[0].id : action.id"
    >
      <GuideSplitAction
        v-if="Array.isArray(action)"
        :features="action"
        :lang="lang"
        @preview="preview"
      />
      <GuideAction
        v-else-if="action.doc && !action.local"
        :feature="action"
        :lang="lang"
        :caption="action.caption ? action.label[lang === 'en' ? 1 : 0] : ''"
        @preview="preview"
      />
      <button
        v-else
        type="button"
        class="guide-panel-action"
        :class="{ 'is-active': pressed.includes(action.id) }"
        :aria-label="action.label[lang === 'en' ? 1 : 0]"
        :title="action.label[lang === 'en' ? 1 : 0]"
        :aria-pressed="
          toggles.includes(action.id) ? pressed.includes(action.id) : undefined
        "
        :data-panel-action="action.id"
        @mouseenter="preview(action, $event)"
        @focus="preview(action, $event)"
        @click="activate(action)"
      >
        <img
          :src="withBase(`/img/ui-guide/${action.icon}.svg`)"
          width="18"
          height="18"
          alt=""
          no-view
        />
        <span v-if="action.caption">{{
          action.label[lang === "en" ? 1 : 0]
        }}</span>
      </button>
    </template>
    <slot />
  </div>
</template>
