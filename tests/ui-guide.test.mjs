import assert from "node:assert/strict";
import { existsSync, readFileSync } from "node:fs";
import test from "node:test";
import {
  getGuideHref,
  guideFeatures,
  guideLayout,
  searchGuideFeatures,
} from "../docs/.vuepress/components/ui-guide/features.js";

const docs = new URL("../docs/", import.meta.url);
const byId = Object.fromEntries(guideFeatures.map((item) => [item.id, item]));

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
        const headings = [
          ...readFileSync(file, "utf8").matchAll(/^##+ (.+)$/gm),
        ];
        assert.ok(
          headings.some(
            ([, heading]) =>
              heading.toLowerCase().replaceAll(" ", "-") === hash,
          ),
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
