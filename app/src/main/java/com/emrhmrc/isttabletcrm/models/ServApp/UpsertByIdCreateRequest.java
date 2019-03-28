package com.emrhmrc.isttabletcrm.models.ServApp;

import java.util.ArrayList;
import java.util.List;

public class UpsertByIdCreateRequest {
    private String UserId;
    private serviceAppointment serviceAppointment;
    private List<ServAppGetByIdNotes> servAppNotesList;
    private List<ServAppGetByIdServAppModernizationChecklists> servAppModernizationChecklistsList;
    private List<ServAppGetByIdServAppUnsuitabilities> servAppUnsuitabilitiesTable;
    private List<ServAppGetByIdServAppBreakdownTypes> servAppBreakdownTypesList;
    private List<ServAppGetByIdServAppDetails> servAppDetailsList;
    private List<ServAppGetByIdServAppWorkListDetails> servAppWorkListDetailsList;

    public UpsertByIdCreateRequest() {
        servAppNotesList=new ArrayList<>();
        servAppModernizationChecklistsList=new ArrayList<>();
        servAppUnsuitabilitiesTable=new ArrayList<>();
        servAppBreakdownTypesList=new ArrayList<>();
        servAppDetailsList=new ArrayList<>();
        servAppWorkListDetailsList=new ArrayList<>();
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public com.emrhmrc.isttabletcrm.models.ServApp.serviceAppointment getServiceAppointment() {
        return serviceAppointment;
    }

    public void setServiceAppointment(com.emrhmrc.isttabletcrm.models.ServApp.serviceAppointment serviceAppointment) {
        this.serviceAppointment = serviceAppointment;
    }

    public List<ServAppGetByIdNotes> getServAppNotesList() {
        return servAppNotesList;
    }

    public void setServAppNotesList(List<ServAppGetByIdNotes> servAppNotesList) {
        this.servAppNotesList = servAppNotesList;
    }

    public List<ServAppGetByIdServAppModernizationChecklists> getServAppModernizationChecklistsList() {
        return servAppModernizationChecklistsList;
    }

    public void setServAppModernizationChecklistsList(List<ServAppGetByIdServAppModernizationChecklists> servAppModernizationChecklistsList) {
        this.servAppModernizationChecklistsList = servAppModernizationChecklistsList;
    }

    public List<ServAppGetByIdServAppUnsuitabilities> getServAppUnsuitabilitiesTable() {
        return servAppUnsuitabilitiesTable;
    }

    public void setServAppUnsuitabilitiesTable(List<ServAppGetByIdServAppUnsuitabilities> servAppUnsuitabilitiesTable) {
        this.servAppUnsuitabilitiesTable = servAppUnsuitabilitiesTable;
    }

    public List<ServAppGetByIdServAppBreakdownTypes> getServAppBreakdownTypesList() {
        return servAppBreakdownTypesList;
    }

    public void setServAppBreakdownTypesList(List<ServAppGetByIdServAppBreakdownTypes> servAppBreakdownTypesList) {
        this.servAppBreakdownTypesList = servAppBreakdownTypesList;
    }

    public List<ServAppGetByIdServAppDetails> getServAppDetailsList() {
        return servAppDetailsList;
    }

    public void setServAppDetailsList(List<ServAppGetByIdServAppDetails> servAppDetailsList) {
        this.servAppDetailsList = servAppDetailsList;
    }

    public List<ServAppGetByIdServAppWorkListDetails> getServAppWorkListDetailsList() {
        return servAppWorkListDetailsList;
    }

    public void setServAppWorkListDetailsList(List<ServAppGetByIdServAppWorkListDetails> servAppWorkListDetailsList) {
        this.servAppWorkListDetailsList = servAppWorkListDetailsList;
    }
}
