# Notes

## 升级

```sh
rm -rf ./node_modules && pnpm add @vuepress/client@next vue vuepress@next vuepress-theme-hope@next && pnpm i && pnpm up
```

## 预览文档

```sh
pnpm docs:dev
```

## 构建文档

```sh
pnpm docs:build
```

## 格式化文档

```sh
pnpm docs:lint
```

## 更新文档依赖

```sh
pnpm docs:update
```

## 部署

sudo sh ./deploy.sh

- 中: `\*(.*)\(`
- 英: `\(.*\)`
