---
title: Skills
icon: skills
---

[[toc]]

## Url error

![urlError](/img/skill/urlError_en.png)

<Badge text="Solution" type="tip" />

1. Configure the correct domain name and enable the 2 drop down boxes for project and environment
2. Set the correct url, for example to replace the variable {id} on the url, you can replace the variable in the Path Param tab

## Multi-file upload

Enter multiple values and set the field type to file

![multiFileUpload](/img/skill/multiFileUpload.png)

## Pass text/plain param in body

Please add **Content-Type: text/plain** in the header

Consider using add header by [`Common header`](./features/commonHeader.md)

## No controller, how to send a request

Refer to [Temporary request](./features/tempRequest.md)


## API doc sync
When you modify your method, the api returned from the APIs list is the API saved before you updated it. At this time, if you want to ensure that the online API document is your modified one,
you need to click <svg class="icon svg-icon" aria-hidden="true"><use xlink:href="#icon-saveNew"></use></svg>(save) or click <svg class="icon svg-icon" aria-hidden="true"><use xlink:href="#icon-restfulFastRequest"></use></svg> again and then sync api doc.