# 版本策略(Version strategy)

```
2.0.8:常规功能迭代发版(use for develop routine function)
2.0.8.1:快速修复版本发版(use for fix bug for target version)
```

# 分支策略(Branch strategy)

```
功能分支(feature branch):feature-v2.0.8
bug分支(fixbug branch):fixbug-v2.0.8
```

# 提供pull request(provide pull request)
```
从master拉取分支,并注明当时分支所属版本号,从build.gradle从的version属性获取
(Pull the branch from the master, and indicate the version number of the branch at the time, and get it from the version property of build.gradle)

功能分支(feature branch):feature-addParam-v2.0.8
bug分支(fixbug branch):fixbug-npe-v2.0.8
```