package com.emrhmrc.isttabletcrm.models.CommonClass;

public class NotificationCountResponse {

    private int NotificationCount;
    private String ErrorMsg;
    private boolean Success;

    public NotificationCountResponse() {
    }

    public int getNotificationCount() {
        return NotificationCount;
    }

    public void setNotificationCount(int notificationCount) {
        NotificationCount = notificationCount;
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
}
