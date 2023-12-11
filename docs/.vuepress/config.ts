import {getDirname, path} from "@vuepress/utils";
import {defineUserConfig} from "vuepress";
import {docsearchPlugin} from "@vuepress/plugin-docsearch";
import {sitemapPlugin} from "vuepress-plugin-sitemap2";
import theme from "./theme.js";

const __dirname = getDirname(import.meta.url);

export default defineUserConfig({
  title: "Fast Request",
  description: "Intellij Restful Fast Request",
  shouldPrefetch:false,
  head: [
    [
      "link",
      {
        rel: "icon",
        href: "/img/fastRequest.svg",
      },
    ],
    ["script", { src: "//at.alicdn.com/t/c/font_2601581_144d8yxi3ht.js" }],
    ["script", { src: "/js/baidu.js" }],
    ["script", { src: "/js/gtag.js" }],
    [
      'script', {}, `
        (function() {
	       if (location.href.indexOf('gitee.io') > -1 || location.href.indexOf('sheng90.wang') > -1) {
           		location.href = 'https://api-buddy.cn'
           }
        })();
        `
    ],
    [
    'script', {}, `
      window.dataLayer = window.dataLayer || [];
      function gtag(){dataLayer.push(arguments);}
      gtag('js', new Date());
      gtag('config', 'G-Y8G30ZWCYE');
    `
    ]
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
    sitemapPlugin({
      hostname: "https://api-buddy.cn",
    }),
  ],

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
