package com.emrhmrc.isttabletcrm.models.ServApp;

import com.emrhmrc.isttabletcrm.models.CommonClass.Code;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;

public class ServiceAppointmentElevator {

    private String ActivityId;
    private Code inv_TypeCode;
    private Code StatusCode;
    private String ScheduledStart;

    public String getActivityId() {
        return ActivityId;
    }

    public void setActivityId(String activityId) {
        ActivityId = activityId;
    }

    public Code getInv_TypeCode() {
        return inv_TypeCode;
    }

    public void setInv_TypeCode(Code inv_TypeCode) {
        this.inv_TypeCode = inv_TypeCode;
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

    public Inv_Id getOwnerId() {
        return OwnerId;
    }

    public void setOwnerId(Inv_Id ownerId) {
        OwnerId = ownerId;
    }

    private String ScheduledEnd;
    private Inv_Id OwnerId;

    public ServiceAppointmentElevator() {
    }
}
