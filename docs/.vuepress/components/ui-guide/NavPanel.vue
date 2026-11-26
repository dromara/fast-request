<script setup>
import { ref } from "vue";
import GuidePanelToolbar from "./GuidePanelToolbar.vue";
import { windowPanelActions } from "./window-panels.js";

defineProps({ lang: { type: String, default: "zh" } });
const emit = defineEmits(["preview"]);
const preview = (...args) => emit("preview", ...args);
const query = ref("");
</script>

<template>
  <div class="guide-window-panel" data-window-panel="nav">
    <input
      v-model="query"
      type="search"
      class="guide-window-search"
      :aria-label="lang === 'en' ? 'Search API navigation' : '搜索 API 导航'"
      :placeholder="
        lang === 'en'
          ? 'Search URL, method description or class name'
          : '输入 URL、方法描述或类名来搜索'
      "
    />
    <GuidePanelToolbar
      :actions="windowPanelActions.nav"
      :lang="lang"
      @preview="preview"
    />
    <p v-if="query.trim()" class="guide-window-empty">
      {{ lang === "en" ? "No matching APIs." : "未找到匹配的 API。" }}
    </p>
  </div>
</template>
