package com.emrhmrc.isttabletcrm.models.ServApp;

import java.util.ArrayList;
import java.util.List;

public class UpsertByIdUpdateRequest {

    private String UserId;
    private ServiceAppointmentUpdate serviceAppointment;
    private List<Notes> servAppNotesList;
    private List<ServAppGetByIdServAppWorkListDetails> servAppWorkListDetailsList;
    private List<ServAppDetailsList> servAppDetailsList;
    private List<ServAppGetByIdServAppBreakdownTypes> servAppBreakdownTypesList;
    private List<ServAppGetByIdServAppModernizationChecklists> servAppModernizationChecklistsList;

    public UpsertByIdUpdateRequest() {
        serviceAppointment = new ServiceAppointmentUpdate();
        servAppNotesList = new ArrayList<>();
        servAppWorkListDetailsList = new ArrayList<>();
        servAppDetailsList = new ArrayList<>();
        servAppBreakdownTypesList = new ArrayList<>();
        servAppModernizationChecklistsList = new ArrayList<>();
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public ServiceAppointmentUpdate getServiceAppointment() {
        return serviceAppointment;
    }

    public void setServiceAppointment(ServiceAppointmentUpdate serviceAppointment) {
        this.serviceAppointment = serviceAppointment;
    }

    public List<Notes> getServAppNotesList() {
        return servAppNotesList;
    }

    public void setServAppNotesList(List<Notes> servAppNotesList) {
        this.servAppNotesList = servAppNotesList;
    }

    public List<ServAppGetByIdServAppWorkListDetails> getServAppWorkListDetailsList() {
        return servAppWorkListDetailsList;
    }

    public void setServAppWorkListDetailsList(List<ServAppGetByIdServAppWorkListDetails> servAppWorkListDetailsList) {
        this.servAppWorkListDetailsList = servAppWorkListDetailsList;
    }

    public List<ServAppDetailsList> getServAppDetailsList() {
        return servAppDetailsList;
    }

    public void setServAppDetailsList(List<ServAppDetailsList> servAppDetailsList) {
        this.servAppDetailsList = servAppDetailsList;
    }

    public List<ServAppGetByIdServAppBreakdownTypes> getServAppBreakdownTypesList() {
        return servAppBreakdownTypesList;
    }

    public void setServAppBreakdownTypesList(List<ServAppGetByIdServAppBreakdownTypes> servAppBreakdownTypesList) {
        this.servAppBreakdownTypesList = servAppBreakdownTypesList;
    }

    public List<ServAppGetByIdServAppModernizationChecklists> getServAppModernizationChecklistsList() {
        return servAppModernizationChecklistsList;
    }

    public void setServAppModernizationChecklistsList(List<ServAppGetByIdServAppModernizationChecklists> servAppModernizationChecklistsList) {
        this.servAppModernizationChecklistsList = servAppModernizationChecklistsList;
    }
}
