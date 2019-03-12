package com.emrhmrc.isttabletcrm.models.Notification;

import com.emrhmrc.isttabletcrm.models.CommonClass.Code;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;

public class Notification {

    private String ActivityId;
    private String Subject;
    private String ScheduledStart;
    private String Description;
    private Code NotificationType;

    public Inv_Id getFrom() {
        return From;
    }

    public void setFrom(Inv_Id from) {
        From = from;
    }

    private Inv_Id From;

    public Notification() {
    }

    public String getActivityId() {

        return ActivityId;
    }

    public void setActivityId(String activityId) {
        ActivityId = activityId;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getScheduledStart() {
        return ScheduledStart;
    }

    public void setScheduledStart(String scheduledStart) {
        ScheduledStart = scheduledStart;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Code getNotificationType() {
        return NotificationType;
    }

    public void setNotificationType(Code notificationType) {
        NotificationType = notificationType;
    }
}
