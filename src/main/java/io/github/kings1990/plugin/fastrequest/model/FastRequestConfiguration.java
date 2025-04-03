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

package io.github.kings1990.plugin.fastrequest.model;

import com.google.common.collect.Lists;
import io.github.kings1990.plugin.fastrequest.config.Constant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 总配置
 *
 * @author Kings
 * @date 2021/05/22
 * @see Serializable
 */
public class FastRequestConfiguration implements Serializable {

    /**
     * 项目列表
     */
    private List<String> projectList = new ArrayList<>();
    /**
     * env列表
     */
    private List<String> envList = new ArrayList<>();
    /**
     * 数据集合
     */
    private List<NameGroup> dataList = new ArrayList<>();

    /**
     * 自定义数据映射
     */
    private List<DataMapping> customDataMappingList = new ArrayList<>();

    private List<String> ignoreDataMappingList = Constant.IGNORE_PARAM_PARSE_LIST;

    /**
     * 默认的数据映射
     */
    private List<DataMapping> defaultDataMappingList = Lists.newArrayList(
            new DataMapping("byte", "1"),
            new DataMapping("java.lang.Byte", "1"),
            new DataMapping("short", "1"),
            new DataMapping("java.lang.Short", "1"),
            new DataMapping("int", "1"),
            new DataMapping("java.lang.Integer", "1"),
            new DataMapping("long", "1"),
            new DataMapping("java.lang.Long", "1"),
            new DataMapping("char", "a"),
            new DataMapping("java.lang.Character", "a"),
            new DataMapping("float", "1"),
            new DataMapping("java.lang.Float", "1"),
            new DataMapping("double", "1"),
            new DataMapping("java.lang.Double", "1"),
            new DataMapping("boolean", "true"),
            new DataMapping("java.lang.Boolean", "true"),
            new DataMapping("java.math.BigDecimal", "1")
    );

    /**
     * url替换规则
     */
    private List<DataMapping> urlReplaceMappingList = new ArrayList<>();


    private String enableEnv;

    private String enableProject;

    private String domain = "";

    private ParamGroup paramGroup = new ParamGroup();

    private int randomStringLength = 3;

    /**
     * String生成策略
     * name+random
     * random
     * none
     */
    private String randomStringStrategy = "name+random";

    /**
     * String生成器分隔符
     */
    private String randomStringDelimiter = "_";

    private List<DataMapping> headerList = new ArrayList<>();

    private List<HeaderGroup> headerGroupList = new ArrayList<>();

    private List<DataMapping> globalHeaderList = new ArrayList<>();

    public List<String> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<String> projectList) {
        this.projectList = projectList;
    }

    public List<String> getEnvList() {
        return envList;
    }

    public void setEnvList(List<String> envList) {
        this.envList = envList;
    }

    public List<NameGroup> getDataList() {
        return dataList;
    }

    public void setDataList(List<NameGroup> dataList) {
        this.dataList = dataList;
    }

    public String getEnableEnv() {
        return enableEnv;
    }

    public void setEnableEnv(String enableEnv) {
        this.enableEnv = enableEnv;
    }

    public String getEnableProject() {
        return enableProject;
    }

    public void setEnableProject(String enableProject) {
        this.enableProject = enableProject;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public List<DataMapping> getDefaultDataMappingList() {
        return defaultDataMappingList;
    }

    public void setDefaultDataMappingList(List<DataMapping> defaultDataMappingList) {
        this.defaultDataMappingList = defaultDataMappingList;
    }

    public List<DataMapping> getCustomDataMappingList() {
        return customDataMappingList;
    }

    public void setCustomDataMappingList(List<DataMapping> customDataMappingList) {
        this.customDataMappingList = customDataMappingList;
    }

    public ParamGroup getParamGroup() {
        return paramGroup;
    }

    public void setParamGroup(ParamGroup paramGroup) {
        this.paramGroup = paramGroup;
    }

    public int getRandomStringLength() {
        return randomStringLength;
    }

    public void setRandomStringLength(int randomStringLength) {
        this.randomStringLength = randomStringLength;
    }

    public List<DataMapping> getHeaderList() {
        return headerList;
    }

    public void setHeaderList(List<DataMapping> headerList) {
        this.headerList = headerList;
    }

    public List<DataMapping> getUrlReplaceMappingList() {
        return urlReplaceMappingList;
    }

    public void setUrlReplaceMappingList(List<DataMapping> urlReplaceMappingList) {
        this.urlReplaceMappingList = urlReplaceMappingList;
    }

    public String getRandomStringStrategy() {
        return randomStringStrategy;
    }

    public void setRandomStringStrategy(String randomStringStrategy) {
        this.randomStringStrategy = randomStringStrategy;
    }

    public String getRandomStringDelimiter() {
        return randomStringDelimiter;
    }

    public void setRandomStringDelimiter(String randomStringDelimiter) {
        this.randomStringDelimiter = randomStringDelimiter;
    }

    public List<HeaderGroup> getHeaderGroupList() {
        return headerGroupList;
    }

    public void setHeaderGroupList(List<HeaderGroup> headerGroupList) {
        this.headerGroupList = headerGroupList;
    }

    public List<String> getIgnoreDataMappingList() {
        return ignoreDataMappingList;
    }

    public void setIgnoreDataMappingList(List<String> ignoreDataMappingList) {
        this.ignoreDataMappingList = ignoreDataMappingList;
    }

    public List<DataMapping> getGlobalHeaderList() {
        return globalHeaderList;
    }

    public void setGlobalHeaderList(List<DataMapping> globalHeaderList) {
        this.globalHeaderList = globalHeaderList;
    }
}
