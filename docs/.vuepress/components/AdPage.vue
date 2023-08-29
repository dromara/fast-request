<template>
  <div :class="`wwads-cn wwads-${mode} page-wwads-${content}`" data-id="274"></div>
</template>
<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  props: {
    mode: {
      type: String,
      default: 'horizontal',
    },
    content: {
      type: String,
      default: '',
    },
  }

});

</script>
<style>
@use "vuepress-shared/styles/wrapper";

.page-wwads-content {
  max-width: var(--content-width, 740px);
  margin: 0 auto;
}
</style>