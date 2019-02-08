package com.emrhmrc.isttabletcrm.models.Notification;

public class NotificationIdRequest {
    private String NotificationId;

    public NotificationIdRequest() {

    }

    public NotificationIdRequest(String notificationId) {

        NotificationId = notificationId;
    }

    public String getNotificationId() {
        return NotificationId;
    }

    public void setNotificationId(String notificationId) {
        NotificationId = notificationId;
    }
}
