// These links reference existing documentation; the request tabs themselves
// switch panels without opening a documentation page.
export const scriptLinkGroups = [
  {
    label: ["代码片段", "Code snippets"],
    links: [
      {
        label: ["签名参数", "Sign parameters"],
        anchors: ["_1-签名参数", "_1-sign-parameter"],
      },
      {
        label: ["使用响应设置 Header", "Set a header from a response"],
        anchors: [
          "_2-利用某一个请求的响应充当请求的-header-参数",
          "_2-use-the-response-of-a-request-as-the-header-parameter-of-the-request",
        ],
      },
      {
        label: ["引用第三方 Jar", "Import third-party JARs"],
        anchors: ["引入第三方-jar", "import-third-jar"],
      },
      {
        label: ["直接引用项目中的类", "Reference project classes"],
        anchors: [
          "_4-直接引用项目中的类",
          "_4-reference-a-project-class-directly",
        ],
      },
    ],
  },
  {
    label: ["UI 代码片段", "UI snippets"],
    links: [
      {
        label: ["项目级别头参数", "Project headers"],
        anchors: ["rfr-projectheader", "rfr-projectheader"],
      },
      {
        label: ["API Header", "API headers"],
        anchors: ["rfr-apiheader", "rfr-apiheader"],
      },
      {
        label: ["Environment 变量", "Environment variables"],
        page: "features/environment",
      },
      {
        label: ["当前项目名", "Current project name"],
        anchors: ["rfr-currentprojectname", "rfr-currentprojectname"],
      },
      {
        label: ["当前环境名", "Current environment name"],
        anchors: ["rfr-currentenvname", "rfr-currentenvname"],
      },
      {
        label: ["当前域名", "Current domain"],
        anchors: ["rfr-currentdomain", "rfr-currentdomain"],
      },
      {
        label: ["当前 API 模块名", "Current API module"],
        anchors: ["rfr-currentmodulename", "rfr-currentmodulename"],
      },
    ],
  },
  {
    label: ["Console 打印", "Console logging"],
    links: ["print", "info", "warn", "success", "error"].map((name) => ({
      label: [name, name],
      anchors: ["console", "console"],
    })),
  },
];

export function getScriptLinkHref(link, lang = "zh") {
  const anchor = link.anchors?.[lang === "en" ? 1 : 0];
  return `${lang === "en" ? "/en" : ""}/guide/${link.page || "script"}.html${anchor ? `#${anchor}` : ""}`;
}
