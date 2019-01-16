package com.emrhmrc.isttabletcrm.helper;

public class ShareData {
    private static final ShareData Instance = new ShareData();

    private String UserId;

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
}
