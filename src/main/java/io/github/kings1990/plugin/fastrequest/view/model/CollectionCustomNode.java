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
