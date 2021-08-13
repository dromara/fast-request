package io.github.kings1990.plugin.fastrequest.model;

import com.intellij.psi.PsiClass;

import java.io.Serializable;

public class ParamNameType implements Serializable {
    private String name;
    private String type;
    private PsiClass psiClass;
    private Integer parseType;

    public ParamNameType() {
    }

    public ParamNameType(String name, String type, PsiClass psiClass, Integer parseType) {
        this.name = name;
        this.type = type;
        this.psiClass = psiClass;
        this.parseType = parseType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PsiClass getPsiClass() {
        return psiClass;
    }

    public void setPsiClass(PsiClass psiClass) {
        this.psiClass = psiClass;
    }

    public Integer getParseType() {
        return parseType;
    }

    public void setParseType(Integer parseType) {
        this.parseType = parseType;
    }
}
