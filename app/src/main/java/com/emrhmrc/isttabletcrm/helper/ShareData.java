package com.emrhmrc.isttabletcrm.helper;

public class ShareData {
    private static final ShareData Instance = new ShareData();

    private String UserId;
    private String ServAppId;

    private ShareData() {
    }

    public static ShareData getInstance() {
        return Instance;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getServAppId() {
        return ServAppId;
    }

    public void setServAppId(String servAppId) {
        ServAppId = servAppId;
    }
}
