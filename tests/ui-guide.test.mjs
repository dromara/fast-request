import assert from "node:assert/strict";
import { existsSync, readFileSync } from "node:fs";
import test from "node:test";
import { createMarkdown } from "vuepress/markdown";
import {
  getGuideHref,
  groupGuideFeatures,
  guideCategories,
  guideFeatures,
  guideLayout,
  searchGuideFeatures,
} from "../docs/.vuepress/components/ui-guide/features.js";

const docs = new URL("../docs/", import.meta.url);
const byId = Object.fromEntries(guideFeatures.map((item) => [item.id, item]));
const markdown = createMarkdown();
const headerSlugs = (headers) =>
  headers.flatMap((header) => [header.slug, ...headerSlugs(header.children)]);

test("every guide entry points to existing bilingual documentation and a local icon", () => {
  assert.equal(
    new Set(guideFeatures.map((item) => item.id)).size,
    guideFeatures.length,
  );
  for (const item of guideFeatures) {
    for (const lang of ["zh", "en"]) {
      const href = getGuideHref(item, lang);
      const [path, hash] = href.split("#");
      const file = new URL(path.slice(1).replace(/\.html$/, ".md"), docs);
      assert.ok(existsSync(file), `${item.id}: ${file.pathname}`);
      if (hash) {
        const env = {};
        markdown.render(readFileSync(file, "utf8"), env);
        assert.ok(
          headerSlugs(env.headers).includes(hash),
          `${item.id}: missing anchor ${hash}`,
        );
      }
      assert.ok(item.label[lang === "en" ? 1 : 0]);
      assert.ok(item.description[lang === "en" ? 1 : 0]);
    }
    assert.ok(
      existsSync(
        new URL(`.vuepress/public/img/ui-guide/${item.icon}.svg`, docs),
      ),
      `Missing icon: ${item.icon}`,
    );
  }
  assert.ok(
    existsSync(
      new URL(".vuepress/public/img/ui-guide/runWithCoverage.svg", docs),
    ),
  );
});

test("every layout reference resolves to a documented feature", () => {
  for (const id of Object.values(guideLayout).flat(Infinity)) {
    assert.ok(byId[id], `Unknown layout feature: ${id}`);
  }
});

test("all configurable Pro toolbar actions have a visible guide entry", () => {
  // Snapshot of CustomMainToolbarView and FastRequestConfiguration action IDs.
  // Include actions regardless of their default enabled/hidden configuration.
  const actions = JSON.parse(
    readFileSync(
      new URL("./fixtures/ui-guide-toolbar-actions.json", import.meta.url),
      "utf8",
    ),
  );
  const toolbar = new Set(guideLayout.toolbarGroups.flat(Infinity));
  for (const [action, id] of Object.entries(actions)) {
    assert.ok(toolbar.has(id), `Missing configurable action: ${action}`);
  }
});

test("the guide covers active feature documents and excludes deprecated entries", () => {
  // Preserve coverage of the feature lists replaced by the interface guide.
  const featureDocs = JSON.parse(
    readFileSync(
      new URL("./fixtures/ui-guide-feature-docs.json", import.meta.url),
      "utf8",
    ),
  );
  const deprecatedDocs = [
    "features/apiToPostman.md",
    "features/apiImportExport.md",
  ];
  for (const lang of ["zh", "en"]) {
    const prefix = lang === "en" ? "en/" : "";
    const guideDir = new URL(`${prefix}guide/`, docs);
    const covered = new Set(
      guideFeatures.map((item) => {
        const path = getGuideHref(item, lang).split("#")[0];
        return new URL(path.slice(1).replace(/\.html$/, ".md"), docs).pathname;
      }),
    );
    const excluded = new Set(
      deprecatedDocs.map((file) => new URL(file, guideDir).pathname),
    );
    for (const path of excluded) {
      assert.ok(!covered.has(path), `Deprecated guide entry: ${path}`);
    }
    assert.ok(featureDocs.length > 0, `No feature links found: ${lang}`);
    const missing = featureDocs
      .map((file) => new URL(file, guideDir).pathname)
      .filter((path) => !excluded.has(path) && !covered.has(path));
    assert.deepEqual(
      [...new Set(missing)],
      [],
      `${lang}: missing feature documents`,
    );
  }
});

test("additional features appear last without duplicating existing document cards", () => {
  const groups = groupGuideFeatures();
  const additional = groups.at(-1);
  assert.equal(additional.id, "additional");
  const existingDocs = new Set(
    groups
      .slice(0, -1)
      .flatMap((group) => group.items.map((item) => item.doc.split("#")[0])),
  );
  const additionalDocs = additional.items.map((item) => item.doc.split("#")[0]);
  assert.equal(new Set(additionalDocs).size, additionalDocs.length);
  for (const doc of additionalDocs) {
    assert.ok(!existingDocs.has(doc), `Duplicate document: ${doc}`);
  }
});

test("links stay within the selected documentation locale", () => {
  assert.equal(getGuideHref(byId.save), "/guide/features/saveRequest.html");
  assert.equal(
    getGuideHref(byId.save, "en"),
    "/en/guide/features/saveRequest.html",
  );
  assert.equal(
    getGuideHref(byId.methods),
    "/guide/features/runMethod.html#methods",
  );
  assert.equal(
    getGuideHref(byId.documentSettings),
    "/guide/features/onlineDocument.html#自动同步",
  );
  assert.equal(
    getGuideHref(byId.documentSettings, "en"),
    "/en/guide/features/onlineDocument.html#automatic-synchronization",
  );
});

test("search handles Chinese, English, whitespace, case and empty results", () => {
  assert.ok(searchGuideFeatures("保存").some((item) => item.id === "save"));
  assert.ok(
    searchGuideFeatures(" CURL ").some((item) => item.id === "curlImport"),
  );
  assert.ok(
    searchGuideFeatures("LAN sharing").some((item) => item.id === "document"),
  );
  assert.deepEqual(searchGuideFeatures("no-such-feature-987"), []);
  assert.deepEqual(searchGuideFeatures("   "), guideFeatures);
  assert.notEqual(searchGuideFeatures(""), guideFeatures);
});

test("categories contain every documented feature exactly once", () => {
  const ids = guideCategories.flatMap((category) => category.features);
  assert.equal(
    new Set(guideCategories.map((category) => category.id)).size,
    guideCategories.length,
  );
  assert.equal(ids.length, guideFeatures.length);
  assert.equal(new Set(ids).size, ids.length);
  assert.deepEqual(new Set(ids), new Set(guideFeatures.map((item) => item.id)));
  for (const category of guideCategories) {
    assert.ok(category.label[0] && category.label[1]);
  }
});

test("grouped search keeps matching categories and omits empty ones", () => {
  const groups = groupGuideFeatures(" CURL ");
  assert.deepEqual(
    groups.map((group) => group.id),
    ["integrations"],
  );
  assert.deepEqual(
    new Set(groups[0].items.map((item) => item.id)),
    new Set(["curlImport", "copyCurl"]),
  );
  assert.deepEqual(groupGuideFeatures("no-such-feature-987"), []);
  assert.deepEqual(groupGuideFeatures("   "), groupGuideFeatures());
  assert.equal(
    groupGuideFeatures().flatMap((group) => group.items).length,
    guideFeatures.length,
  );
  assert.equal(groupGuideFeatures("认证")[0].id, "headers");
});
