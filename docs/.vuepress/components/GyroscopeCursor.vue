<script setup lang="ts">
import { onMounted, onUnmounted, ref } from "vue";

const cursor = ref<HTMLElement | null>(null);
const visible = ref(false);
const ripples = ref<{ id: number; x: number; y: number }[]>([]);
let x = 0,
  y = 0,
  angle = -45,
  velocity = 0,
  target = -45;
let lastMove = 0,
  lastFrame = 0,
  frame = 0,
  nextId = 0;
let pressed = false,
  hovering = false;
const timers = new Set<ReturnType<typeof setTimeout>>();

function nativeTarget(target: EventTarget | null) {
  return (
    target instanceof Element &&
    !!target.closest(
      'input, textarea, select, [contenteditable]:not([contenteditable="false"]), [data-cursor="native"]',
    )
  );
}

function animate(time: number) {
  const dt = Math.min((time - (lastFrame || time)) / 1000, 0.032);
  lastFrame = time;
  if (time - lastMove > 450) target = -45;
  const difference = ((target - angle + 540) % 360) - 180;
  velocity += (difference * 120 - velocity * 18) * dt;
  angle += velocity * dt;
  if (cursor.value) {
    const scale = pressed ? 0.85 : hovering ? 1.15 : 1;
    cursor.value.style.transform = `translate3d(${x}px, ${y}px, 0) rotate(${angle}deg) scale(${scale})`;
  }
  if (
    Math.abs(difference) > 0.1 ||
    Math.abs(velocity) > 0.1 ||
    time - lastMove <= 450
  ) {
    frame = requestAnimationFrame(animate);
  } else {
    frame = 0;
    lastFrame = 0;
  }
}

function wake() {
  if (!frame) frame = requestAnimationFrame(animate);
}

function move(event: PointerEvent) {
  if (event.pointerType !== "mouse") return;
  const dx = event.clientX - x,
    dy = event.clientY - y;
  if (visible.value && Math.hypot(dx, dy) > 1) {
    target = (Math.atan2(dy, dx) * 180) / Math.PI + 90;
    lastMove = performance.now();
  }
  x = event.clientX;
  y = event.clientY;
  visible.value = !nativeTarget(event.target);
  hovering =
    event.target instanceof Element &&
    !!event.target.closest('a, button, [role="button"]');
  document.documentElement.classList.toggle(
    "gyroscope-cursor-active",
    visible.value,
  );
  wake();
}

function down(event: PointerEvent) {
  if (!visible.value || event.pointerType !== "mouse") return;
  pressed = true;
  wake();
  const id = nextId++;
  ripples.value.push({ id, x: event.clientX, y: event.clientY });
  const timer = setTimeout(() => {
    ripples.value = ripples.value.filter((ripple) => ripple.id !== id);
    timers.delete(timer);
  }, 600);
  timers.add(timer);
}
function up() {
  pressed = false;
  wake();
}
function leave() {
  visible.value = false;
  document.documentElement.classList.remove("gyroscope-cursor-active");
}

onMounted(() => {
  window.addEventListener("pointermove", move);
  window.addEventListener("pointerdown", down);
  window.addEventListener("pointerup", up);
  window.addEventListener("blur", leave);
  document.documentElement.addEventListener("pointerleave", leave);
});
onUnmounted(() => {
  window.removeEventListener("pointermove", move);
  window.removeEventListener("pointerdown", down);
  window.removeEventListener("pointerup", up);
  window.removeEventListener("blur", leave);
  document.documentElement.removeEventListener("pointerleave", leave);
  document.documentElement.classList.remove("gyroscope-cursor-active");
  cancelAnimationFrame(frame);
  timers.forEach(clearTimeout);
});
</script>

<template>
  <div
    v-for="ripple in ripples"
    :key="ripple.id"
    class="gyroscope-ripple"
    :style="{ left: `${ripple.x}px`, top: `${ripple.y}px` }"
  />
  <div ref="cursor" class="gyroscope-cursor" :class="{ 'is-visible': visible }">
    <svg width="32.5" height="32.5" viewBox="-13 -3 26 26" aria-hidden="true">
      <path
        d="M0 0 L9 20 L0 17 L-9 20 Z"
        fill="#8901ff"
        stroke="white"
        stroke-width="3"
        stroke-linejoin="round"
      />
    </svg>
  </div>
</template>

<style>
html.gyroscope-cursor-active,
html.gyroscope-cursor-active * {
  cursor: none !important;
}
</style>
<style scoped>
.gyroscope-cursor {
  position: fixed;
  left: 0;
  top: 0;
  width: 0;
  height: 0;
  opacity: 0;
  transform-origin: 0 0;
  will-change: transform;
}
.gyroscope-cursor.is-visible {
  opacity: 1;
}
.gyroscope-cursor svg {
  position: absolute;
  left: -16.25px;
  top: -3.75px;
  filter: drop-shadow(0 2px 3px rgb(0 0 0 / 0.4));
}
.gyroscope-ripple {
  position: fixed;
  width: 12px;
  height: 12px;
  margin: -6px;
  border: 2px solid #ffffff;
  border-radius: 50%;
  animation: ripple 0.6s ease-out forwards;
}
@keyframes ripple {
  from {
    transform: scale(0.4);
    opacity: 0.8;
  }
  to {
    transform: scale(3);
    opacity: 0;
  }
}
</style>
