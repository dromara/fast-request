<script setup lang="ts">
defineProps({
  mode: {
    type: String,
    default: "horizontal",
  },
  content: {
    type: String,
    default: "",
  },
});
</script>

<template>
  <div :class="`wwads-cn wwads-${mode} page-wwads-${content}`" data-id="274" />
</template>

<style lang="scss">
@use "vuepress-shared/styles/wrapper";

.page-wwads-content {
  max-width: var(--content-width, 740px);
  margin: 0 auto;
}
</style>
