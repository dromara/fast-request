<script setup>
import { computed, ref } from "vue";
import { withBase } from "vuepress/client";
import GuidePanelToolbar from "./GuidePanelToolbar.vue";
import { filterPanelTree, windowPanelActions } from "./window-panels.js";

defineProps({ lang: { type: String, default: "zh" } });
const emit = defineEmits(["preview"]);
const preview = (...args) => emit("preview", ...args);
const query = ref("");
const matches = computed(() => filterPanelTree(query.value, ["Saved Methods"]));
</script>

<template>
  <div class="guide-window-panel" data-window-panel="methods">
    <input
      v-model="query"
      type="search"
      class="guide-window-search"
      :aria-label="lang === 'en' ? 'Search saved methods' : '搜索已保存方法'"
      :placeholder="
        lang === 'en'
          ? 'Type method name or class name to search'
          : '键入方法名或类名来搜索'
      "
    />
    <GuidePanelToolbar
      :actions="windowPanelActions.methods"
      :lang="lang"
      @preview="preview"
    />
    <div class="guide-tree-columns is-methods"><span>Method Name</span></div>
    <div v-if="matches" class="guide-methods-root">
      <img
        :src="withBase('/img/ui-guide/folder.svg')"
        width="18"
        height="18"
        alt=""
        no-view
      />
      Saved Methods
    </div>
    <p v-else class="guide-window-empty">
      {{ lang === "en" ? "No matching methods." : "未找到匹配的方法。" }}
    </p>
  </div>
</template>
