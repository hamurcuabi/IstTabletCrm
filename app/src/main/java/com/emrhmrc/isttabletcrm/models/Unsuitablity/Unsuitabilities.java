package com.emrhmrc.isttabletcrm.models.Unsuitablity;

import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;

public class Unsuitabilities {
    private String ActivityId;
    private String Subject;
    private String ScheduledStart;
    private Inv_Id CustomerId;
    private Inv_Id ElevatorId;

    public Unsuitabilities() {
    }

    public String getScheduledStart() {
        return ScheduledStart;
    }

    public void setScheduledStart(String scheduledStart) {
        ScheduledStart = scheduledStart;
    }

    public Inv_Id getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(Inv_Id customerId) {
        CustomerId = customerId;
    }

    public Inv_Id getElevatorId() {
        return ElevatorId;
    }

    public void setElevatorId(Inv_Id elevatorId) {
        ElevatorId = elevatorId;
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
        this.Subject = subject;
    }
}
