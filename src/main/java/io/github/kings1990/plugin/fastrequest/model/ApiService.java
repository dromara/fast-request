/*
 * Copyright 2021 kings1990(darkings1990@gmail.com)
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
