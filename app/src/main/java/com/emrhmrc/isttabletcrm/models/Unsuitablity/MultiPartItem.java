package com.emrhmrc.isttabletcrm.models.Unsuitablity;

import com.emrhmrc.isttabletcrm.models.ServApp.Notes;

import java.util.List;

public class MultiPartItem {

    private String UserId;
    private String Subject;
    private String Description;
    private String SentOn;
    private List<Notes> UnsuitabilityNotes;


    public MultiPartItem() {
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getSentOn() {
        return SentOn;
    }

    public void setSentOn(String sentOn) {
        SentOn = sentOn;
    }

    public List<Notes> getUnsuitabilityNotes() {
        return UnsuitabilityNotes;
    }

    public void setUnsuitabilityNotes(List<Notes> unsuitabilityNotes) {
        UnsuitabilityNotes = unsuitabilityNotes;
    }
}
