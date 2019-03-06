package com.emrhmrc.isttabletcrm.models.ServApp;

import com.emrhmrc.isttabletcrm.bindingModel.ServiceAppointment;
import com.emrhmrc.isttabletcrm.models.Elevator.ServAppDetails;
import com.emrhmrc.isttabletcrm.models.Unsuitablity.Unsuitabilities;

import java.util.List;

public class UpsertByIdRequest {
    private String UserId;
    private List<String> ServAppChangedFields;
    private ServiceAppointment ServiceApp;
    private List<ServAppGetByIdNotes> Notes;
    private List<ServAppGetByIdServAppWorkListDetails> ServAppWorkDetails;
    private List<ServAppGetByIdServAppDetails> ServAppDetails;
    private List<ServAppGetByIdServAppBreakdownTypes> ServAppBreakdownTypes;
    private List<ServAppGetByIdServAppModernizationChecklists> ServAppModernizationChecklists;
    private List<Unsuitabilities> ServAppUnsuitabilities;

    public UpsertByIdRequest() {
    }

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

    public ServiceAppointment getServiceApp() {
        return ServiceApp;
    }

    public void setServiceApp(ServiceAppointment serviceApp) {
        ServiceApp = serviceApp;
    }

    public List<ServAppGetByIdNotes> getNotes() {
        return Notes;
    }

    public void setNotes(List<ServAppGetByIdNotes> notes) {
        Notes = notes;
    }

    public List<ServAppGetByIdServAppWorkListDetails> getServAppWorkDetails() {
        return ServAppWorkDetails;
    }

    public void setServAppWorkDetails(List<ServAppGetByIdServAppWorkListDetails> servAppWorkDetails) {
        ServAppWorkDetails = servAppWorkDetails;
    }


    public List<ServAppGetByIdServAppDetails> getServAppDetails() {
        return ServAppDetails;
    }

    public void setServAppDetails(List<ServAppGetByIdServAppDetails> servAppDetails) {
        ServAppDetails = servAppDetails;
    }

    public List<ServAppGetByIdServAppBreakdownTypes> getServAppBreakdownTypes() {
        return ServAppBreakdownTypes;
    }

    public void setServAppBreakdownTypes(List<ServAppGetByIdServAppBreakdownTypes> servAppBreakdownTypes) {
        ServAppBreakdownTypes = servAppBreakdownTypes;
    }

    public List<ServAppGetByIdServAppModernizationChecklists> getServAppModernizationChecklists() {
        return ServAppModernizationChecklists;
    }

    public void setServAppModernizationChecklists(List<ServAppGetByIdServAppModernizationChecklists> servAppModernizationChecklists) {
        ServAppModernizationChecklists = servAppModernizationChecklists;
    }

    public List<Unsuitabilities> getServAppUnsuitabilities() {
        return ServAppUnsuitabilities;
    }

    public void setServAppUnsuitabilities(List<Unsuitabilities> servAppUnsuitabilities) {
        ServAppUnsuitabilities = servAppUnsuitabilities;
    }
}
