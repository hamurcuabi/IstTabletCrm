package com.emrhmrc.isttabletcrm.models.ServApp;

import com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServAppListAll {
    @SerializedName("TableMetadataServApps")
    private TableMetadata TableMetadataServApps;

    @SerializedName("ServiceAppointments")
    private List<ServiceAppointments> ServiceAppointments;

    private String ErrorMsg;

    private boolean Success;

    public TableMetadata getTableMetadataServApps() {
        return TableMetadataServApps;
    }

    public void setTableMetadataServApps(TableMetadata TableMetadataServApps) {
        this.TableMetadataServApps = TableMetadataServApps;
    }

    public List<ServiceAppointments> getServiceAppointments() {
        return ServiceAppointments;
    }

    public void setServiceAppointments(List<ServiceAppointments> ServiceAppointments) {
        this.ServiceAppointments = ServiceAppointments;
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

    @Override
    public String toString() {
        return "ServAppListAll{" +
                "TableMetadataServApps=" + TableMetadataServApps +
                ", ServiceAppointments=" + ServiceAppointments +
                ", ErrorMsg='" + ErrorMsg + '\'' +
                ", Success=" + Success +
                '}';
    }
}


