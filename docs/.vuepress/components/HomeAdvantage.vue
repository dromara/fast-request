<script setup lang="ts">
import { onMounted, onUnmounted, ref } from "vue";

const props = withDefaults(
  defineProps<{
    src: string;
    title: string;
    height?: number;
    lang?: string;
  }>(),
  { height: 500, lang: "zh" },
);

const frame = ref<HTMLIFrameElement | null>(null);
const preview = ref<HTMLDialogElement | null>(null);
const imageSrc = ref("");
const imageTitle = ref("");
let previousOverflow: string | null = null;

function openPreview(event: MessageEvent) {
  const data = event.data;
  if (
    event.origin !== window.location.origin ||
    event.source !== frame.value?.contentWindow ||
    !data ||
    data.type !== "home-advantage:preview" ||
    !Number.isInteger(data.index) ||
    data.index < 0 ||
    data.index > 6 ||
    typeof data.title !== "string" ||
    !preview.value ||
    preview.value.open
  ) {
    return;
  }

  const imageIndex = [0, 7, 2, 3, 4, 5, 6][data.index];
  imageSrc.value = new URL(
    `../img/index/${imageIndex}.png`,
    new URL(props.src, window.location.href),
  ).href;
  imageTitle.value = data.title;
  previousOverflow = document.documentElement.style.overflow;
  document.documentElement.style.overflow = "hidden";
  preview.value.showModal();
}

function onClose() {
  if (previousOverflow !== null) {
    document.documentElement.style.overflow = previousOverflow;
    previousOverflow = null;
  }
  frame.value?.contentWindow?.postMessage(
    { type: "home-advantage:close" },
    window.location.origin,
  );
}

onMounted(() => window.addEventListener("message", openPreview));
onUnmounted(() => {
  window.removeEventListener("message", openPreview);
  if (previousOverflow !== null) {
    document.documentElement.style.overflow = previousOverflow;
  }
});
</script>

<template>
  <div class="home-advantage-frame">
    <iframe
      ref="frame"
      :title="title"
      :src="src"
      width="100%"
      :height="height"
      frameborder="0"
      scrolling="no"
    />
  </div>
  <dialog
    ref="preview"
    class="home-image-preview"
    :aria-label="imageTitle"
    @click.self="preview?.close()"
    @close="onClose"
  >
    <button
      type="button"
      class="home-image-preview-close"
      :aria-label="lang === 'en' ? 'Close preview' : '关闭预览'"
      autofocus
      @click="preview?.close()"
    >
      ×
    </button>
    <img :src="imageSrc" :alt="imageTitle" />
  </dialog>
</template>

<style scoped>
.home-advantage-frame {
  overflow: hidden;
  border-radius: 10px;
}

.home-image-preview {
  position: fixed;
  inset: 0;
  box-sizing: border-box;
  width: 100vw;
  max-width: none;
  height: 100dvh;
  max-height: none;
  margin: 0;
  padding: 64px 24px 24px;
  border: 0;
  background: transparent;
}

.home-image-preview[open] {
  display: flex;
  align-items: center;
  justify-content: center;
}

.home-image-preview::backdrop {
  background: rgb(0 0 0 / 85%);
}

.home-image-preview img {
  width: auto;
  max-width: 100%;
  height: auto;
  max-height: 100%;
  object-fit: contain;
}

.home-image-preview-close {
  position: absolute;
  top: 12px;
  right: 20px;
  width: 40px;
  height: 40px;
  padding: 0;
  border: 1px solid rgb(255 255 255 / 50%);
  border-radius: 50%;
  background: #202630;
  color: #fff;
  font-size: 30px;
  line-height: 1;
  cursor: pointer;
}

.home-image-preview-close:hover,
.home-image-preview-close:focus-visible {
  background: #384353;
  outline: 2px solid #21d789;
  outline-offset: 3px;
}
</style>
