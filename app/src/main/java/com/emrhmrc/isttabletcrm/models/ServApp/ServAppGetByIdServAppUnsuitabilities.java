package com.emrhmrc.isttabletcrm.models.ServApp;

public class ServAppGetByIdServAppUnsuitabilities {
    private String Description;

    private String ActivityId;

    private String Subject;

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
                '}';
    }
}
