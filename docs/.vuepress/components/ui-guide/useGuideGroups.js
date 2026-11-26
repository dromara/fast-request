import { computed, ref, watch } from "vue";

export function useGuideGroups(query, groups) {
  const collapsedGroups = ref(new Set());
  const allExpanded = computed(() =>
    groups.value.every((group) => !collapsedGroups.value.has(group.id)),
  );
  const allCollapsed = computed(() =>
    groups.value.every((group) => collapsedGroups.value.has(group.id)),
  );

  function setGroupOpen(id, open) {
    if (open) collapsedGroups.value.delete(id);
    else collapsedGroups.value.add(id);
  }

  function setAllGroupsOpen(open) {
    collapsedGroups.value = new Set(
      open ? [] : groups.value.map((group) => group.id),
    );
  }

  watch(query, () => setAllGroupsOpen(true));

  return {
    collapsedGroups,
    allExpanded,
    allCollapsed,
    setGroupOpen,
    setAllGroupsOpen,
  };
}
