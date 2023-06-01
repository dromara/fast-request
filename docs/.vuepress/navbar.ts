import { navbar } from "vuepress-theme-hope";

export const enNavbar = navbar([
  {
    text: "Get start",
    link: "/en/guide/getstarted/start",
    icon: "start1",
  },
  {
    text: "Features",
    link: "/en/guide/features",
    icon: "featuresNew",
  },
  {
    text: "Script",
    link: "/en/guide/script",
    icon: "code-box-fill",
  },
  {
    text: "What's New",
    link: "/en/guide/history",
    icon: "changelog",
  },
  {
    text: "Buy",
    link: "/en/guide/buy",
    icon: "buy",
  },
  {
    text: "Version",
    link: "/en/guide/versionCompare",
    icon: "versionCompare",
  },
  {
    text: "🔥 Teamwork",
    link: "/en/guide/teamwork",
    // icon: "teamwork",
  },
  {
      text: "Contact us",
      link: "/en/guide/concatGroup",
      // icon: "teamwork",
    },
    {
          text: "Faq",
          link: "/en/guide/faq",
          // icon: "teamwork",
        },
//   {
//     text: "More",
//     children: [],
//   },
]);

export const zhNavbar = navbar([
  {
    text: "快速开始",
    link: "/guide/getstarted/start",
    icon: "start1",
  },
  {
    text: "功能",
    link: "/guide/features",
    icon: "featuresNew",
  },
  {
    text: "脚本",
    link: "/guide/script",
    icon: "code-box-fill",
  },
  {
    text: "最新变化",
    link: "/guide/history",
    icon: "changelog",
  },
  {
    text: "购买插件",
    link: "/guide/buy",
    icon: "buy",
  },
  {
    text: "版本",
    link: "/guide/versionCompare",
    icon: "versionCompare",
  },
  {
    text: "视频教程",
    link: "/guide/teachingVideo",
    icon: "bilibili",
  },
  {
    text: "🔥 团队协作",
    link: "/guide/teamwork",
    // icon: "teamwork",
  },
  {
    text: "🎯 群聊",
    link: "/guide/concatGroup",
  },
  {
      text: "FAQ",
      link: "/guide/faq",
    },
  {
    text: "更多",
    children: [
      "/guide/link",
      "/guide/sponsorList",
      "/guide/activity",
    ],
  },
]);
