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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CollectionConfiguration implements Serializable {
    private CollectionDetail detail;

    public CollectionDetail getDetail() {
        return detail;
    }

    public void setDetail(CollectionDetail detail) {
        this.detail = detail;
    }

    public static class CollectionDetail implements Serializable{
        private String id;

        private String name = "";

        private String groupId;

        private String description = "";

        private Integer type;

        private String enableEnv;

        private String enableProject;

        private String domain = "";

        private ParamGroupCollection paramGroup = new ParamGroupCollection();

        private List<DataMapping> headerList = new ArrayList<>();

        private List<CollectionDetail> childList = new ArrayList<>();

        private String searchText;

        public CollectionDetail(){}

        public CollectionDetail(String id, String name, Integer type) {
            this.id = id;
            this.name = name;
            this.type = type;
        }

        /***getter setter***/
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
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

        public ParamGroupCollection getParamGroup() {
            return paramGroup;
        }

        public void setParamGroup(ParamGroupCollection paramGroup) {
            this.paramGroup = paramGroup;
        }

        public List<DataMapping> getHeaderList() {
            return headerList;
        }

        public void setHeaderList(List<DataMapping> headerList) {
            this.headerList = headerList;
        }

        public List<CollectionDetail> getChildList() {
            return childList;
        }

        public void setChildList(List<CollectionDetail> childList) {
            this.childList = childList;
        }

        public String getSearchText() {
            return searchText;
        }

        public void setSearchText(String searchText) {
            this.searchText = searchText;
        }
    }


}
