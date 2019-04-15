package com.emrhmrc.isttabletcrm.models.ServApp;

import com.emrhmrc.isttabletcrm.bindingModel.ServiceAppointment;
import com.emrhmrc.isttabletcrm.models.Elevator.ServAppDetails;

import java.util.List;

public class ServappSendToSuperVisorRequest {

    private String UserId;
    private List<ServAppDetails> servAppDetailsList;
    private ServiceAppointmentSupervisor serviceAppointment;

    public ServappSendToSuperVisorRequest() {
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public List<ServAppDetails> getServAppDetailsList() {
        return servAppDetailsList;
    }

    public void setServAppDetailsList(List<ServAppDetails> servAppDetailsList) {
        this.servAppDetailsList = servAppDetailsList;
    }

    public ServiceAppointmentSupervisor getServiceAppointment() {
        return serviceAppointment;
    }

    public void setServiceAppointment(ServiceAppointmentSupervisor serviceAppointment) {
        this.serviceAppointment = serviceAppointment;
    }
}
