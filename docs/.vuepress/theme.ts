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

      navbarLayout: {
          start: ["Brand"],
          center: ["Links"],
          end: ["BgMusic","Language",  "Repo", "Outlook", "Search"],
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
      alert: true,
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

      icon: {
        assets: "//at.alicdn.com/t/c/font_2601581_tleme42m6wm.css",
      },

      notice: [
        {
          path: "/",
          title: "将在2026.1.1推出",
          content:
            '<ul><li><a href="https://plugins.jetbrains.com/plugin/24576-bean-assistant--spring-code-helper" target="_blank">免费插件Bean Assistant实现快速注入 Bean</a></li><li>AI 参数智能优化</li><li>API 批量生成保存</li><li>Run Method 支持</li><li>在线文档支持</li></ul>',
          actions: [
            {
              text: "了解更多→",
              link: "/guide/history.html#_2026-1-1",
              type: "primary",
            },
          ],
          showOnce: true,
          key: "2026.1.1",
        },
        {
          path: "/en",
          title: "Coming in <b>2026.1.1</b>",
          content:
            '<ul><li><a href="https://plugins.jetbrains.com/plugin/24576-bean-assistant--spring-code-helper" target="_blank">Another plugin Bean Assistant help injected bean easily</a></li><li>AI parameter optimization</li><li>API batch generation save</li><li>Run Method support</li><li>Online documentation support</li></ul>',
          actions: [
            {
              text: "Learn more→",
              link: "/en/guide/history.html#_2026-1-1",
              type: "primary",
            },
          ],
          showOnce: true,
          key: "2026.1.1",
        },
      ],
    },
  },
  { custom: true },
);
