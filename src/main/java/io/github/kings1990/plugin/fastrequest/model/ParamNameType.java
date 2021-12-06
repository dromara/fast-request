/*
 * Copyright 2021 kings1990
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.kings1990.plugin.fastrequest.model;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiType;

import java.io.Serializable;

public class ParamNameType implements Serializable {
    private String name;
    private String type;
    private PsiClass psiClass;
    private Integer parseType;
    private PsiType psiType;

    public ParamNameType() {
    }

    public ParamNameType(String name, String type, PsiClass psiClass, Integer parseType) {
        this.name = name;
        this.type = type;
        this.psiClass = psiClass;
        this.parseType = parseType;
    }

    public ParamNameType(String name, String type, PsiClass psiClass, Integer parseType, PsiType psiType) {
        this.name = name;
        this.type = type;
        this.psiClass = psiClass;
        this.parseType = parseType;
        this.psiType = psiType;
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

    public PsiType getPsiType() {
        return psiType;
    }

    public void setPsiType(PsiType psiType) {
        this.psiType = psiType;
    }
}
