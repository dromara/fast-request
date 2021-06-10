package io.github.kings1990.plugin.fastrequest.config;

public class Constant {
    public enum SpringMappingConfig {
        GET_MAPPING("org.springframework.web.bind.annotation.GetMapping", "GET"),
        POST_MAPPING("org.springframework.web.bind.annotation.PostMapping", "POST"),
        REQUEST_MAPPING("org.springframework.web.bind.annotation.RequestMapping", ""),
        DELETE_MAPPING("org.springframework.web.bind.annotation.DeleteMapping", "DELETE"),
        PUT_MAPPING("org.springframework.web.bind.annotation.PutMapping", "PUT"),
        PATCH_MAPPING("org.springframework.web.bind.annotation.PatchMapping", "PATCH");
        private final String code;
        private final String methodType;

        public String getCode() {
            return code;
        }

        public String getMethodType() {
            return methodType;
        }

        SpringMappingConfig(String code, String methodType) {
            this.code = code;
            this.methodType = methodType;
        }
    }

    public enum SpringControllerConfig {
        CONTROLLER("org.springframework.stereotype.Controller"),
        REST_CONTROLLER("org.springframework.web.bind.annotation.RestController");
        private final String code;

        public String getCode() {
            return code;
        }

        SpringControllerConfig(String code) {
            this.code = code;
        }
    }

    public enum SpringUrlParamConfig {
        PATH_VARIABLE("org.springframework.web.bind.annotation.PathVariable", 1),
        REQUEST_PARAM("org.springframework.web.bind.annotation.RequestParam", 2),
        REQUEST_BODY("org.springframework.web.bind.annotation.RequestBody", 3),
        MATRIX_VARIABLE("org.springframework.web.bind.annotation.MatrixVariable", 0),
        MODEL_ATTRIBUTE("org.springframework.web.bind.annotation.ModelAttribute", 0),
        REQUEST_HEADER("org.springframework.web.bind.annotation.RequestHeader", 0),
        REQUEST_PART("org.springframework.web.bind.annotation.RequestPart", 0),
        COOKIE_VALUE("org.springframework.web.bind.annotation.CookieValue", 0),
        SESSION_ATTRIBUTE("org.springframework.web.bind.annotation.SessionAttribute", 0),
        REQUEST_ATTRIBUTE("org.springframework.web.bind.annotation.RequestAttribute", 0);
        private final String code;

        /**
         * 1- path url参数  2-url参数&拼接 3-body参数 0-不参与
         */

        private final Integer parseType;

        public String getCode() {
            return code;
        }

        public Integer getParseType() {
            return parseType;
        }

        SpringUrlParamConfig(String code, Integer parseType) {
            this.code = code;
            this.parseType = parseType;
        }
    }

    public enum SpringParamTypeConfig {
        URL_PARAMS("URL Params"),
        JSON("JSON"),
        FORM_URL_ENCODED("Form URL-Encoded");

        private final String code;

        public String getCode() {
            return code;
        }

        SpringParamTypeConfig(String code) {
            this.code = code;
        }
    }


}
