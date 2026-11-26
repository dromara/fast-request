import assert from "node:assert/strict";
import { readFileSync } from "node:fs";
import test from "node:test";
import { createMarkdown } from "vuepress/markdown";
import {
  getScriptLinkHref,
  scriptLinkGroups,
} from "../docs/.vuepress/components/ui-guide/script-links.js";

test("script help links resolve to existing sections in both documentation locales", () => {
  const markdown = createMarkdown();
  for (const lang of ["zh", "en"]) {
    for (const link of scriptLinkGroups.flatMap((group) => group.links)) {
      const [path, anchor] = getScriptLinkHref(link, lang).split("#");
      const file = new URL(
        `../docs${path.replace(/\.html$/, ".md")}`,
        import.meta.url,
      );
      const content = readFileSync(file, "utf8");
      if (anchor) {
        const env = {};
        markdown.render(content, env);
        assert.ok(
          env.sfcBlocks.template.contentStripped.includes(`id="${anchor}"`),
          `${lang}: ${path}#${anchor}`,
        );
      }
      assert.ok(link.label[lang === "en" ? 1 : 0]);
    }
  }
});

test("script links default to the Chinese script page and preserve locale", () => {
  assert.equal(getScriptLinkHref({}), "/guide/script.html");
  assert.equal(getScriptLinkHref({}, "en"), "/en/guide/script.html");
  assert.equal(
    getScriptLinkHref({ page: "features/environment" }, "en"),
    "/en/guide/features/environment.html",
  );
});
