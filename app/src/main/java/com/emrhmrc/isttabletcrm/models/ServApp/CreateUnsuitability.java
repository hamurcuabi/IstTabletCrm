package com.emrhmrc.isttabletcrm.models.ServApp;

import com.emrhmrc.isttabletcrm.models.Elevator.CustomerIdRequest;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorIdRequest;

import java.util.List;

public class CreateUnsuitability {
    private String ServAppId;
    private String UserId;
    private ElevatorIdRequest ElevatorId;
    private CustomerIdRequest CustomerId;
    private String Description;
    private String Subject;
    private String SentOn;
    private List<Notes> UnsuitabilityNotes;

    public CreateUnsuitability() {
    }

    public ElevatorIdRequest getElevatorId() {
        return ElevatorId;
    }

    public void setElevatorId(ElevatorIdRequest elevatorId) {
        ElevatorId = elevatorId;
    }

    public CustomerIdRequest getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(CustomerIdRequest customerId) {
        CustomerId = customerId;
    }

    public List<Notes> getUnsuitabilityNotes() {
        return UnsuitabilityNotes;
    }

    public void setUnsuitabilityNotes(List<Notes> unsuitabilityNotes) {
        UnsuitabilityNotes = unsuitabilityNotes;
    }

    public String getServAppId() {
        return ServAppId;
    }

    public void setServAppId(String servAppId) {
        ServAppId = servAppId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getSentOn() {
        return SentOn;
    }

    public void setSentOn(String sentOn) {
        SentOn = sentOn;
    }
}
