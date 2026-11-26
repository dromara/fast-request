<script setup>
import { computed } from "vue";
import { withBase } from "vuepress/client";
import GuideAction from "./GuideAction.vue";
import GuideSplitAction from "./GuideSplitAction.vue";
import { guideFeatures, guideLayout } from "./features.js";

const props = defineProps({
  lang: { type: String, default: "zh" },
  pathOnly: { type: Boolean, default: false },
  multipart: { type: Boolean, default: false },
});
const emit = defineEmits(["preview"]);
const byId = Object.fromEntries(guideFeatures.map((item) => [item.id, item]));
const preview = (...args) => emit("preview", ...args);
const extraIcons = computed(() =>
  props.pathOnly
    ? ["hide"]
    : [
        "copy",
        ...(props.multipart ? [] : ["copyParams", "bulkEdit"]),
        "clear",
        "ignored",
        "hide",
        "sortByLetterHide",
        "filterEnabled",
      ],
);
</script>

<template>
  <div class="guide-parameter-panel">
    <div class="guide-panel-tools">
      <span class="guide-static-icon" aria-hidden="true">＋</span
      ><span class="guide-static-icon" aria-hidden="true">−</span>
      <GuideSplitAction
        :features="guideLayout.parameterAiActions.map((id) => byId[id])"
        :lang="lang"
        @preview="preview"
      />
      <GuideAction
        v-for="id in ['environment', 'addEnvironment']"
        :key="id"
        :feature="byId[id]"
        :lang="lang"
        @preview="preview"
      />
      <span
        v-for="icon in extraIcons"
        :key="icon"
        class="guide-static-icon"
        aria-hidden="true"
        ><img
          :src="withBase(`/img/ui-guide/${icon}.svg`)"
          width="18"
          height="18"
          alt=""
          no-view
      /></span>
    </div>
    <div class="guide-panel-table-scroll">
      <table class="guide-panel-table">
        <thead>
          <tr>
            <th class="guide-check-column" aria-label="Enabled" />
            <th>Type</th>
            <th>Key</th>
            <th>Value</th>
            <th>Description</th>
          </tr>
        </thead>
      </table>
    </div>
  </div>
</template>
