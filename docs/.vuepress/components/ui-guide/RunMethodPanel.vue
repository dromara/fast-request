<script setup>
import { computed, ref } from "vue";
import { withBase } from "vuepress/client";
import GuideAction from "./GuideAction.vue";
import GuideTabs from "./GuideTabs.vue";
import GuidePanelToolbar from "./GuidePanelToolbar.vue";
import { guideFeatures } from "./features.js";
import { runMethodScriptActions, windowPanelActions } from "./window-panels.js";

const props = defineProps({ lang: { type: String, default: "zh" } });
const emit = defineEmits(["preview"]);
const preview = (...args) => emit("preview", ...args);
const byId = Object.fromEntries(guideFeatures.map((item) => [item.id, item]));
const active = ref("parameters");
const script = ref("pre");
const tabs = computed(() => [
  { id: "parameters", label: props.lang === "en" ? "Parameters" : "参数" },
  {
    id: "response",
    label: props.lang === "en" ? "Response" : "响应",
    class: "response",
  },
  { id: "script", label: "Script", class: "script" },
  { id: "console", label: "Console", class: "console" },
]);
const scriptTabs = computed(() => [
  {
    id: "pre",
    label: props.lang === "en" ? "Pre-invocation script" : "前置脚本",
  },
  {
    id: "post",
    label: props.lang === "en" ? "Post-invocation script" : "后置脚本",
  },
]);
</script>

<template>
  <div class="guide-window-panel" data-window-panel="run">
    <GuidePanelToolbar
      :actions="windowPanelActions.run"
      :lang="lang"
      @preview="preview"
    />
    <div class="guide-method-environment">
      <GuideAction
        :feature="byId.environment"
        :lang="lang"
        caption="local"
        @preview="preview"
      />
      <GuideAction
        :feature="byId.environment"
        :lang="lang"
        icon="refresh"
        @preview="preview"
      />
    </div>
    <div class="guide-method-signature">
      <img
        :src="withBase('/img/ui-guide/method.svg')"
        width="20"
        height="20"
        alt=""
        no-view
      />
      {{ lang === "en" ? "No method selected" : "未选择方法" }}
    </div>
    <GuideTabs
      v-model="active"
      :items="tabs"
      :label="lang === 'en' ? 'Method panels' : '方法面板'"
      secondary
    >
      <GuideTabs
        v-if="active === 'script'"
        v-model="script"
        :items="scriptTabs"
        :label="lang === 'en' ? 'Method script stages' : '方法脚本阶段'"
        secondary
      >
        <div class="guide-method-script">
          <div class="guide-method-empty" />
          <GuidePanelToolbar
            :actions="runMethodScriptActions"
            :lang="lang"
            @preview="preview"
          />
        </div>
      </GuideTabs>
      <div v-else-if="active === 'response'" class="guide-method-editor">
        <span>1</span>
      </div>
      <div v-else class="guide-method-empty" />
    </GuideTabs>
  </div>
</template>
