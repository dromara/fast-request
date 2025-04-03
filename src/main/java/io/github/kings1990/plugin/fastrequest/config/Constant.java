/*
 *
 *  * End-User License Agreement (EULA) of Restful Fast Request
 *  * This End-User License Agreement ("EULA") is a legal agreement between you and Kings(darkings1990@gmail.com). Our EULA was created by EULA Template for Restful Fast Request.
 *  *
 *  * This EULA agreement governs your acquisition and use of our Restful Fast Request software ("Software") directly from Kings(darkings1990@gmail.com) or indirectly through a Kings(darkings1990@gmail.com) authorized reseller or distributor (a "Reseller").
 *  *
 *  * Please read this EULA agreement carefully before completing the installation process and using the Restful Fast Request software. It provides a license to use the Restful Fast Request software and contains warranty information and liability disclaimers.
 *  *
 *  * If you register for a free trial of the Restful Fast Request software, this EULA agreement will also govern that trial. By clicking "accept" or installing and/or using the Restful Fast Request software, you are confirming your acceptance of the Software and agreeing to become bound by the terms of this EULA agreement.
 *  *
 *  * If you are entering into this EULA agreement on behalf of a company or other legal entity, you represent that you have the authority to bind such entity and its affiliates to these terms and conditions. If you do not have such authority or if you do not agree with the terms and conditions of this EULA agreement, do not install or use the Software, and you must not accept this EULA agreement.
 *  *
 *  * This EULA agreement shall apply only to the Software supplied by Kings(darkings1990@gmail.com) herewith regardless of whether other software is referred to or described herein. The terms also apply to any Kings(darkings1990@gmail.com) updates, supplements, Internet-based services, and support services for the Software, unless other terms accompany those items on delivery. If so, those terms apply.
 *  *
 *  * License Grant
 *  * Kings(darkings1990@gmail.com) hereby grants you a personal, non-transferable, non-exclusive licence to use the Restful Fast Request software on your devices in accordance with the terms of this EULA agreement.
 *  *
 *  * You are permitted to load the Restful Fast Request software (for example a PC, laptop, mobile or tablet) under your control. You are responsible for ensuring your device meets the minimum requirements of the Restful Fast Request software.
 *  *
 *  * You are not permitted to:
 *  *
 *  * Edit, alter, modify, adapt, translate or otherwise change the whole or any part of the Software nor permit the whole or any part of the Software to be combined with or become incorporated in any other software, nor decompile, disassemble or reverse engineer the Software or attempt to do any such things
 *  * Copy this project and republish a new plugin in JetBrains Marketplace
 *  * Reproduce, copy, distribute, resell or otherwise use the Software for any commercial purpose
 *  * Allow any third party to use the Software on behalf of or for the benefit of any third party
 *  * Use the Software in any way which breaches any applicable local, national or international law
 *  * use the Software for any purpose that Kings(darkings1990@gmail.com) considers is a breach of this EULA agreement
 *  * Intellectual Property and Ownership
 *  * Kings(darkings1990@gmail.com) shall at all times retain ownership of the Software as originally downloaded by you and all subsequent downloads of the Software by you. The Software (and the copyright, and other intellectual property rights of whatever nature in the Software, including any modifications made thereto) are and shall remain the property of Kings(darkings1990@gmail.com).
 *  *
 *  * Kings(darkings1990@gmail.com) reserves the right to grant licences to use the Software to third parties.
 *  *
 *  * Termination
 *  * This EULA agreement is effective from the date you first use the Software and shall continue until terminated. You may terminate it at any time upon written notice to Kings(darkings1990@gmail.com).
 *  *
 *  * It will also terminate immediately if you fail to comply with any term of this EULA agreement. Upon such termination, the licenses granted by this EULA agreement will immediately terminate and you agree to stop all access and use of the Software. The provisions that by their nature continue and survive will survive any termination of this EULA agreement.
 *  *
 *  * Governing Law
 *  * This EULA agreement, and any dispute arising out of or in connection with this EULA agreement, shall be governed by and construed in accordance with the laws of cn.
 *
 */

package io.github.kings1990.plugin.fastrequest.config;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.intellij.openapi.util.Key;
import icons.PluginIcons;
import io.github.kings1990.plugin.fastrequest.model.MethodType;

import java.util.List;
import java.util.Map;

public class Constant {
    public static final String I18N_PATH = "io/github/kings1990/fastrequest/18n/fr";

    public enum FrameworkType {
        SPRING,
        JAX_RS;
    }

    public enum JaxRsMappingConfig {
        PATH("javax.ws.rs.Path", "");
        private final String code;
        private final String methodType;

        public String getCode() {
            return code;
        }

        public String getMethodType() {
            return methodType;
        }

        JaxRsMappingConfig(String code, String methodType) {
            this.code = code;
            this.methodType = methodType;
        }
    }

    public enum JaxRsMappingMethodConfig {
        GET("javax.ws.rs.GET", "GET"),
        POST("javax.ws.rs.POST", "POST"),
        DELETE("javax.ws.rs.DELETE", "DELETE"),
        PUT("javax.ws.rs.PUT", "PUT");
        private final String code;
        private final String methodType;

        public String getCode() {
            return code;
        }

        public String getMethodType() {
            return methodType;
        }

        JaxRsMappingMethodConfig(String code, String methodType) {
            this.code = code;
            this.methodType = methodType;
        }
    }

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

    public enum JaxRsUrlParamConfig {
        PATH_PARAM("javax.ws.rs.PathParam", 1),
        QUERY_PARAM("javax.ws.rs.QueryParam", 2),
        FORM_PARAM("javax.ws.rs.FormParam", 2),
        BEAN_PARAM("javax.ws.rs.BeanParam", 2),
        HEADER_PARAM("javax.ws.rs.HeaderParam", 0),
        COOKIE_PARAM("javax.ws.rs.CookieParam", 0),
        MATRIX_PARAM("javax.ws.rs.MatrixParam", 0),
        ;
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

        JaxRsUrlParamConfig(String code, Integer parseType) {
            this.code = code;
            this.parseType = parseType;
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

    public static class HttpStatusDesc {
        public static Map<Integer, String> STATUS_MAP
                = ImmutableMap.<Integer, String>builder()
                .put(0, "")
                .put(200, "OK")
                .put(201, "Created")
                .put(202, "Accepted")
                .put(203, "Non-Authoritative Information")
                .put(204, "No Content.")
                .put(205, "Reset Content")
                .put(206, "Partial Content")

                .put(300, "Multiple Choices")
                .put(301, "Moved Permanently")
                .put(302, "Temporary Redirect")
                .put(303, "See Other")
                .put(304, "Not Modified")
                .put(305, "Use Proxy")
                .put(307, "Temporary Redirect")
                .put(308, "Permanent Redirect")

                .put(400, "Bad Request")
                .put(401, "Unauthorized")
                .put(402, "Payment Required")
                .put(403, "Forbidden")
                .put(404, "Not Found")
                .put(405, "Method Not Allowed")
                .put(406, "Not Acceptable")
                .put(407, "Proxy Authentication Required")
                .put(408, "Request Time-Out")
                .put(409, "Conflict")
                .put(410, "Gone")
                .put(411, "Length Required")
                .put(412, "Precondition Failed")
                .put(413, "Request Entity Too Large")
                .put(414, "Request-URI Too Large")
                .put(415, "Unsupported Media Type")

                .put(500, "Internal Server Error")
                .put(501, "Not Implemented")
                .put(502, "Bad Gateway")
                .put(503, "Service Unavailable")
                .put(504, "Gateway Timeout")
                .put(505, "HTTP Version Not Supported")
                .build();
    }

    public static List<String> IGNORE_PARAM_PARSE_LIST = Lists.newArrayList(
            "javax.servlet.http.HttpServletRequest",
            "javax.servlet.http.HttpServletResponse",
            "org.springframework.ui.ModelMap"
    );

    public static List<String> SUPPORTED_ANNOTATIONS = Lists.newArrayList(
            "GetMapping", "PostMapping", "RequestMapping", "DeleteMapping", "PutMapping", "PatchMapping",
            "GET", "POST", "DELETE", "PUT"
    );

    public static List<MethodType> METHOD_TYPE_LIST = Lists.newArrayList(
            new MethodType("GET", PluginIcons.ICON_GET),
            new MethodType("POST", PluginIcons.ICON_POST),
            new MethodType("PUT", PluginIcons.ICON_PUT),
            new MethodType("DELETE", PluginIcons.ICON_DELETE),
            new MethodType("PATCH", PluginIcons.ICON_PATCH)
    );

    public static final String CN_DOC_DOMAIN = "https://dromara.gitee.io/fast-request";
    public static final String EN_DOC_DOMAIN = "https://dromara.org/fast-request/en";

    public static Key<Integer> KEY_FASTREQUEST = Key.create("FastRequest");
}
