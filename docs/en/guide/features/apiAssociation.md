# API group automatic association
Version required:<Badge text="2.0.6" /><Badge text="Deprecated" />

The API group is to save the saved apis into different groups to distinguish different apis,When in a multi-module project,plugin support scan modules in projects,and quickly add the module name to the group, this way will add the group to the root group

**v2.1.2 will automatically create module group**

![apiGroup](../../../.vuepress/public/img/apiGroup_en.gif)

:::tip Special Note
1. If the module group is not created, then when saving the request, the saved request will be placed in the Default Group
2. If you create a module group, when you save the request, it will be automatically classified into the corresponding Module Group according to the module where the current API is located.
3. Of course, you can move the API to the corresponding smaller group by dragging  
   :::

Module supports quick search(Put the cursor on the list and enter keywords)

![moduleSearch](../../../.vuepress/public/img/moduleSearch.gif)