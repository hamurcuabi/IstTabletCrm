package com.emrhmrc.isttabletcrm.bindingModel;


import com.emrhmrc.isttabletcrm.models.CommonClass.Code;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdNotes;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppBreakdownTypes;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppDetails;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppModernizationChecklists;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppUnsuitabilities;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppWorkListDetails;

import java.util.List;

public class ServiceAppointment {
    private Code StatusCode;
    private String ActivityId;
    private String inv_Description;
    private String Subject;
    private List<ServAppGetByIdServAppBreakdownTypes> ServAppGetByIdServAppBreakdownTypes;
    private Inv_Id inv_CustomerId;
    private List<ServAppGetByIdServAppWorkListDetails> ServAppGetByIdServAppWorkListDetails;
    private List<ServAppGetByIdNotes> ServAppGetByIdNotes;
    private String ScheduledStart;
    private String ScheduledEnd;
    private Inv_Id inv_ElevatorId;
    private List<ServAppGetByIdServAppUnsuitabilities> ServAppGetByIdServAppUnsuitabilities;
    private List<ServAppGetByIdServAppDetails> ServAppGetByIdServAppDetails;
    private Code inv_TypeCode;
    private List<ServAppGetByIdServAppModernizationChecklists> ServAppGetByIdServAppModernizationChecklists;
    private double inv_Latitude;
    private double inv_Longitude;
    private Code PriortiyCode;
    private String inv_BlockName;
    private String inv_QuoteId;
    private Inv_Id inv_Supervisorid;
    private Inv_Id OwnerId;
    private Inv_Id inv_BreakdownDefCodeid;
    private Inv_Id inv_MainProductGroupid;
    private Inv_Id inv_SubProductGroupid;
    private Inv_Id inv_BreakdownCodeid;

    public Inv_Id getInv_BreakdownCodeid() {
        return inv_BreakdownCodeid;
    }

    public void setInv_BreakdownCodeid(Inv_Id inv_BreakdownCodeid) {
        this.inv_BreakdownCodeid = inv_BreakdownCodeid;
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


    public String getInv_Description() {
        return inv_Description;
    }

    public void setInv_Description(String inv_Description) {
        this.inv_Description = inv_Description;
    }

    public String getInv_QuoteId() {
        return inv_QuoteId;
    }

    public void setInv_QuoteId(String inv_QuoteId) {
        this.inv_QuoteId = inv_QuoteId;
    }

    public Inv_Id getInv_Supervisorid() {
        return inv_Supervisorid;
    }

    public void setInv_Supervisorid(Inv_Id inv_Supervisorid) {
        this.inv_Supervisorid = inv_Supervisorid;
    }

    public Inv_Id getOwnerId() {
        return OwnerId;
    }

    public void setOwnerId(Inv_Id ownerId) {
        OwnerId = ownerId;
    }

    public String getInv_BlockName() {
        return inv_BlockName;
    }

    public void setInv_BlockName(String inv_BlockName) {
        this.inv_BlockName = inv_BlockName;
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

    public Inv_Id getInv_CustomerId() {
        return inv_CustomerId;
    }

    public void setInv_CustomerId(Inv_Id inv_CustomerId) {
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

    public Inv_Id getInv_ElevatorId() {
        return inv_ElevatorId;
    }

    public void setInv_ElevatorId(Inv_Id inv_ElevatorId) {
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

    public Code getInv_TypeCode() {
        return inv_TypeCode;
    }

    public void setInv_TypeCode(Code inv_TypeCode) {
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
                ", ActualStart='" + ScheduledStart + '\'' +
                ", ActualEnd='" + ScheduledEnd + '\'' +
                ", inv_ElevatorId='" + inv_ElevatorId + '\'' +
                ", ServAppGetByIdServAppUnsuitabilities=" + ServAppGetByIdServAppUnsuitabilities +
                ", ServAppGetByIdServAppDetails=" + ServAppGetByIdServAppDetails +
                ", inv_TypeCode=" + inv_TypeCode +
                ", ServAppGetByIdServAppModernizationChecklists=" + ServAppGetByIdServAppModernizationChecklists +
                '}';
    }
}

