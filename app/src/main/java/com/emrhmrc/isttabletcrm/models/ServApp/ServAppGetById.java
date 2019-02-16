package com.emrhmrc.isttabletcrm.models.ServApp;

import com.emrhmrc.isttabletcrm.bindingModel.ServiceAppointment;
import com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServAppGetById {
    @SerializedName("TableMetadataServAppBreakdownTypes")
    private TableMetadata TableMetadataServAppBreakdownTypes;
    @SerializedName("TableMetadataServAppDetails")
    private TableMetadata TableMetadataServAppDetails;
    @SerializedName("TableMetadataNotes")
    private TableMetadata TableMetadataNotes;
    @SerializedName("TableMetadataServAppModernizationChecklists")
    private TableMetadata TableMetadataServAppModernizationChecklists;
    @SerializedName("TableMetadataServApp")
    private TableMetadata TableMetadataServApp;
    private List<ServAppGetByIdNotes> ServAppGetByIdNotes;
    private List<ServAppGetByIdServAppWorkListDetails> ServAppGetByIdServAppWorkListDetails;
    private List<ServAppGetByIdServAppDetails> ServAppGetByIdServAppDetails;
    private List<ServAppGetByIdServAppBreakdownTypes> ServAppGetByIdServAppBreakdownTypes;
    private List<ServAppGetByIdServAppModernizationChecklists> ServAppGetByIdServAppModernizationChecklists;
    private List<ServAppGetByIdServAppUnsuitabilities> ServAppGetByIdServAppUnsuitabilities;
    private String ErrorMsg;
    private boolean Success;
    private com.emrhmrc.isttabletcrm.bindingModel.ServiceAppointment ServiceAppointment;
    @SerializedName("TableMetadataServAppUnsuitabilities")
    private TableMetadata TableMetadataServAppUnsuitabilities;
    @SerializedName("TableMetadataServAppWorkListDetails")
    private TableMetadata TableMetadataServAppWorkListDetails;

    public List<com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdNotes> getServAppGetByIdNotes() {
        return ServAppGetByIdNotes;
    }

    public void setServAppGetByIdNotes(List<com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdNotes> servAppGetByIdNotes) {
        ServAppGetByIdNotes = servAppGetByIdNotes;
    }

    public List<com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppWorkListDetails> getServAppGetByIdServAppWorkListDetails() {
        return ServAppGetByIdServAppWorkListDetails;
    }

    public void setServAppGetByIdServAppWorkListDetails(List<com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppWorkListDetails> servAppGetByIdServAppWorkListDetails) {
        ServAppGetByIdServAppWorkListDetails = servAppGetByIdServAppWorkListDetails;
    }

    public List<com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppDetails> getServAppGetByIdServAppDetails() {
        return ServAppGetByIdServAppDetails;
    }

    public void setServAppGetByIdServAppDetails(List<com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppDetails> servAppGetByIdServAppDetails) {
        ServAppGetByIdServAppDetails = servAppGetByIdServAppDetails;
    }

    public List<com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppBreakdownTypes> getServAppGetByIdServAppBreakdownTypes() {
        return ServAppGetByIdServAppBreakdownTypes;
    }

    public void setServAppGetByIdServAppBreakdownTypes(List<com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppBreakdownTypes> servAppGetByIdServAppBreakdownTypes) {
        ServAppGetByIdServAppBreakdownTypes = servAppGetByIdServAppBreakdownTypes;
    }

    public List<com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppModernizationChecklists> getServAppGetByIdServAppModernizationChecklists() {
        return ServAppGetByIdServAppModernizationChecklists;
    }

    public void setServAppGetByIdServAppModernizationChecklists(List<com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppModernizationChecklists> servAppGetByIdServAppModernizationChecklists) {
        ServAppGetByIdServAppModernizationChecklists = servAppGetByIdServAppModernizationChecklists;
    }

    public List<com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppUnsuitabilities> getServAppGetByIdServAppUnsuitabilities() {
        return ServAppGetByIdServAppUnsuitabilities;
    }

    public void setServAppGetByIdServAppUnsuitabilities(List<com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppUnsuitabilities> servAppGetByIdServAppUnsuitabilities) {
        ServAppGetByIdServAppUnsuitabilities = servAppGetByIdServAppUnsuitabilities;
    }

    public boolean isSuccess() {
        return Success;
    }

    public TableMetadata getTableMetadataServAppBreakdownTypes() {
        return TableMetadataServAppBreakdownTypes;
    }

    public void setTableMetadataServAppBreakdownTypes(TableMetadata TableMetadataServAppBreakdownTypes) {
        this.TableMetadataServAppBreakdownTypes = TableMetadataServAppBreakdownTypes;
    }

    public TableMetadata getTableMetadataServAppDetails() {
        return TableMetadataServAppDetails;
    }

    public void setTableMetadataServAppDetails(TableMetadata TableMetadataServAppDetails) {
        this.TableMetadataServAppDetails = TableMetadataServAppDetails;
    }

    public TableMetadata getTableMetadataNotes() {
        return TableMetadataNotes;
    }

    public void setTableMetadataNotes(TableMetadata TableMetadataNotes) {
        this.TableMetadataNotes = TableMetadataNotes;
    }

    public TableMetadata getTableMetadataServAppModernizationChecklists() {
        return TableMetadataServAppModernizationChecklists;
    }

    public void setTableMetadataServAppModernizationChecklists(TableMetadata TableMetadataServAppModernizationChecklists) {
        this.TableMetadataServAppModernizationChecklists = TableMetadataServAppModernizationChecklists;
    }

    public TableMetadata getTableMetadataServApp() {
        return TableMetadataServApp;
    }

    public void setTableMetadataServApp(TableMetadata TableMetadataServApp) {
        this.TableMetadataServApp = TableMetadataServApp;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }

    public boolean getSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public ServiceAppointment getServiceAppointment() {
        return ServiceAppointment;
    }

    public void setServiceAppointment(ServiceAppointment ServiceAppointment) {
        this.ServiceAppointment = ServiceAppointment;
    }

    public TableMetadata getTableMetadataServAppUnsuitabilities() {
        return TableMetadataServAppUnsuitabilities;
    }

    public void setTableMetadataServAppUnsuitabilities(TableMetadata TableMetadataServAppUnsuitabilities) {
        this.TableMetadataServAppUnsuitabilities = TableMetadataServAppUnsuitabilities;
    }

    public TableMetadata getTableMetadataServAppWorkListDetails() {
        return TableMetadataServAppWorkListDetails;
    }

    public void setTableMetadataServAppWorkListDetails(TableMetadata TableMetadataServAppWorkListDetails) {
        this.TableMetadataServAppWorkListDetails = TableMetadataServAppWorkListDetails;
    }


}

