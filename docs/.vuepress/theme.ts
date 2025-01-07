import { path } from "vuepress/utils";
import { hopeTheme } from "vuepress-theme-hope";
import { enNavbar, zhNavbar } from "./navbar.js";
import { enSidebar, zhSidebar } from "./sidebar.js";

export default hopeTheme(
  {
    hostname: "https://api-buddy.com",
    logo: "/img/fastRequest.svg",

    repo: "https://github.com/dromara/fast-request",
    docsDir: "docs",
    docsBranch: "master",

    copyright: "Copyright © 2021-present Kings",
    displayFooter: true,

    pageInfo: false,
    fullscreen: true,
    editLink: false,
    contributors: false,

    darkmode: "enable",

    iconAssets: "//at.alicdn.com/t/c/font_2601581_tleme42m6wm.css",

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

    markdown: {
      highlighter: {
        type: "shiki",
        lineNumbers: 10,
        theme: "one-dark-pro",
      },
      align: true,
      chartjs: true,
      component: true,
      include: {
        resolvePath: (file) =>
          file.startsWith("@src")
            ? file.replace("@src", path.resolve(import.meta.dirname, ".."))
            : file,
      },
      mark: true,
      tasklist: true,
      imgLazyload: true,
      imgSize: true,
      tabs: true,
      codeTabs: true,
    },

    plugins: {
      components: {
        components: [
          "Badge",
          "BiliBili",
          "SiteInfo",
          "VPBanner",
          "VPCard",
          "VidStack",
        ],
      },

      docsearch: {
        appId: "6CYP6DKPIT",
        apiKey: "35f9f152a5203e42231a73eb0de1546d",
        indexName: "api-buddy",
        locales: {
          "/en/": {
            placeholder: "Search docs",
            translations: {
              button: {
                buttonText: "Search docs",
              },
            },
          },
        },
      },

      notice: [
        {
          path: "/",
          title: "将在2024.1.9推出",
          content:
            '<ul><li><a href="https://plugins.jetbrains.com/plugin/24576-bean-assistant--spring-code-helper" target="_blank">免费插件Bean Assistant实现快速注入 Bean</a></li></li><li>OpenAPI 导出支持</li><li>Solon 框架解析支持</li></ul><div class="addthis_inline_follow_toolbox_qssu"></div>',
          actions: [
            {
              text: "了解详情→",
              link: "/guide/history.html#_2024-1-9",
              type: "primary",
            },
          ],
          showOnce: true,
          key: "2024.1.9",
        },
        {
          path: "/en",
          title: "Coming in <b>2024.1.9</b>",
          content:
            '<ul><li><a href="https://plugins.jetbrains.com/plugin/24576-bean-assistant--spring-code-helper" target="_blank">Another plugin Bean Assistant help injected bean easily</a></li><li>OpenAPI export support</li><li>Solon framework support</li></ul><div class="addthis_inline_follow_toolbox"></div>',
          actions: [
            {
              text: "Learn more→",
              link: "/en/guide/history.html#_2024-1-9",
              type: "primary",
            },
          ],
          showOnce: true,
          key: "2024.1.9",
        },
      ],
    },
  },
  { custom: true },
);
