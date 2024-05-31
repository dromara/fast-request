<script setup lang="ts">
withDefaults(
  defineProps<{
    mode?: string;
    content?: string;
  }>(),
  {
    mode: "horizontal",
    content: "",
  },
);
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
