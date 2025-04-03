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

package io.github.kings1990.plugin.fastrequest.parse;

import com.intellij.psi.PsiClass;
import com.intellij.psi.util.PsiTypesUtil;
import com.intellij.psi.util.PsiUtil;
import io.github.kings1990.plugin.fastrequest.model.DataMapping;
import io.github.kings1990.plugin.fastrequest.model.FastRequestConfiguration;
import io.github.kings1990.plugin.fastrequest.model.ParamKeyValue;
import io.github.kings1990.plugin.fastrequest.model.ParamNameType;
import io.github.kings1990.plugin.fastrequest.util.KV;
import io.github.kings1990.plugin.fastrequest.util.StringUtils;
import io.github.kings1990.plugin.fastrequest.util.TypeUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class PathValueParamParse extends AbstractParamParse {
    @Override
    public LinkedHashMap<String, Object> parseParam(FastRequestConfiguration config, List<ParamNameType> paramNameTypeList) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        List<DataMapping> customDataMappingList = config.getCustomDataMappingList();
        List<DataMapping> defaultDataMappingList = config.getDefaultDataMappingList();
        List<ParamNameType> pathParamList = paramNameTypeList.stream().filter(q -> q.getParseType() == 1).collect(Collectors.toList());
        int randomStringLength = config.getRandomStringLength();
        String randomStringStrategy = config.getRandomStringStrategy();
        String randomStringDelimiter = config.getRandomStringDelimiter();
        for (ParamNameType paramNameType : pathParamList) {
            String name = paramNameType.getName();
            String type = paramNameType.getType();
            //random String
            if ("java.lang.String".equals(type)) {
                ParamKeyValue value = new ParamKeyValue(name, StringUtils.randomString(name,randomStringDelimiter,randomStringLength,randomStringStrategy), 2, TypeUtil.Type.String.name());
                result.put(name, value);
                continue;
            }
            //pathValueParam 不支持自定义类解析 只能是基础类型
//            DataMapping dataMapping = customDataMappingList.stream().filter(q -> type.equals(q.getType())).findFirst().orElse(null);
//            if (dataMapping != null) {
//                Object value = dataMapping.getValue();
//                result.put(name, value);
//                continue;
//            }
            DataMapping dataMapping = defaultDataMappingList.stream().filter(q -> type.equals(q.getType())).findFirst().orElse(null);
            if (dataMapping != null) {
                Object value = dataMapping.getValue();
                String calcType = TypeUtil.calcType(type);
                result.put(name, new ParamKeyValue(name, value, 2, calcType));
            } else {
                //匹配不到默认匹配string

                PsiClass psiClass = PsiUtil.resolveClassInClassTypeOnly(PsiTypesUtil.getClassType(paramNameType.getPsiClass()).getDeepComponentType());
                boolean isEnum = psiClass != null && psiClass.isEnum();
                if (isEnum) {

                    KV kv = KV.getFields(paramNameType.getPsiClass());
                    Object enumParamKeyValue = kv.values().stream().findFirst().orElse(null);
                    if (enumParamKeyValue != null) {
                        result.put(name, new ParamKeyValue(name, ((ParamKeyValue) enumParamKeyValue).getValue(), 2, TypeUtil.Type.String.name()));
                    }
                    continue;
                }

                result.put(name, new ParamKeyValue(name, StringUtils.randomString(name, randomStringDelimiter, randomStringLength, randomStringStrategy), 2, TypeUtil.Type.String.name()));
            }
        }
        return result;
    }
}
