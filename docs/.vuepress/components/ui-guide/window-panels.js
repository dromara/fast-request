// Action order and icons follow the four Pro tool windows. An empty doc keeps
// an action in the preview with its explanation; add a doc when one exists.
function action(id, icon, label, description, doc = "", local = false) {
  return { id, icon, label, description, doc, local };
}

const expand = action(
  "expand",
  "expandAll",
  ["全部展开", "Expand all"],
  ["展开当前树中的所有分组。", "Expand all groups in this tree."],
  "",
  true,
);
const collapse = action(
  "collapse",
  "collapseAll",
  ["全部折叠", "Collapse all"],
  ["收起当前树中的分组。", "Collapse the groups in this tree."],
  "",
  true,
);
const remove = action(
  "remove",
  "remove",
  ["删除选中项", "Delete selection"],
  ["删除列表中选中的已保存条目。", "Delete the selected saved entries."],
  "",
  true,
);

export const windowPanelActions = {
  apis: [
    remove,
    action(
      "apisRefresh",
      "refresh",
      ["刷新 API 列表", "Refresh APIs"],
      ["重新加载已保存的 API 列表。", "Reload the saved API list."],
      "features/apiList",
    ),
    expand,
    collapse,
    action(
      "apisDocument",
      "doc",
      ["同步在线 API 文档", "Sync online API documents"],
      [
        "将选中的模块、类或 API 同步到在线文档。",
        "Sync selected modules, classes or APIs to online documentation.",
      ],
      "features/onlineDocument",
    ),
    action(
      "apisExport",
      "share",
      ["批量导出 API 文档", "Batch export API documents"],
      [
        "导出所选 API 或目录的 Markdown 文档。",
        "Export Markdown documentation for selected APIs or folders.",
      ],
      "features/batchExportApiDoc",
    ),
    action(
      "apisOpenApi",
      "openApi",
      ["OpenAPI 导出", "Export OpenAPI"],
      ["将所选 API 导出为 OpenAPI。", "Export selected APIs as OpenAPI."],
      "features/openApi",
    ),
    action(
      "apisApifox",
      "apifox",
      ["同步 API 到 Apifox", "Sync APIs to Apifox"],
      ["将选中的 API 同步到 Apifox。", "Sync the selected APIs to Apifox."],
      "features/apifox",
    ),
    action(
      "apisMark",
      "mark",
      ["标记筛选", "Filter marked APIs"],
      ["切换列表中已标记 API 的筛选。", "Toggle filtering of marked APIs."],
      "",
      true,
    ),
    action(
      "apisSwitchTab",
      "runSwitchTab",
      ["运行后切换 Tab", "Switch tab after running"],
      [
        "设置运行 API 后是否切换到 Request。",
        "Choose whether running an API opens Request.",
      ],
      "",
      true,
    ),
    action(
      "apisJumpMethod",
      "runJumpMethod",
      ["运行后定位方法", "Locate method after running"],
      [
        "设置运行 API 时是否定位到源码方法。",
        "Choose whether running an API locates its source method.",
      ],
      "",
      true,
    ),
    action(
      "apisHelp",
      "help",
      ["API 列表文档", "API list documentation"],
      ["查看已保存 API 的使用说明。", "Read about saved APIs."],
      "features/apiList",
    ),
  ],
  nav: [
    action(
      "navRefresh",
      "refresh",
      ["刷新 API 导航", "Refresh API navigation"],
      [
        "扫描项目并刷新 API 导航树。",
        "Scan the project and refresh its API tree.",
      ],
      "features/apiNavigateTree",
    ),
    expand,
    collapse,
    action(
      "navModule",
      "groupByModule",
      ["筛选模块", "Filter modules"],
      [
        "选择 API 导航中需要显示的模块。",
        "Choose which modules appear in API navigation.",
      ],
      "",
      true,
    ),
    action(
      "navMethod",
      "groupByMethod",
      ["筛选请求方法", "Filter HTTP methods"],
      [
        "按 GET、POST 等请求方法筛选 API。",
        "Filter APIs by HTTP methods such as GET and POST.",
      ],
      "",
      true,
    ),
    action(
      "navSettings",
      "setting",
      ["导航刷新设置", "Navigation refresh settings"],
      [
        "配置 API 导航树的刷新行为。",
        "Configure how the API navigation tree refreshes.",
      ],
      "",
      true,
    ),
    action(
      "navHelp",
      "help",
      ["API 导航文档", "API navigation documentation"],
      [
        "查看 API 导航树的搜索与定位说明。",
        "Read about searching and locating APIs in the navigation tree.",
      ],
      "features/apiNavigateTree",
    ),
  ],
  run: [
    action(
      "runLocate",
      "locate",
      ["定位方法", "Locate method"],
      ["定位到当前方法的源码。", "Locate the current method in source."],
      "features/runMethod",
    ),
    action(
      "runRegenerate",
      "redo",
      ["重新生成参数", "Regenerate parameters"],
      [
        "根据当前方法签名重新生成参数。",
        "Regenerate arguments from the current method signature.",
      ],
      "features/runMethod",
    ),
    action(
      "runClear",
      "clear",
      ["清空方法", "Clear method"],
      [
        "清空当前方法、参数和响应。",
        "Clear the current method, arguments and response.",
      ],
      "features/runMethod",
    ),
    [
      action(
        "runSave",
        "save",
        ["保存方法", "Save method"],
        ["保存当前方法和参数。", "Save the current method and arguments."],
        "features/runMethod",
      ),
      action(
        "runSaveGroup",
        "saveGroup",
        ["保存参数组", "Save parameter group"],
        [
          "将当前参数保存为同一方法的另一个参数组。",
          "Save another argument group for the same method.",
        ],
        "features/runMethod",
      ),
    ],
    action(
      "runGroup",
      "apiParamGroupNew",
      ["切换参数组", "Switch parameter group"],
      [
        "切换当前方法已保存的参数组。",
        "Switch between the method's saved argument groups.",
      ],
      "features/runMethod",
    ),
    action(
      "runTab",
      "storeData",
      ["在新 Tab 中打开方法", "Open method in a new tab"],
      [
        "在独立 Tab 中打开当前方法调用。",
        "Open the current method invocation in a separate tab.",
      ],
      "",
      true,
    ),
    action(
      "runHistory",
      "history",
      ["方法调用历史", "Method invocation history"],
      [
        "查看和恢复历史方法调用。",
        "Browse and restore previous method invocations.",
      ],
      "",
      true,
    ),
    action(
      "runLibrary",
      "libraryFolder",
      ["脚本库", "Script library"],
      ["管理可复用的 RunMethod 脚本。", "Manage reusable RunMethod scripts."],
      "",
      true,
    ),
    action(
      "runProjectScripts",
      "script",
      ["项目脚本", "Project scripts"],
      [
        "管理项目级别的 RunMethod 前置和后置脚本。",
        "Manage project-level RunMethod pre- and post-invocation scripts.",
      ],
      "",
      true,
    ),
    action(
      "runHelp",
      "help",
      ["RunMethod 文档", "RunMethod documentation"],
      [
        "查看方法调用、参数和响应的说明。",
        "Read about invoking methods, arguments and results.",
      ],
      "features/runMethod",
    ),
  ],
  methods: [remove, expand, collapse],
};

export const windowPanelToggles = [
  "apisMark",
  "apisSwitchTab",
  "apisJumpMethod",
];

export const runMethodScriptActions = [
  {
    ...action(
      "runAddScript",
      "add",
      ["添加脚本", "Add script"],
      [
        "添加当前阶段的脚本，或引用脚本库中的脚本。",
        "Add a script for this stage or reference one from the script library.",
      ],
      "",
      true,
    ),
    caption: true,
  },
];

export function filterPanelTree(query, labels) {
  const term = query.trim().toLocaleLowerCase();
  return (
    !term || labels.some((label) => label.toLocaleLowerCase().includes(term))
  );
}
