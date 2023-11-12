import { getDirname, path } from "@vuepress/utils";
import { hopeTheme } from "vuepress-theme-hope";
import { enNavbar, zhNavbar } from "./navbar.js";
import { enSidebar, zhSidebar } from "./sidebar.js";

const __dirname = getDirname(import.meta.url);

export default hopeTheme(
  {
    logo: "/img/fastRequest.svg",

    repo: "https://github.com/dromara/fast-request",
    docsDir: "docs",
    docsBranch: "master",

    copyright: "Copyright © 2021-present Kings",
    displayFooter: true,

    pageInfo: false,

    darkmode: "enable",

    iconAssets: "//at.alicdn.com/t/c/font_2601581_dhvis4jtn7m.css",

    locales: {
      "/": {
        footer:
          "主题使用 <a target='blank' href='https://theme-hope.vuejs.press/zh/'>vuepress-theme-hope</a>",

        navbar: zhNavbar,
        sidebar: zhSidebar,
      },

      "/en/": {
        navbar: enNavbar,
        sidebar: enSidebar,
        footer:
          "Theme by <a target='blank' href='https://theme-hope.vuejs.press/'>vuepress-theme-hope</a>",
      },
    },

    plugins: {
      comment: {
        provider: "Giscus",
        repo: "kings1990/giscus-fastrequest",
        repoId: "R_kgDOHLlUsg",
        category: "fastRequest",
        categoryId: "DIC_kwDOHLlUss4COlsW",
      },

      components: {
        components: ["Badge", "BiliBili", "SiteInfo"],
        rootComponents: {
          addThis: "ra-617a48e0d1826cc4",
          notice: [
            {
              path: "/",
              title: "将在2023.2.2推出",
              content:
                '<ul><li>Markdown文档模板配置</li><li>JSON5 支持</li></ul><div class="addthis_inline_follow_toolbox_qssu"></div>',
              actions: [
                {
                  text: "了解详情→",
                  link: "/guide/history.html#_2023-2-2",
                  type: "primary",
                },
              ],
              showOnce: false,
              key: "2023.2.2",
            },
            {
              path: "/en",
              title: "Coming in <b>2023.2.2</b>",
              content:
                '<ul><li>Markdown doc template config</li><li>JSON5 support</li></ul><div class="addthis_inline_follow_toolbox"></div>',
              actions: [
                {
                  text: "Learn more→",
                  link: "/en/guide/history.html#_2023-2-2",
                  type: "primary",
                },
              ],
              showOnce: true,
              key: "2023.2.2",
            },
          ],
        },
      },

      mdEnhance: {
        align: true,
        chart: true,
        card: true,
        codetabs: true,
        container: true,
        imgLazyload: true,
        imgSize: true,
        include: {
          resolvePath: (file) =>
            file.startsWith("@src")
              ? file.replace("@src", path.resolve(__dirname, ".."))
              : file,
        },
        mark: true,
        tabs: true,
        tasklist: true,
      },
    },
  },
  { custom: true }
);
