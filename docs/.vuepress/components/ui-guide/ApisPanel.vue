<script setup>
import { computed, ref } from "vue";
import { withBase } from "vuepress/client";
import GuidePanelToolbar from "./GuidePanelToolbar.vue";
import {
  filterPanelTree,
  windowPanelActions,
  windowPanelToggles,
} from "./window-panels.js";

defineProps({ lang: { type: String, default: "zh" } });
const emit = defineEmits(["preview"]);
const preview = (...args) => emit("preview", ...args);
const query = ref("");
const expanded = ref(true);
const pressed = ref(["apisSwitchTab", "apisJumpMethod"]);
const matches = computed(() =>
  filterPanelTree(query.value, ["Saved Apis", "Default Group"]),
);
function onAction(id) {
  if (id === "expand") expanded.value = true;
  else if (id === "collapse") expanded.value = false;
  else if (windowPanelToggles.includes(id)) {
    pressed.value = pressed.value.includes(id)
      ? pressed.value.filter((item) => item !== id)
      : [...pressed.value, id];
  }
}
</script>

<template>
  <div class="guide-window-panel" data-window-panel="apis">
    <input
      v-model="query"
      type="search"
      class="guide-window-search"
      :aria-label="lang === 'en' ? 'Search saved APIs' : '搜索已保存 API'"
      placeholder="Type name or url to search"
    />
    <GuidePanelToolbar
      :actions="windowPanelActions.apis"
      :lang="lang"
      :toggles="windowPanelToggles"
      :pressed="pressed"
      @preview="preview"
      @action="onAction"
    />
    <div class="guide-tree-columns"><span>Api Name</span><span>Url</span></div>
    <details v-if="matches" class="guide-saved-tree" :open="expanded">
      <summary @click.prevent="expanded = !expanded">
        <img
          :src="withBase('/img/ui-guide/module.svg')"
          width="18"
          height="18"
          alt=""
          no-view
        />
        Saved Apis
      </summary>
      <div class="guide-tree-child">
        <img
          :src="withBase('/img/ui-guide/module.svg')"
          width="18"
          height="18"
          alt=""
          no-view
        />
        Default Group
      </div>
    </details>
    <p v-else class="guide-window-empty">
      {{ lang === "en" ? "No matching APIs." : "未找到匹配的 API。" }}
    </p>
  </div>
</template>
