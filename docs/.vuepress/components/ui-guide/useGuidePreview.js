import { onScopeDispose, ref } from "vue";

export function useGuidePreview(initial) {
  const selected = ref(initial);
  const pinned = ref(false);
  let timer;

  function cancelPreview() {
    clearTimeout(timer);
    timer = undefined;
  }

  function preview(item, event) {
    cancelPreview();
    if (pinned.value) return;
    if (event.type === "focus") {
      selected.value = item;
      return;
    }

    // Capture the element while the event is being dispatched. A quick pass
    // over another button on the way to the inspector must not replace it.
    const target = event.currentTarget;
    timer = setTimeout(() => {
      timer = undefined;
      if (target.matches(":hover")) selected.value = item;
    }, 300);
  }

  function togglePin() {
    cancelPreview();
    pinned.value = !pinned.value;
  }

  onScopeDispose(cancelPreview);
  return { selected, pinned, preview, cancelPreview, togglePin };
}
