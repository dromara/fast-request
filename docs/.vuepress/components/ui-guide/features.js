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
    "shortcuts",
    "getstarted/shortcut",
    ["快捷键", "Shortcuts"],
    [
      "查看插件常用操作对应的快捷键。",
      "Look up keyboard shortcuts for common plugin actions.",
    ],
  ),
  feature(
    "settings",
    "setting",
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
    "releaseNotes",
    "whatsnew",
    "history",
    ["更新记录", "Release notes"],
    [
      "查看各版本的功能与修复记录。",
      "Read feature changes and fixes for each release.",
    ],
  ),
  feature(
    "faq",
    "help",
    "faq",
    ["常见问题", "FAQ"],
    ["查看插件使用中的常见问题与解答。", "Find answers to common plugin questions."],
  ),
  feature(
    "updates",
    "pluginUpdate",
    "history",
    ["检查更新", "Check for updates"],
    ["查看插件版本和更新记录。", "View plugin versions and release notes."],
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
    ["同步 API 到 Apifox", "Sync API to Apifox"],
    [
      "将已保存的 API 同步到映射的 Apifox 项目。",
      "Synchronize a saved API to the mapped Apifox project.",
    ],
  ),
  feature(
    "apifoxEnvironment",
    "apifoxEnvironment",
    "features/apifox",
    ["同步 Apifox 环境", "Sync Apifox environments"],
    [
      "将项目环境的域名、变量和全局参数同步到 Apifox。",
      "Synchronize project environment domains, variables and global parameters with Apifox.",
    ],
  ),
  feature(
    "apifoxSettings",
    "syncConfig",
    "features/apifox",
    ["Apifox 同步配置", "Apifox sync settings"],
    [
      "配置 Apifox 项目映射，以及保存 API 后自动同步。",
      "Configure Apifox project mappings and automatic synchronization after saving an API.",
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
    "postmanEnvironment",
    "pmSyncEnvironment",
    "features/postmanSync",
    ["同步 Postman 环境", "Sync Postman environments"],
    [
      "将环境配置同步到 Postman。",
      "Synchronize environment settings with Postman.",
    ],
  ),
  feature(
    "postmanSettings",
    "syncConfig",
    "features/postmanSync",
    ["Postman 同步设置", "Postman sync settings"],
    ["配置 Postman 同步。", "Configure Postman synchronization."],
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
    "redo",
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
    "curl_new",
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
    ["导出 Markdown 文档", "Export Markdown documentation"],
    [
      "将当前 API 文档导出为 Markdown 格式。",
      "Export the current API documentation in Markdown format.",
    ],
  ),
  feature(
    "exportHtml",
    "html",
    "features/shareApiDoc",
    ["导出 HTML 文档", "Export HTML documentation"],
    [
      "将当前 API 文档导出为 HTML 格式。",
      "Export the current API documentation in HTML format.",
    ],
  ),
  feature(
    "exportWord",
    "word",
    "features/shareApiDoc",
    ["导出 Word 文档", "Export Word documentation"],
    [
      "将当前 API 文档导出为 Word 文档。",
      "Export the current API documentation as a Word document.",
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
    "syncConfig",
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
    "localMode",
    "localMode",
    "history#_2025-1-7",
    ["本地模式", "Local mode"],
    [
      "设置项目的 .fastRequest 目录是否参与 Git 管理。",
      "Control whether the project's .fastRequest directory is tracked by Git.",
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
    "bulkEdit",
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
    "aiRegenerate",
    "openai",
    "features/aiParameterOptimization",
    ["全部重新生成参数", "Regenerate all parameters"],
    [
      "使用 AI 重新生成当前参数表中的全部参数值。",
      "Use AI to regenerate all values in the current parameter table.",
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
  feature(
    "addHeader",
    "add",
    "features/quickAddHeader",
    ["添加请求头", "Add header"],
    ["为请求添加 Header。", "Add a header to a request."],
  ),
  feature(
    "addEnvironment",
    "addEnvironmentVariable",
    "features/environment",
    ["添加环境变量", "Add environment variable"],
    ["将参数保存为环境变量。", "Save a parameter as an environment variable."],
  ),
  feature(
    "autoDomain",
    "fastRequest",
    "features/autoDomain",
    ["自动域名", "Auto domain"],
    [
      "根据模块名和项目配置自动创建、切换 API 域名。",
      "Create and switch API domains using module names and project configuration.",
    ],
  ),
  feature(
    "apiDocSync",
    "github",
    "features/apiDocSync",
    ["API 文档同步", "API document sync"],
    [
      "将 API 文档同步到 GitHub、Gitee 或 GitLab 仓库。",
      "Synchronize API documentation to GitHub, Gitee or GitLab repositories.",
    ],
  ),
  feature(
    "makeIconMove",
    "fastRequest",
    "features/makeIconMove",
    ["让图标动起来", "Make icon move"],
    [
      "设置插件图标的颜色与动画效果。",
      "Configure the plugin icon color and animation.",
    ],
  ),
  feature(
    "eula",
    "eula",
    "eula",
    ["最终用户许可协议", "End user license agreement"],
    ["查看插件的 EULA 使用条款。", "Read the plugin EULA and terms of use."],
  ),
  feature(
    "batchExportApiDoc",
    "share",
    "features/batchExportApiDoc",
    ["批量导出 API 文档", "Batch export API documents"],
    [
      "选中文件夹，批量导出其中的 API 文档。",
      "Select a folder to export its API documentation in a batch.",
    ],
  ),
  feature(
    "batchGenerateApi",
    "apis",
    "features/batchGenerateApi",
    ["批量生成 API", "Batch API generation"],
    [
      "按 Controller 类或源码包批量生成并保存 API。",
      "Generate and save APIs from a Controller class or source package.",
    ],
  ),
  feature(
    "swaggerDefaultValueParse",
    "code",
    "features/swaggerDefaultValueParse",
    ["Swagger 默认值", "Swagger default values"],
    [
      "读取 Swagger 注解中的示例值与默认值，生成请求参数。",
      "Generate request parameters from examples and defaults in Swagger annotations.",
    ],
  ),
  feature(
    "autoDescription",
    "class",
    "features/autoDescription",
    ["API 自动生成注释", "Auto description"],
    [
      "从字段注释生成参数描述，并切换 Description 列的显示。",
      "Generate parameter descriptions from field comments and toggle the Description column.",
    ],
  ),
  feature(
    "apiPreview",
    "fastRequest",
    "features/apiPreview",
    ["API 一览表预览", "API list preview"],
    [
      "按路径关键字搜索 API，双击条目定位源码方法。",
      "Search APIs by path keywords and double-click an entry to locate its source method.",
    ],
  ),
  feature(
    "apiCommentPreview",
    "class",
    "features/apiCommentPreview",
    ["API 注释预览", "API comment preview"],
    [
      "配置并查看类与导航树中的 API 注释。",
      "Configure and view API comments in classes and the navigation tree.",
    ],
  ),
  feature(
    "parseStrategy",
    "ignored",
    "features/parseStrategy",
    ["字段解析", "Field parsing"],
    [
      "配置类型映射，通过注解、修饰符或字段名忽略字段生成。",
      "Configure type mappings and exclude fields using annotations, modifiers or field names.",
    ],
  ),
  feature(
    "jsonGrammarCheck",
    "json",
    "features/jsonGrammarCheck",
    ["JSON 语法检查", "JSON syntax check"],
    [
      "检查 JSON 语法错误，并格式化 JSON 内容。",
      "Check JSON syntax errors and format JSON content.",
    ],
  ),
];

// Include every configurable action in the current Pro toolbar, even when its
// default configuration hides it. Source: CustomMainToolbarView / FastRequestToolWindow.
export const guideLayout = {
  windowTabs: ["debug", "apis", "nav", "run", "methods"],
  titleActions: ["updates", "shortcuts", "settings", "fullscreen", "help"],
  helpActions: ["help", "releaseNotes", "eula", "faq"],
  toolbarGroups: [
    [["toolbar"], ["domain"], ["global"], ["apis"], ["nav"]],
    [
      ["search"],
      ["debug", "download"],
      ["stop"],
      ["save", "saveGroup", "temporary"],
      ["document", "documentSettings"],
      ["apifox", "apifoxEnvironment", "apifoxSettings"],
      ["postman", "postmanEnvironment", "postmanSettings"],
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
      ["export", "exportHtml", "exportWord", "openApi", "template"],
    ],
    [["refresh"], ["localMode"], ["report"], ["aiSettings"]],
  ],
  parameterActions: ["commonHeaders", "presets", "bulk", "auth"],
  parameterAiActions: ["ai", "aiRegenerate"],
};

export const guideCategories = [
  {
    id: "debugging",
    label: ["请求调试", "Request debugging"],
    features: ["debug", "download", "stop", "regenerate", "clear"],
  },
  {
    id: "saving",
    label: ["请求保存与复用", "Request saving and reuse"],
    features: ["save", "saveGroup", "group", "temporary", "history"],
  },
  {
    id: "parameters",
    label: ["参数、脚本与 AI", "Parameters, scripts and AI"],
    features: ["ai", "aiRegenerate", "function", "script", "aiSettings"],
  },
  {
    id: "configuration",
    label: ["环境与配置", "Environment and configuration"],
    features: [
      "domain",
      "global",
      "environment",
      "addEnvironment",
      "settings",
      "refresh",
      "localMode",
    ],
  },
  {
    id: "integrations",
    label: ["导入与集成", "Import and integrations"],
    features: [
      "curlImport",
      "copyCurl",
      "copyUrl",
      "apifox",
      "apifoxEnvironment",
      "apifoxSettings",
      "postman",
      "postmanEnvironment",
      "postmanSettings",
    ],
  },
  {
    id: "documentation",
    label: ["文档与分享", "Documentation and sharing"],
    features: [
      "document",
      "documentSettings",
      "export",
      "exportHtml",
      "exportWord",
      "openApi",
      "template",
    ],
  },
  {
    id: "headers",
    label: ["请求头与认证", "Headers and authentication"],
    features: [
      "addHeader",
      "headers",
      "presets",
      "commonHeaders",
      "bulk",
      "auth",
    ],
  },
  {
    id: "workspace",
    label: ["工作区与辅助", "Workspace and utilities"],
    features: [
      "apis",
      "nav",
      "run",
      "methods",
      "search",
      "locate",
      "tabs",
      "toolbar",
      "fullscreen",
      "report",
      "help",
      "shortcuts",
      "updates",
      "releaseNotes",
      "faq",
    ],
  },
  {
    id: "additional",
    label: ["其他功能", "Additional features"],
    features: [
      "autoDomain",
      "apiDocSync",
      "makeIconMove",
      "eula",
      "batchExportApiDoc",
      "batchGenerateApi",
      "swaggerDefaultValueParse",
      "autoDescription",
      "apiPreview",
      "apiCommentPreview",
      "parseStrategy",
      "jsonGrammarCheck",
    ],
  },
];

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

export function groupGuideFeatures(query = "") {
  const matches = new Map(
    searchGuideFeatures(query).map((item) => [item.id, item]),
  );
  return guideCategories
    .map((category) => ({
      id: category.id,
      label: category.label,
      items: category.features
        .filter((id) => matches.has(id))
        .map((id) => matches.get(id)),
    }))
    .filter((category) => category.items.length > 0);
}
