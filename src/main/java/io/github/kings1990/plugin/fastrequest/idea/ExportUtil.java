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

package io.github.kings1990.plugin.fastrequest.idea;

import com.intellij.CommonBundle;
import com.intellij.ide.IdeBundle;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ExportUtil {
    public static void exportTextToFile(Project project, String fileName, String textToExport) {
        boolean append = false;
        File file = new File(fileName);
        if (file.exists()) {
            int result = Messages.showYesNoCancelDialog(
                    project,
                    IdeBundle.message("error.text.file.already.exists", fileName),
                    IdeBundle.message("dialog.title.export.to.file"),
                    IdeBundle.message("action.overwrite"),
                    IdeBundle.message("action.append"),
                    CommonBundle.getCancelButtonText(),
                    Messages.getWarningIcon()
            );

            if (result != Messages.NO && result != Messages.YES) {
                return;
            }
            if (result == Messages.NO) {
                append = true;
            }
        }

        try (FileWriter writer = new FileWriter(fileName, StandardCharsets.UTF_8, append)) {
            writer.write(textToExport);
        }
        catch (IOException e) {
            Messages.showMessageDialog(
                    project,
                    IdeBundle.message("error.writing.to.file", fileName),
                    CommonBundle.getErrorTitle(),
                    Messages.getErrorIcon()
            );
        }
    }
}
