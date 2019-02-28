package com.emrhmrc.isttabletcrm.models.ServApp;

import com.emrhmrc.isttabletcrm.models.CommonClass.Code;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;

public class ServiceAppointmentRequest {

    private Code StatusCode;
    private String ScheduledStart;
    private String ScheduledEnd;
    private String ActivityId;
    private String Subject;
    private Inv_Id inv_CustomerId;
    private Inv_Id inv_QuoteId;

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

    public String getActivityId() {
        return ActivityId;
    }

    public void setActivityId(String activityId) {
        ActivityId = activityId;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public Inv_Id getInv_CustomerId() {
        return inv_CustomerId;
    }

    public void setInv_CustomerId(Inv_Id inv_CustomerId) {
        this.inv_CustomerId = inv_CustomerId;
    }

    public Inv_Id getInv_QuoteId() {
        return inv_QuoteId;
    }

    public void setInv_QuoteId(Inv_Id inv_QuoteId) {
        this.inv_QuoteId = inv_QuoteId;
    }

    public Code getInv_TypeCode() {
        return inv_TypeCode;
    }

    public void setInv_TypeCode(Code inv_TypeCode) {
        this.inv_TypeCode = inv_TypeCode;
    }

    public Inv_Id getInv_ElevatorId() {
        return inv_ElevatorId;
    }

    public void setInv_ElevatorId(Inv_Id inv_ElevatorId) {
        this.inv_ElevatorId = inv_ElevatorId;
    }

    public ServiceAppointmentRequest() {
    }

    private Code inv_TypeCode;
    private Inv_Id inv_ElevatorId;
}
