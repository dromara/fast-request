<script setup lang="ts">
import { useRoutePath } from "vuepress/client";
import { computed, ref, watch } from "vue";

import MainFadeInUpTransition from "@theme-hope/components/base/MainFadeInUpTransition";
import MainLayout from "@theme-hope/components/base/MainLayout";
import PageContent from "@theme-hope/components/base/PageContent";
import SkipLink from "@theme-hope/components/base/SkipLink";
import HomePage from "@theme-hope/components/home/HomePage";
import PortfolioHome from "@theme-hope/components/home/PortfolioHome";
import { useData } from "@theme-hope/composables/useData";

const { frontmatter, page } = useData();
const routePath = useRoutePath();

const sidebarTopArray = [
  `<a href="https://codegeex.cn/?utm_source=pay&utm_medium=fast-request" target="_blank">
    <img className="no-zoom" height="50px" width="220px" src="/img/sponsor/codegeex-line.svg">
  </a>`,
  `<a href="https://brucege.com/pay/view?code=fastRequest" target="_blank">
    <img className="no-zoom" height="50px" width="220px" src="/img/sponsor/mybatisCodeHelperPro.svg">
  </a>`,
  `<a href="https://doc.xiaominfo.com/?utm_source=FastRequest" target="_blank">
    <img className="no-zoom" src="/img/sponsor/knife4j.svg">
  </a>`,
  `<a href="https://easysearch.cn?utm_source=pay&utm_medium=fast-request" target="_blank">
    <img className="no-zoom" src="/img/sponsor/easysearch-line.svg" width="220px" >
  </a>`,
];

const sidebarContent = ref("");
const showSidebarTop = computed(
  () => !frontmatter.value.home && sidebarContent.value.length > 0,
);

function shuffle<T>(arr: T[]): T[] {
  let l = arr.length;
  let index: number;
  let temp: T;

  while (l > 0) {
    index = Math.floor(Math.random() * l);
    temp = arr[l - 1];
    arr[l - 1] = arr[index];
    arr[index] = temp;
    l--;
  }
  return arr;
}

function renderSponsor(): void {
  if (page.value.path.startsWith("/en/")) {
    sidebarContent.value = "";
    return;
  } else {
    shuffle(sidebarTopArray);
    sidebarContent.value = `\
    <div style="width:230px;margin:5px auto;">
    ${sidebarTopArray.slice(0, 4).join("\n  ")}
    <br/>
    <!--  <span style='color: grey;font-size: 11px;'>广告采用随机方式显示</span>-->
      <span style='float: right;'>
      <a href='/guide/sponsor.html' style='color: var(--vp-c-accent-bg);font-size: 11px;font-weight: bolder;'>成为赞助商</a>
      </span>
    </div>
    `;
  }
}

watch(routePath, renderSponsor, { immediate: true });
</script>
<template>
  <SkipLink />
  <MainLayout>
    <template #default>
      <PortfolioHome v-if="frontmatter.portfolio" />
      <HomePage v-else-if="frontmatter.home" />
      <MainFadeInUpTransition v-else>
        <PageContent :key="page.path" />
      </MainFadeInUpTransition>
    </template>
    <template v-if="showSidebarTop" #sidebarTop>
      <div v-html="sidebarContent" />
    </template>
  </MainLayout>
</template>
