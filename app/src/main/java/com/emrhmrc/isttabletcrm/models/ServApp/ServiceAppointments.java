package com.emrhmrc.isttabletcrm.models.ServApp;

import com.emrhmrc.isttabletcrm.models.CommonClass.Code;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;
import com.google.gson.annotations.SerializedName;

public class ServiceAppointments {
    @SerializedName("StatusCode")
    private Code StatusCode;
    private String ActualStart;
    private String ActualEnd;
    private String ActivityId;
    private String Subject;
    private Inv_Id inv_CustomerId;
    private Inv_Id inv_TypeCode;
    private double inv_Latitude;
    private double inv_Longitude;

    private Code PriortiyCode;

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

    public Code getPriortiyCode() {
        return PriortiyCode;
    }

    public void setPriortiyCode(Code priortiyCode) {
        PriortiyCode = priortiyCode;
    }

    public Code getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(Code StatusCode) {
        this.StatusCode = StatusCode;
    }

    public String getActualStart() {
        return ActualStart;
    }

    public void setActualStart(String ActualStart) {
        this.ActualStart = ActualStart;
    }

    public String getActualEnd() {
        return ActualEnd;
    }

    public void setActualEnd(String ActualEnd) {
        this.ActualEnd = ActualEnd;
    }

    public String getActivityId() {
        return ActivityId;
    }

    public void setActivityId(String ActivityId) {
        this.ActivityId = ActivityId;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    public Inv_Id getInv_CustomerId() {
        return inv_CustomerId;
    }

    public void setInv_CustomerId(Inv_Id inv_CustomerId) {
        this.inv_CustomerId = inv_CustomerId;
    }

    public Inv_Id getInv_TypeCode() {
        return inv_TypeCode;
    }

    public void setInv_TypeCode(Inv_Id inv_TypeCode) {
        this.inv_TypeCode = inv_TypeCode;
    }

    @Override
    public String toString() {
        return "ServiceAppointments{" +
                "StatusCode=" + StatusCode +
                ", ActualStart='" + ActualStart + '\'' +
                ", ActualEnd='" + ActualEnd + '\'' +
                ", ActivityId='" + ActivityId + '\'' +
                ", Subject='" + Subject + '\'' +
                ", inv_CustomerId=" + inv_CustomerId +
                ", inv_TypeCode=" + inv_TypeCode +
                ", inv_Latitude=" + inv_Latitude +
                ", inv_Longitude=" + inv_Longitude +
                ", PriortiyCode=" + PriortiyCode +
                '}';
    }
}

