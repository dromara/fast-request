const { defineHopeConfig } = require("vuepress-theme-hope");
const { comment } = require("vuepress-plugin-comment2");


module.exports = defineHopeConfig({
    plugins: [
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
    title: '',
//    description: 'Intellij Restful Fast Request',
    head: [
        [
            'link',
            {
                rel: 'icon',
                href: '/fast-request/img/fastRequest.svg'
            }
        ],
        [
            'link',
            {
                rel: "stylesheet",
                href: "//at.alicdn.com/t/c/font_2601581_jazhgjsvaba.css",
            },
        ],
        [
            'script',
            {
                src: '//at.alicdn.com/t/c/font_2601581_jazhgjsvaba.js'
            }
        ],

        [
            'script',
            {
                src: '/fast-request/js/love-me.js'
            }
        ],
          [
              'script',
              {
                  src: '/fast-request/js/baidu.js'
              }
          ]
    ],
    markdown: {
        lineNumbers: true,
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
        darkmode: 'enable',
        pageInfo: false,
        plugins: {
              mdEnhance: {
                  align: true,
                  tasklist: true,
                  container: true,
                  lazyLoad: true,
                  codegroup: true,
                  mark: true,
                  chart: true,
                  flowchart: true
              },
              comment: {
                   type: 'giscus',
                   repo:'kings1990/giscus-fastrequest',
                   repoId:'R_kgDOHLlUsg',
                   category:'fastRequest',
                   categoryId:'DIC_kwDOHLlUss4COlsW'
              }
        },
        displayFooter: true,
        copyright: "Copyright © 2021-present Kings",
        themeColor: {
              pink :"#f26d6d",
              lightBlue:"#07C3F2",
              orange: "#FC801D",
              blue: "#087CFA",
              red: "#FE2857"
        },
        iconPrefix:'iconfont icon-',
        logo:'/img/logoLine.gif',
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
                footer: "Theme by <a target='blank' href='https://vuepress-theme-hope.github.io/v2/'>vuepress-theme-hope</a>",
                selectText: 'Languages',
                label: 'English',
                navbar: [
                      {
                          text: 'Get start',
                          link: '/en/guide/getstarted/start',
                          icon: "start1"
                      },
                      {
                          text: 'Features',
                          link: '/en/guide/features',
                          icon: "featuresNew"
                      },
                      {
                          text: 'Script',
                          link: '/en/guide/script',
                          icon: "code-box-fill"
                      },
                      {
                          text: 'History changes',
                          link: '/en/guide/history',
                          icon: "changelog"
                      },
                      {
                          text: 'Buy plugin',
                          link: '/en/guide/buy',
                          icon: "buy"
                      },
                      {
                           text: 'Contacts',
                           link: '/en/guide/concatGroup',
                           icon: "contactGroup"
                       }
                  ],
                sidebar: {
                    '/en/guide/': [
                        "introduce",
                        {
                            text: "Getting started",
                            icon: "leibie",
                            prefix: "getstarted/",
                            collapsable: true,
                            children: [
                              "start",
                              "projectEnv",
                              "dataMapping",
                              "stringGenerationStrategy",
                              "icon",
                              "otherConfig",
                              "shortcut"
                            ],
                        },
                        "install",
                        {
                            text: "Features",
                            icon: "featuresNew",
                            prefix: "features/",
                            collapsable: true,
                            link: "/en/guide/features",
                            children: [
                                "makeIconMove",
                                "searchEveryWhere",
                                "projectValueConfig",
                                "navigateCurrentMethod",
                                "debugApi",
                                "downloadApi",
                                "stopRequest",
                                "saveRequest",
                                "apiGroup",
                                "tempRequest",
                                "regenerate",
                                "curlImport",
                                "copyCurl",
                                "copyUrl",
                                "historyRequest",
                                "htmlPreview",
                                "swaggerDefaultValueParse",
                                "autoDescription",
                                "apiList",
                                "apiToPostman",
                                "apiImportExport",
                                "batchExportApiDoc",
                                "apiNavigateTree",
                                "apiPreview",
                                "apiCommentPreview",
                                "script",
                                "quickAddHeader",
                                "headersGroup",
                                "jsonGrammarCheck",
                            ],
                        },
                        "script",
                        "whatsnew",
                        "history",
                        "buy",
                        "concatGroup",
                        "faq",
                        "eula",
                        "feature"
                    ]
                }
            },
            '/': {
                footer: "Theme by <a target='blank' href='https://vuepress-theme-hope.github.io/v2/zh/'>vuepress-theme-hope</a>",
                // 多语言下拉菜单的标题
                selectText: 'Languages',
                // 该语言在下拉菜单中的标签
                label: '简体中文',
                // 当前 locale 的 algolia docsearch 选项
                algolia: {},
                navbar: [
                    {
                        text: '快速开始',
                        link: '/guide/getstarted/start',
                        icon: "start1"
                    },
                    {
                        text: '功能',
                        link: '/guide/features',
                        icon: "featuresNew"
                    },
                     {
                         text: '脚本',
                         link: '/guide/script',
                         icon: "code-box-fill"
                     },
                    {
                        text: '历史变更',
                        link: '/guide/history',
                        icon: "changelog"
                    },
                    {
                        text: '支持插件',
                        link: '/guide/buy',
                        icon: "buy"
                    },
                    {
                        text: '视频教程',
                        link: '/guide/teachingVideo',
                        icon: "bilibili"
                    },
                     {
                         text: '🔥 加入群聊',
                         link: '/guide/concatGroup',
                     },
                    {
                         text: '❤️ 赞助',
                         link: '/guide/sponsorList',
                     },
                     {
                          text: '🎁 活动',
                          link: '/guide/activity',
                      }
                ],
                sidebar: {
                    '/guide/': [
                        "introduce",
                        {
                            text: "快速上手",
                            icon: "leibie",
                            prefix: "getstarted/",
                            collapsable: true,
                            children: [
                              "start",
                              "projectEnv",
                              "dataMapping",
                              "stringGenerationStrategy",
                              "icon",
                              "otherConfig",
                              "shortcut"
                            ],
                        },
                        "install",
                        {
                            text: "功能",
                            icon: "featuresNew",
                            prefix: "features/",
                            collapsable: true,
                            link: "/guide/features",
                            children: [
                              "makeIconMove",
                              "searchEveryWhere",
                              "projectValueConfig",
                              "navigateCurrentMethod",
                              "debugApi",
                              "downloadApi",
                              "stopRequest",
                              "saveRequest",
                              "apiGroup",
                              "tempRequest",
                              "regenerate",
                              "curlImport",
                              "copyCurl",
                              "copyUrl",
                              "historyRequest",
                              "htmlPreview",
                              "swaggerDefaultValueParse",
                              "autoDescription",
                              "apiList",
                              "apiToPostman",
                              "apiImportExport",
                              "batchExportApiDoc",
                              "apiNavigateTree",
                              "apiPreview",
                              "apiCommentPreview",
                              "script",
                              "quickAddHeader",
                              "headersGroup",
                              "jsonGrammarCheck",
                            ],
                        },
                        "script",
                        "whatsnew",
                        "history",
                        "buy",
                        "concatGroup",
                        "teachingVideo",
                        "faq",
                        "sponsorList",
                        "activity",
                        "eula",
                        "feature"
                    ],
                }
            }
        }
    }
})
