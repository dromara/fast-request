# Project-level domain config

This function allows developers to add a domain quickly.

Version required: <Badge text="2023.1.1+"/>

::: danger Suggestion
It is recommended to use project-level domain config，original config [<svg class="icon svg-icon" aria-hidden="true"><use xlink:href="#icon-tool"></use></svg> Setting manager](./settingManager.md) will be used as global config and will have a lower priority than project level config.

The advantage of using project-level config is that when there are many projects, the drop-down box options will be reduced (excluding the config of other projects)

:::

**How to**: Select `application.properties` or `application.yml` in the project, go through the configuration file and click on the icon <svg class="icon svg-icon" aria-hidden="true"><use xlink:href="#icon-restfulFastRequest"></use></svg>.

![projectLevelDomainConfig](/img/2023.1.1/projectLevelDomainConfig_en.png "Domain config")

![projectLevelDomainDialog](/img/2023.1.1/projectLevelDomainDialog_en.png "Domain list")

::: tip Value taken

1. For file with environment names such as `application-dev.properties`, plugin will automatically adapt the variables between `-` and `. ` as environment names.
2. Project name comes from module name
3. Domain = `http://localhost:port/context-path`

:::
