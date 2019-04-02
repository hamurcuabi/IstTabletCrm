package com.emrhmrc.isttabletcrm.helper;

import com.emrhmrc.isttabletcrm.models.BreakDown.BreakDownTypeListAll;
import com.emrhmrc.isttabletcrm.models.Notification.Notification;

public class ShareData {
    private static final ShareData Instance = new ShareData();

    private String UserId;
    private String ServAppId;
    private String ProductMainId;
    private String ProductMainName;
    private String ElevatorId;
    private Notification NotificationObject;
    private String NotificationDetail;
    private double Latitude;
    private double Longitude;
    private boolean add_sub_piece;
    private boolean sub_servapp;

    public BreakDownTypeListAll getBreakDownTypeListAll() {
        return breakDownTypeListAll;
    }

    public void setBreakDownTypeListAll(BreakDownTypeListAll breakDownTypeListAll) {
        this.breakDownTypeListAll = breakDownTypeListAll;
    }

    private BreakDownTypeListAll breakDownTypeListAll;

    private ShareData() {
    }

    public static ShareData getInstance() {
        return Instance;
    }

    public boolean isAdd_sub_piece() {
        return add_sub_piece;
    }

    public void setAdd_sub_piece(boolean add_sub_piece) {
        this.add_sub_piece = add_sub_piece;
    }

    public boolean isSub_servapp() {
        return sub_servapp;
    }

    public void setSub_servapp(boolean sub_servapp) {
        this.sub_servapp = sub_servapp;
    }

    public String getNotificationDetail() {
        return NotificationDetail;
    }

    public void setNotificationDetail(String notificationDetail) {
        NotificationDetail = notificationDetail;
    }

    public String getProductMainName() {
        return ProductMainName;
    }

    public void setProductMainName(String productMainName) {
        ProductMainName = productMainName;
    }

    public String getProductMainId() {
        return ProductMainId;
    }

    public void setProductMainId(String productMainId) {
        ProductMainId = productMainId;
    }

    public Notification getNotificationObject() {
        return NotificationObject;
    }

    public void setNotificationObject(Notification notificationObject) {
        NotificationObject = notificationObject;
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
