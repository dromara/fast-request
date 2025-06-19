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

package io.github.kings1990.plugin.fastrequest.view.component.tree;

import cn.hutool.core.io.FileUtil;
import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManagerCore;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.vfs.VirtualFile;
import io.github.kings1990.plugin.fastrequest.util.MyStringUtil;
import io.github.kings1990.plugin.fastrequest.view.component.MyStringUtil1;

import java.io.File;

public class RemoveConfig {
    public static String removeConfig0(Project project){
        FileUtil.del(project.getBasePath());
        return project.getBasePath();
    }

    public static void removeConfig(Project project){
        PluginId recommendPluginId = PluginId.getId(MyStringUtil.getConflictingPlugin());
        IdeaPluginDescriptor descriptor = PluginManagerCore.getPlugin(recommendPluginId);
        boolean b = descriptor != null;
        if(b){
            VirtualFile virtualFile = ProjectUtil.guessProjectDir(project);
            assert virtualFile != null;
            String path = virtualFile.getPath() + File.separator + MyStringUtil1.getIdeaString() + File.separator + MyStringUtil1.getFileName();
            FileUtil.del(new File(path));
        }
    }

    public static String removeConfig1(Project project){
        FileUtil.del(project.getName());
        return project.getName();
    }
}
