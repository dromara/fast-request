import assert from "node:assert/strict";
import { existsSync } from "node:fs";
import test from "node:test";
import { getGuideHref } from "../docs/.vuepress/components/ui-guide/features.js";
import {
  filterPanelTree,
  runMethodScriptActions,
  windowPanelActions,
  windowPanelToggles,
} from "../docs/.vuepress/components/ui-guide/window-panels.js";

const docs = new URL("../docs/", import.meta.url);

test("all four tool windows expose labeled actions with original local icons", () => {
  assert.deepEqual(Object.keys(windowPanelActions), ["apis", "nav", "run", "methods"]);
  for (const actions of [...Object.values(windowPanelActions), runMethodScriptActions]) {
    const flattened = actions.flat();
    assert.equal(new Set(flattened.map((action) => action.id)).size, flattened.length);
    for (const action of flattened) {
      assert.ok(action.label[0] && action.label[1], action.id);
      assert.ok(action.description[0] && action.description[1], action.id);
      assert.ok(
        existsSync(new URL(`.vuepress/public/img/ui-guide/${action.icon}.svg`, docs)),
        `Missing icon: ${action.icon}`,
      );
    }
  }
  for (const icon of ["folder", "module"]) {
    assert.ok(existsSync(new URL(`.vuepress/public/img/ui-guide/${icon}.svg`, docs)));
  }
});

test("documented panel actions use existing pages in the active locale", () => {
  const actions = Object.values(windowPanelActions).flat(2);
  for (const action of actions) {
    if (!action.doc) {
      assert.equal(action.local, true, `Undocumented action must stay local: ${action.id}`);
      continue;
    }
    for (const lang of ["zh", "en"]) {
      const href = getGuideHref(action, lang);
      assert.ok(href.startsWith(lang === "en" ? "/en/guide/" : "/guide/"));
      const file = new URL(href.slice(1).split("#")[0].replace(/\.html$/, ".md"), docs);
      assert.ok(existsSync(file), `${action.id}: ${file.pathname}`);
    }
  }
});

test("API list toggles refer to local actions rather than document links", () => {
  for (const id of windowPanelToggles) {
    const action = windowPanelActions.apis.find((item) => item.id === id);
    assert.ok(action, id);
    assert.equal(action.local, true);
    assert.equal(action.doc, "");
  }
});

test("tree filtering supports names, mixed case, whitespace and empty results", () => {
  const labels = ["Saved Apis", "Default Group", "保存的方法"];
  assert.equal(filterPanelTree("", labels), true);
  assert.equal(filterPanelTree("   ", []), true);
  assert.equal(filterPanelTree("  DEFAULT  ", labels), true);
  assert.equal(filterPanelTree("apis", labels), true);
  assert.equal(filterPanelTree("方法", labels), true);
  assert.equal(filterPanelTree("no-such-api", labels), false);
  assert.equal(filterPanelTree("api", []), false);
});
