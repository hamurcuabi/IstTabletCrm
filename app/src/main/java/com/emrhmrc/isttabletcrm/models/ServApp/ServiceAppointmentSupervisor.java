package com.emrhmrc.isttabletcrm.models.ServApp;

import com.emrhmrc.isttabletcrm.models.CommonClass.Code;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;

public class ServiceAppointmentSupervisor {

    private Inv_Id inv_BreakdownDefCodeid;
    private Inv_Id inv_MainProductGroupid;
    private Inv_Id inv_SubProductGroupid;
    private Inv_Id inv_BreakdownCodeid;
    private Code StatusCode;
    private String ScheduledStart;
    private String ScheduledEnd;
    private Code inv_TypeCode;

    public ServiceAppointmentSupervisor() {
    }

    public Inv_Id getInv_BreakdownDefCodeid() {
        return inv_BreakdownDefCodeid;
    }

    public void setInv_BreakdownDefCodeid(Inv_Id inv_BreakdownDefCodeid) {
        this.inv_BreakdownDefCodeid = inv_BreakdownDefCodeid;
    }

    public Inv_Id getInv_MainProductGroupid() {
        return inv_MainProductGroupid;
    }

    public void setInv_MainProductGroupid(Inv_Id inv_MainProductGroupid) {
        this.inv_MainProductGroupid = inv_MainProductGroupid;
    }

    public Inv_Id getInv_SubProductGroupid() {
        return inv_SubProductGroupid;
    }

    public void setInv_SubProductGroupid(Inv_Id inv_SubProductGroupid) {
        this.inv_SubProductGroupid = inv_SubProductGroupid;
    }

    public Inv_Id getInv_BreakdownCodeid() {
        return inv_BreakdownCodeid;
    }

    public void setInv_BreakdownCodeid(Inv_Id inv_BreakdownCodeid) {
        this.inv_BreakdownCodeid = inv_BreakdownCodeid;
    }

    public Code getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(Code statusCode) {
        StatusCode = statusCode;
    }

    public String getScheduledStart() {
        return ScheduledStart;
    }

    public void setScheduledStart(String scheduledStart) {
        ScheduledStart = scheduledStart;
    }

    public String getScheduledEnd() {
        return ScheduledEnd;
    }

    public void setScheduledEnd(String scheduledEnd) {
        ScheduledEnd = scheduledEnd;
    }

    public Code getInv_TypeCode() {
        return inv_TypeCode;
    }

    public void setInv_TypeCode(Code inv_TypeCode) {
        this.inv_TypeCode = inv_TypeCode;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public Inv_Id getInv_ElevatorId() {
        return inv_ElevatorId;
    }

    public void setInv_ElevatorId(Inv_Id inv_ElevatorId) {
        this.inv_ElevatorId = inv_ElevatorId;
    }

    public String getActivityId() {
        return ActivityId;
    }

    public void setActivityId(String activityId) {
        ActivityId = activityId;
    }

    public Inv_Id getInv_CustomerId() {
        return inv_CustomerId;
    }

    public void setInv_CustomerId(Inv_Id inv_CustomerId) {
        this.inv_CustomerId = inv_CustomerId;
    }

    private String Subject;
    private Inv_Id inv_ElevatorId;
    private String ActivityId;
    private Inv_Id inv_CustomerId;
}
