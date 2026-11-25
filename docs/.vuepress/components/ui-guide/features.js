// Layout source: restful-fast-request-pro FastRequestToolWindow / ToolWindowFactory.
// Only include features with an existing page in both documentation locales.
function feature(id, icon, doc, label, description) {
  return { id, icon, doc, label, description };
}

export const guideFeatures = [
  feature(
    "debug",
    "send",
    "features/debugApi",
    ["发送请求", "Send request"],
    [
      "在 Request 中调试 API，发送请求并查看响应。",
      "Debug an API in Request and inspect its response.",
    ],
  ),
  feature(
    "apis",
    "apis",
    "features/apiList",
    ["APIs · 已保存接口", "APIs · Saved requests"],
    [
      "查找、管理和运行已经保存的 API。",
      "Find, manage and run your saved APIs.",
    ],
  ),
  feature(
    "nav",
    "locateNavigate",
    "features/apiNavigateTree",
    ["Nav · 接口导航", "Nav · API navigation"],
    [
      "通过导航树浏览项目中的 API，并定位到源码。",
      "Browse project APIs in the navigation tree and locate their source.",
    ],
  ),
  feature(
    "run",
    "runMethod",
    "features/runMethod",
    ["RunMethod · 方法调用", "RunMethod · Invoke methods"],
    [
      "直接调用 Java 方法，配置参数并查看执行结果。",
      "Invoke Java methods, configure arguments and inspect results.",
    ],
  ),
  feature(
    "methods",
    "method",
    "features/runMethod#methods",
    ["Methods · 方法管理", "Methods · Saved methods"],
    [
      "管理已保存的方法，复用方法调用配置。",
      "Manage saved methods and reuse invocation settings.",
    ],
  ),
  feature(
    "shortcuts",
    "code",
    "getstarted/shortcut",
    ["快捷键", "Shortcuts"],
    [
      "查看插件常用操作对应的快捷键。",
      "Look up keyboard shortcuts for common plugin actions.",
    ],
  ),
  feature(
    "settings",
    "config",
    "features/settingManager",
    ["管理配置", "Settings"],
    [
      "管理插件配置及参数生成策略。",
      "Manage plugin settings and parameter generation strategies.",
    ],
  ),
  feature(
    "fullscreen",
    "fullScreen",
    "features/fullScreen",
    ["全屏", "Full screen"],
    [
      "展开工具窗口，获得更大的 API 调试空间。",
      "Expand the tool window for more debugging space.",
    ],
  ),
  feature(
    "help",
    "doc",
    "features/docAndContact",
    ["文档与联系", "Documentation & contact"],
    [
      "打开使用文档，了解联系与反馈渠道。",
      "Open the documentation and find contact and feedback channels.",
    ],
  ),
  feature(
    "toolbar",
    "customToolbar",
    "features/customToolbar",
    ["自定义工具栏", "Customize toolbar"],
    [
      "选择工具栏中显示的功能按钮。实际界面会因配置而不同。",
      "Choose which toolbar actions are visible. Your layout may vary with settings.",
    ],
  ),
  feature(
    "domain",
    "domainConfig",
    "features/projectLevelDomainConfig",
    ["项目域名配置", "Project domain configuration"],
    [
      "为项目配置不同环境的域名，切换请求地址。",
      "Configure project domains for different environments and switch request hosts.",
    ],
  ),
  feature(
    "global",
    "projectGlobalConfig",
    "features/projectValueConfig",
    ["项目全局参数", "Project global parameters"],
    [
      "集中配置项目级别的公共参数。",
      "Configure shared parameters at project level.",
    ],
  ),
  feature(
    "search",
    "searchApi",
    "features/searchEveryWhere",
    ["搜索 API", "Search APIs"],
    [
      "按 URL、方法或注释查找 API。",
      "Find APIs by URL, method or description.",
    ],
  ),
  feature(
    "download",
    "send_download",
    "features/downloadApi",
    ["发送并下载", "Send & download"],
    [
      "发送请求，并将接口返回的文件下载到本地。",
      "Send a request and download the returned file.",
    ],
  ),
  feature(
    "stop",
    "stop",
    "features/stopRequest",
    ["终止请求", "Stop request"],
    [
      "终止当前正在执行的 API 请求。",
      "Stop the API request currently in progress.",
    ],
  ),
  feature(
    "save",
    "save",
    "features/saveRequest",
    ["保存请求", "Save request"],
    [
      "保存 API 的地址、参数和请求配置，便于再次使用。",
      "Save the API address, parameters and request configuration for reuse.",
    ],
  ),
  feature(
    "saveGroup",
    "saveGroup",
    "features/apiGroup",
    ["分组保存", "Save parameter group"],
    [
      "为同一个 API 保存多组参数，在不同场景中复用。",
      "Save multiple parameter sets for one API and reuse them across scenarios.",
    ],
  ),
  feature(
    "temporary",
    "saveTemp",
    "features/tempRequest",
    ["临时请求", "Temporary request"],
    [
      "保存临时请求，方便后续继续调试。",
      "Keep a temporary request to continue debugging later.",
    ],
  ),
  feature(
    "document",
    "doc",
    "features/onlineDocument",
    ["在线文档", "Online documentation"],
    [
      "将已保存的 API 汇总为文档工作区，支持本地预览和局域网分享。",
      "Organize saved APIs in a documentation workspace for local preview and LAN sharing.",
    ],
  ),
  feature(
    "documentSettings",
    "syncConfig",
    "features/onlineDocument#自动同步",
    ["在线文档设置", "Online document settings"],
    [
      "设置保存请求后自动同步，以及已有文档的处理策略。",
      "Configure automatic synchronization after saving and how existing documents are handled.",
    ],
  ),
  feature(
    "apifox",
    "apifox",
    "features/apifox",
    ["Apifox 集成", "Apifox integration"],
    [
      "配置 Apifox 集成，同步 API 与环境。",
      "Configure Apifox integration and synchronize APIs and environments.",
    ],
  ),
  feature(
    "postman",
    "postmanNew",
    "features/postmanSync",
    ["Postman 集成", "Postman integration"],
    [
      "配置 Postman 集成，同步 API 与环境。",
      "Configure Postman integration and synchronize APIs and environments.",
    ],
  ),
  feature(
    "group",
    "apiParamGroupNew",
    "features/apiGroup",
    ["切换参数分组", "Switch parameter group"],
    [
      "在当前 API 已保存的多组参数之间切换。",
      "Switch between saved parameter groups for the current API.",
    ],
  ),
  feature(
    "locate",
    "locate",
    "features/navigateCurrentMethod",
    ["定位当前方法", "Locate current method"],
    [
      "从当前请求返回对应的源码方法。",
      "Jump from the current request to its source method.",
    ],
  ),
  feature(
    "tabs",
    "storeData",
    "features/navigateCurrentMethodJson",
    ["多窗口 Tab", "Multiple request tabs"],
    [
      "在独立 Tab 中打开请求，便于同时处理多个 API。",
      "Open requests in separate tabs to work with multiple APIs.",
    ],
  ),
  feature(
    "regenerate",
    "retry",
    "features/regenerate",
    ["重新生成请求", "Regenerate request"],
    [
      "根据当前方法重新生成 URL 和参数。",
      "Regenerate the URL and parameters from the current method.",
    ],
  ),
  feature(
    "clear",
    "clear",
    "features/clear",
    ["清除", "Clear"],
    [
      "清理当前请求区域的数据。",
      "Clear data from the current request workspace.",
    ],
  ),
  feature(
    "curlImport",
    "import",
    "features/curlImport",
    ["导入 cURL", "Import cURL"],
    [
      "将 cURL 命令转换为可以调试的请求。",
      "Turn a cURL command into a request you can debug.",
    ],
  ),
  feature(
    "copyUrl",
    "url",
    "features/copyUrl",
    ["拷贝 URL", "Copy URL"],
    ["复制当前 API 的请求地址。", "Copy the current API request URL."],
  ),
  feature(
    "copyCurl",
    "curl",
    "features/copyCurl",
    ["拷贝 cURL", "Copy cURL"],
    [
      "将当前请求复制为 cURL 命令。",
      "Copy the current request as a cURL command.",
    ],
  ),
  feature(
    "history",
    "history",
    "features/historyRequest",
    ["历史请求", "Request history"],
    [
      "查找历史请求记录，恢复请求配置。",
      "Find previous requests and restore a debugging session.",
    ],
  ),
  feature(
    "export",
    "share",
    "features/shareApiDoc",
    ["导出 API 文档", "Export API documentation"],
    [
      "将 API 文档导出为 Markdown、HTML 等格式。",
      "Export API documentation as Markdown, HTML and other formats.",
    ],
  ),
  feature(
    "openApi",
    "openApi",
    "features/openApi",
    ["OpenAPI 导出", "Export OpenAPI"],
    ["将 API 导出为 OpenAPI 格式。", "Export APIs in OpenAPI format."],
  ),
  feature(
    "template",
    "config",
    "features/markdownDocTemplateConfig",
    ["Markdown 模板", "Markdown template"],
    [
      "配置导出 Markdown 文档时使用的模板。",
      "Configure templates used for Markdown documentation exports.",
    ],
  ),
  feature(
    "refresh",
    "refresh",
    "features/projectConfigRefresh",
    ["刷新项目配置", "Refresh project configuration"],
    [
      "重新加载项目中的 Fast Request 配置。",
      "Reload the project's Fast Request configuration.",
    ],
  ),
  feature(
    "report",
    "analyse",
    "features/annualReport",
    ["年度报告", "Annual report"],
    ["查看 API 使用情况的年度统计。", "View annual API usage statistics."],
  ),
  feature(
    "aiSettings",
    "openai",
    "features/ai",
    ["AI 设置", "AI settings"],
    [
      "配置 AI 服务，为参数生成与优化提供支持。",
      "Configure an AI provider for parameter generation and optimization.",
    ],
  ),
  feature(
    "environment",
    "environment",
    "features/environment",
    ["环境变量", "Environment variables"],
    [
      "定义环境变量，并在 URL、Headers 和 Body 中使用 {{key}}。",
      "Define environment variables and use {{key}} in URLs, headers and bodies.",
    ],
  ),
  feature(
    "headers",
    "header",
    "features/headerGroup",
    ["Headers 分组", "Header groups"],
    [
      "管理和切换请求头分组。",
      "Manage and switch between request header groups.",
    ],
  ),
  feature(
    "presets",
    "headerPresets",
    "features/headersPreset",
    ["Headers 预设", "Header presets"],
    ["通过预设填写常用请求头。", "Fill common request headers using presets."],
  ),
  feature(
    "commonHeaders",
    "commonHeader",
    "features/commonHeader",
    ["常用头参数", "Common headers"],
    ["管理可复用的常用请求头。", "Manage reusable request headers."],
  ),
  feature(
    "bulk",
    "edit",
    "features/bulkEdit",
    ["批量编辑", "Bulk edit"],
    ["以文本方式批量编辑 Headers。", "Edit multiple headers in text form."],
  ),
  feature(
    "auth",
    "basicAuth",
    "features/basicAuth",
    ["Basic Auth", "Basic Auth"],
    [
      "为请求配置 HTTP Basic 身份认证。",
      "Configure HTTP Basic authentication for a request.",
    ],
  ),
  feature(
    "script",
    "script",
    "features/script",
    ["前置 / 后置脚本", "Pre / post-request scripts"],
    [
      "使用 Groovy 脚本，在请求前后处理参数与响应。",
      "Use Groovy scripts to process parameters and responses before or after a request.",
    ],
  ),
  feature(
    "ai",
    "openai",
    "features/aiParameterOptimization",
    ["AI 参数智能优化", "AI parameter optimization"],
    [
      "根据 API 上下文生成和优化参数示例值。",
      "Generate and optimize example parameter values using API context.",
    ],
  ),
  feature(
    "function",
    "function",
    "features/function",
    ["函数", "Functions"],
    [
      "在请求参数中使用函数，生成动态参数值。",
      "Use functions in request parameters to generate dynamic values.",
    ],
  ),
];

// Split buttons follow the current Pro toolbar order. Hidden document-sync
// actions and actions without their own documentation are intentionally omitted.
export const guideLayout = {
  windowTabs: ["debug", "apis", "nav", "run", "methods"],
  titleActions: ["shortcuts", "settings", "fullscreen", "help"],
  toolbarGroups: [
    [["toolbar"], ["domain"], ["global"], ["apis"], ["nav"]],
    [
      ["search"],
      ["debug", "download"],
      ["stop"],
      ["save", "saveGroup", "temporary"],
      ["document", "documentSettings"],
      ["apifox"],
      ["postman"],
      ["group"],
      ["locate"],
      ["tabs"],
      ["regenerate"],
      ["clear"],
    ],
    [
      ["curlImport"],
      ["copyUrl", "copyCurl"],
      ["history"],
      ["export", "openApi", "template"],
    ],
    [["refresh"], ["report"], ["aiSettings"]],
  ],
  parameterActions: ["headers", "presets", "commonHeaders", "bulk", "auth"],
  quickLinks: [
    "document",
    "ai",
    "debug",
    "domain",
    "script",
    "curlImport",
    "run",
    "apis",
  ],
};

export function getGuideHref(feature, lang = "zh") {
  const [path, anchor] = feature.doc.split("#");
  // English headings differ from their Chinese equivalents.
  const hash =
    lang === "en" && anchor === "自动同步"
      ? "automatic-synchronization"
      : anchor;
  return `${lang === "en" ? "/en" : ""}/guide/${path}.html${hash ? `#${hash}` : ""}`;
}

export function searchGuideFeatures(query) {
  const term = query.trim().toLocaleLowerCase();
  return guideFeatures.filter((item) =>
    [item.id, ...item.label, ...item.description].some((value) =>
      value.toLocaleLowerCase().includes(term),
    ),
  );
}
