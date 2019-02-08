package com.emrhmrc.isttabletcrm.helper;

public class ShareData {
    private static final ShareData Instance = new ShareData();

    private String UserId;
    private String ServAppId;

    public String getNotificationIdRequest() {
        return NotificationIdRequest;
    }

    public void setNotificationIdRequest(String notificationIdRequest) {
        NotificationIdRequest = notificationIdRequest;
    }

    private String NotificationIdRequest;
    private double Latitude;
    private double Longitude;

    private ShareData() {
    }

    public static ShareData getInstance() {
        return Instance;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
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
