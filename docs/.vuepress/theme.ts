import { hopeTheme } from "vuepress-theme-hope";
import { enNavbar, zhNavbar } from "./navbar.js";
import { enSidebar, zhSidebar } from "./sidebar.js";

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
  iconPrefix: "iconfont icon-",

  locales: {
    "/": {
      footer:
        "Theme by <a target='blank' href='https://vuepress-theme-hope.github.io/v2/zh/'>vuepress-theme-hope</a>",

      navbar: zhNavbar,
      sidebar: zhSidebar,
    },

    "/en/": {
      navbar: enNavbar,
      sidebar: enSidebar,
      footer:
        "Theme by <a target='blank' href='https://vuepress-theme-hope.github.io/v2/'>vuepress-theme-hope</a>",
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

    components: ["Badge", "BiliBili"],

    mdEnhance: {
      align: true,
      chart: true,
      codetabs: true,
      container: true,
      flowchart: true,
      imageLazyload: true,
      mark: true,
      tasklist: true,
    },
  },
});
