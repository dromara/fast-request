/*
 *
 *  * Copyright (C) 2021 WangSheng darkings1990@gmail.com
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU Affero General Public License as published
 *  * by the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU Affero General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU Affero General Public License
 *  * along with this program.  If not, see <https://www.gnu.org/licenses/>.
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
