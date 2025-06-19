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
