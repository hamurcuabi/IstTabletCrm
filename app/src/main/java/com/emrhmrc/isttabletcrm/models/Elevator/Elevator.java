package com.emrhmrc.isttabletcrm.models.Elevator;

import java.util.List;

public class Elevator {
    private String inv_Capacity;

    private String inv_SerialNumber;

    private List<ServApps> ServApps;

    private String inv_ElevatorNumber;

    private String inv_BlockName;

    private String inv_ColorCode;

    private String inv_Location;

    private Inv_AccountId inv_AccountId;

    private String inv_BuilderFirmId;

    private String inv_Floor;

    private Inv_ClassId inv_ClassId;

    private String inv_ElevatorId;

    private Inv_WorkingStCode inv_WorkingStCode;

    public String getInv_Capacity() {
        return inv_Capacity;
    }

    public void setInv_Capacity(String inv_Capacity) {
        this.inv_Capacity = inv_Capacity;
    }

    public String getInv_SerialNumber() {
        return inv_SerialNumber;
    }

    public void setInv_SerialNumber(String inv_SerialNumber) {
        this.inv_SerialNumber = inv_SerialNumber;
    }

    public List<ServApps> getServApps() {
        return ServApps;
    }

    public void setServApps(List<ServApps> ServApps) {
        this.ServApps = ServApps;
    }

    public String getInv_ElevatorNumber() {
        return inv_ElevatorNumber;
    }

    public void setInv_ElevatorNumber(String inv_ElevatorNumber) {
        this.inv_ElevatorNumber = inv_ElevatorNumber;
    }

    public String getInv_BlockName() {
        return inv_BlockName;
    }

    public void setInv_BlockName(String inv_BlockName) {
        this.inv_BlockName = inv_BlockName;
    }

    public String getInv_ColorCode() {
        return inv_ColorCode;
    }

    public void setInv_ColorCode(String inv_ColorCode) {
        this.inv_ColorCode = inv_ColorCode;
    }

    public String getInv_Location() {
        return inv_Location;
    }

    public void setInv_Location(String inv_Location) {
        this.inv_Location = inv_Location;
    }

    public Inv_AccountId getInv_AccountId() {
        return inv_AccountId;
    }

    public void setInv_AccountId(Inv_AccountId inv_AccountId) {
        this.inv_AccountId = inv_AccountId;
    }

    public String getInv_BuilderFirmId() {
        return inv_BuilderFirmId;
    }

    public void setInv_BuilderFirmId(String inv_BuilderFirmId) {
        this.inv_BuilderFirmId = inv_BuilderFirmId;
    }

    public String getInv_Floor() {
        return inv_Floor;
    }

    public void setInv_Floor(String inv_Floor) {
        this.inv_Floor = inv_Floor;
    }

    public Inv_ClassId getInv_ClassId() {
        return inv_ClassId;
    }

    public void setInv_ClassId(Inv_ClassId inv_ClassId) {
        this.inv_ClassId = inv_ClassId;
    }

    public String getInv_ElevatorId() {
        return inv_ElevatorId;
    }

    public void setInv_ElevatorId(String inv_ElevatorId) {
        this.inv_ElevatorId = inv_ElevatorId;
    }

    public Inv_WorkingStCode getInv_WorkingStCode() {
        return inv_WorkingStCode;
    }

    public void setInv_WorkingStCode(Inv_WorkingStCode inv_WorkingStCode) {
        this.inv_WorkingStCode = inv_WorkingStCode;
    }

    @Override
    public String toString() {
        return "ClassPojo [inv_Capacity = " + inv_Capacity + ", inv_SerialNumber = " + inv_SerialNumber + ", ServApps = " + ServApps + ", inv_ElevatorNumber = " + inv_ElevatorNumber + ", inv_BlockName = " + inv_BlockName + ", inv_ColorCode = " + inv_ColorCode + ", inv_Location = " + inv_Location + ", inv_AccountId = " + inv_AccountId + ", inv_BuilderFirmId = " + inv_BuilderFirmId + ", inv_Floor = " + inv_Floor + ", inv_ClassId = " + inv_ClassId + ", inv_ElevatorId = " + inv_ElevatorId + ", inv_WorkingStCode = " + inv_WorkingStCode + "]";
    }
}

