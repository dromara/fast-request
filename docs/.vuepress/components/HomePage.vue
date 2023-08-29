<template>
  <HopeHomePage>
    <template #center>
      <AdPage content="content" />
    </template>
  </HopeHomePage>
</template>
<script setup lang="ts">
import HopeHomePage from "vuepress-theme-hope/components/HomePage.js";
import AdPage from "./AdPage.vue";
</script>