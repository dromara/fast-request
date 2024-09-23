<script setup lang="ts">
import type { PropType } from "vue";
import { Carousel, Navigation, Pagination, Slide } from "vue3-carousel";
import "vue3-carousel/dist/carousel.css";

defineProps({
  imgList: {
    type: Array as PropType<string[]>,
    required: true,
    validator: (value: string[]) => {
      // 进行更严格的类型验证，确保数组元素都是字符串
      return value.every((item) => typeof item === "string");
    },
  },
});
</script>

<template>
  <Carousel>
    <Slide v-for="imgSrc in imgList" :key="imgSrc">
      <div class="carousel__item">
        <img :src="imgSrc" loading="lazy" />
      </div>
    </Slide>

    <template #addons>
      <Navigation />
      <Pagination />
    </template>
  </Carousel>
</template>

<style>
.carousel__prev,
.carousel__next {
  box-sizing: content-box;
  color: var(--vp-c-accent-bg);
}
.carousel__pagination-button::after {
  background-color: #fff;
}

.carousel__pagination-button:hover::after,
.carousel__pagination-button--active::after {
  background-color: var(--vp-c-accent-bg);
}
.carousel__track {
  margin-top: 0;
}
</style>
