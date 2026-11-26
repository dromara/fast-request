import assert from "node:assert/strict";
import test from "node:test";
import { computed, effectScope, nextTick, ref } from "vue";
import { useGuideGroups } from "../docs/.vuepress/components/ui-guide/useGuideGroups.js";
import { groupGuideFeatures } from "../docs/.vuepress/components/ui-guide/features.js";

function setup(t) {
  const scope = effectScope();
  t.after(() => scope.stop());
  const query = ref("");
  const groups = computed(() => groupGuideFeatures(query.value));
  const state = scope.run(() => useGuideGroups(query, groups));
  return { ...state, query, groups };
}

test("categories start expanded and can be toggled independently", (t) => {
  const state = setup(t);
  assert.equal(state.allExpanded.value, true);
  assert.equal(state.allCollapsed.value, false);
  state.setGroupOpen("debugging", false);
  assert.equal(state.allExpanded.value, false);
  assert.equal(state.allCollapsed.value, false);
  assert.deepEqual([...state.collapsedGroups.value], ["debugging"]);
  state.setGroupOpen("debugging", true);
  assert.equal(state.allExpanded.value, true);
});

test("bulk actions collapse and expand every displayed category", (t) => {
  const state = setup(t);
  state.setAllGroupsOpen(false);
  assert.equal(state.allCollapsed.value, true);
  assert.equal(state.allExpanded.value, false);
  assert.equal(state.collapsedGroups.value.size, state.groups.value.length);
  state.setGroupOpen("headers", true);
  assert.equal(state.allCollapsed.value, false);
  state.setAllGroupsOpen(true);
  assert.equal(state.allExpanded.value, true);
  assert.equal(state.collapsedGroups.value.size, 0);
});

test("search reveals matching categories and clearing it reveals the full list", async (t) => {
  const state = setup(t);
  state.setAllGroupsOpen(false);
  state.query.value = "cURL";
  await nextTick();
  assert.equal(state.groups.value.length, 1);
  assert.equal(state.groups.value[0].id, "integrations");
  assert.equal(state.allExpanded.value, true);
  state.setAllGroupsOpen(false);
  assert.deepEqual([...state.collapsedGroups.value], ["integrations"]);
  state.query.value = "";
  await nextTick();
  assert.equal(state.allExpanded.value, true);
  assert.equal(state.collapsedGroups.value.size, 0);
});

test("empty search results tolerate bulk actions and recover on the next search", async (t) => {
  const state = setup(t);
  state.query.value = "no-such-feature-987";
  await nextTick();
  state.setAllGroupsOpen(false);
  assert.equal(state.groups.value.length, 0);
  assert.equal(state.collapsedGroups.value.size, 0);
  state.setAllGroupsOpen(true);
  state.query.value = "JSON";
  await nextTick();
  assert.ok(state.groups.value.length > 0);
  assert.equal(state.allExpanded.value, true);
});

test("separate guide instances keep independent expansion state", (t) => {
  const first = setup(t);
  const second = setup(t);
  first.setAllGroupsOpen(false);
  assert.equal(first.allCollapsed.value, true);
  assert.equal(second.allExpanded.value, true);
});
