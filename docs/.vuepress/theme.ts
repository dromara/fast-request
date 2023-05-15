import { getDirname, path } from "@vuepress/utils";
import { hopeTheme } from "vuepress-theme-hope";
import { enNavbar, zhNavbar } from "./navbar.js";
import { enSidebar, zhSidebar } from "./sidebar.js";

const __dirname = getDirname(import.meta.url);

export default hopeTheme({
  logo: "/img/logo/logo.svg",

  repo: "https://github.com/dromara/fast-request",
  docsDir: "docs",
  docsBranch: "master",

  copyright: "Copyright © 2021-present Kings",
  displayFooter: true,

  pageInfo: false,

  darkmode: "enable",
  themeColor: {
    pink: "#f26d6d",
    lightBlue: "#07c3f2",
    orange: "#fc801d",
    blue: "#087cfa",
    red: "#fe2857",
  },

  iconAssets: "//at.alicdn.com/t/c/font_2601581_qdgotug8lt.css",

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
            title: "将于<b>2023-05-25 20:30在Bilibili</b>直播答疑",
            content:
              '<div>扫码预约不错过</div><div class="addthis_inline_follow_toolbox_qssu"></div>',
            actions: [
              {
                text: "了解详情→",
                link: "/guide/activity.html#_20230524",
                type: "primary",
              },
            ],
            showOnce: false,
            key: "20230524",
          },
          {
            path: "/en",
            title: "Coming in <b>2023.1.3</b>",
            content:
              '<ul><li>Teamwork support</li></ul><div class="addthis_inline_follow_toolbox"></div>',
            actions: [
              {
                text: "Learn more→",
                link: "/en/guide/history.html#_2023-1-3",
                type: "primary",
              },
            ],
            showOnce: true,
            key: "2023.1.3",
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
    },
  },
});
