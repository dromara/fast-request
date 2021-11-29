# FAQ

[[toc]]

## Why create Module group
1. If the module group is not created, then when saving the request, the saved request will be placed in the Default Group      
2. If you create a module group, when you save the request, it will be automatically classified into the corresponding Module Group according to the module where the current API is located.  
3. Of course, you can move the API to the corresponding smaller group by dragging  

## Why the plugin doesn't respond
Answer:Please configure the relevant configuration according to the steps introduced in the first chapter first, and then click the icon.  

## Idea freezes after clicking the icon
Answer:The entity class you designed is nested and recursive, the plugin does not support.

## Regeneration function
Designed to reset generated parameters,it will clear the parameters of the previous API,but does not include the save action.  
If your API has been saved and you want to change the parameters again, then you can click the regenerate button and remember to save again, otherwise the original parameters will be retained.  
(It is equivalent to operating a file, emptying the content of the file but not saving it, then you will still see the original file before modification)






## Console blocking the tool window
Answer:At present, when the tool window and console are visible at the same time, It does not support the display of tool windows prior to the console. So you can only adjust [view mode](https://www.jetbrains.com/help/idea/viewing-modes.html)
to control the view,Or use shortcut keys to quickly hide and show the console(shortcut:look at view->toolWindow->run/debug) to make all the content in the tool window visible。Of course, you can click to hide the Request part to see the Response.