<script setup lang="ts">
import { computed, onMounted, onUnmounted, ref } from "vue";

const props = defineProps<{ lang?: string }>();

const steps = computed(() =>
  props.lang === "en"
    ? ["Start", "Pre-request script", "Send request", "Post-request script", "End"]
    : ["开始", "前置脚本", "发送请求", "后置脚本", "结束"],
);

const activeStep = ref(0);
let timer: ReturnType<typeof setTimeout> | undefined;

function advance() {
  activeStep.value = activeStep.value < steps.value.length ? activeStep.value + 1 : 0;
  timer = setTimeout(advance, activeStep.value === steps.value.length ? 2500 : 1500);
}

onMounted(() => {
  if (window.matchMedia("(prefers-reduced-motion: reduce)").matches) {
    activeStep.value = steps.value.length;
  } else {
    timer = setTimeout(advance, 1500);
  }
});

onUnmounted(() => clearTimeout(timer));
</script>

<template>
  <div class="script-pipeline" :aria-label="steps.join(' → ')">
    <ol class="pipeline-steps">
      <li
        v-for="(step, index) in steps"
        :key="step"
        class="pipeline-step"
      >
        <div class="pipeline-node" :class="{ 'is-active': index === activeStep, 'is-complete': index < activeStep }">
          <span class="pipeline-icon" aria-hidden="true">
            <svg v-if="index === 0" viewBox="0 0 24 24"><path d="m9 6 9 6-9 6z" /></svg>
            <svg v-else-if="index === 4" viewBox="0 0 24 24"><path d="m6 12 4 4 8-8" /></svg>
            <svg v-else-if="index === 2" viewBox="0 0 24 24"><path d="M4 12h15m-5-5 5 5-5 5" /></svg>
            <svg v-else viewBox="0 0 24 24"><path d="m9 7-5 5 5 5m6-10 5 5-5 5" /></svg>
          </span>
          <span class="pipeline-copy">
            <span class="pipeline-title">{{ step }}</span>
            <span class="pipeline-status">
              <span v-if="index < activeStep" class="pipeline-check" aria-hidden="true"><svg viewBox="0 0 24 24"><path d="m5 12 4 4L19 6" /></svg></span>
              {{ index < activeStep ? (lang === 'en' ? 'Completed' : '已完成') : index === activeStep ? (lang === 'en' ? 'Running' : '运行中') : (lang === 'en' ? 'Waiting' : '等待中') }}
            </span>
          </span>
        </div>
        <span v-if="index < steps.length - 1" class="pipeline-connector" :class="{ 'is-complete': index < activeStep }" aria-hidden="true" />
      </li>
    </ol>
  </div>
</template>

<style scoped>
@property --script-pipeline-beam-angle {
  syntax: "<angle>";
  initial-value: 0deg;
  inherits: false;
}

.script-pipeline {
  --pipeline-accent: #7257e8;
  --pipeline-line: #d9d7e8;
  margin: 1.5rem 0;
  padding: 1.25rem 0.75rem;
  border: 1px solid var(--vp-c-border, #e4e4e7);
  border-radius: 16px;
  background-color: var(--vp-c-bg-soft, #f8f8fb);
  background-image: radial-gradient(circle, rgba(120, 113, 150, 0.18) 1px, transparent 1px);
  background-size: 18px 18px;
}

.pipeline-steps {
  display: flex;
  align-items: stretch;
  margin: 0;
  padding: 0;
  list-style: none;
}

.pipeline-step {
  display: flex;
  flex: 1 1 0;
  align-items: stretch;
  min-width: 0;
  margin: 0;
}

.pipeline-step:last-child { flex: 0.8 1 0; }

.pipeline-node {
  position: relative;
  display: flex;
  flex: 1 1 0;
  flex-direction: row;
  align-items: center;
  gap: 0.45rem;
  min-width: 0;
  min-height: 62px;
  box-sizing: border-box;
  padding: 0.5rem;
  border: 1px solid var(--vp-c-border, #e4e4e7);
  border-radius: 12px;
  background: var(--vp-c-bg, #fff);
  box-shadow: 0 3px 10px rgba(30, 25, 60, 0.06);
  transition: border-color 0.25s, box-shadow 0.25s;
}

.pipeline-node.is-active {
  border-color: var(--pipeline-accent);
  box-shadow: 0 0 0 2px rgba(114, 87, 232, 0.13), 0 5px 18px rgba(114, 87, 232, 0.15);
}

.pipeline-node.is-active::after {
  position: absolute;
  inset: -1px;
  padding: 2px;
  border-radius: inherit;
  content: "";
  pointer-events: none;
  background: conic-gradient(from var(--script-pipeline-beam-angle), transparent 0deg, transparent 275deg, #a99aff 330deg, #e3ddff 350deg, transparent 360deg);
  -webkit-mask: linear-gradient(#fff 0 0) content-box, linear-gradient(#fff 0 0);
  -webkit-mask-composite: xor;
  mask: linear-gradient(#fff 0 0) content-box, linear-gradient(#fff 0 0);
  mask-composite: exclude;
  animation: script-pipeline-beam 1.5s linear infinite;
}

@keyframes script-pipeline-beam {
  to { --script-pipeline-beam-angle: 360deg; }
}

.pipeline-node.is-complete { border-color: rgba(114, 87, 232, 0.45); }

.pipeline-icon {
  position: relative;
  display: grid;
  flex: 0 0 28px;
  width: 28px;
  height: 28px;
  place-items: center;
  border-radius: 8px;
  color: var(--pipeline-accent);
  background: rgba(114, 87, 232, 0.1);
}

.pipeline-check {
  display: inline-grid;
  flex: 0 0 14px;
  width: 14px;
  height: 14px;
  place-items: center;
  border-radius: 50%;
  color: #102d1a;
  background: #5bd98a;
}

.pipeline-check svg { width: 11px; height: 11px; stroke-width: 3; }

.pipeline-copy { display: grid; min-width: 0; grid-template-rows: 2.08rem auto; align-content: center; gap: 3px; }

.pipeline-icon svg {
  width: 17px;
  height: 17px;
  fill: none;
  stroke: currentColor;
  stroke-linecap: round;
  stroke-linejoin: round;
  stroke-width: 1.8;
}

.pipeline-title {
  display: flex;
  align-items: end;
  color: var(--vp-c-text, #24222e);
  font-size: 0.8rem;
  font-weight: 600;
  line-height: 1.3;
  text-align: left;
  overflow-wrap: anywhere;
}

.pipeline-status { display: flex; align-items: center; gap: 4px; min-height: 14px; color: var(--vp-c-text-mute, #85838e); font-size: 0.7rem; line-height: 1.2; }
.is-complete .pipeline-status { color: #39a968; }

.pipeline-connector {
  position: relative;
  flex: 0 0 20px;
  align-self: center;
  height: 2px;
  overflow: hidden;
  background: var(--pipeline-line);
}

.pipeline-connector::after {
  position: absolute;
  inset: 0;
  content: "";
  background: var(--pipeline-accent);
  transform: scaleX(0);
  transform-origin: left;
  transition: transform 0.35s ease;
}

.pipeline-connector.is-complete::after { transform: scaleX(1); }

@media (max-width: 640px) {
  .pipeline-steps, .pipeline-step { flex-direction: column; }
  .pipeline-steps { align-items: center; }
  .pipeline-step, .pipeline-step:last-child { flex: auto; width: min(100%, 260px); }
  .pipeline-node { flex-direction: row; width: 100%; min-height: 60px; box-sizing: border-box; }
  .pipeline-connector { flex: 0 0 20px; width: 2px; height: 20px; }
  .pipeline-connector::after { transform: scaleY(0); transform-origin: top; }
  .pipeline-connector.is-complete::after { transform: scaleY(1); }
}

@media (prefers-reduced-motion: reduce) {
  .pipeline-node, .pipeline-connector::after { transition: none; }
  .pipeline-node.is-active::after { animation: none; }
}
</style>
