package com.emrhmrc.isttabletcrm.helper;

public class ShareData {
    private static final ShareData Instance = new ShareData();

    private String UserId;
    private String ServAppId;
    private String ProductMainId;
    private String ElevatorId;
    private String NotificationIdRequest;
    private double Latitude;
    private double Longitude;
    private ShareData() {
    }

    public static ShareData getInstance() {
        return Instance;
    }

    public String getProductMainId() {
        return ProductMainId;
    }

    public void setProductMainId(String productMainId) {
        ProductMainId = productMainId;
    }

    public String getNotificationIdRequest() {
        return NotificationIdRequest;
    }

    public void setNotificationIdRequest(String notificationIdRequest) {
        NotificationIdRequest = notificationIdRequest;
    }

    public String getElevatorId() {
        return ElevatorId;
    }

    public void setElevatorId(String elevatorId) {
        ElevatorId = elevatorId;
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
