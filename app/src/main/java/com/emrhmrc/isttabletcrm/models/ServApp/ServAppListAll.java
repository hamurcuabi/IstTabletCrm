package com.emrhmrc.isttabletcrm.models.ServApp;

import java.util.Arrays;
import java.util.List;

public class ServAppListAll {
    private TableMetadataServApps TableMetadataServApps;

    private List<ServiceAppointments> ServiceAppointments;

    private String ErrorMsg;

    private boolean Success;

    public TableMetadataServApps getTableMetadataServApps() {
        return TableMetadataServApps;
    }

    public void setTableMetadataServApps(TableMetadataServApps TableMetadataServApps) {
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
                ", ServiceAppointments=" + Arrays.toString(ServiceAppointments) +
                ", ErrorMsg='" + ErrorMsg + '\'' +
                ", Success='" + Success + '\'' +
                '}';
    }
}


