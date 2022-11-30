# Swagger default value

Version required: <Badge text="2022.1.4" />

Below are some examples.

**Priority**: swagger default value > config default value

:::: code-tabs

@tab swagger2

```java
* @ApiParam

@GetMapping(value="/test/{id}")
public String test3(@ApiParam(name = "id",example="2") @PathVariable("id") Integer id) {
        return "";
        }

@GetMapping(value="/test/{id}")
public String test3(@ApiParam(name = "id",defaultValue="2") @PathVariable("id") Integer id) {
        return "";
        }


        * @ApiImplicitParam

@ApiImplicitParams({
        @ApiImplicitParam(paramType="query",name="pageNo",dataType="String",required=true,value="pageNo",defaultValue="1"),
        @ApiImplicitParam(paramType="query",name="pageSize",dataType="String",required=true,value="pageSize",defaultValue="10")
})
@GetMapping(value="/testPage)
        public String testPage(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        return "";
        }


        * @ApiModelProperty
        @Data
        public class UserDto {
        @ApiModelProperty(example = "Bob")
        private String userName;
        }
```

@tab swagger3

```java
* @Parameter

@GetMapping(value="/test/{id}")
public String test3(@Parameter(name = "id",example="2") @PathVariable("id") Integer id) {
        return "";
        }

        * @Schema(swagger3)

@Data
public class UserDto {
    @Schema(example = "Bob")
    private String userName;
}
```

:::
