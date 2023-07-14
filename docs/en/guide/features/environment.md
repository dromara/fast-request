# Environment

Version required: <Badge text="2023.1.8" />

In Environment, you can store variables and use `{{key}}` to replace fixed or magic values elsewhere.

You can use `{{key}}` in this places:
* Url
* Headers
* Path Params
* URL Params
* Body
    * JSON
    * Form URL-Encoded
    * Multipart

## Define
The Tab value of Environment is taken from the project domain config. Key needs to satisfy the regular expression `^[a-z A-Z_][a-z A-Z 0-9_]`

![environment](/img/2023.1.8/environment.png)

::: tip Tips
After focusing on the table, you can enter the key value to quickly locate the rows
:::


## Usage
Enter `{{key}}}` directly in Value column to replace the fixed value or magic value, when value replaced, if the environment contains this key, it will be highlighted in green. The current Environment pop value can be displayed after 500 ms of mouse hovering.

![useEnvironmentVariable](/img/2023.1.8/useEnvironmentVariable.png)

You can also select a single row and click <ColorIcon icon="environment" /> and select from the list.

![chooseEnvironmentVariable](/img/2023.1.8/chooseEnvironmentVariable.png)

::: tip Tip
After focusing on the table, you can enter the key value to quickly locate the rows
:::

## Script
参考 [Script -> demo -> Set an Environment variable](../script.md#demo)

![environmentScript](/img/2023.1.8/environmentScript.png)