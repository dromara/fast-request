<script setup>
import { computed, ref } from "vue";
import { withBase } from "vuepress/client";
import GuideAction from "./GuideAction.vue";
import GuideTabs from "./GuideTabs.vue";
import ParameterPanel from "./ParameterPanel.vue";
import { guideFeatures, guideLayout } from "./features.js";
import { getScriptLinkHref, scriptLinkGroups } from "./script-links.js";

const props = defineProps({ lang: { type: String, default: "zh" } });
const emit = defineEmits(["preview"]);
const index = computed(() => (props.lang === "en" ? 1 : 0));
const text = (zh, en) => (index.value ? en : zh);
const byId = Object.fromEntries(guideFeatures.map((item) => [item.id, item]));
const preview = (...args) => emit("preview", ...args);
const active = ref("header");
const body = ref("raw");
const script = ref("pre");
const response = ref("tree");
const tabs = [
  { id: "header", label: "Header" },
  { id: "path", label: "Path Param" },
  { id: "url", label: "URL Param" },
  { id: "body", label: "Body" },
  { id: "script", label: "Script", class: "script" },
  { id: "response", label: "> Response", class: "response" },
];
const bodyTabs = [
  { id: "raw", label: "Raw" },
  { id: "urlencoded", label: "Form-Urlencoded" },
  { id: "formdata", label: "Form-Data" },
  { id: "binary", label: "Binary" },
];
const scriptTabs = computed(() => [
  { id: "pre", label: text("前置脚本", "Pre-request script") },
  { id: "post", label: text("后置脚本", "Post-request script") },
]);
const responseTabs = [
  { id: "tree", label: "Tree" },
  { id: "pretty", label: "Pretty" },
  { id: "raw", label: "Raw" },
  { id: "image", label: "Image" },
  { id: "headers", label: "Headers" },
];
const scriptHref = (link) => withBase(getScriptLinkHref(link, props.lang));
</script>

<template>
  <div class="guide-request-panels">
    <GuideTabs
      v-model="active"
      :items="tabs"
      :label="text('请求面板', 'Request panels')"
    >
      <template #actions>
        <a
          class="guide-console-link"
          :href="scriptHref({ anchors: ['console', 'console'] })"
          >Console</a
        >
      </template>
      <div class="guide-panel-content">
        <template v-if="active === 'header'">
          <div class="guide-panel-tools">
            <GuideAction
              :feature="byId.addHeader"
              :lang="lang"
              @preview="preview"
            />
            <span class="guide-static-icon" aria-hidden="true">−</span>
            <GuideAction
              v-for="id in ['environment', 'addEnvironment']"
              :key="id"
              :feature="byId[id]"
              :lang="lang"
              @preview="preview"
            />
            <GuideAction
              v-for="id in guideLayout.parameterActions"
              :key="id"
              :feature="byId[id]"
              :lang="lang"
              @preview="preview"
            />
          </div>
          <div class="guide-panel-table-scroll">
            <table class="guide-panel-table">
              <thead>
                <tr>
                  <th aria-label="Enabled">✓</th>
                  <th>Header Name</th>
                  <th>Header Value</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>✓</td>
                  <td>Content-Type</td>
                  <td>application/json</td>
                </tr>
                <tr>
                  <td>✓</td>
                  <td>Accept</td>
                  <td>application/json</td>
                </tr>
              </tbody>
            </table>
          </div>
        </template>

        <GuideTabs
          v-else-if="active === 'body'"
          v-model="body"
          :items="bodyTabs"
          :label="text('请求体类型', 'Request body type')"
          secondary
        >
          <div
            v-if="body === 'raw'"
            class="guide-code-editor"
            :aria-label="text('Raw 请求体编辑区', 'Raw request body editor')"
          >
            <span class="guide-line-number">1</span>
            <div class="guide-editor-tools">
              <GuideAction :feature="byId.ai" :lang="lang" @preview="preview" />
              <span
                v-for="icon in [
                  'redo',
                  'copy',
                  'jsonPath',
                  'filter',
                  'switchCase',
                ]"
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
          </div>
          <div v-else-if="body === 'binary'" class="guide-binary-panel">
            <span>File</span
            ><span class="guide-file-placeholder">{{
              text("未选择文件", "No file selected")
            }}</span>
          </div>
          <ParameterPanel
            v-else
            :lang="lang"
            :multipart="body === 'formdata'"
            @preview="preview"
          />
        </GuideTabs>

        <GuideTabs
          v-else-if="active === 'script'"
          v-model="script"
          :items="scriptTabs"
          :label="text('脚本类型', 'Script type')"
          secondary
        >
          <div class="guide-script-layout">
            <div
              class="guide-code-editor"
              :aria-label="scriptTabs.find((item) => item.id === script).label"
            >
              <span class="guide-line-number">1</span>
              <div class="guide-editor-tools">
                <GuideAction
                  :feature="byId.script"
                  :lang="lang"
                  @preview="preview"
                />
              </div>
            </div>
            <div class="guide-script-help">
              <p>
                {{
                  script === "pre"
                    ? text(
                        "前置脚本使用 Groovy 编写，在发送请求前运行。",
                        "Pre-request scripts use Groovy and run before the request is sent.",
                      )
                    : text(
                        "后置脚本使用 Groovy 编写，在收到响应后运行。",
                        "Post-request scripts use Groovy and run after the response is received.",
                      )
                }}
              </p>
              <a :href="scriptHref({})">{{
                text("教程文档", "Script documentation")
              }}</a>
              <section v-for="group in scriptLinkGroups" :key="group.label[0]">
                <h3>{{ group.label[index] }}</h3>
                <ol>
                  <li v-for="link in group.links" :key="link.label[0]">
                    <a :href="scriptHref(link)">{{ link.label[index] }}</a>
                  </li>
                </ol>
              </section>
            </div>
          </div>
        </GuideTabs>

        <GuideTabs
          v-else-if="active === 'response'"
          v-model="response"
          :items="responseTabs"
          :label="text('响应视图', 'Response view')"
          secondary
        >
          <template v-if="['tree', 'headers'].includes(response)">
            <div
              v-if="response === 'tree'"
              class="guide-panel-tools"
              aria-hidden="true"
            >
              <span class="guide-static-icon">＋</span
              ><span class="guide-static-icon">＋</span
              ><span class="guide-static-icon"
                ><img
                  :src="withBase('/img/ui-guide/addEnvironmentVariable.svg')"
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
                    <th>Name</th>
                    <th>Value</th>
                  </tr>
                </thead>
                <tbody v-if="response === 'tree'">
                  <tr>
                    <td>Root</td>
                    <td />
                  </tr>
                </tbody>
              </table>
            </div>
          </template>
          <div
            v-else-if="['pretty', 'raw'].includes(response)"
            class="guide-code-editor"
            :aria-label="
              response === 'pretty' ? 'Pretty response' : 'Raw response'
            "
          >
            <span class="guide-line-number">1</span>
          </div>
          <div v-else class="guide-panel-empty" />
        </GuideTabs>

        <ParameterPanel
          v-else-if="['path', 'url'].includes(active)"
          :lang="lang"
          :path-only="active === 'path'"
          @preview="preview"
        />
      </div>
    </GuideTabs>
  </div>
</template>

<style lang="scss">
.guide-request-panels {
  color: #dfe1e5;
  background: #26282c;
  .guide-console-link {
    display: inline-flex;
    align-items: center;
    min-height: 49px;
    padding: 10px 12px;
    border-bottom: 3px solid transparent;
    color: #ed8e99;
    font-size: 0.875rem;
  }
  .guide-console-link:hover {
    background: #3a4354;
  }
  .guide-panel-content {
    min-height: 240px;
  }
  .guide-panel-tools {
    display: flex;
    position: relative;
    flex-wrap: wrap;
    align-items: center;
    gap: 3px;
    padding: 5px 10px;
    border-bottom: 1px solid #1e1f22;
  }
  .guide-panel-tools .guide-action,
  .guide-editor-tools .guide-action {
    min-width: 30px;
    min-height: 32px;
    padding: 5px;
  }
  .guide-panel-tools .guide-action img,
  .guide-editor-tools .guide-action img {
    width: 18px;
    height: 18px;
  }
  .guide-panel-tools .guide-dropdown-panel {
    right: auto;
    left: 0;
  }
  .guide-static-icon {
    display: inline-flex;
    align-items: center;
    justify-content: center;
    width: 30px;
    height: 32px;
    color: #9b9fa7;
    font-size: 1.4rem;
  }
  .guide-static-icon img {
    margin: 0;
  }
  .guide-panel-table-scroll {
    overflow-x: auto;
  }
  .guide-panel-table {
    display: table;
    width: 100%;
    margin: 0;
    border-collapse: collapse;
    background: #26282c;
    color: #bec4d0;
    font-size: 0.875rem;
    white-space: nowrap;
  }
  .guide-panel-table th,
  .guide-panel-table td {
    padding: 10px 13px;
    border: 0;
    border-right: 1px solid #3c3e44;
    border-bottom: 1px solid #3c3e44;
    background: #26282c;
    font-weight: 400;
    text-align: left;
  }
  .guide-panel-table th {
    color: #e0e2e8;
    background: #2b2d30;
  }
  .guide-panel-table .guide-check-column {
    width: 38px;
  }
  .guide-code-editor {
    position: relative;
    min-height: 240px;
    background: linear-gradient(#2b2d30 28px, #232528 28px);
  }
  .guide-line-number {
    display: block;
    box-sizing: border-box;
    width: 43px;
    height: 100%;
    min-height: inherit;
    padding-top: 3px;
    border-right: 1px solid #3c3e44;
    color: #989da7;
    text-align: center;
    font: 0.875rem/24px monospace;
  }
  .guide-editor-tools {
    display: flex;
    flex-wrap: wrap;
    justify-content: flex-end;
    position: absolute;
    top: 0;
    right: 4px;
    max-width: calc(100% - 48px);
    border-radius: 5px;
    background: #202226;
  }
  .guide-panel-empty {
    min-height: 240px;
    background: #232528;
  }
  .guide-binary-panel {
    display: flex;
    align-items: flex-start;
    flex-wrap: wrap;
    gap: 12px;
    min-height: 240px;
    padding: 18px;
    font-size: 0.875rem;
  }
  .guide-file-placeholder {
    color: #989da7;
  }
  .guide-script-layout {
    display: grid;
    grid-template-columns: 1fr 1fr;
    min-width: 0;
  }
  .guide-script-help {
    min-width: 0;
    max-height: 300px;
    overflow-y: auto;
    padding: 12px 14px;
    border-left: 1px solid #3c3e44;
    font-size: 0.875rem;
  }
  .guide-script-help p {
    margin: 0 0 6px;
  }
  .guide-script-help h3 {
    margin: 16px 0 6px;
    padding: 0;
    border: 0;
    font-size: 0.875rem;
    color: #dfe1e5;
  }
  .guide-script-help ol {
    margin: 0;
    padding-left: 20px;
  }
  .guide-script-help a {
    color: #88aaff;
    text-decoration: none !important;
  }
  .guide-script-help a:hover {
    color: #b5cbff;
  }
  @media (max-width: 640px) {
    .guide-script-layout {
      grid-template-columns: 1fr;
    }
    .guide-script-help {
      border-left: 0;
      border-top: 1px solid #3c3e44;
    }
  }
}
</style>
