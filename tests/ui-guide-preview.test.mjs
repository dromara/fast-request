import assert from "node:assert/strict";
import test from "node:test";
import { effectScope } from "vue";
import { useGuidePreview } from "../docs/.vuepress/components/ui-guide/useGuidePreview.js";

function setup(t) {
  t.mock.timers.enable({ apis: ["setTimeout"] });
  const scope = effectScope();
  t.after(() => scope.stop());
  const state = scope.run(() => useGuidePreview({ id: "document" }));
  const hover = (id, isHovered = () => true) =>
    state.preview(
      { id },
      {
        type: "mouseenter",
        currentTarget: { matches: isHovered },
      },
    );
  const focus = (id) => state.preview({ id }, { type: "focus" });
  return { ...state, hover, focus, scope };
}

test("waits for deliberate hover and ignores a button already left", (t) => {
  const state = setup(t);
  state.hover("save");
  t.mock.timers.tick(299);
  assert.equal(state.selected.value.id, "document");
  t.mock.timers.tick(1);
  assert.equal(state.selected.value.id, "save");
  state.hover("clear", () => false);
  t.mock.timers.tick(300);
  assert.equal(state.selected.value.id, "save");
});

test("quick movement and leaving the workbench do not replace the chosen feature", (t) => {
  const state = setup(t);
  state.hover("save");
  t.mock.timers.tick(200);
  state.hover("clear");
  t.mock.timers.tick(100);
  assert.equal(state.selected.value.id, "document");
  state.cancelPreview();
  t.mock.timers.tick(500);
  assert.equal(state.selected.value.id, "document");
});

test("pinning freezes both hover and focus; unpinning resumes exploration", (t) => {
  const state = setup(t);
  state.focus("save");
  state.hover("clear");
  state.togglePin();
  assert.equal(state.pinned.value, true);
  t.mock.timers.tick(500);
  state.hover("apis");
  state.focus("run");
  t.mock.timers.tick(500);
  assert.equal(state.selected.value.id, "save");
  state.togglePin();
  assert.equal(state.pinned.value, false);
  state.hover("apis");
  t.mock.timers.tick(300);
  assert.equal(state.selected.value.id, "apis");
});

test("keyboard focus updates immediately and cancels a pending hover", (t) => {
  const state = setup(t);
  state.hover("clear");
  state.focus("save");
  assert.equal(state.selected.value.id, "save");
  t.mock.timers.tick(500);
  assert.equal(state.selected.value.id, "save");
});

test("unmounting clears pending work and each guide has independent state", (t) => {
  const state = setup(t);
  state.hover("clear");
  state.scope.stop();
  t.mock.timers.tick(500);
  assert.equal(state.selected.value.id, "document");
  const otherScope = effectScope();
  const other = otherScope.run(() => useGuidePreview({ id: "apis" }));
  other.togglePin();
  assert.equal(state.pinned.value, false);
  assert.equal(other.pinned.value, true);
  otherScope.stop();
});
