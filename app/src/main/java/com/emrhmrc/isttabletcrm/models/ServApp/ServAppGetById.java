package com.emrhmrc.isttabletcrm.models.ServApp;

import com.emrhmrc.isttabletcrm.bindingModel.ServiceAppointment;
import com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata;
import com.google.gson.annotations.SerializedName;

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

    private String ErrorMsg;

    private boolean Success;

    private com.emrhmrc.isttabletcrm.bindingModel.ServiceAppointment ServiceAppointment;
    @SerializedName("TableMetadataServAppUnsuitabilities")
    private TableMetadata TableMetadataServAppUnsuitabilities;
    @SerializedName("TableMetadataServAppWorkListDetails")
    private TableMetadata TableMetadataServAppWorkListDetails;

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

