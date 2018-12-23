package com.emrhmrc.isttabletcrm.models.Elevator;

import java.util.List;

public class ServApps {
    private String ActualStart;

    private List<ServAppDetails> ServAppDetails;

    private String ActivityId;

    private String Subject;

    private String inv_TypeCode;

    public String getActualStart() {
        return ActualStart;
    }

    public void setActualStart(String ActualStart) {
        this.ActualStart = ActualStart;
    }

    public List<ServAppDetails> getServAppDetails() {
        return ServAppDetails;
    }

    public void setServAppDetails(List<ServAppDetails> ServAppDetails) {
        this.ServAppDetails = ServAppDetails;
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

    public String getInv_TypeCode() {
        return inv_TypeCode;
    }

    public void setInv_TypeCode(String inv_TypeCode) {
        this.inv_TypeCode = inv_TypeCode;
    }

    @Override
    public String toString() {
        return " [ActualStart = " + ActualStart + ", ServAppDetails = " + ServAppDetails + ", ActivityId = " + ActivityId + ", Subject = " + Subject + ", inv_TypeCode = " + inv_TypeCode + "]";
    }
}

