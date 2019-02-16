package com.emrhmrc.isttabletcrm.models.ServApp;

import java.util.Date;

public class CreateUnsuitability {
    private String ServAppId;
    private String UserId;
    private String Description;
    private String Subject;
    private Date SentOn;

    @Override
    public String toString() {
        return "CreateUnsuitability{" +
                "ServAppId='" + ServAppId + '\'' +
                ", UserId='" + UserId + '\'' +
                ", Description='" + Description + '\'' +
                ", Subject='" + Subject + '\'' +
                ", SentOn=" + SentOn +
                '}';
    }

    public CreateUnsuitability() {
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

    public Date getSentOn() {
        return SentOn;
    }

    public void setSentOn(Date sentOn) {
        SentOn = sentOn;
    }
}
