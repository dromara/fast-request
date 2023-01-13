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

  iconAssets: "//at.alicdn.com/t/c/font_2601581_bwh3hrhwv7e.css",

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
          notice: [
            {
              path: "/en",
              title: "Next version: 2023.1.1",
              content: "<b>Pretty point</b>: Add configuration file domain recognize",
              actions: [
                {
                  text: "View",
                  link: "/en/guide/history.html",
                  type: "primary",
                }
              ],
              showOnce: true,
              key:"2023.1.1"
            },
            {
              path: "/",
              title: "下个版本: 2023.1.1",
              content: "<b>靓点</b>：新增配置文件域名识别",
              actions: [
                {
                  text: "查看",
                  link: "/guide/history.html",
                  type: "primary",
                }
              ],
              showOnce: true,
              key:"2023.1.1"
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
      figure: true,
      imgLazyload: true,
      // FIXME: Upstream bug
      // @ts-ignore
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
