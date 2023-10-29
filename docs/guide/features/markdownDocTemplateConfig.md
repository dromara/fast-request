# Markdown文档模板配置

版本要求: <Badge text="2023.2.2+"/>

提供了导出文档和[Api在线文档](./apiDocSync.md)的自定义格式实现.

## 实现技术

1. [Velocity](https://velocity.apache.org/)
2. Markdown

## 内置变量

### 内容变量

| 变量                     | 参数类型                          | 说明                                                            |
|:-----------------------|:------------------------------|:--------------------------------------------------------------|
| url                    | String                        | 最终url                                                         |
| originUrl              | String                        | 原始url,包含路径原值变量名                                               |
| namingPolicy           | String                        | 命名策略,固定值[1.byMethodName:根据方法名 2.byDoc:根据JavaDoc或者Swagger方法描述] |
| methodName             | String                        | Java方法名                                                       |
| methodDescription      | String                        | 方法描述,来源javadoc或者Swagger方法描述                                   |
| methodType             | String                        | 方法类型,例如Get,Post,Put,Delete,Patch                              |
| headerList             | List\<[Header](#header)\>     | 头参数                                                           |
| pathKeyValueList       | List\<[KeyValue](#keyvalue)\> | Url路径参数                                                       |
| urlParamsKeyValueList  | List\<[KeyValue](#keyvalue)\> | Url Query参数                                                   |
| multipartKeyValueList  | List\<[KeyValue](#keyvalue)\> | Multipart参数                                                   |
| urlEncodedKeyValueList | List\<[KeyValue](#keyvalue)\> | Form Url-Encoded参数                                            |
| jsonParam              | String                        | Json参数                                                        |
| jsonParamDocument      | String                        | Json参数文档                                                      |
| responseExample        | String                        | 响应                                                            |
| returnDocument         | String                        | 响应参数文档                                                        |

### 语法变量

| 变量 | 说明              |
|:---|:----------------|
| H1 | Markdown #      |
| H2 | Markdown ##     |
| H3 | Markdown ###    |
| H4 | Markdown ####   |
| H5 | Markdown #####  |
| H6 | Markdown ###### |

## 参数类型

### Header

| 变量      | 参数类型    | 说明            |
|:--------|:--------|:--------------|
| enabled | Boolean | 是否启用          |
| type    | String  | Header的key值   |
| value   | String  | Header的value值 |

### KeyValue

| 变量      | 参数类型    | 说明                                            |
|:--------|:--------|:----------------------------------------------|
| enabled | Boolean | 是否启用                                          |
| key     | String  | 参数Key                                         |
| type    | String  | 参数类型,固定格式[Object Array String Number Boolean] |
| value   | Object  | 参数Value                                       |
| comment | String  | 参数备注                                          |

## 默认模板

```velocity
    #if (${namingPolicy}=='byDoc')
    $H1 ${methodDescription}
    #else
    $H1 $!{methodName}
    
    $H3 Method description
    
    ```
    $!{methodDescription}
    ```
    #end
    
    > URL: $!{url}
    >
    > Origin Url: $!{originUrl}
    >
    > Type: $!{methodType}
    
    
    $H3 Request headers
    
    |Header Name| Header Value|
    |---------|------|
    #foreach( $h in ${headerList})
    |$h.type|$h.value|
    #end
    
    $H3 Parameters
    
    $H5 Path parameters
    
    | Parameter | Type | Value | Description |
    |---------|------|------|------------|
    #foreach( $node in ${pathKeyValueList})
    |$node.key|$!{node.type}|$!{node.value}|$!{node.comment}|
    #end
    
    
    $H5 URL parameters
    
    |Required| Parameter | Type | Value | Description |
    |---------|---------|------|------|------------|
    #foreach( $node in ${urlParamsKeyValueList})
    |$!{node.enabled}|$!{node.key}|$!{node.type}|$!{node.value}|$!{node.comment}|
    #end
    
    
    $H5 Body parameters
    
    $H6 JSON
    
    ```
    ${jsonParam}
    ```
    
    $H6 JSON document
    
    ```
    ${jsonParamDocument}
    ```
    
    
    $H5 Form URL-Encoded
    |Required| Parameter | Type | Value | Description |
    |---------|---------|------|------|------------|
    #foreach( $node in ${urlEncodedKeyValueList})
    |$!{node.enabled}|$!{node.key}|$!{node.type}|$!{node.value}|$!{node.comment}|
    #end
    
    
    $H5 Multipart
    |Required | Parameter | Type | Value | Description |
    |---------|---------|------|------|------------|
    #foreach( $node in ${multipartKeyValueList})
    |$!{node.enabled}|$!{node.key}|$!{node.type}|$!{node.value}|$!{node.comment}|
    #end
    
    
    $H3 Response
    
    $H5 Response example
    
    ```
    $!{responseExample}
    ```
    
    $H5 Response document
    ```
    $!{returnDocument}
    ```

```


