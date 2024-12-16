import { container } from "@mdit/plugin-container";
import { viteBundler } from "@vuepress/bundler-vite";
import { defineUserConfig } from "vuepress";
import { path } from "vuepress/utils";

import theme from "./theme.js";

export default defineUserConfig({
  title: "Fast Request",
  description: "Intellij Restful Fast Request",
  shouldPrefetch: false,
  head: [
    [
      "script",
      {},
      `
        (function() {
          if (location.href.indexOf('gitee.io') > -1 || location.href.indexOf('sheng90.wang') > -1 || || location.href.indexOf('api-buddy.cn') > -1) {
            window.location.href = 'https://api-buddy.com';
          }
        })();
        `,
    ],
    [
      "link",
      {
        rel: "icon",
        href: "/img/fastRequest.svg",
      },
    ],
    [
      "script",
      { src: "//at.alicdn.com/t/c/font_2601581_tleme42m6wm.js", async: true },
    ],
    // ["script", { src: "/js/baidu.js" ,"async":true}],
    ["script", { src: "/js/gtag.js", async: true }],
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

  locales: {
    "/": {
      lang: "zh-CN",
    },
    "/en/": {
      lang: "en-US",
    },
  },

  bundler: viteBundler(),

  theme,

  pagePatterns: ["**/*.md", "!*.snippet.md", "!.vuepress", "!node_modules"],

  alias: {
    "@theme-hope/layouts/Layout": path.resolve(
      import.meta.dirname,
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
  extendsMarkdown: (md) => {
    md.use(container, {
      name: "hint",
      openRender: (tokens, index, _options) => {
        const info = tokens[index].info.trim().slice(4).trim();
        let style = "background:#262626";

        if (info.indexOf("style") > -1) {
          style = info.split("style=")[1].split('"')[1];
        }

        const title = info.replace('style="' + style + '"', "") || "Hint";
        return `<div class="custom-container hint" style="${style}">\n<p class="custom-container-title">${title}</p>\n`;
      },
    });
  },
});
