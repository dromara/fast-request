<script setup lang="ts">
import { onMounted, onUnmounted, ref, useId } from "vue";

const gradientId = `premium-border-${useId()}`;
const border = ref<HTMLDivElement | null>(null);
const animated = ref(false);
const highlighted = ref(false);
let cleanup: (() => void) | null = null;

onMounted(() => {
  const decoration = border.value;
  const card = decoration?.parentElement;
  if (!decoration || !card) return;

  const motionQuery = window.matchMedia("(prefers-reduced-motion: reduce)");
  const pointerQuery = window.matchMedia("(hover: hover) and (pointer: fine)");
  let inView = false;
  let frame = 0;
  let pointer: { x: number; y: number } | null = null;
  let focused: HTMLElement | null = null;

  const paint = () => {
    frame = 0;
    if (document.hidden || motionQuery.matches) return;
    let point = pointer;
    if (!point && focused) {
      const rect = focused.getBoundingClientRect();
      point = { x: rect.left + rect.width / 2, y: rect.top + rect.height / 2 };
    }
    highlighted.value = !!point;
    if (!point) return;
    const rect = card.getBoundingClientRect();
    decoration.style.setProperty("--spotlight-x", `${point.x - rect.left}px`);
    decoration.style.setProperty("--spotlight-y", `${point.y - rect.top}px`);
  };

  const schedulePaint = () => {
    if (!frame && !document.hidden && !motionQuery.matches) {
      frame = window.requestAnimationFrame(paint);
    }
  };

  const syncMotion = () => {
    animated.value = inView && !document.hidden && !motionQuery.matches;
    if (!animated.value) {
      window.cancelAnimationFrame(frame);
      frame = 0;
      highlighted.value = false;
    } else if (pointer || focused) {
      schedulePaint();
    }
  };

  const onPointer = (event: PointerEvent) => {
    if (!pointerQuery.matches || event.pointerType === "touch") return;
    pointer = { x: event.clientX, y: event.clientY };
    schedulePaint();
  };

  const onLeave = () => {
    pointer = null;
    if (!focused) highlighted.value = false;
    else schedulePaint();
  };

  const onFocus = (event: FocusEvent) => {
    focused = event.target instanceof HTMLElement ? event.target : null;
    schedulePaint();
  };

  const onBlur = (event: FocusEvent) => {
    if (
      event.relatedTarget instanceof Node &&
      card.contains(event.relatedTarget)
    )
      return;
    focused = null;
    if (!pointer) highlighted.value = false;
  };

  const onScroll = () => {
    if (pointer || focused) schedulePaint();
  };

  const observer = new IntersectionObserver(([entry]) => {
    inView = entry.isIntersecting;
    syncMotion();
  });
  observer.observe(card);
  motionQuery.addEventListener("change", syncMotion);
  document.addEventListener("visibilitychange", syncMotion);
  card.addEventListener("pointerenter", onPointer, { passive: true });
  card.addEventListener("pointermove", onPointer, { passive: true });
  card.addEventListener("pointerleave", onLeave);
  card.addEventListener("focusin", onFocus);
  card.addEventListener("focusout", onBlur);
  window.addEventListener("scroll", onScroll, { passive: true, capture: true });
  window.addEventListener("resize", onScroll, { passive: true });

  cleanup = () => {
    window.cancelAnimationFrame(frame);
    observer.disconnect();
    motionQuery.removeEventListener("change", syncMotion);
    document.removeEventListener("visibilitychange", syncMotion);
    card.removeEventListener("pointerenter", onPointer);
    card.removeEventListener("pointermove", onPointer);
    card.removeEventListener("pointerleave", onLeave);
    card.removeEventListener("focusin", onFocus);
    card.removeEventListener("focusout", onBlur);
    window.removeEventListener("scroll", onScroll, true);
    window.removeEventListener("resize", onScroll);
  };
});

onUnmounted(() => cleanup?.());
</script>

<template>
  <div
    ref="border"
    class="premium-border"
    :class="{ 'is-animated': animated, 'is-highlighted': highlighted }"
    aria-hidden="true"
  >
    <span class="premium-border-surface" />
    <svg class="premium-border-outline" focusable="false">
      <defs>
        <linearGradient :id="gradientId" x1="0" y1="0" x2="1" y2="1">
          <stop offset="0%" stop-color="#21d789" />
          <stop offset="45%" stop-color="#07c3f2" />
          <stop offset="100%" stop-color="#4079ff" />
        </linearGradient>
      </defs>
      <g :stroke="`url(#${gradientId})`">
        <rect
          class="premium-border-ambient"
          width="100%"
          height="100%"
          rx="8"
        />
        <rect class="premium-border-track" width="100%" height="100%" rx="8" />
        <rect
          class="premium-border-orbit premium-border-glow"
          width="100%"
          height="100%"
          rx="8"
          pathLength="1000"
        />
        <rect
          class="premium-border-orbit premium-border-trail"
          width="100%"
          height="100%"
          rx="8"
          pathLength="1000"
        />
        <rect
          class="premium-border-orbit premium-border-core"
          width="100%"
          height="100%"
          rx="8"
          pathLength="1000"
        />
      </g>
      <rect
        class="premium-border-orbit premium-border-head"
        width="100%"
        height="100%"
        rx="8"
        pathLength="1000"
      />
    </svg>
    <span class="premium-border-spotlight" />
  </div>
</template>

<style scoped>
.premium-border {
  position: absolute;
  inset: 0;
  border-radius: inherit;
  pointer-events: none;
}

.premium-border-outline,
.premium-border-surface,
.premium-border-spotlight {
  position: absolute;
  inset: 0;
  width: 100%;
  height: 100%;
  border-radius: inherit;
}

.premium-border-outline {
  z-index: 1;
  overflow: visible;
}

.premium-border-outline rect {
  fill: none;
  vector-effect: non-scaling-stroke;
}

.premium-border-ambient {
  stroke-width: 8;
  opacity: 0.08;
}

.premium-border-track {
  stroke-width: 2;
  opacity: 0.65;
}

.premium-border-orbit {
  stroke-linecap: round;
  stroke-dasharray: 120 380;
  animation: premium-border-orbit 10s linear infinite;
  animation-play-state: paused;
}

.is-animated .premium-border-orbit {
  animation-play-state: running;
}

.premium-border-glow {
  stroke-width: 7;
  opacity: 0.2;
}

.premium-border-trail {
  stroke-width: 2;
  opacity: 0.65;
}

.premium-border-core {
  stroke-width: 2;
  stroke-dasharray: 55 445;
  opacity: 0.95;
}

.premium-border-head {
  stroke: #c5fff0;
  stroke-width: 2;
  stroke-dasharray: 2 498;
  opacity: 0.85;
}

.premium-border-surface,
.premium-border-spotlight {
  opacity: 0;
  transition: opacity 250ms ease;
}

.premium-border-surface {
  z-index: -1;
  background: radial-gradient(
    circle 300px at var(--spotlight-x, 50%) var(--spotlight-y, 0px),
    rgb(33 215 137 / 8%),
    rgb(7 195 242 / 3%) 45%,
    transparent 75%
  );
}

.premium-border-spotlight {
  z-index: 2;
  box-sizing: border-box;
  padding: 2px;
  background: radial-gradient(
    circle 220px at var(--spotlight-x, 50%) var(--spotlight-y, 0px),
    rgb(179 255 234 / 85%),
    rgb(33 215 137 / 40%) 30%,
    transparent 70%
  );
  -webkit-mask:
    linear-gradient(#fff 0 0) content-box,
    linear-gradient(#fff 0 0);
  mask:
    linear-gradient(#fff 0 0) content-box,
    linear-gradient(#fff 0 0);
  -webkit-mask-composite: xor;
  mask-composite: exclude;
}

.is-highlighted .premium-border-surface,
.is-highlighted .premium-border-spotlight {
  opacity: 1;
}

@keyframes premium-border-orbit {
  to {
    stroke-dashoffset: 1000;
  }
}

@media (prefers-reduced-motion: reduce) {
  .premium-border-orbit,
  .premium-border-surface,
  .premium-border-spotlight {
    display: none;
    animation: none;
    transition: none;
  }
}
</style>
