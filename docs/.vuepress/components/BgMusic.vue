<template>
  <button
      class="sound-toggle"
      :class="{ 'is-playing': isPlaying }"
      @click="toggleSound"
      aria-label="Toggle sound"
      :aria-pressed="isPlaying"
  >
    <span class="sound-toggle__bg"></span>
    <span class="sound-toggle__icon">
      <span class="bar"></span>
      <span class="bar"></span>
      <span class="bar"></span>
    </span>
  </button>

  <!-- 背景音乐：去掉 autoplay，交给 JS 控制 -->
  <audio
      ref="audio"
      src="/sound/generic.ogg"
      loop
      preload="auto"
  ></audio>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from "vue";

const audio = ref(null);
const isPlaying = ref(false);

async function playMusic() {
  if (!audio.value) return;

  try {
    await audio.value.play();
    isPlaying.value = true;
  } catch (err) {
    console.warn("无法播放音频：", err);
    isPlaying.value = false;
  }
}

function pauseMusic() {
  if (!audio.value) return;
  audio.value.pause();
  isPlaying.value = false;
}

function toggleSound() {
  if (isPlaying.value) {
    pauseMusic();
  } else {
    playMusic();
  }
}

// ---------- 关键逻辑：第一次用户交互时自动播放 ----------
let firstInteractionHandler = null;

onMounted(() => {
  // 只在客户端执行（VuePress SSR 下更安全）
  if (typeof window === "undefined") return;

  firstInteractionHandler = () => {
    // 用户第一次交互时尝试播放音乐
    playMusic();
    // 移除监听，避免重复触发
    window.removeEventListener("click", firstInteractionHandler);
    window.removeEventListener("touchstart", firstInteractionHandler);
    window.removeEventListener("keydown", firstInteractionHandler);
  };

  // 监听任意一种用户交互
  window.addEventListener("click", firstInteractionHandler, { once: true });
  window.addEventListener("touchstart", firstInteractionHandler, { once: true });
  window.addEventListener("keydown", firstInteractionHandler, { once: true });
});

onBeforeUnmount(() => {
  if (!firstInteractionHandler) return;
  window.removeEventListener("click", firstInteractionHandler);
  window.removeEventListener("touchstart", firstInteractionHandler);
  window.removeEventListener("keydown", firstInteractionHandler);
});
</script>

<style scoped>
.sound-toggle {
  position: relative;
  width: 2rem;
  height: 2rem;
  border-radius: 50%;
  border: none;
  padding: 0;
  background: transparent;
  cursor: pointer;
  outline: none;
}

.sound-toggle__bg {
  position: absolute;
  inset: 0;
  border-radius: 50%;
  background: rgba(255, 255, 255, 0.9);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.06);
  backdrop-filter: blur(10px);
  transition: transform 0.25s ease, box-shadow 0.25s ease, background 0.25s ease;
}

.sound-toggle__icon {
  position: relative;
  z-index: 1;
  width: 18px;
  height: 14px;
  margin: auto;
  inset: 0;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.sound-toggle__icon .bar {
  width: 3px;
  border-radius: 999px;
  background: #111;
  height: 2px;
  transition: height 0.25s ease;
}

.sound-toggle:hover .sound-toggle__bg {
  transform: scale(1.06);
  box-shadow: 0 10px 26px rgba(0, 0, 0, 0.1);
}

.sound-toggle.is-playing .sound-toggle__bg {
  background: rgba(255, 255, 255, 1);
  box-shadow: 0 12px 28px rgba(0, 0, 0, 0.18);
}

.sound-toggle.is-playing .sound-toggle__icon .bar {
  animation-duration: 0.5s;
  animation-timing-function: ease-in-out;
  animation-iteration-count: infinite;
  animation-direction: alternate;
}

.sound-toggle.is-playing .sound-toggle__icon .bar:nth-child(1) {
  animation-name: barBounce1;
}

.sound-toggle.is-playing .sound-toggle__icon .bar:nth-child(2) {
  animation-name: barBounce2;
}

.sound-toggle.is-playing .sound-toggle__icon .bar:nth-child(3) {
  animation-name: barBounce3;
}

@keyframes barBounce1 {
  from {
    height: 4px;
  }
  to {
    height: 12px;
  }
}

@keyframes barBounce2 {
  from {
    height: 2px;
  }
  to {
    height: 10px;
  }
}

@keyframes barBounce3 {
  from {
    height: 6px;
  }
  to {
    height: 14px;
  }
}
</style>
