<script setup>
import { computed, onMounted, onUnmounted, ref } from "vue";
import { withBase } from "vuepress/client";
import GuideSplitAction from "./ui-guide/GuideSplitAction.vue";
import GuideTabs from "./ui-guide/GuideTabs.vue";
import ApisPanel from "./ui-guide/ApisPanel.vue";
import NavPanel from "./ui-guide/NavPanel.vue";
import RunMethodPanel from "./ui-guide/RunMethodPanel.vue";
import MethodsPanel from "./ui-guide/MethodsPanel.vue";
import RequestPanels from "./ui-guide/RequestPanels.vue";
import { useGuidePreview } from "./ui-guide/useGuidePreview.js";
import { useGuideGroups } from "./ui-guide/useGuideGroups.js";
import { closeOutsideGuideDropdowns } from "./ui-guide/dropdowns.js";
import {
  getGuideHref,
  guideFeatures,
  guideLayout,
  groupGuideFeatures,
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
const groups = computed(() => groupGuideFeatures(query.value));
const {
  collapsedGroups,
  allExpanded,
  allCollapsed,
  setGroupOpen,
  setAllGroupsOpen,
} = useGuideGroups(query, groups);
const resultCount = computed(() =>
  groups.value.reduce((count, group) => count + group.items.length, 0),
);
const href = (item) => withBase(getGuideHref(item, props.lang));
const windowCaptions = ["Request", "APIs", "Nav", "RunMethod", "Methods"];
const activeWindow = ref("debug");
const windowTabs = guideLayout.windowTabs.map((id, i) => ({
  id,
  label: windowCaptions[i],
  icon: i === 0 ? "runWithCoverage" : byId[id].icon,
}));
const previewWindow = (item, event) => preview(byId[item.id], event);
function selectWindow(id) {
  activeWindow.value = id;
  preview(byId[id], { type: "focus" });
  closeOutsideGuideDropdowns(guideRoot.value, null);
}
</script>

<template>
  <section
    ref="guideRoot"
    class="plugin-ui-guide"
    :aria-label="text('插件界面导览', 'Plugin interface guide')"
  >
    <header class="guide-heading">
      <div>
        <p class="guide-intro">
          {{
            text(
              "按插件界面查看功能说明。",
              "View features by their position in the plugin.",
            )
          }}
        </p>
        <p class="guide-interaction-hint">
          {{
            text(
              "悬停显示说明 · 点击打开文档 · 下拉查看操作",
              "Hover for details · Click for documentation · Expand for actions",
            )
          }}
        </p>
      </div>
      <div class="guide-heading-actions">
        <a class="guide-overview" href="#guide-find-title">{{
          text("浏览功能文档", "Browse feature documentation")
        }}</a>
      </div>
    </header>

    <div class="guide-workspace">
      <div class="guide-window" @mouseleave="cancelPreview">
        <GuideTabs
          :model-value="activeWindow"
          :items="windowTabs"
          :label="text('工具窗口', 'Tool windows')"
          window-tabs
          @update:model-value="selectWindow"
          @preview="previewWindow"
        >
          <template #tab="{ item }">
            <img
              :src="withBase(`/img/ui-guide/${item.icon}.svg`)"
              width="22"
              height="22"
              alt=""
              no-view
            />
            {{ item.label }}
          </template>
          <template #actions>
            <div class="guide-title-actions">
              <GuideSplitAction
                v-for="id in guideLayout.titleActions"
                :key="id"
                :features="
                  id === 'help'
                    ? guideLayout.helpActions.map((key) => byId[key])
                    : [byId[id]]
                "
                :lang="lang"
                :active="selected.id"
                @preview="preview"
              />
            </div>
          </template>

          <div v-show="activeWindow === 'debug'" data-window-panel="debug">
            <nav
              class="guide-toolbar"
              :aria-label="text('请求工具栏', 'Request toolbar')"
            >
              <div
                v-for="(group, groupIndex) in guideLayout.toolbarGroups"
                :key="groupIndex"
                class="guide-toolbar-group"
              >
                <GuideSplitAction
                  v-for="ids in group"
                  :key="ids[0]"
                  :features="ids.map((id) => byId[id])"
                  :lang="lang"
                  :active="selected.id"
                  @preview="preview"
                />
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

            <RequestPanels :lang="lang" @preview="preview" />
          </div>
          <ApisPanel
            v-show="activeWindow === 'apis'"
            :lang="lang"
            @preview="preview"
          />
          <NavPanel
            v-show="activeWindow === 'nav'"
            :lang="lang"
            @preview="preview"
          />
          <RunMethodPanel
            v-show="activeWindow === 'run'"
            :lang="lang"
            @preview="preview"
          />
          <MethodsPanel
            v-show="activeWindow === 'methods'"
            :lang="lang"
            @preview="preview"
          />
        </GuideTabs>
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
        <a v-if="selected.doc" class="guide-read-doc" :href="href(selected)">{{
          text("阅读功能文档", "Read documentation")
        }}</a>
      </aside>
    </div>

    <section class="guide-find" aria-labelledby="guide-find-title">
      <div class="guide-find-heading">
        <h2 id="guide-find-title">
          {{ text("功能文档", "Feature documentation") }}
        </h2>
        <div class="guide-search">
          <input
            id="guide-feature-search"
            v-model="query"
            type="search"
            :aria-label="text('搜索功能', 'Search features')"
            :placeholder="
              text('搜索功能，如 AI、保存、cURL…', 'Search AI, save, cURL…')
            "
          />
        </div>
      </div>
      <div class="guide-find-toolbar">
        <p class="guide-result-count" role="status">
          {{
            query.trim()
              ? text(
                  `找到 ${resultCount} 项功能`,
                  `${resultCount} features found`,
                )
              : text(
                  `共 ${resultCount} 项功能，${groups.length} 个分类`,
                  `${resultCount} features in ${groups.length} categories`,
                )
          }}
        </p>
        <div class="guide-group-actions">
          <button
            type="button"
            :disabled="!groups.length || allExpanded"
            @click="setAllGroupsOpen(true)"
          >
            {{ text("全部展开", "Expand all") }}
          </button>
          <button
            type="button"
            :disabled="!groups.length || allCollapsed"
            @click="setAllGroupsOpen(false)"
          >
            {{ text("全部折叠", "Collapse all") }}
          </button>
        </div>
      </div>
      <div v-if="groups.length" class="guide-feature-groups">
        <details
          v-for="group in groups"
          :key="group.id"
          class="guide-feature-group"
          :open="!collapsedGroups.has(group.id)"
          :aria-labelledby="`guide-category-${group.id}`"
        >
          <summary
            class="guide-group-summary"
            @click.prevent="
              setGroupOpen(group.id, collapsedGroups.has(group.id))
            "
          >
            <span class="guide-group-chevron" aria-hidden="true" />
            <h3 :id="`guide-category-${group.id}`" class="guide-group-heading">
              {{ group.label[index] }} <span>{{ group.items.length }}</span>
            </h3>
          </summary>
          <div class="guide-feature-grid">
            <a
              v-for="item in group.items"
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
                <h4>{{ item.label[index] }}</h4>
                <p>{{ item.description[index] }}</p>
              </div>
            </a>
          </div>
        </details>
      </div>
      <p v-else class="guide-no-results">
        {{ text("未找到匹配的功能。", "No matching features.") }}
      </p>
    </section>
  </section>
</template>

<style src="./ui-guide/window-panels.css" />

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
.plugin-ui-guide :deep(a) {
  text-decoration: none !important;
}
.guide-heading {
  display: flex;
  align-items: flex-end;
  justify-content: space-between;
  gap: 2rem;
  margin-bottom: 1.25rem;
}
.guide-heading > div {
  min-width: 0;
}
.guide-heading-actions {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  gap: 0.5rem;
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
  color: #fff;
  font-size: 0.95rem;
}
.guide-overview {
  flex-shrink: 0;
  padding: 0.6rem 1rem;
  border: 2px solid rgba(255, 255, 255, 0.55);
  border-radius: 8px;
  color: #fff;
  font-size: 0.875rem;
}
.guide-overview:hover {
  border-color: #fff;
}
.guide-interaction-hint {
  margin: 0.5rem 0 0;
  color: #fff;
  font-size: 0.95rem;
  text-align: left;
}
.guide-intro::before,
.guide-interaction-hint::before {
  display: inline-block;
  width: 0.5rem;
  height: 0.5rem;
  margin-right: 0.5rem;
  border-radius: 50%;
  content: "";
}
.guide-intro::before {
  background: #6d8fe3;
}
.guide-interaction-hint::before {
  background: #ef4b50;
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
.guide-title-actions {
  display: flex;
  position: relative;
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
.guide-toolbar :deep(.guide-action) {
  min-width: 30px;
  min-height: 32px;
  padding: 5px;
}
.guide-toolbar :deep(.guide-action img) {
  width: 18px;
  height: 18px;
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
  display: inline-block;
  flex-shrink: 0;
  width: 7px;
  height: 7px;
  margin-left: auto;
  border-right: 1.5px solid #b3b9c6;
  border-bottom: 1.5px solid #b3b9c6;
  transform: rotate(45deg) translateY(-2px);
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
  color: #fff;
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
  color: #fff;
  font-size: 1.25rem;
  line-height: 1.45;
}
.guide-inspector > p {
  margin: 0 0 22px;
  color: #fff;
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
.guide-search {
  width: 320px;
  max-width: 100%;
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
.guide-find-toolbar {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: space-between;
  gap: 0.75rem 1rem;
  margin: 1.5rem 0 0.65rem;
}
.guide-result-count {
  margin: 0;
  color: var(--vp-c-text-mute, #a0a9bc);
  font-size: 0.75rem;
  letter-spacing: 0.08em;
}
.guide-group-actions {
  display: flex;
  gap: 0.5rem;
}
.guide-group-actions button {
  padding: 0.35rem 0.75rem;
  border: 1px solid var(--vp-c-divider, #454c60);
  border-radius: 6px;
  color: var(--vp-c-text, #e4e8f1);
  background: transparent;
  font: inherit;
  font-size: 0.8125rem;
  cursor: pointer;
}
.guide-group-actions button:hover:not(:disabled) {
  border-color: #8ba9ed;
  background: #30394c;
}
.guide-group-actions button:disabled {
  opacity: 0.45;
  cursor: default;
}
.guide-feature-grid {
  display: grid;
  grid-template-columns: repeat(4, minmax(0, 1fr));
  gap: 12px;
}
.guide-feature-group + .guide-feature-group {
  margin-top: 28px;
}
.guide-group-summary {
  display: flex;
  align-items: center;
  gap: 12px;
  padding-bottom: 10px;
  border-bottom: 1px solid var(--vp-c-divider, #454c60);
  list-style: none;
  cursor: pointer;
}
.guide-group-summary::-webkit-details-marker {
  display: none;
}
.guide-feature-group[open] > .guide-group-summary {
  margin-bottom: 14px;
}
.guide-group-summary:hover {
  color: var(--guide-accent);
}
.guide-group-summary:focus-visible {
  outline: 2px solid #88aaff;
  outline-offset: 3px;
}
.guide-group-chevron {
  flex-shrink: 0;
  width: 7px;
  height: 7px;
  margin-left: 2px;
  border-right: 1.5px solid currentColor;
  border-bottom: 1.5px solid currentColor;
  color: var(--guide-muted);
  transform: rotate(-45deg);
}
.guide-feature-group[open] > .guide-group-summary .guide-group-chevron {
  transform: rotate(45deg);
}
.guide-group-heading {
  display: flex;
  align-items: center;
  gap: 10px;
  margin: 0;
  padding: 0;
  border: 0;
  color: var(--vp-c-text, #e4e8f1);
  font-size: 1.0625rem;
  font-weight: 600;
}
.guide-group-heading span {
  padding: 1px 7px;
  border: 1px solid var(--vp-c-divider, #454c60);
  border-radius: 5px;
  color: var(--vp-c-text-mute, #a0a9bc);
  font-size: 0.75rem;
  font-weight: 400;
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
.guide-feature-card h4 {
  margin: 0 0 6px;
  color: #fff;
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
  .guide-heading,
  .guide-find-heading {
    flex-direction: column;
    align-items: flex-start;
    gap: 1rem;
  }
  .guide-heading {
    margin-bottom: 1rem;
  }
  .guide-heading-actions {
    align-items: flex-start;
  }
  .guide-interaction-hint {
    text-align: left;
  }
  .guide-title-actions {
    margin-left: auto;
  }
  .guide-env-row {
    grid-template-columns: 1fr;
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
