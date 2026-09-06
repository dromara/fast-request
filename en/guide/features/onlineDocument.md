---
url: /en/guide/features/onlineDocument.md
---
# Online Document&#x20;

Online Document collects saved Fast Request APIs into a browsable and editable documentation workspace. Request parameters, schemas, and response examples come from the saved request data and can be viewed locally or shared over the LAN.

## Open the workspace

Click **Online Document** in the **Request** toolbar. The left side groups and searches synchronized APIs. The right side displays and edits API metadata, request parameters, request bodies, response examples, and schemas.

::: tip Change required fields in bulk
Select multiple schema rows and press Space to mark or unmark them as required.
:::

## Automatic synchronization

Open **Online Document Settings** and enable **Automatically sync the online document after saving a request**. Both a normal request save and [batch API generation](./batchGenerateApi.md) will then synchronize the final saved data.

![Online Document settings](/img/2026.1.1/onlineDocumentSaveSetting_en.png)

When a document already exists, choose one of these strategies:

* **Overwrite existing document**: rebuild the document snapshot from the current request. AI-optimized example values are included.
* **Keep existing document and skip update**: preserve manually edited document content.

## Browser preview and sharing

Start the service from the workspace to open the document in a browser:

* Use the local address for local preview.
* Share the LAN address with team members on the same network.
* Use an automatically selected port or configure a fixed port.

![Online Document in a browser](/img/2026.1.1/onlineDocumentHtml_en.png)

## Recommended workflow

1. Generate and review an API in Request.
2. Use [AI parameter optimization](./aiParameterOptimization.md) when realistic examples are needed.
3. Save the request, or generate and save APIs for a class or package in bulk.
4. Review the workspace, then start the service to share it.

::: warning
The overwrite strategy replaces corresponding manual document edits with the latest request snapshot. Choose the skip strategy when those edits must be retained.
:::
