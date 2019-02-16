package com.emrhmrc.isttabletcrm.models.Elevator;

import com.emrhmrc.isttabletcrm.models.CommonClass.Code;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;

import java.util.List;

public class ServApps {
    private String ScheduledStart;

    private List<ServAppDetails> ServAppDetails;

    private String ActivityId;

    private String Subject;

    private Code inv_TypeCode;

    public ServApps() {
    }

    public String getScheduledStart() {
        return ScheduledStart;
    }

    public void setScheduledStart(String scheduledStart) {
        ScheduledStart = scheduledStart;
    }

    public List<com.emrhmrc.isttabletcrm.models.Elevator.ServAppDetails> getServAppDetails() {
        return ServAppDetails;
    }

    public void setServAppDetails(List<com.emrhmrc.isttabletcrm.models.Elevator.ServAppDetails> servAppDetails) {
        ServAppDetails = servAppDetails;
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

    public Code getInv_TypeCode() {
        return inv_TypeCode;
    }

    public void setInv_TypeCode(Code inv_TypeCode) {
        this.inv_TypeCode = inv_TypeCode;
    }
}

