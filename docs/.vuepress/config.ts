import { defineUserConfig } from "vuepress";
import { docsearchPlugin } from "@vuepress/plugin-docsearch";
import theme from "./theme.js";

export default defineUserConfig({
  base: "/fast-request/",

  title: "Fast Request",
  description: "Intellij Restful Fast Request",

  head: [
    [
      "link",
      {
        rel: "icon",
        href: "/fast-request/img/fastRequest.svg",
      },
    ],
    ["script", { src: "//at.alicdn.com/t/c/font_2601581_9pz0ama5329.js" }],
    ["script", { src: "/fast-request/js/baidu.js" }],
  ],

  markdown: {
    code: {
      lineNumbers: 10,
    },
  },

  locales: {
    "/": {
      lang: "zh-CN",
    },
    "/en/": {
      lang: "en-US",
    },
  },

  theme,

  plugins: [
    docsearchPlugin({
      appId: "8FRYEU6KK8",
      apiKey: "84f513df1e83406ba42179da778d87b4",
      indexName: "dromara-fast-request",
      locales: {
        "/": {
          placeholder: "搜索文档",
          translations: {
            button: {
              buttonText: "搜索文档",
            },
          },
        },
        "/en": {
          placeholder: "Search Documentation",
          translations: {
            button: {
              buttonText: "Search Documentation",
            },
          },
        },
      },
    }),
  ],

  pagePatterns: ["**/*.md", "!*.snippet.md", "!.vuepress", "!node_modules"],
});
