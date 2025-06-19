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

import com.intellij.psi.PsiMethod;

import java.util.List;

public class ApiService {
    private String moduleName;
    private String packageName;
    private String className;
    private List<ApiMethod> apiMethodList;

    public ApiService() {
    }

    public ApiService(String moduleName, String packageName, String className, List<ApiMethod> apiMethodList) {
        this.moduleName = moduleName;
        this.packageName = packageName;
        this.className = className;
        this.apiMethodList = apiMethodList;
    }

    public static class ApiMethod {
        private PsiMethod psiMethod;
        private String url;
        private String description;
        private String name;
        private String methodType;

        public ApiMethod(PsiMethod psiMethod, String url, String description, String name, String methodType) {
            this.psiMethod = psiMethod;
            this.url = url;
            this.description = description;
            this.name = name;
            this.methodType = methodType;
        }

        public PsiMethod getPsiMethod() {
            return psiMethod;
        }

        @Override
        public String toString() {
            return url;
        }

        public void setPsiMethod(PsiMethod psiMethod) {
            this.psiMethod = psiMethod;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMethodType() {
            return methodType;
        }

        public void setMethodType(String methodType) {
            this.methodType = methodType;
        }
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public List<ApiMethod> getApiMethodList() {
        return apiMethodList;
    }

    public void setApiMethodList(List<ApiMethod> apiMethodList) {
        this.apiMethodList = apiMethodList;
    }
}
