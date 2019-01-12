package com.emrhmrc.isttabletcrm.models;

import java.io.Serializable;

public class MapModel implements Serializable {
    private double latitude;
    private double longitude;
    private String title;
    private String descp;

    public MapModel(double latitude, double longitude, String title, String descp) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.descp = descp;
    }

    public MapModel() {

    }

    public double getLatitude() {

        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }
}
