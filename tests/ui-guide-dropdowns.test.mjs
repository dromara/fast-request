import assert from "node:assert/strict";
import test from "node:test";
import { closeOutsideGuideDropdowns } from "../docs/.vuepress/components/ui-guide/dropdowns.js";

function dropdown(children) {
  return { open: true, contains: (target) => children.includes(target) };
}

test("clicking outside closes every open guide dropdown", () => {
  const menus = [dropdown([]), dropdown([])];
  closeOutsideGuideDropdowns({ querySelectorAll: () => menus }, {});
  assert.ok(menus.every((menu) => !menu.open));
});

test("clicking a menu item or its summary preserves that menu and closes others", () => {
  for (const target of ["summary", "item", "icon"]) {
    const current = dropdown(["summary", "item", "icon"]);
    const other = dropdown([]);
    closeOutsideGuideDropdowns(
      { querySelectorAll: () => [current, other] },
      target,
    );
    assert.equal(current.open, true);
    assert.equal(other.open, false);
  }
});

test("missing roots and an empty set of open menus are harmless", () => {
  assert.doesNotThrow(() => closeOutsideGuideDropdowns(null, {}));
  assert.doesNotThrow(() =>
    closeOutsideGuideDropdowns({ querySelectorAll: () => [] }, {}),
  );
});
