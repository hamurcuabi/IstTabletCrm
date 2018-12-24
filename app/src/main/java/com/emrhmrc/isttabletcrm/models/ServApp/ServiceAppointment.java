package com.emrhmrc.isttabletcrm.models.ServApp;


import java.util.List;

public class ServiceAppointment {
    private StatusCode StatusCode;

    private String ActivityId;

    private String Subject;

    private List<ServAppGetByIdServAppBreakdownTypes> ServAppGetByIdServAppBreakdownTypes;

    private Inv_CustomerId inv_CustomerId;

    private List<ServAppGetByIdServAppWorkListDetails> ServAppGetByIdServAppWorkListDetails;

    private List<ServAppGetByIdNotes> ServAppGetByIdNotes;

    private String ActualStart;

    private String ActualEnd;

    private String inv_ElevatorId;

    private List<ServAppGetByIdServAppUnsuitabilities> ServAppGetByIdServAppUnsuitabilities;

    private List<ServAppGetByIdServAppDetails> ServAppGetByIdServAppDetails;

    private Inv_TypeCode inv_TypeCode;

    private List<ServAppGetByIdServAppModernizationChecklists> ServAppGetByIdServAppModernizationChecklists;

    public StatusCode getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(StatusCode StatusCode) {
        this.StatusCode = StatusCode;
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

    public List<ServAppGetByIdServAppBreakdownTypes> getServAppGetByIdServAppBreakdownTypes() {
        return ServAppGetByIdServAppBreakdownTypes;
    }

    public void setServAppGetByIdServAppBreakdownTypes(List<ServAppGetByIdServAppBreakdownTypes> ServAppGetByIdServAppBreakdownTypes) {
        this.ServAppGetByIdServAppBreakdownTypes = ServAppGetByIdServAppBreakdownTypes;
    }

    public Inv_CustomerId getInv_CustomerId() {
        return inv_CustomerId;
    }

    public void setInv_CustomerId(Inv_CustomerId inv_CustomerId) {
        this.inv_CustomerId = inv_CustomerId;
    }

    public List<ServAppGetByIdServAppWorkListDetails> getServAppGetByIdServAppWorkListDetails() {
        return ServAppGetByIdServAppWorkListDetails;
    }

    public void setServAppGetByIdServAppWorkListDetails(List<ServAppGetByIdServAppWorkListDetails> ServAppGetByIdServAppWorkListDetails) {
        this.ServAppGetByIdServAppWorkListDetails = ServAppGetByIdServAppWorkListDetails;
    }

    public List<ServAppGetByIdNotes> getServAppGetByIdNotes() {
        return ServAppGetByIdNotes;
    }

    public void setServAppGetByIdNotes(List<ServAppGetByIdNotes> ServAppGetByIdNotes) {
        this.ServAppGetByIdNotes = ServAppGetByIdNotes;
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

    public String getInv_ElevatorId() {
        return inv_ElevatorId;
    }

    public void setInv_ElevatorId(String inv_ElevatorId) {
        this.inv_ElevatorId = inv_ElevatorId;
    }

    public List<ServAppGetByIdServAppUnsuitabilities> getServAppGetByIdServAppUnsuitabilities() {
        return ServAppGetByIdServAppUnsuitabilities;
    }

    public void setServAppGetByIdServAppUnsuitabilities(List<ServAppGetByIdServAppUnsuitabilities> ServAppGetByIdServAppUnsuitabilities) {
        this.ServAppGetByIdServAppUnsuitabilities = ServAppGetByIdServAppUnsuitabilities;
    }

    public List<ServAppGetByIdServAppDetails> getServAppGetByIdServAppDetails() {
        return ServAppGetByIdServAppDetails;
    }

    public void setServAppGetByIdServAppDetails(List<ServAppGetByIdServAppDetails> ServAppGetByIdServAppDetails) {
        this.ServAppGetByIdServAppDetails = ServAppGetByIdServAppDetails;
    }

    public Inv_TypeCode getInv_TypeCode() {
        return inv_TypeCode;
    }

    public void setInv_TypeCode(Inv_TypeCode inv_TypeCode) {
        this.inv_TypeCode = inv_TypeCode;
    }

    public List<ServAppGetByIdServAppModernizationChecklists> getServAppGetByIdServAppModernizationChecklists() {
        return ServAppGetByIdServAppModernizationChecklists;
    }

    public void setServAppGetByIdServAppModernizationChecklists(List<ServAppGetByIdServAppModernizationChecklists> ServAppGetByIdServAppModernizationChecklists) {
        this.ServAppGetByIdServAppModernizationChecklists = ServAppGetByIdServAppModernizationChecklists;
    }

    @Override
    public String toString() {
        return "ServiceAppointment{" +
                "StatusCode=" + StatusCode +
                ", ActivityId='" + ActivityId + '\'' +
                ", Subject='" + Subject + '\'' +
                ", ServAppGetByIdServAppBreakdownTypes=" + ServAppGetByIdServAppBreakdownTypes +
                ", inv_CustomerId=" + inv_CustomerId +
                ", ServAppGetByIdServAppWorkListDetails=" + ServAppGetByIdServAppWorkListDetails +
                ", ServAppGetByIdNotes=" + ServAppGetByIdNotes +
                ", ActualStart='" + ActualStart + '\'' +
                ", ActualEnd='" + ActualEnd + '\'' +
                ", inv_ElevatorId='" + inv_ElevatorId + '\'' +
                ", ServAppGetByIdServAppUnsuitabilities=" + ServAppGetByIdServAppUnsuitabilities +
                ", ServAppGetByIdServAppDetails=" + ServAppGetByIdServAppDetails +
                ", inv_TypeCode=" + inv_TypeCode +
                ", ServAppGetByIdServAppModernizationChecklists=" + ServAppGetByIdServAppModernizationChecklists +
                '}';
    }
}

