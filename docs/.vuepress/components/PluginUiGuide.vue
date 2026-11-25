<script setup>
import { computed, onMounted, onUnmounted, ref } from "vue";
import { withBase } from "vuepress/client";
import GuideAction from "./ui-guide/GuideAction.vue";
import { useGuidePreview } from "./ui-guide/useGuidePreview.js";
import { closeOutsideGuideDropdowns } from "./ui-guide/dropdowns.js";
import {
  getGuideHref,
  guideFeatures,
  guideLayout,
  searchGuideFeatures,
} from "./ui-guide/features.js";

const props = defineProps({ lang: { type: String, default: "zh" } });
const guideRoot = ref(null);
const onPointerDown = (event) =>
  closeOutsideGuideDropdowns(guideRoot.value, event.target);
onMounted(() => document.addEventListener("pointerdown", onPointerDown, true));
onUnmounted(() =>
  document.removeEventListener("pointerdown", onPointerDown, true),
);
const index = computed(() => (props.lang === "en" ? 1 : 0));
const text = (zh, en) => (index.value ? en : zh);
const byId = Object.fromEntries(guideFeatures.map((item) => [item.id, item]));
const { selected, pinned, preview, cancelPreview, togglePin } = useGuidePreview(
  byId.document,
);
const query = ref("");
const showAll = ref(false);
const results = computed(() =>
  query.value.trim() || showAll.value
    ? searchGuideFeatures(query.value)
    : guideLayout.quickLinks.map((id) => byId[id]),
);
const href = (item) => withBase(getGuideHref(item, props.lang));
const windowCaptions = ["Request", "APIs", "Nav", "RunMethod", "Methods"];
</script>

<template>
  <section
    ref="guideRoot"
    class="plugin-ui-guide"
    :aria-label="text('插件界面导览', 'Plugin interface guide')"
  >
    <header class="guide-heading">
      <div>
        <p class="guide-eyebrow">
          FAST REQUEST <span>/</span> {{ text("界面导览", "INTERFACE GUIDE") }}
        </p>
        <h1>
          {{ text("界面导览", "Interface guide") }}
        </h1>
        <p class="guide-intro">
          {{
            text(
              "按插件界面查看功能说明，点击按钮打开对应文档。",
              "View features by their position in the plugin. Click a button to open its documentation.",
            )
          }}
        </p>
      </div>
      <a
        class="guide-overview"
        :href="withBase(`${lang === 'en' ? '/en' : ''}/guide/features/`)"
        >{{ text("浏览功能列表", "Browse all features") }}</a
      >
    </header>

    <div class="guide-canvas-heading">
      <span>{{ text("插件界面", "PLUGIN INTERFACE") }}</span>
      <span>{{
        text(
          "悬停显示说明 · 点击打开文档 · 下拉查看操作",
          "Hover for details · Click for documentation · Expand for actions",
        )
      }}</span>
    </div>

    <div class="guide-workspace">
      <div class="guide-window" @mouseleave="cancelPreview">
        <div class="guide-window-top">
          <nav
            class="guide-window-tabs"
            :aria-label="text('工具窗口', 'Tool windows')"
          >
            <GuideAction
              v-for="(id, i) in guideLayout.windowTabs"
              :key="id"
              :feature="byId[id]"
              :lang="lang"
              :caption="windowCaptions[i]"
              :icon="i === 0 ? 'runWithCoverage' : ''"
              :class="{ 'request-tab': i === 0 }"
              @preview="preview"
            />
          </nav>
          <div class="guide-title-actions">
            <GuideAction
              v-for="id in guideLayout.titleActions"
              :key="id"
              :feature="byId[id]"
              :lang="lang"
              @preview="preview"
            />
          </div>
        </div>

        <nav
          class="guide-toolbar"
          :aria-label="text('请求工具栏', 'Request toolbar')"
        >
          <div
            v-for="(group, groupIndex) in guideLayout.toolbarGroups"
            :key="groupIndex"
            class="guide-toolbar-group"
          >
            <div v-for="ids in group" :key="ids[0]" class="guide-split-action">
              <GuideAction
                :feature="byId[ids[0]]"
                :lang="lang"
                :active="selected.id === ids[0]"
                @preview="preview"
              />
              <details
                v-if="ids.length > 1"
                class="guide-dropdown"
                name="guide-toolbar-menu"
                @keydown.esc.prevent="$event.currentTarget.open = false"
              >
                <summary
                  :aria-label="`${byId[ids[0]].label[index]} · ${text('更多操作', 'More actions')}`"
                >
                  <span class="guide-chevron" />
                </summary>
                <div class="guide-dropdown-panel">
                  <GuideAction
                    v-for="id in ids"
                    :key="id"
                    :feature="byId[id]"
                    :lang="lang"
                    :caption="byId[id].label[index]"
                    @preview="preview"
                  />
                </div>
              </details>
            </div>
          </div>
        </nav>

        <div class="guide-env-row">
          <a
            :href="href(byId.domain)"
            class="guide-select"
            @mouseenter="preview(byId.domain, $event)"
            @focus="preview(byId.domain, $event)"
            ><span class="guide-env-letter">P</span
            ><span>fast-request-demo</span><span class="guide-chevron"
          /></a>
          <a
            :href="href(byId.environment)"
            class="guide-select"
            @mouseenter="preview(byId.environment, $event)"
            @focus="preview(byId.environment, $event)"
            ><span class="guide-env-letter">E</span><span>local</span
            ><span class="guide-chevron"
          /></a>
        </div>
        <a
          class="guide-url"
          :href="href(byId.debug)"
          @mouseenter="preview(byId.debug, $event)"
          @focus="preview(byId.debug, $event)"
          ><span>GET</span><code>http://localhost:8080/api/users</code></a
        >

        <nav
          class="guide-param-tabs"
          :aria-label="
            text('参数与响应文档', 'Parameters and response documentation')
          "
        >
          <a
            class="is-current"
            :href="href(byId.headers)"
            @mouseenter="preview(byId.headers, $event)"
            @focus="preview(byId.headers, $event)"
            >Headers</a
          >
          <a
            :href="href(byId.debug)"
            @mouseenter="preview(byId.debug, $event)"
            @focus="preview(byId.debug, $event)"
            >Path Param</a
          >
          <a
            :href="href(byId.debug)"
            @mouseenter="preview(byId.debug, $event)"
            @focus="preview(byId.debug, $event)"
            >URL Param</a
          >
          <a
            :href="href(byId.debug)"
            @mouseenter="preview(byId.debug, $event)"
            @focus="preview(byId.debug, $event)"
            >Body</a
          >
          <a
            class="guide-script-tab"
            :href="href(byId.script)"
            @mouseenter="preview(byId.script, $event)"
            @focus="preview(byId.script, $event)"
            >Script</a
          >
          <a
            class="guide-response-tab"
            :href="href(byId.debug)"
            @mouseenter="preview(byId.debug, $event)"
            @focus="preview(byId.debug, $event)"
            >&gt; Response</a
          >
          <a
            class="guide-console-tab"
            :href="href(byId.debug)"
            @mouseenter="preview(byId.debug, $event)"
            @focus="preview(byId.debug, $event)"
            >Console</a
          >
        </nav>

        <div class="guide-parameter-tools">
          <GuideAction
            v-for="id in guideLayout.parameterActions"
            :key="id"
            :feature="byId[id]"
            :lang="lang"
            @preview="preview"
          />
          <span class="guide-tools-divider" />
          <GuideAction
            :feature="byId.environment"
            :lang="lang"
            @preview="preview"
          />
          <GuideAction
            :feature="byId.function"
            :lang="lang"
            @preview="preview"
          />
        </div>
        <div class="guide-table-scroll">
          <table class="guide-param-table">
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
        <div class="guide-window-footer">
          <span>Fast Request Pro</span
          ><span>{{
            text("界面导览 · 示例数据", "Interface guide · Example data")
          }}</span>
        </div>
      </div>

      <aside
        class="guide-inspector"
        :aria-label="text('功能说明', 'Feature details')"
        @mouseenter="cancelPreview"
      >
        <div class="guide-inspector-heading">
          <span class="guide-inspector-label">{{
            text("功能说明", "FEATURE DETAILS")
          }}</span>
          <button
            type="button"
            class="guide-pin"
            :aria-pressed="pinned"
            :aria-label="
              pinned
                ? text('取消固定功能', 'Unpin feature')
                : text('固定当前功能', 'Pin current feature')
            "
            @click="togglePin"
          >
            <svg
              viewBox="0 0 24 24"
              width="14"
              height="14"
              fill="none"
              stroke="currentColor"
              stroke-width="1.6"
              stroke-linejoin="round"
              aria-hidden="true"
            >
              <path d="M8 3h8l-1 6 4 4v2h-6v6l-2-2v-4H5v-2l4-4-1-6Z" />
            </svg>
            {{ pinned ? text("取消固定", "Unpin") : text("固定", "Pin") }}
          </button>
        </div>
        <div class="guide-inspector-icon">
          <img
            :src="withBase(`/img/ui-guide/${selected.icon}.svg`)"
            width="32"
            height="32"
            alt=""
            no-view
          />
        </div>
        <h2>{{ selected.label[index] }}</h2>
        <p>{{ selected.description[index] }}</p>
        <a class="guide-read-doc" :href="href(selected)">{{
          text("阅读功能文档", "Read documentation")
        }}</a>
      </aside>
    </div>

    <section class="guide-find" aria-labelledby="guide-find-title">
      <div class="guide-find-heading">
        <div>
          <h2 id="guide-find-title">
            {{ text("功能文档", "Feature documentation") }}
          </h2>
          <p>
            {{
              text(
                "按名称筛选功能文档。",
                "Filter feature documentation by name.",
              )
            }}
          </p>
        </div>
        <div class="guide-search">
          <label for="guide-feature-search">{{
            text("搜索功能", "Search features")
          }}</label
          ><input
            id="guide-feature-search"
            v-model="query"
            type="search"
            :placeholder="
              text('搜索功能，如 AI、保存、cURL…', 'Search AI, save, cURL…')
            "
          />
        </div>
      </div>
      <p class="guide-result-count" role="status">
        {{
          query.trim()
            ? text(
                `找到 ${results.length} 项功能`,
                `${results.length} features found`,
              )
            : text("常用功能", "COMMON FEATURES")
        }}
      </p>
      <div v-if="results.length" class="guide-feature-grid">
        <a
          v-for="item in results"
          :key="item.id"
          class="guide-feature-card"
          :href="href(item)"
        >
          <img
            :src="withBase(`/img/ui-guide/${item.icon}.svg`)"
            width="24"
            height="24"
            alt=""
            no-view
          />
          <div>
            <h3>{{ item.label[index] }}</h3>
            <p>{{ item.description[index] }}</p>
          </div>
        </a>
      </div>
      <p v-else class="guide-no-results">
        {{ text("未找到匹配的功能。", "No matching features.") }}
      </p>
      <button
        v-if="!query.trim()"
        type="button"
        class="guide-show-all"
        :aria-expanded="showAll"
        @click="showAll = !showAll"
      >
        {{
          showAll
            ? text("收起功能列表", "Show common features")
            : text(
                `查看全部 ${guideFeatures.length} 项功能`,
                `View all ${guideFeatures.length} features`,
              )
        }}
      </button>
    </section>
  </section>
</template>

<style>
.plugin-guide-page {
  --content-width: 1360px;
}
.plugin-guide-page .vp-page-title,
.plugin-guide-page .vp-page-meta {
  display: none;
}
.plugin-guide-page [vp-content] {
  padding-top: 2.5rem;
}
</style>

<style scoped>
.plugin-ui-guide {
  --guide-accent: #9bb8ff;
  --guide-muted: #a0a9bc;
  color: #e4e8f1;
  font-size: 1rem;
  line-height: 1.6;
}
.plugin-ui-guide * {
  box-sizing: border-box;
}
.plugin-ui-guide a {
  text-decoration: none;
}
.guide-heading {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 2rem;
  margin-bottom: 2.5rem;
}
.guide-eyebrow {
  margin: 0 0 1rem;
  color: var(--vp-c-text-mute, #a0a9bc);
  font-size: 0.75rem;
  font-weight: 650;
  letter-spacing: 0.12em;
}
.guide-eyebrow span {
  margin-inline: 0.6rem;
  color: #7285ac;
}
.guide-heading h1 {
  margin: 0;
  color: var(--vp-c-text, #e4e8f1);
  font-size: clamp(1.75rem, 3vw, 2.65rem);
  letter-spacing: -0.04em;
  line-height: 1.35;
}
.guide-intro {
  margin: 0.9rem 0 0;
  color: var(--vp-c-text-mute, #a0a9bc);
}
.guide-overview {
  flex-shrink: 0;
  padding: 0.6rem 1rem;
  border: 1px solid var(--vp-c-divider, #3b4355);
  border-radius: 8px;
  color: var(--vp-c-text, #dfe1e5);
  font-size: 0.875rem;
}
.guide-overview:hover {
  border-color: #6d8fe3;
}
.guide-canvas-heading {
  display: flex;
  justify-content: space-between;
  gap: 1rem;
  margin: 0 0 0.8rem;
  color: var(--vp-c-text-mute, #a0a9bc);
  font-size: 0.8rem;
}
.guide-canvas-heading > :first-child {
  font-weight: 650;
  letter-spacing: 0.1em;
}
.guide-workspace {
  display: grid;
  grid-template-columns: minmax(0, 1fr) 260px;
  gap: 1rem;
  align-items: stretch;
}
.guide-window {
  min-width: 0;
  border: 1px solid #4a4e59;
  border-radius: 10px;
  background: #2b2d30;
  box-shadow: 0 14px 45px #0002;
}
.guide-window-top {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: space-between;
  border-bottom: 1px solid #1e1f22;
  background: #25262a;
  border-radius: 10px 10px 0 0;
}
.guide-window-tabs {
  display: flex;
  flex-wrap: wrap;
  gap: 2px;
  padding: 5px 5px 0;
}
.guide-window-tabs :deep(.guide-action) {
  min-height: 48px;
  padding-inline: 10px;
  border-bottom-left-radius: 0;
  border-bottom-right-radius: 0;
  font-weight: 550;
}
.guide-window-tabs :deep(.request-tab) {
  border-bottom: 3px solid #548af7;
  background: #32353b;
}
.guide-title-actions {
  display: flex;
  padding: 4px 7px;
}
.guide-toolbar {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  gap: 3px 0;
  padding: 8px 5px;
  border-bottom: 1px solid #1e1f22;
}
.guide-toolbar-group {
  display: flex;
  flex-wrap: wrap;
  gap: 1px;
  padding: 0 5px;
  border-right: 1px solid #4b4d53;
}
.guide-toolbar-group:last-child {
  border-right: 0;
}
.guide-split-action {
  display: flex;
  align-items: center;
  position: relative;
}
.guide-dropdown {
  position: relative;
}
.guide-dropdown summary {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 20px;
  height: 38px;
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
.guide-env-row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 10px;
  padding: 12px 12px 10px;
}
.guide-select {
  display: flex;
  align-items: center;
  gap: 10px;
  min-width: 0;
  padding: 9px 12px;
  border: 1px solid #51545c;
  border-radius: 5px;
  color: #dfe1e5;
  background: #393b40;
  font-size: 0.875rem;
}
.guide-select > span:nth-child(2) {
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}
.guide-select .guide-chevron {
  flex-shrink: 0;
  margin-left: auto;
}
.guide-env-letter {
  display: grid;
  flex-shrink: 0;
  place-items: center;
  width: 21px;
  height: 21px;
  border: 1.5px solid #b889eb;
  border-radius: 50%;
  color: #c59af2;
  font-size: 0.875rem;
}
.guide-url {
  display: flex;
  align-items: center;
  gap: 13px;
  margin: 0 12px 12px;
  padding: 10px 12px;
  border: 1px solid #51545c;
  border-radius: 4px;
  background: #26282c;
}
.guide-url > span {
  color: #7ba7ff;
  font-size: 0.875rem;
  font-weight: 650;
}
.guide-url code {
  overflow-wrap: anywhere;
  background: transparent;
  color: #cbd0dc;
  padding: 0;
  font-size: 0.875rem;
}
.guide-param-tabs {
  display: flex;
  flex-wrap: wrap;
  gap: 1px;
  padding: 0 7px;
  border-bottom: 1px solid #1e1f22;
}
.guide-param-tabs a {
  display: flex;
  align-items: center;
  min-height: 49px;
  padding: 10px 12px;
  border-bottom: 3px solid transparent;
  color: #dfe1e5;
  font-size: 0.875rem;
}
.guide-param-tabs .is-current {
  border-bottom-color: #548af7;
}
.guide-param-tabs .guide-script-tab {
  color: #e5a778;
}
.guide-param-tabs .guide-response-tab {
  color: #57cba2;
}
.guide-param-tabs .guide-console-tab {
  color: #ed8e99;
}
.guide-param-tabs a:hover {
  background: #3a4354;
}
.guide-parameter-tools {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  gap: 3px;
  padding: 5px 10px;
  border-bottom: 1px solid #1e1f22;
}
.guide-tools-divider {
  height: 22px;
  width: 1px;
  background: #51545c;
  margin-inline: 5px;
}
.guide-table-scroll {
  overflow-x: auto;
  min-height: 160px;
}
.guide-param-table {
  display: table;
  width: 100%;
  margin: 0;
  border-collapse: collapse;
  background: #2b2d30;
  color: #bec4d0;
  font-size: 0.875rem;
  white-space: nowrap;
}
.guide-param-table th,
.guide-param-table td {
  padding: 10px 13px;
  border: 0;
  border-right: 1px solid #3c3e44;
  border-bottom: 1px solid #3c3e44;
  background: #2b2d30;
  font-weight: 400;
  text-align: left;
}
.guide-param-table th {
  color: #e0e2e8;
  background: #303237;
}
.guide-param-table td:first-child,
.guide-param-table th:first-child {
  width: 38px;
  text-align: center;
  color: #86a9f0;
}
.guide-param-table td:nth-child(2) {
  color: #ccaf88;
}
.guide-window-footer {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  gap: 0.5rem;
  padding: 8px 13px;
  border-top: 1px solid #41434a;
  color: #a0a9bc;
  font-size: 0.75rem;
}
.guide-inspector {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  align-self: start;
  padding: 22px;
  border: 1px solid #3d4960;
  border-radius: 12px;
  background: linear-gradient(155deg, #202b40, #192130 80%);
  box-shadow: 0 8px 28px #0002;
}
.guide-inspector-label {
  color: #a2b6dc;
  font-size: 0.75rem;
  font-weight: 600;
  letter-spacing: 0.07em;
}
.guide-inspector-heading {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: space-between;
  gap: 10px;
  width: 100%;
}
.guide-pin {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  gap: 5px;
  min-height: 30px;
  padding: 4px 9px;
  border: 1px solid #52627e;
  border-radius: 7px;
  color: #c2d0eb;
  background: #ffffff05;
  font: inherit;
  font-size: 0.8125rem;
  cursor: pointer;
}
.guide-pin:hover,
.guide-pin[aria-pressed="true"] {
  border-color: #9bb8ff;
  background: #344c7a;
}
.guide-inspector-icon {
  display: grid;
  place-items: center;
  width: 54px;
  height: 54px;
  margin-top: 24px;
  border: 1px solid #455673;
  border-radius: 12px;
  background: #2a3851;
}
.guide-inspector h2 {
  margin: 18px 0 10px;
  padding: 0;
  border: 0;
  color: #f1f4fa;
  font-size: 1.25rem;
  line-height: 1.45;
}
.guide-inspector > p {
  margin: 0 0 22px;
  color: #b9c5dc;
  font-size: 0.9375rem;
}
.guide-read-doc {
  display: block;
  width: 100%;
  padding: 11px 14px;
  border: 1px solid #7497e3;
  border-radius: 8px;
  color: #f4f7ff;
  background: #38578f;
  text-align: center;
  font-size: 0.875rem;
  font-weight: 550;
}
.guide-read-doc:hover {
  background: #46649c;
}
.guide-find {
  margin-top: 3rem;
}
.guide-find-heading {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 2rem;
}
.guide-find h2 {
  margin: 0;
  padding: 0;
  border: 0;
  color: var(--vp-c-text, #e4e8f1);
  font-size: 1.4rem;
}
.guide-find-heading p {
  margin: 0.35rem 0 0;
  color: var(--vp-c-text-mute, #a0a9bc);
  font-size: 0.9375rem;
}
.guide-search {
  width: 320px;
  max-width: 100%;
}
.guide-search label {
  display: block;
  margin-bottom: 6px;
  color: var(--vp-c-text-mute, #a0a9bc);
  font-size: 0.8125rem;
}
.guide-search input {
  box-sizing: border-box;
  width: 100%;
  padding: 11px 13px;
  border: 1px solid var(--vp-c-divider, #454c60);
  border-radius: 7px;
  color: var(--vp-c-text, #dfe1e5);
  background: var(--vp-c-bg-alt, #242832);
  font: inherit;
  font-size: 0.875rem;
}
.guide-result-count {
  margin: 1.5rem 0 0.65rem;
  color: var(--vp-c-text-mute, #a0a9bc);
  font-size: 0.75rem;
  letter-spacing: 0.08em;
}
.guide-feature-grid {
  display: grid;
  grid-template-columns: repeat(4, minmax(0, 1fr));
  gap: 12px;
}
.guide-feature-card {
  display: flex;
  align-items: flex-start;
  gap: 12px;
  padding: 18px;
  border: 1px solid #454b5a;
  border-radius: 8px;
  background: #292d36;
}
.guide-feature-card > img {
  flex-shrink: 0;
  margin-top: 3px;
}
.guide-feature-card h3 {
  margin: 0 0 6px;
  color: #e4e8f1;
  font-size: 0.9375rem;
  line-height: 1.5;
}
.guide-feature-card p {
  margin: 0;
  color: #aeb8cc;
  font-size: 0.8125rem;
  line-height: 1.7;
}
.guide-feature-card:hover {
  border-color: #8ba9ed;
  background: #30394c;
}
.guide-show-all {
  display: block;
  margin: 1.5rem auto 0;
  padding: 0.5rem 1rem;
  border: 1px solid var(--vp-c-divider, #454c60);
  border-radius: 6px;
  color: var(--vp-c-text, #dfe1e5);
  background: transparent;
  font: inherit;
  font-size: 0.875rem;
  cursor: pointer;
}
.guide-no-results {
  padding: 2rem;
  border: 1px dashed var(--vp-c-divider, #454c60);
  border-radius: 7px;
  color: var(--vp-c-text-mute, #a0a9bc);
  text-align: center;
}
.plugin-ui-guide a:focus-visible,
.plugin-ui-guide button:focus-visible,
.plugin-ui-guide input:focus-visible {
  outline: 2px solid #88aaff;
  outline-offset: 3px;
}
.guide-select:hover,
.guide-url:hover {
  border-color: #88aaff;
}
@media (max-width: 1100px) {
  .guide-workspace {
    grid-template-columns: minmax(0, 1fr);
  }
  .guide-inspector {
    display: grid;
    grid-template-columns: 48px 1fr auto;
    gap: 8px 18px;
    align-items: center;
    padding: 20px;
  }
  .guide-inspector-heading {
    grid-column: 2;
  }
  .guide-inspector-icon {
    grid-column: 1;
    grid-row: 1 / 4;
    width: 48px;
    height: 48px;
    margin: 0;
  }
  .guide-inspector h2 {
    grid-column: 2;
    margin: 0;
  }
  .guide-inspector > p {
    grid-column: 2;
    margin: 0;
  }
  .guide-read-doc {
    grid-column: 3;
    grid-row: 1 / 4;
    width: auto;
  }
  .guide-feature-grid {
    grid-template-columns: repeat(2, minmax(0, 1fr));
  }
}
@media (max-width: 640px) {
  .guide-toolbar-group {
    position: relative;
  }
  .guide-split-action,
  .guide-dropdown {
    position: static;
  }
  .guide-dropdown-panel {
    top: 100%;
    right: auto;
    left: 0;
    max-width: calc(100vw - 80px);
  }
  .guide-heading,
  .guide-find-heading {
    flex-direction: column;
    align-items: flex-start;
    gap: 1rem;
  }
  .guide-heading {
    margin-bottom: 1.75rem;
  }
  .guide-canvas-heading {
    flex-direction: column;
    gap: 0.25rem;
  }
  .guide-window-tabs :deep(.guide-action) {
    gap: 5px;
    padding-inline: 7px;
    font-size: 0.8125rem;
  }
  .guide-window-tabs :deep(img) {
    width: 18px;
    height: 18px;
  }
  .guide-title-actions {
    margin-left: auto;
  }
  .guide-env-row {
    grid-template-columns: 1fr;
  }
  .guide-param-tabs a {
    padding-inline: 9px;
  }
  .guide-inspector {
    grid-template-columns: 48px 1fr;
  }
  .guide-read-doc {
    grid-column: 1 / -1;
    grid-row: auto;
    margin-top: 8px;
  }
  .guide-feature-grid {
    grid-template-columns: 1fr;
  }
  .guide-search {
    width: 100%;
  }
}
</style>
