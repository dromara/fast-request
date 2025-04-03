/*
 *
 *  * End-User License Agreement (EULA) of Restful Fast Request
 *  * This End-User License Agreement ("EULA") is a legal agreement between you and Kings(darkings1990@gmail.com). Our EULA was created by EULA Template for Restful Fast Request.
 *  *
 *  * This EULA agreement governs your acquisition and use of our Restful Fast Request software ("Software") directly from Kings(darkings1990@gmail.com) or indirectly through a Kings(darkings1990@gmail.com) authorized reseller or distributor (a "Reseller").
 *  *
 *  * Please read this EULA agreement carefully before completing the installation process and using the Restful Fast Request software. It provides a license to use the Restful Fast Request software and contains warranty information and liability disclaimers.
 *  *
 *  * If you register for a free trial of the Restful Fast Request software, this EULA agreement will also govern that trial. By clicking "accept" or installing and/or using the Restful Fast Request software, you are confirming your acceptance of the Software and agreeing to become bound by the terms of this EULA agreement.
 *  *
 *  * If you are entering into this EULA agreement on behalf of a company or other legal entity, you represent that you have the authority to bind such entity and its affiliates to these terms and conditions. If you do not have such authority or if you do not agree with the terms and conditions of this EULA agreement, do not install or use the Software, and you must not accept this EULA agreement.
 *  *
 *  * This EULA agreement shall apply only to the Software supplied by Kings(darkings1990@gmail.com) herewith regardless of whether other software is referred to or described herein. The terms also apply to any Kings(darkings1990@gmail.com) updates, supplements, Internet-based services, and support services for the Software, unless other terms accompany those items on delivery. If so, those terms apply.
 *  *
 *  * License Grant
 *  * Kings(darkings1990@gmail.com) hereby grants you a personal, non-transferable, non-exclusive licence to use the Restful Fast Request software on your devices in accordance with the terms of this EULA agreement.
 *  *
 *  * You are permitted to load the Restful Fast Request software (for example a PC, laptop, mobile or tablet) under your control. You are responsible for ensuring your device meets the minimum requirements of the Restful Fast Request software.
 *  *
 *  * You are not permitted to:
 *  *
 *  * Edit, alter, modify, adapt, translate or otherwise change the whole or any part of the Software nor permit the whole or any part of the Software to be combined with or become incorporated in any other software, nor decompile, disassemble or reverse engineer the Software or attempt to do any such things
 *  * Copy this project and republish a new plugin in JetBrains Marketplace
 *  * Reproduce, copy, distribute, resell or otherwise use the Software for any commercial purpose
 *  * Allow any third party to use the Software on behalf of or for the benefit of any third party
 *  * Use the Software in any way which breaches any applicable local, national or international law
 *  * use the Software for any purpose that Kings(darkings1990@gmail.com) considers is a breach of this EULA agreement
 *  * Intellectual Property and Ownership
 *  * Kings(darkings1990@gmail.com) shall at all times retain ownership of the Software as originally downloaded by you and all subsequent downloads of the Software by you. The Software (and the copyright, and other intellectual property rights of whatever nature in the Software, including any modifications made thereto) are and shall remain the property of Kings(darkings1990@gmail.com).
 *  *
 *  * Kings(darkings1990@gmail.com) reserves the right to grant licences to use the Software to third parties.
 *  *
 *  * Termination
 *  * This EULA agreement is effective from the date you first use the Software and shall continue until terminated. You may terminate it at any time upon written notice to Kings(darkings1990@gmail.com).
 *  *
 *  * It will also terminate immediately if you fail to comply with any term of this EULA agreement. Upon such termination, the licenses granted by this EULA agreement will immediately terminate and you agree to stop all access and use of the Software. The provisions that by their nature continue and survive will survive any termination of this EULA agreement.
 *  *
 *  * Governing Law
 *  * This EULA agreement, and any dispute arising out of or in connection with this EULA agreement, shall be governed by and construed in accordance with the laws of cn.
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
