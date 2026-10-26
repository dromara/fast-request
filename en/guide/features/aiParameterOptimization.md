---
url: /en/guide/features/aiParameterOptimization.md
---
# AI Parameter Optimization&#x20;

AI Parameter Optimization uses the API path, parameter names, types, and field descriptions to replace random values with realistic business examples. Results are applied automatically and can be undone.

## Configuration

Configure an AI provider, API key, and model in **AI API Key Management** first. See [AI](./ai.md) for provider configuration.

If the API key is invalid or expired, or the provider configuration is incomplete, the error notification provides an action that opens AI settings.

![AI Settings](/img/2026.1.1/aiSetting_en.png)

## Manual optimization

The AI optimization action is available in these parameter editors:

* Path Param
* URL Param
* Form URL-Encoded
* Form-data
* Raw JSON request body
* RunMethod parameters

Click the AI action in the parameter toolbar to optimize the current values. A progress bar is displayed at the bottom of the window while the request is running.

![AI Parameter Optimization](/img/2026.1.1/aiParameterOptimization_en.png)

## Optimize automatically after API generation

Enable **Optimize parameters after API generation** in **AI API Key Management**. Every API generation first creates the normal parameters, then invokes AI and applies the optimized values automatically.

This is not a one-time state. Generating another API or regenerating the current API triggers optimization for the new result again.

## Automatic application and undo

After optimization, click **Undo** in the result notification to restore the values changed by that AI operation.

Undo only restores fields that still contain the AI result. If a field was edited manually after optimization, that later edit is preserved.

## Batch generation

When automatic optimization is enabled, [batch API generation](./batchGenerateApi.md) optimizes each API independently:

![Automatic AI optimization](/img/2026.1.1/aiAutoOptimization_en.png)

* A failure for one API does not stop later APIs.
* A failed optimization keeps the normally generated values and the API is still saved.
* The completion notification reports generation, AI optimization, and online-document synchronization separately.
* Batch AI changes can also be undone in one operation.

When [automatic Online Document synchronization](./onlineDocument.md) is enabled, its example values use the final AI-optimized and saved request data.
