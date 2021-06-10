package io.github.kings1990.plugin.fastrequest.view;

import com.intellij.ide.DataManager;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.project.Project;

public class MyNotifier {

    public static void notifyCopy() {
        DataContext dataContext = DataManager.getInstance().getDataContext();
        Project project = dataContext.getData(LangDataKeys.PROJECT);
        NotificationGroupManager.getInstance().getNotificationGroup("Copy Notification Group")
                .createNotification("Copy success", NotificationType.INFORMATION)
                .notify(project);
    }
}