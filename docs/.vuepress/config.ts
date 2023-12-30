import { getDirname, path } from "@vuepress/utils";
import { defineUserConfig } from "vuepress";
import theme from "./theme.js";

const __dirname = getDirname(import.meta.url);

export default defineUserConfig({
  title: "Fast Request",
  description: "Intellij Restful Fast Request",
  shouldPrefetch: false,
  head: [
    [
      "link",
      {
        rel: "icon",
        href: "/img/fastRequest.svg",
      },
    ],
    [
      "script",
      { src: "//at.alicdn.com/t/c/font_2601581_144d8yxi3ht.js", async: true },
    ],
    // ["script", { src: "/js/baidu.js" ,"async":true}],
    ["script", { src: "/js/gtag.js", async: true }],
    [
      "script",
      {},
      `
        (function() {
	       if (location.href.indexOf('gitee.io') > -1 || location.href.indexOf('sheng90.wang') > -1) {
           		location.href = 'https://api-buddy.cn'
           }
        })();
        `,
    ],
    [
      "script",
      {},
      `
      window.dataLayer = window.dataLayer || [];
      function gtag(){dataLayer.push(arguments);}
      gtag('js', new Date());
      gtag('config', 'G-Y8G30ZWCYE');
    `,
    ],
    // ["script", { src: "https://cdn.wwads.cn/js/makemoney.js" }],
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

  pagePatterns: ["**/*.md", "!*.snippet.md", "!.vuepress", "!node_modules"],

  alias: {
    "@theme-hope/layouts/Layout": path.resolve(
      __dirname,
      "./layouts/Layout.vue",
    ),
    // "@theme-hope/components/HomePage": path.resolve(
    //     __dirname,
    //     "./components/HomePage.vue"
    // ),
    // "@theme-hope/components/NormalPage": path.resolve(
    //     __dirname,
    //     "./components/NormalPage.vue"
    // ),
    // "@theme-hope/modules/sidebar/components/Sidebar": path.resolve(
    //     __dirname,
    //     "./components/Sidebar.vue"
    // ),
  },
});
