package com.emrhmrc.isttabletcrm.models.ServApp;

public class ServappCheckinRequest {
    private String UserId;
    private String inv_QrCode;
    private String inv_Date;
    private double inv_Latitude;
    private double inv_Longitude;

    public ServappCheckinRequest() {
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getInv_QrCode() {
        return inv_QrCode;
    }

    public void setInv_QrCode(String inv_QrCode) {
        this.inv_QrCode = inv_QrCode;
    }

    public String getInv_Date() {
        return inv_Date;
    }

    public void setInv_Date(String inv_Date) {
        this.inv_Date = inv_Date;
    }

    public double getInv_Latitude() {
        return inv_Latitude;
    }

    public void setInv_Latitude(double inv_Latitude) {
        this.inv_Latitude = inv_Latitude;
    }

    public double getInv_Longitude() {
        return inv_Longitude;
    }

    public void setInv_Longitude(double inv_Longitude) {
        this.inv_Longitude = inv_Longitude;
    }
}
