package com.emrhmrc.isttabletcrm.models.ServApp;

import com.emrhmrc.isttabletcrm.bindingModel.ServiceAppointment;
import com.emrhmrc.isttabletcrm.models.Elevator.ServAppDetails;
import com.emrhmrc.isttabletcrm.models.Unsuitablity.Unsuitabilities;

import java.util.List;

public class UpsertByIdRequest {
    private String UserId;
    private List<String> ServAppChangedFields;
    private ServiceAppointment serviceAppointment;
    private ServAppGetByIdNotes servAppNotesList;
    private List<ServAppGetByIdServAppWorkListDetails> servAppWorkListDetailsList;
    private List<ServAppDetails> servAppDetailsList;
    private List<ServAppGetByIdServAppBreakdownTypes> servAppBreakdownTypesList;
    private List<ServAppGetByIdServAppModernizationChecklists> servAppModernizationChecklistsList;
    private List<Unsuitabilities> servAppUnsuitabilitiesTable;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public List<String> getServAppChangedFields() {
        return ServAppChangedFields;
    }

    public void setServAppChangedFields(List<String> servAppChangedFields) {
        ServAppChangedFields = servAppChangedFields;
    }

    public ServiceAppointment getServiceAppointment() {
        return serviceAppointment;
    }

    public void setServiceAppointment(ServiceAppointment serviceAppointment) {
        this.serviceAppointment = serviceAppointment;
    }

    public ServAppGetByIdNotes getServAppNotesList() {
        return servAppNotesList;
    }

    public void setServAppNotesList(ServAppGetByIdNotes servAppNotesList) {
        this.servAppNotesList = servAppNotesList;
    }

    public List<ServAppGetByIdServAppWorkListDetails> getServAppWorkListDetailsList() {
        return servAppWorkListDetailsList;
    }

    public void setServAppWorkListDetailsList(List<ServAppGetByIdServAppWorkListDetails> servAppWorkListDetailsList) {
        this.servAppWorkListDetailsList = servAppWorkListDetailsList;
    }

    public List<ServAppDetails> getServAppDetailsList() {
        return servAppDetailsList;
    }

    public void setServAppDetailsList(List<ServAppDetails> servAppDetailsList) {
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

    public List<Unsuitabilities> getServAppUnsuitabilitiesTable() {
        return servAppUnsuitabilitiesTable;
    }

    public void setServAppUnsuitabilitiesTable(List<Unsuitabilities> servAppUnsuitabilitiesTable) {
        this.servAppUnsuitabilitiesTable = servAppUnsuitabilitiesTable;
    }

    public UpsertByIdRequest() {
    }
}
