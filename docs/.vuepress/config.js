const { defineHopeConfig } = require("vuepress-theme-hope");
const { comment } = require("vuepress-plugin-comment2");


module.exports = defineHopeConfig({
    plugins: ['@vuepress/nprogress',
//        ["@vuepress-yard/vuepress-plugin-window",
//            {
//              title: "二轮投票支持",  //vuepress公告插件 先安装在配置 npm install @vuepress-yard/vuepress-plugin-window --save
//              contentInfo: {
//                  title: "投票支持🎉🎉🎉",
//                content: "《2021年度OSC中国最佳开源项目评选》已成功晋级TOP50进入下一轮，希望大家再次为Restful Fast Request投上一票，在此非常感谢各位 ❤️❤️❤️ 二轮投票有效时间：2021.12.20~2021.12.26 ",
//                contentStyle: {"font-size":"13px"}
//              },
//              windowStyle:{right: '10px',top: '300px',width: '210px'},
//              bottomInfo: {
//                btnText: '投票',
//                linkTo: 'https://www.oschina.net/project/top_cn_2021/?id=589'
//              },
//              hideRouteList:["en"],
//              closeOnce: false
//            }]
           [
                 '@vuepress/docsearch',
                 {
                   appId:"8FRYEU6KK8",
                   apiKey: '84f513df1e83406ba42179da778d87b4',
                   indexName: 'dromara-fast-request',
                   locales: {
                     '/en': {
                       placeholder: 'Search Documentation',
                       translations: {
                         button: {
                           buttonText: 'Search Documentation',
                         },
                       },
                     },
                     '/': {
                       placeholder: '搜索文档',
                       translations: {
                         button: {
                           buttonText: '搜索文档',
                         },
                       },
                     },
                   },
                 },
               ],

    ],
    base : '/fast-request/',
    title: 'Restful Fast Request',
    description: 'Intellij Restful Fast Request',
    head: [
        [
            'link',
            {
                rel: "stylesheet",
                href: "//at.alicdn.com/t/font_2601581_sp9fwshuf7k.css",
            },
        ],
        [
            'link',
            {
                rel: 'icon',
                href: '/fast-request/img/fastRequest.svg'
            }
        ],

    ],
    markdown: {
        lineNumbers: true
    },
    locales: {
        // 键名是该语言所属的子路径
        // 作为特例，默认语言可以使用 '/' 作为其路径。
        '/': {
            lang: 'zh-CN',
        },
        '/en/': {
            lang: 'en-US'
        }
    },
    themeConfig: {
        repo: "https://github.com/dromara/fast-request",
        repoLabel: "GitHub",
        plugins: {
              mdEnhance: {
                  align: true,
                  tasklist: true,
                  container: true,
                  lazyLoad: true,
                  codegroup: true,
                  mark: true
              },
              comment: {
                   type: 'waline',
                   serverURL: "https://fast-request-vercel.vercel.app"
              }
        },
        displayFooter: true,
        copyright: "Copyright © 2021-present Kings",
        themeColor: {
              blue: "#087CFA",
              red: "#FE2857",
              green: "#21D789",
              orange: "#FC801D",
              pink :"#FF318C",
              lightBlue:"#07C3F2"
        },
        iconPrefix:'iconfont icon-',
        logo:'/img/fastRequest.svg',
        lastUpdated: 'Last Updated',
      // 以下为可选的编辑链接选项
      // 假如你的文档仓库和项目本身不在一个仓库：
//      docsRepo: 'kings1990/restful-fast-request-doc',
      // 假如文档不是放在仓库的根目录下：
      docsDir: 'docs',
      // 假如文档放在一个特定的分支下：
      docsBranch: 'master',
      // 默认是 false, 设置为 true 来启用
      editLinks: true,
      locales: {
            '/en/': {
                footer: "Apache License 2.0  | Theme by <a target='blank' href='https://vuepress-theme-hope.github.io/v2/'>vuepress-theme-hope</a>",
                selectText: 'Languages',
                label: 'English',
//                navbar: [
//                    {
//                        text: 'Guide',
//                        link: '/en/guide/introduce',
//                        icon: "config"
//                    },
//                    {
//                        text: 'Code',
//                        children: [
//                            {
//                                text: 'Github',
//                                link: 'https://github.com/dromara/fast-request'
//                            },
//                            {
//                                text: 'Gitee',
//                                link: 'https://gitee.com/dromara/fast-request'
//                            }
//                        ],
//                        icon: "code"
//                    }
//                ],
                sidebar: {
                    '/en/guide/': [
                        "introduce",
                        {
                            text: "Getting started",
                            icon: "leibie",
                            prefix: "getstarted/",
                            collapsable: false,
                            children: [
                              "start",
                              "projectEnv",
                              "dataMapping",
                              "stringGenerationStrategy",
                              "icon",
                              "otherConfig",
                            ],
                        },
                        "install",
                        "feature",
                        "whatsnew",
                        "history",
                        "buy",
                        "faq",
                    ]
                }
            },
            '/': {
                footer: "Apache License 2.0  | Theme by <a target='blank' href='https://vuepress-theme-hope.github.io/v2/zh/'>vuepress-theme-hope</a>",
                // 多语言下拉菜单的标题
                selectText: 'Languages',
                // 该语言在下拉菜单中的标签
                label: '简体中文',
                // 当前 locale 的 algolia docsearch 选项
                algolia: {},
//                navbar: [
//                    {
//                        text: '指南',
//                        link: '/guide/introduce',
//                        icon: "config"
//                    },
//                    {
//                        text: '代码',
//                        children: [
//                            {
//                                text: 'Github',
//                                link: 'https://github.com/dromara/fast-request'
//                            },
//                            {
//                                text: 'Gitee',
//                                link: 'https://gitee.com/dromara/fast-request'
//                            }
//                        ],
//                        icon: "code"
//                    }
//                ],
                sidebar: {
                    '/guide/': [
                        "introduce",
                        {
                            text: "快速上手",
                            icon: "leibie",
                            prefix: "getstarted/",
                            collapsable: false,
                            children: [
                              "start",
                              "projectEnv",
                              "dataMapping",
                              "stringGenerationStrategy",
                              "icon",
                              "otherConfig",
                            ],
                        },
                        "install",
                        "feature",
                        "whatsnew",
                        "history",
                        "buy",
                        "faq"
                    ],
                }
            }
        }
    }
})
