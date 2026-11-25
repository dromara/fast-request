export function closeOutsideGuideDropdowns(root, target) {
  for (const dropdown of root?.querySelectorAll(".guide-dropdown[open]") ??
    []) {
    if (!dropdown.contains(target)) dropdown.open = false;
  }
}
