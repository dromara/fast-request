const { config } = require("vuepress-theme-hope");
module.exports = config({
    plugins: ['@vuepress/nprogress',["add-this",{ pubid: "ra-617a48e0d1826cc4" }]],
    base : '/fast-request/',
    title: 'Restful Fast Request',
    description: 'Intellij Restful Fast Request',
    head: [
        [
            'link',
            {
                rel: 'icon',
                href: '/img/fastRequest.svg'
            }
        ]
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
        breadcrumb: false,
        mdEnhance: {
          align: true
        },
        themeColor: {
              blue: "#087CFA",
              red: "#FE2857",
              green: "#21D789",
              orange: "#FC801D",
              pink :"#FF318C",
              lightBlue:"#07C3F2"
        },
        iconPrefix:'icon-',
        logo:'/img/fastRequest.svg',
        lastUpdated: 'Last Updated',
      // 以下为可选的编辑链接选项
      // 假如你的文档仓库和项目本身不在一个仓库：
      docsRepo: 'kings1990/restful-fast-request-doc',
      // 假如文档不是放在仓库的根目录下：
      docsDir: 'docs',
      // 假如文档放在一个特定的分支下：
      docsBranch: 'master',
      // 默认是 false, 设置为 true 来启用
      editLinks: true,
      locales: {
            '/en/': {

                selectText: 'Languages',
                label: 'English',
                nav: [
                    {
                        text: 'Guide',
                        link: '/en/guide/',
                        icon: "config"
                    },
                    {
                        text: 'Code',
                        items: [
                            {
                                text: 'Github',
                                link: 'https://github.com/kings1990/fast-request'
                            },
                            {
                                text: 'Gitee',
                                link: 'https://gitee.com/kings/fast-request'
                            }
                        ],
                        icon: "code"
                    }
                ],
                sidebar: {
                    '/en/guide/': [{
                        title: 'Getting started',
                        collapsable: false,
                        children: [
                            '',
                            'install',
                            'getstarted',
                            'feature',
                            'whatsnew',
                            'faq'
                        ]
                    }]
                }
            },
            '/': {
                // 多语言下拉菜单的标题
                selectText: 'Languages',
                // 该语言在下拉菜单中的标签
                label: '简体中文',
                // 当前 locale 的 algolia docsearch 选项
                algolia: {},
                nav: [
                    {
                        text: '指南',
                        link: '/guide/',
                        icon: "config"
                    },
                    {
                        text: '代码',
                        items: [
                            {
                                text: 'Github',
                                link: 'https://github.com/kings1990/fast-request'
                            },
                            {
                                text: 'Gitee',
                                link: 'https://gitee.com/kings/fast-request'
                            }
                        ],
                        icon: "code"
                    }
                ],
                sidebar: {
                    '/guide/': [{
                        title: '快速入门',
                        collapsable: false,
                        children: [
                            '',
                            'install',
                            'getstarted',
                            'feature',
                            'whatsnew',
                            'faq',
                            'donate'
                        ]
                    }]
                }
            }
        }
    }
})
