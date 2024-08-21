# Project-level domain config

This function allows developers to add a domain quickly.

Version required: <Badge text="2023.1.1+"/>

::: caution Suggestion
It is recommended to use project-level domain config，original config [<ColorIcon icon="tool" /> Setting manager](./settingManager.md) will be used as global config and will have a lower priority than project level config.

The advantage of using project-level config is that when there are many projects, the drop-down box options will be reduced (excluding the config of other projects)

:::

::: tip

1. **Project name**: The project name of the API. If [auto domain](./autoDomain.md) is turned on, for single-module projects, the project name will be automatically set to the project name, and for multi-modules, it will be the module name. Manual replacement is not recommended.
2. **Env**: Different environments, for example, **local、dev、test、prod**.
3. **Domain**: The domain value corresponding to the project name and environment name.

:::

## Auto domain or Generate by config file

1. Refer to this [document](./autoDomain.md) for auth domain, and finally only need to modify the domain name value if not the value you want.
2. Generate by config file: Select `application.properties` or `application.yml` in the project, click on the icon <ColorIcon icon="restfulFastRequest" />.

![projectLevelDomainConfig](/img/2023.1.1/projectLevelDomainConfig_en.png "Domain config")

> How value comes from

1. For file with environment names such as `application-dev.properties`, plugin will automatically adapt the variables between `-` and `. ` as environment names.
2. Project name comes from module name
3. Domain = `http://localhost:port/context-path`

## Manually configure the domain.

![projectLevelDomainDialog](/img/2023.1.1/projectLevelDomainDialog_en.png "Domain list")

Click on the Project-level domain config to manually enter the corresponding value.
