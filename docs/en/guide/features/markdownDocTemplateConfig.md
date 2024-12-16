# Markdown doc template config

Version required: <Badge text="2023.2.2+"/>

Provides a custom format for local markdown and [Online API](./apiDocSync.md) doc.

## Tech stack

1. [Velocity](https://velocity.apache.org/)
2. Markdown

## Build-in variable

### Content variable

| Key                    | Parameter type                | Description                                                                                                          |
| :--------------------- | :---------------------------- | :------------------------------------------------------------------------------------------------------------------- |
| url                    | String                        | Final url                                                                                                            |
| originUrl              | String                        | Origin url, contains the name of the original path variable                                                          |
| namingPolicy           | String                        | API namingPolicy, fixed value \[1.byMethodName:by java method name 2.byDoc:by JavaDoc or Swagger method description] |
| methodName             | String                        | Java method name                                                                                                     |
| methodDescription      | String                        | Method description, comes from javadoc or Swagger method description                                                 |
| methodType             | String                        | Method type, for example Get,Post,Put,Delete,Patch                                                                   |
| headerList             | List\<[Header](#header)\>     | Header params                                                                                                        |
| pathKeyValueList       | List\<[KeyValue](#keyvalue)\> | Url Path params                                                                                                      |
| urlParamsKeyValueList  | List\<[KeyValue](#keyvalue)\> | Url Query params                                                                                                     |
| multipartKeyValueList  | List\<[KeyValue](#keyvalue)\> | Multipart params                                                                                                     |
| urlEncodedKeyValueList | List\<[KeyValue](#keyvalue)\> | Form Url-Encoded params                                                                                              |
| jsonParam              | String                        | Json params                                                                                                          |
| jsonParamDocument      | String                        | Json params document                                                                                                 |
| responseExample        | String                        | Response                                                                                                             |
| returnDocument         | String                        | Response params document                                                                                             |

### Syntax variables

| Key | Description     |
| :-- | :-------------- |
| H1  | Markdown #      |
| H2  | Markdown ##     |
| H3  | Markdown ###    |
| H4  | Markdown ####   |
| H5  | Markdown #####  |
| H6  | Markdown ###### |

## Parameter type

### Header

| Key     | Parameter type | Description     |
| :------ | :------------- | :-------------- |
| enabled | Boolean        | Enable flag     |
| type    | String         | Key of Header   |
| value   | String         | Value of Header |

### KeyValue

| Key     | Parameter type | Description                                                       |
| :------ | :------------- | :---------------------------------------------------------------- |
| enabled | Boolean        | Enable flag                                                       |
| key     | String         | Parameter key                                                     |
| type    | String         | Parameter type, fixed value \[Object Array String Number Boolean] |
| value   | Object         | Parameter value                                                   |
| comment | String         | Parameter description                                             |

## Default template

````velocity
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

````
