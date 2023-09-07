import {navbar} from "vuepress-theme-hope";

export const enNavbar = navbar([
    {
        text: "Get start",
        link: "/en/guide/getstarted/start",
        icon: "start1",
    },
    {
        text: "Features",
        link: "/en/guide/features/README.md",
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
        link: "/guide/features/README.md",
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
        text: "视频教程",
        link: "/guide/teachingVideo",
        icon: "bilibili",
    },
    
    
    {
        text: "🎯 FAQ",
        link: "/guide/faq",
    },
    {
        text: "友链",
        link: "/guide/link",
    },
    {
        text: "更多",
        children: ["/guide/sponsorList", "/guide/activity", "/guide/java"],
    },
]);
