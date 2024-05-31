<script setup lang="ts">
import NormalPage from "vuepress-theme-hope/components/NormalPage.js";
import AdPage from "./AdPage.vue";
</script>

<template>
  <NormalPage>
    <template #contentBefore>
      <AdPage content="content" />
    </template>
  </NormalPage>
</template>
