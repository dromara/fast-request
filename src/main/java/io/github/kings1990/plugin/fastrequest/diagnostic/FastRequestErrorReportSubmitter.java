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

package io.github.kings1990.plugin.fastrequest.diagnostic;

import com.intellij.ide.DataManager;
import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManagerCore;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.application.ApplicationInfo;
import com.intellij.openapi.diagnostic.ErrorReportSubmitter;
import com.intellij.openapi.diagnostic.IdeaLoggingEvent;
import com.intellij.openapi.diagnostic.SubmittedReportInfo;
import com.intellij.openapi.diagnostic.SubmittedReportInfo.SubmissionStatus;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsActions;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.util.Consumer;
import io.github.kings1990.plugin.fastrequest.util.MyResourceBundleUtil;
import org.intellij.plugins.markdown.lang.MarkdownLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class FastRequestErrorReportSubmitter extends ErrorReportSubmitter {
    @Override
    public @NlsActions.ActionText @NotNull String getReportActionText() {
        return "zh".equals(MyResourceBundleUtil.getKey("language")) ?  "Report to https://gitee.com/dromara/fast-request/issues"
                : "Report to https://github.com/dromara/fast-request/issues";
    }

    public boolean submit(@NotNull IdeaLoggingEvent[] events, @Nullable String additionalInfo, @NotNull Component parentComponent, @NotNull Consumer<? super SubmittedReportInfo> consumer) {
        if (events.length == 0) {
            fail(consumer);
            return false;
        } else {
            String stackTrace = null;
            String errorMessage = null;
            DataContext dataContext = DataManager.getInstance().getDataContext(parentComponent);
            Project project = CommonDataKeys.PROJECT.getData(dataContext);
            if (project == null) {
                fail(consumer);
                return false;
            } else {
                StringBuilder builder = new StringBuilder();
                String url = "zh".equals(MyResourceBundleUtil.getKey("language")) ?  "Please report to https://gitee.com/dromara/fast-request/issues"
                        : "Please report to https://github.com/dromara/fast-request/issues";
                builder.append("<!--").append(url).append("-->\n");
                builder.append("### 重现步骤(Repeat steps)\n");
                builder.append("- 最好提供gif动图(It's best to provide a GIF)\n");
                builder.append("- please describe what you were doing when this exception occurred\n");
                builder.append("\n");
                if (additionalInfo != null) {
                    builder.append(additionalInfo.trim()).append("\n");
                }

                builder.append("\n");
                builder.append("### 当前使用版本(Current Version)\n");
                builder.append("\n");
                ApplicationInfo info = ApplicationInfo.getInstance();
                builder.append(info.getVersionName()).append(" `").append(info.getFullVersion()).append("`");
                PluginId pid = getPluginId();
                IdeaPluginDescriptor flutterPlugin = PluginManagerCore.getPlugin(pid);
                builder.append(" • Notes plugin `").append(pid.getIdString()).append(' ').append(flutterPlugin.getVersion()).append("`\n");
                IdeaLoggingEvent[] var13 = events;
                int var14 = events.length;

                builder.append("\n");
                for(int var15 = 0; var15 < var14; ++var15) {
                    IdeaLoggingEvent event = var13[var15];
                    builder.append("### Exception\n");
                    builder.append("\n");
                    builder.append(event.getMessage()).append("\n");
                    builder.append("\n");
                    if (event.getThrowable() != null) {
                        builder.append("```\n");
                        builder.append(event.getThrowableText().trim()).append("\n");
                        builder.append("```\n");
                        builder.append("\n");
                    }
                }

                String text = builder.toString().trim() + "\n";
                PsiFile file = PsiFileFactory.getInstance(project).createFileFromText("bug-report.md", MarkdownLanguage.INSTANCE, text);
                if (file == null) {
                    fail(consumer);
                    return false;
                } else {
                    (new OpenFileDescriptor(project, file.getVirtualFile())).navigate(true);
                    consumer.consume(new SubmittedReportInfo("https://github.com/dromara/fast-request/issues/new", "https://github.com/dromara/fast-request/issues/new", SubmissionStatus.NEW_ISSUE));
                    return true;
                }
            }
        }
    }

    @NotNull
    public static PluginId getPluginId() {
        PluginId pluginId = PluginId.findId(new String[]{"io.github.kings1990.FastRequest"});
        assert pluginId != null;
        return pluginId;
    }

    private static void fail(@NotNull Consumer<? super SubmittedReportInfo> consumer) {
        consumer.consume(new SubmittedReportInfo((String)null, (String)null, SubmissionStatus.FAILED));
    }

}
