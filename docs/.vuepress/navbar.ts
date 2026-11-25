import { navbar } from "vuepress-theme-hope";

export const enNavbar = navbar([
  {
    text: "Get start",
    link: "/en/guide/getstarted/start",
    icon: "start1",
  },
  {
    text: "Features",
    icon: "featuresNew",
    children: [
      { text: "Feature list", link: "/en/guide/features/README.md" },
      { text: "Interface guide", link: "/en/guide/ui-guide" },
    ],
  },
  {
    text: "What's New",
    link: "/en/guide/history",
    icon: "changelog",
  },
  {
    text: "Script",
    link: "/en/guide/script",
    icon: "code-box-fill",
  },
  {
    text: "Buy",
    icon: "buy",
    children: ["/en/guide/buy", "/en/guide/versionCompare"],
  },
  {
    text: "🔥 Teamwork",
    link: "/en/guide/teamwork",
    // icon: "teamwork",
  },
  {
    text: "Contact us",
    icon: "team",
    link: "/en/guide/concatGroup",
    // icon: "teamwork",
  },
  {
    text: "🎯 Faq",
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
    text: "快速上手",
    link: "/guide/getstarted/start",
    icon: "start1",
  },
  {
    text: "功能",
    icon: "featuresNew",
    children: [
      { text: "功能列表", link: "/guide/features/README.md" },
      { text: "界面导览", link: "/guide/ui-guide" },
    ],
  },
  {
    text: "最新变化",
    link: "/guide/history",
    icon: "changelog",
  },
  {
    text: "脚本",
    link: "/guide/script",
    icon: "code-box-fill",
  },
  {
    text: "购买",
    icon: "buy",
    children: ["/guide/buy", "/guide/versionCompare"],
  },
  {
    text: "视频教程",
    link: "/guide/teachingVideo",
    icon: "bilibili",
  },
  {
    text: "🔥 团队协作",
    // icon: "teamwork",
    link: "/guide/teamwork",
  },
  {
    text: "群聊",
    icon: "team",
    link: "/guide/concatGroup",
  },
  {
    text: "🎯 FAQ",
    link: "/guide/faq",
  },
  {
    text: "更多",
    children: [
      "/guide/link",
      "/guide/sponsorList",
      "/guide/activity",
      "/guide/java",
    ],
  },
]);
