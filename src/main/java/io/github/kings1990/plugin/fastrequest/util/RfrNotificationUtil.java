package io.github.kings1990.plugin.fastrequest.util;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;

public class RfrNotificationUtil {

    public static Notification buildInfo(String content) {
        return NotificationGroupManager.getInstance().getNotificationGroup("toolWindowNotificationGroup").createNotification("Fast Request","", content, NotificationType.INFORMATION);
    }

    
    public static Notification buildError( String content) {
        return NotificationGroupManager.getInstance().getNotificationGroup("toolWindowNotificationGroup").createNotification("Fast Request", "",content, NotificationType.ERROR);
    }
}
