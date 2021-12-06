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

package io.github.kings1990.plugin.fastrequest.view.model;

import io.github.kings1990.plugin.fastrequest.model.CollectionConfiguration;

import javax.swing.tree.DefaultMutableTreeNode;

public class CollectionCustomNode extends DefaultMutableTreeNode {
    private String id;
    private String name;
    //1-Group 2-RequestItem
    private Integer type;
    private String url;
    private String searchText = "";
    private int row;
    private CollectionConfiguration.CollectionDetail detail;

    public CollectionCustomNode(){

    }
    public CollectionCustomNode(String name, Integer type) {
        this.name = name;
        this.type = type;
    }

    public CollectionCustomNode(String id,String name, Integer type) {
        this.id= id;
        this.name = name;
        this.type = type;
    }

    public CollectionCustomNode(String name, Integer type, String url) {
        this.name = name;
        this.type = type;
        this.url = url;
    }

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public CollectionConfiguration.CollectionDetail getDetail() {
        return detail;
    }

    public void setDetail(CollectionConfiguration.CollectionDetail detail) {
        this.detail = detail;
    }
}
