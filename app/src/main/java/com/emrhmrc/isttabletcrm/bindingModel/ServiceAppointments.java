package com.emrhmrc.isttabletcrm.bindingModel;

import com.emrhmrc.isttabletcrm.models.CommonClass.Code;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;

public class ServiceAppointments {
    private Code StatusCode;
    private String ScheduledStart;
    private String ScheduledEnd;
    private String ActivityId;
    private String Subject;
    private String inv_ServiceAppointmentNumber;
    private Inv_Id inv_CustomerId;
    private Code inv_TypeCode;
    private double inv_Latitude;
    private double inv_Longitude;
    private Code PriorityCode;

    public ServiceAppointments() {
    }

    public String getInv_ServiceAppointmentNumber() {
        return inv_ServiceAppointmentNumber;
    }

    public void setInv_ServiceAppointmentNumber(String inv_ServiceAppointmentNumber) {
        this.inv_ServiceAppointmentNumber = inv_ServiceAppointmentNumber;
    }

    public Code getPriorityCode() {
        return PriorityCode;
    }

    public void setPriorityCode(Code priorityCode) {
        PriorityCode = priorityCode;
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

    public Code getPriortiyCode() {
        return PriorityCode;
    }

    public void setPriortiyCode(Code priortiyCode) {
        PriorityCode = priortiyCode;
    }

    public Code getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(Code StatusCode) {
        this.StatusCode = StatusCode;
    }

    public String getScheduledStart() {
        return ScheduledStart;
    }

    public void setScheduledStart(String ScheduledStart) {
        this.ScheduledStart = ScheduledStart;
    }

    public String getScheduledEnd() {
        return ScheduledEnd;
    }

    public void setScheduledEnd(String ScheduledEnd) {
        this.ScheduledEnd = ScheduledEnd;
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

    public Code getInv_TypeCode() {
        return inv_TypeCode;
    }

    public void setInv_TypeCode(Code inv_TypeCode) {
        this.inv_TypeCode = inv_TypeCode;
    }

}

