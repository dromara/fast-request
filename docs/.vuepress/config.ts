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
    [
      "link",
      {
        rel: "stylesheet",
        href: "//at.alicdn.com/t/c/font_2601581_jazhgjsvaba.css",
      },
    ],
    ["script", { src: "//at.alicdn.com/t/c/font_2601581_jazhgjsvaba.js" }],

    ["script", { src: "/fast-request/js/love-me.js" }],
    ["script", { src: "/fast-request/js/baidu.js" }],
  ],

  markdown: {
    code: {
      lineNumbers: 10,
    },
  },

  locales: {
    // 键名是该语言所属的子路径
    // 作为特例，默认语言可以使用 '/' 作为其路径。
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
        "/en": {
          placeholder: "Search Documentation",
          translations: {
            button: {
              buttonText: "Search Documentation",
            },
          },
        },
        "/": {
          placeholder: "搜索文档",
          translations: {
            button: {
              buttonText: "搜索文档",
            },
          },
        },
      },
    }),
  ],
});
