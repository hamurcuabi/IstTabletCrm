package com.emrhmrc.isttabletcrm.models.ServApp;

import com.emrhmrc.isttabletcrm.models.CommonClass.Code_Id;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id_Id;

import java.util.List;

public class serviceAppointment {

    private Inv_Id_Id inv_CustomerId;
    private Inv_Id_Id inv_ElevatorId;
    private Inv_Id_Id inv_RelatedServiceAppointmentId;
    private Code_Id inv_TypeCode;
    private String Subject;
    private String ScheduledStart;

    public Inv_Id_Id getInv_CustomerId() {
        return inv_CustomerId;
    }

    public void setInv_CustomerId(Inv_Id_Id inv_CustomerId) {
        this.inv_CustomerId = inv_CustomerId;
    }

    public Inv_Id_Id getInv_ElevatorId() {
        return inv_ElevatorId;
    }

    public void setInv_ElevatorId(Inv_Id_Id inv_ElevatorId) {
        this.inv_ElevatorId = inv_ElevatorId;
    }

    public Inv_Id_Id getInv_RelatedServiceAppointmentId() {
        return inv_RelatedServiceAppointmentId;
    }

    public void setInv_RelatedServiceAppointmentId(Inv_Id_Id inv_RelatedServiceAppointmentId) {
        this.inv_RelatedServiceAppointmentId = inv_RelatedServiceAppointmentId;
    }

    public Code_Id getInv_TypeCode() {
        return inv_TypeCode;
    }

    public void setInv_TypeCode(Code_Id inv_TypeCode) {
        this.inv_TypeCode = inv_TypeCode;
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

    public serviceAppointment() {
    }
}
