## 版本策略(Version strategy)

- 2.0.8: 常规功能迭代发版(use for develop routine function)
- 2.0.8.1: 快速修复版本发版(use for fix bug for target version)

## 分支策略(Branch strategy)

- 功能分支(feature branch): feature-v2.0.8
- bug 分支(fixbug branch): fixbug-v2.0.8

## 提供 pull request(provide pull request)

从 master 拉取分支，并注明当时分支所属版本号，从 build.gradle 从的 version 属性获取

> Pull the branch from the master， and indicate the version number of the branch at the time， and get it from the version property of build.gradle)
