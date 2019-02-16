package com.emrhmrc.isttabletcrm.models.ServApp;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class ServAppGetByIdServAppUnsuitabilities {
    private String Description;

    private String ActivityId;

    private String Subject;
    private Date SentOn;

    public Date getSentOn() {
        return SentOn;
    }

    public ServAppGetByIdServAppUnsuitabilities() {
    }

    public void setSentOn(Date sentOn) {
        SentOn = sentOn;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getActivityId() {
        return ActivityId;
    }

    public void setActivityId(String ActivityId) {
        this.ActivityId = ActivityId;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    @Override
    public String toString() {
        return "ServAppGetByIdServAppUnsuitabilities{" +
                "Description='" + Description + '\'' +
                ", ActivityId='" + ActivityId + '\'' +
                ", Subject='" + Subject + '\'' +
                ", SentOn=" + SentOn +
                '}';
    }
}
