# Auto domain

Version required: <Badge text="2023.1.4" />

## Trigger point

When clicking <svg class="icon svg-icon" aria-hidden="true"><use xlink:href="#icon-restfulFastRequest"></use></svg> on the left side of the method,
if no domain is configured, it will be automatically created.

## Project name

The project name is taken from the api `module name`

Why? For example, there are two modules' APIs under the project, `Module-1` and `Module-2`. If the domain name value of `Module-1` is already stored and when clicking the <svg class="icon svg-icon" aria-hidden="true"><use xlink:href="#icon-restfulFastRequest"></use></svg> on the left of the API method under `Module-2`, the old version cannot automatically switch the project name.

It is recommended not to ==manually add the project name== later because the plugin cannot find the mapping relationship, which may easily result in using the domain name of `Module-1` instead of the domain name of `Module-2` when saving the API of `Module-2`.

Therefore, another benefit of auto domain name is the ==automatic switching of API domain names==. If it does not exist, it will be automatically generated. If there is a need for improvement, developers only need to modify the domain name value! The plugin will automatically adapt!

## Configuration file parsing logic

1. Read `application.yml` or `bootstrap.yml`. If the configuration `server.port` or `server.servlet.context-path` can be found, take the configuration.
2. If the above configuration files cannot find `server.port` or `server.servlet.context-path`, read `spring.profiles.active` as the variable `env`. Then look for `application-env.yml` or `bootstrap-env.yml` to find the configuration `server.port` or `server.servlet.context-path`.
3. The logic for `properties` files is similar.
4. If the above configurations cannot be found, use `port=8080, context-path=/`.
5. The project name is taken from the `module name`.
6. `spring.profiles.active` does not support Maven variables parse, for example `spring.profiles.active=@active.env@`.

## Config switch

![autoDomain](/img/2023.1.4/autoDomain_en.png)
