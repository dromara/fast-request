package io.github.kings1990.plugin.fastrequest.model;

import com.google.common.collect.Lists;

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
            new DataMapping("char", "\\u0000"),
            new DataMapping("java.lang.Character", "\\u0000"),
            new DataMapping("float", "1"),
            new DataMapping("java.lang.Float", "1"),
            new DataMapping("double", "1"),
            new DataMapping("java.lang.Double", "1"),
            new DataMapping("boolean", "true"),
            new DataMapping("java.lang.Boolean", "true"),
            new DataMapping("java.math.BigDecimal", "1")
    );

    private boolean enableFlag;

    private String enableEnv;

    private String enableProject;

    private String domain = "";

    private ParamGroup paramGroup = new ParamGroup();

    private int randomStringLength = 3;

    private List<DataMapping> headerList;

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

    public boolean isEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(boolean enableFlag) {
        this.enableFlag = enableFlag;
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
}
