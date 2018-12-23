package com.emrhmrc.isttabletcrm.models.ServApp;

public class ServiceAppointments {
    private StatusCode StatusCode;

    private String ActualStart;

    private String ActualEnd;

    private String ActivityId;

    private String Subject;

    private Inv_CustomerId inv_CustomerId;

    private String inv_TypeCode;

    public StatusCode getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(StatusCode StatusCode) {
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

    public Inv_CustomerId getInv_CustomerId() {
        return inv_CustomerId;
    }

    public void setInv_CustomerId(Inv_CustomerId inv_CustomerId) {
        this.inv_CustomerId = inv_CustomerId;
    }

    public String getInv_TypeCode() {
        return inv_TypeCode;
    }

    public void setInv_TypeCode(String inv_TypeCode) {
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
                ", inv_TypeCode='" + inv_TypeCode + '\'' +
                '}';
    }
}

