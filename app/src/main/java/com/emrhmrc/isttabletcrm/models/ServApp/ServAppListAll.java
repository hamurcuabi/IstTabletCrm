package com.emrhmrc.isttabletcrm.models.ServApp;

import com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata;
import com.emrhmrc.isttabletcrm.models.ServiceAppointments;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServAppListAll {
    @SerializedName("TableMetadataServApps")
    private TableMetadata TableMetadataServApps;

    @SerializedName("ServiceAppointments")
    private List<ServiceAppointments> ServiceAppointments;


    private String ErrorMsg;
    private boolean Success;

    public ServAppListAll() {
    }



    public boolean isSuccess() {
        return Success;
    }

    public TableMetadata getTableMetadataServApps() {
        return TableMetadataServApps;
    }

    public void setTableMetadataServApps(TableMetadata TableMetadataServApps) {
        this.TableMetadataServApps = TableMetadataServApps;
    }

    public List<ServiceAppointments> getServiceAppointments() {
        return ServiceAppointments;
    }

    public void setServiceAppointments(List<com.emrhmrc.isttabletcrm.models.ServiceAppointments> ServiceAppointments) {
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


