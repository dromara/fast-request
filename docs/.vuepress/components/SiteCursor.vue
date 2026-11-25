<script setup lang="ts">
import { onMounted, onUnmounted, ref } from "vue";
import GlowCursor from "./glow-cursor/GlowCursor.vue";
import { useCursorPreference } from "../composables/useCursorPreference.js";

const { enabled, restore } = useCursorPreference();
const motionAllowed = ref(false);
let motionQuery: MediaQueryList | null = null;

function syncMotion() {
  motionAllowed.value = motionQuery?.matches ?? false;
}

onMounted(() => {
  restore();
  motionQuery = window.matchMedia(
    "(hover: hover) and (pointer: fine) and (prefers-reduced-motion: no-preference)",
  );
  syncMotion();
  motionQuery.addEventListener("change", syncMotion);
});

onUnmounted(() => motionQuery?.removeEventListener("change", syncMotion));
</script>

<template>
  <div v-if="enabled && motionAllowed" class="site-cursor" aria-hidden="true">
    <GlowCursor
      color="#ffffff"
      secondary-color="#9b9b9b"
      :intensity="0.9"
      :trail-width="3"
      :linger="2.6"
      :glow="0.6"
      :hotspot="0.4"
      :follow-speed="0.6"
      :grain="0"
      :click-burst="true"
      quality="high"
      :max-device-pixel-ratio="1.5"
    />
  </div>
</template>

<style scoped>
.site-cursor {
  position: fixed;
  z-index: 100;
  inset: 0;
  overflow: hidden;
  pointer-events: none;
  contain: strict;
}
</style>
