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

package io.github.kings1990.plugin.fastrequest.generator.impl;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.PsiClassReferenceType;
import com.intellij.psi.impl.source.PsiMethodImpl;
import com.intellij.psi.impl.source.tree.java.PsiReferenceExpressionImpl;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.javadoc.PsiDocToken;
import com.intellij.psi.util.PsiUtil;
import com.siyeh.ig.psiutils.CollectionUtils;
import io.github.kings1990.plugin.fastrequest.config.Constant;
import io.github.kings1990.plugin.fastrequest.config.FastRequestComponent;
import io.github.kings1990.plugin.fastrequest.generator.FastUrlGenerator;
import io.github.kings1990.plugin.fastrequest.model.DataMapping;
import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;
import io.github.kings1990.plugin.fastrequest.model.ParamGroup;
import io.github.kings1990.plugin.fastrequest.model.ParamNameType;
import io.github.kings1990.plugin.fastrequest.parse.BodyParamParse;
import io.github.kings1990.plugin.fastrequest.parse.PathValueParamParse;
import io.github.kings1990.plugin.fastrequest.parse.RequestParamParse;
import io.github.kings1990.plugin.fastrequest.util.UrlUtil;
import io.github.kings1990.plugin.fastrequest.view.CommonConfigView;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SpringMethodUrlGenerator extends FastUrlGenerator {
    private FastRequestConfiguration config = FastRequestComponent.getInstance().getState();
    private static final Logger LOGGER = Logger.getInstance(CommonConfigView.class);
    private PathValueParamParse pathValueParamParse = new PathValueParamParse();
    private BodyParamParse bodyParamParse = new BodyParamParse();
    private RequestParamParse requestParamParse = new RequestParamParse();

    @Override
    public String generate(PsiElement psiElement) {
        ParamGroup paramGroup = config.getParamGroup();
        if (!(psiElement instanceof PsiMethod)) {
            return StringUtils.EMPTY;
        }
        PsiMethod psiMethod = (PsiMethod) psiElement;
        String methodUrl = getMethodRequestMappingUrl(psiMethod);
        String classUrl = getClassRequestMappingUrl(psiMethod);
        String originUrl = classUrl + "/" + methodUrl;
        originUrl = originUrl.replace("//", "/");
        paramGroup.setOriginUrl(originUrl);

        List<ParamNameType> methodUrlParamList = getMethodUrlParamList(psiMethod);
        List<ParamNameType> methodBodyParamList = getMethodBodyParamList(psiMethod);

        //pathParam
        LinkedHashMap<String, Object> pathParamMap = pathValueParamParse.parseParam(config, methodUrlParamList);

        //requestParam
        LinkedHashMap<String, Object> requestParamMap = requestParamParse.parseParam(config, methodUrlParamList);

        //bodyParam
        LinkedHashMap<String, Object> bodyParamMap = bodyParamParse.parseParam(config, methodBodyParamList);

        //methodType
        String methodType = getMethodType(psiMethod);

        String methodDescription = getMethodDescription(psiMethod);

        paramGroup.setPathParamMap(pathParamMap);
        paramGroup.setRequestParamMap(requestParamMap);
        paramGroup.setBodyParamMap(bodyParamMap);
        paramGroup.setMethodType(methodType);
        paramGroup.setMethodDescription(methodDescription);

        paramGroup.setClassName(((PsiMethodImpl) psiElement).getContainingClass().getQualifiedName());
        paramGroup.setMethod(psiMethod.getName());

        Module moduleForFile = ModuleUtil.findModuleForPsiElement(psiElement);
        if (moduleForFile != null) {
            String name = moduleForFile.getName();
            paramGroup.setModule(name);
        }

        return null;
    }


    private String buildPathParamUrl(String url, Map<String, Object> data) {
        List<String> paramNameList = UrlUtil.paramPathParam(url);
        if (paramNameList.isEmpty()) {
            return url;
        }
        for (String paramName : paramNameList) {
            Object value = data.get(paramName);
            if (value == null) {
                continue;
            }
            String paramNameWithSymbol = "{" + paramName + "}";
            url = url.replace(paramNameWithSymbol, value.toString());
        }
        return url;
    }


    @Override
    public String getMethodRequestMappingUrl(PsiMethod psiMethod) {
        Constant.SpringMappingConfig[] mappingConfigArray = Constant.SpringMappingConfig.values();
        PsiAnnotation annotationRequestMapping = null;
        for (Constant.SpringMappingConfig mappingConfig : mappingConfigArray) {
            String code = mappingConfig.getCode();
            annotationRequestMapping = psiMethod.getAnnotation(code);
            if (annotationRequestMapping != null) {
                break;
            }
        }
        if (annotationRequestMapping == null) {
            return StringUtils.EMPTY;
        }
        //默认取value,再取path
        PsiAnnotationMemberValue value = annotationRequestMapping.findDeclaredAttributeValue("value");
        value = value != null ? value : annotationRequestMapping.findDeclaredAttributeValue("path");
        if (value == null) {
            return StringUtils.EMPTY;
        }

        String url;
        if(value instanceof PsiReferenceExpression){
            PsiField psiField = (PsiField) ((PsiReferenceExpression)value).resolve();
            url = psiField == null ? "" : psiField.getInitializer() == null? "" : psiField.getInitializer().getText();
        } else if(value instanceof PsiArrayInitializerMemberValue){
            PsiAnnotationMemberValue[] initializers = ((PsiArrayInitializerMemberValue) value).getInitializers();
            if(initializers.length > 0){
                PsiAnnotationMemberValue initializer = ((PsiArrayInitializerMemberValue) value).getInitializers()[0];
                if(initializer instanceof PsiReferenceExpression){
                    PsiField psiField = (PsiField) ((PsiReferenceExpression)initializer).resolve();
                    url = psiField == null ? "" : psiField.getInitializer() == null? "" : psiField.getInitializer().getText();
                } else {
                    url = initializer.getText();
                }
            } else {
                url = "";
            }
        } else {
            url = value.getText();
        }
        List<DataMapping> urlReplaceMappingList = config.getUrlReplaceMappingList();
        for (DataMapping dataMapping : urlReplaceMappingList) {
            url = url.replace(dataMapping.getType(), dataMapping.getValue());
        }
        return url.replace("\"", "");
    }

    /**
     * 得到类的mappingUrl
     * //@Controller("xxController") @RequestMapping("url") or @RequestMapping("url/${fixed module name}/xxx")
     *
     * @param psiMethod psi的方法
     * @return {@link String }
     * @author Kings
     * @date 2021/05/23
     */
    public String getClassRequestMappingUrl(PsiMethod psiMethod) {
        String mapping = Constant.SpringMappingConfig.REQUEST_MAPPING.getCode();
        PsiClass containingClass = psiMethod.getContainingClass();
        if (containingClass == null) {
            return StringUtils.EMPTY;
        }
        PsiAnnotation annotationMapping = containingClass.getAnnotation(mapping);;

        if (annotationMapping == null) {
            return StringUtils.EMPTY;
        }
        PsiAnnotationMemberValue value = annotationMapping.findDeclaredAttributeValue("value");
        value = value != null ? value : annotationMapping.findDeclaredAttributeValue("path");
        if (value == null) {
            return StringUtils.EMPTY;
        }
        String classUrl;
        if(value instanceof PsiReferenceExpression){
            PsiField psiField = (PsiField) ((PsiReferenceExpressionImpl)value).resolve();
            classUrl = psiField == null ? "" : psiField.getInitializer() == null? "" : psiField.getInitializer().getText();
        } else {
            classUrl = value.getText();
        }

        List<DataMapping> urlReplaceMappingList = config.getUrlReplaceMappingList();
        for (DataMapping dataMapping : urlReplaceMappingList) {
            classUrl = classUrl.replace(dataMapping.getType(),dataMapping.getValue());
        }
        return classUrl.replace("\"", "");
    }

    public List<ParamNameType> getMethodBodyParamList(PsiMethod psiMethod) {
        List<ParamNameType> result = new ArrayList<>();
        PsiParameterList parameterList = psiMethod.getParameterList();
        PsiParameter[] parameters = parameterList.getParameters();
        for (PsiParameter param : parameters) {
            String canonicalText = param.getType().getCanonicalText();
            if (judgeIgnore(config.getIgnoreDataMappingList(), canonicalText)) {
                // 不需要解析的请求参数类型
                continue;
            }
            PsiClass psiClass = null;
            if (CollectionUtils.isCollectionClassOrInterface(param.getType())) {
                PsiClassReferenceType t = (PsiClassReferenceType) PsiUtil.extractIterableTypeParameter(param.getType(), false);
                if (t != null) {
                    psiClass = t.resolve();
                }
            } else {
                psiClass = PsiUtil.resolveClassInType(param.getType());
            }
            PsiAnnotation annotation = param.getAnnotation(Constant.SpringUrlParamConfig.REQUEST_BODY.getCode());
            if (annotation != null && psiClass != null) {
                ParamNameType paramNameType = new ParamNameType(param.getName(), canonicalText, psiClass, Constant.SpringUrlParamConfig.REQUEST_BODY.getParseType(), param.getType());
                result.add(paramNameType);
            }
        }
        return result;
    }

    @Override
    public List<ParamNameType> getMethodUrlParamList(PsiMethod psiMethod) {
        List<ParamNameType> result = new ArrayList<>();
        Constant.SpringUrlParamConfig[] urlParamConfigArray = Constant.SpringUrlParamConfig.values();
        PsiParameterList parameterList = psiMethod.getParameterList();
        PsiParameter[] parameters = parameterList.getParameters();

        for (PsiParameter param : parameters) {
            if (judgeIgnore(config.getIgnoreDataMappingList(), param.getType().getCanonicalText())) {
                // 不需要解析的请求参数类型。
                continue;
            }
            boolean parseFlag = false;
            PsiClass psiClass = PsiUtil.resolveClassInType(param.getType());
            for (Constant.SpringUrlParamConfig config : urlParamConfigArray) {
                PsiAnnotation annotation = param.getAnnotation(config.getCode());
                if (annotation != null) {
                    //PathVariable  RequestParam
                    Integer parseType = config.getParseType();
                    if (parseType == 1 || parseType == 2) {
                        PsiAnnotationMemberValue value = annotation.findDeclaredAttributeValue("value");
                        String name = value == null ? param.getName() : value.getText().replace("\"", "");
                        ParamNameType paramNameType = new ParamNameType(name, param.getType().getCanonicalText(), psiClass, parseType, param.getType());
                        result.add(paramNameType);
                        parseFlag = true;
                        break;
                    }
                    parseFlag = true;
                }
            }
            if (!parseFlag) {
                //默认无注解传参 requestParam
                ParamNameType paramNameType = new ParamNameType(param.getName(), param.getType().getCanonicalText(), psiClass,
                        Constant.SpringUrlParamConfig.REQUEST_PARAM.getParseType(), param.getType());
                result.add(paramNameType);
            }
        }
        return result;
    }

    public String getMethodType(PsiMethod psiMethod) {
        Constant.SpringMappingConfig[] mappingConfigs = Constant.SpringMappingConfig.values();
        for (Constant.SpringMappingConfig mappingConfig : mappingConfigs) {
            String code = mappingConfig.getCode();
            String methodType = mappingConfig.getMethodType();
            PsiAnnotation annotation = psiMethod.getAnnotation(code);
            if (annotation != null) {
                if (StringUtils.isNotEmpty(methodType)) {
                    return methodType;
                } else {
                    //RequestMapping
                    PsiAnnotationMemberValue method = annotation.findDeclaredAttributeValue("method");
                    if (method == null) {
                        //默认返回GET
                        return "GET";
                    }
                    String methodText;
                    if(method instanceof  PsiArrayInitializerMemberValue){
                        PsiAnnotationMemberValue[] initializers = ((PsiArrayInitializerMemberValue) method).getInitializers();
                        if(initializers.length > 0){
                            methodText = ((PsiArrayInitializerMemberValue)method).getInitializers()[0].getText();
                        } else {
                            methodText = "";
                        }
                    } else {
                        methodText = method.getText();
                    }
                    String method0 = methodText.split(",")[0];
                    return method0.substring(method0.lastIndexOf(".") + 1);
                }

            }
        }
        return "GET";
    }

    @Override
    public String getMethodDescription(PsiMethod psiMethod) {
        //优先获取swagger接口ApiOperation中的value，如果获取不到则获取javadoc
        PsiAnnotation annotation = psiMethod.getAnnotation("io.swagger.annotations.ApiOperation");
        if (annotation != null) {
            PsiAnnotationMemberValue descValue = annotation.findDeclaredAttributeValue("value");
            if (descValue != null) {
                return descValue.getText().replace("\"","");
            }
        } else {
            //javadoc中获取
            PsiDocComment docComment = psiMethod.getDocComment();
            StringBuilder commentStringBuilder = new StringBuilder();
            if(docComment != null){
                PsiElement[] descriptionElements = docComment.getDescriptionElements();
                for (PsiElement descriptionElement : descriptionElements) {
                    if(descriptionElement instanceof PsiDocToken){
                        commentStringBuilder.append(descriptionElement.getText());
                    }
                }
            }
            return commentStringBuilder.toString().trim();
        }
        return null;
    }
}
