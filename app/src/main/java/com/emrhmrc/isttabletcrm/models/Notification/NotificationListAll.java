package com.emrhmrc.isttabletcrm.models.Notification;

import java.util.List;

public class NotificationListAll {

    private String ErrorMsg;
    private boolean Success;
    private List<Notification> Notifications;

    public NotificationListAll() {

    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public List<Notification> getNotifications() {
        return Notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        Notifications = notifications;
    }
}
