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
          "主题使用 <a target='blank' href='https://theme-hope.vuejs.press/zh/'>vuepress-theme-hope</a><hr/><a target='blank' href='https://beian.miit.gov.cn/'>浙ICP备19016479号</a>",

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
              title: "将在2023.1.9推出",
              content:
                '<ul><li>Environment</li><a href="https://www.bilibili.com/video/BV1wu4y1d7c4" target="_blank">API命名保存机制优化</a></ul><div class="addthis_inline_follow_toolbox_qssu"></div>',
              actions: [
                {
                  text: "了解详情→",
                  link: "/guide/history.html#_2023-1-9",
                  type: "primary",
                },
              ],
              showOnce: false,
              key: "2023.1.9",
            },
            {
              path: "/en",
              title: "Coming in <b>2023.1.9</b>",
              content:
                '<ul><li>Environment</li></ul><div class="addthis_inline_follow_toolbox"></div>',
              actions: [
                {
                  text: "Learn more→",
                  link: "/en/guide/history.html#_2023-1-9",
                  type: "primary",
                },
              ],
              showOnce: true,
              key: "2023.1.8",
            },
          ],
        },
      },

      mdEnhance: {
        align: true,
        chart: true,
        codetabs: true,
        container: true,
        flowchart: true,
        imgLazyload: true,
        imgSize: true,
        mermaid: true,
        card: true,
        include: {
          resolvePath: (file) =>
            file.startsWith("@src")
              ? file.replace("@src", path.resolve(__dirname, ".."))
              : file,
        },
        mark: true,
        tasklist: true,
        tabs: true
      },
    },
  },
  { custom: true },
);
