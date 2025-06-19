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
