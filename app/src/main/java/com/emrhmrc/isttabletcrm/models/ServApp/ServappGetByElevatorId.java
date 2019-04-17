package com.emrhmrc.isttabletcrm.models.ServApp;

import com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServappGetByElevatorId extends DefaultResponse {

    private TableMetadata TableMetadataGetByElevatorIdServApp;
    @SerializedName("ServiceAppointment")
    private List<ServiceAppointmentElevator> ServiceAppointment;

    public ServappGetByElevatorId() {
    }

    public List<ServiceAppointmentElevator> getServiceAppointment() {
        return ServiceAppointment;
    }

    public void setServiceAppointment(List<ServiceAppointmentElevator> serviceAppointment) {
        ServiceAppointment = serviceAppointment;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }

    public TableMetadata getTableMetadataGetByElevatorIdServApp() {
        return TableMetadataGetByElevatorIdServApp;
    }

    public void setTableMetadataGetByElevatorIdServApp(TableMetadata tableMetadataGetByElevatorIdServApp) {
        TableMetadataGetByElevatorIdServApp = tableMetadataGetByElevatorIdServApp;
    }

}
