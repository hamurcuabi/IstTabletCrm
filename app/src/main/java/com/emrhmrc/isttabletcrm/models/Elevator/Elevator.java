package com.emrhmrc.isttabletcrm.models.Elevator;

import com.emrhmrc.isttabletcrm.models.CommonClass.Code;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;
import com.google.gson.annotations.SerializedName;

public class Elevator {
    private Integer inv_Capacity;

    private String inv_SerialNumber;

    private String inv_ElevatorNumber;

    private String inv_BlockName;

    @SerializedName("inv_ColorCode")
    private Code inv_ColorCode;

    private String inv_Location;

    @SerializedName("inv_AccountId")
    private Inv_Id inv_AccountId;
    @SerializedName("inv_BuilderFirmId")
    private Inv_Id inv_BuilderFirmId;

    private Integer inv_Floor;
    @SerializedName("inv_ClassId")
    private Inv_Id inv_ClassId;

    private String inv_ElevatorId;
    @SerializedName("inv_WorkingStCode")
    private Code inv_WorkingStCode;

    public Integer getInv_Capacity() {
        return inv_Capacity;
    }

    public void setInv_Capacity(Integer inv_Capacity) {
        this.inv_Capacity = inv_Capacity;
    }

    public String getInv_SerialNumber() {
        return inv_SerialNumber;
    }

    public void setInv_SerialNumber(String inv_SerialNumber) {
        this.inv_SerialNumber = inv_SerialNumber;
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

    public Code getInv_ColorCode() {
        return inv_ColorCode;
    }

    public void setInv_ColorCode(Code inv_ColorCode) {
        this.inv_ColorCode = inv_ColorCode;
    }

    public String getInv_Location() {
        return inv_Location;
    }

    public void setInv_Location(String inv_Location) {
        this.inv_Location = inv_Location;
    }

    public Inv_Id getInv_AccountId() {
        return inv_AccountId;
    }

    public void setInv_AccountId(Inv_Id inv_AccountId) {
        this.inv_AccountId = inv_AccountId;
    }

    public Inv_Id getInv_BuilderFirmId() {
        return inv_BuilderFirmId;
    }

    public void setInv_BuilderFirmId(Inv_Id inv_BuilderFirmId) {
        this.inv_BuilderFirmId = inv_BuilderFirmId;
    }

    public Integer getInv_Floor() {
        return inv_Floor;
    }

    public void setInv_Floor(Integer inv_Floor) {
        this.inv_Floor = inv_Floor;
    }

    public Inv_Id getInv_ClassId() {
        return inv_ClassId;
    }

    public void setInv_ClassId(Inv_Id inv_ClassId) {
        this.inv_ClassId = inv_ClassId;
    }

    public String getInv_ElevatorId() {
        return inv_ElevatorId;
    }

    public void setInv_ElevatorId(String inv_ElevatorId) {
        this.inv_ElevatorId = inv_ElevatorId;
    }

    public Code getInv_WorkingStCode() {
        return inv_WorkingStCode;
    }

    public void setInv_WorkingStCode(Code inv_WorkingStCode) {
        this.inv_WorkingStCode = inv_WorkingStCode;
    }

    @Override
    public String toString() {
        return "Elevator{" +
                "inv_Capacity='" + inv_Capacity + '\'' +
                ", inv_SerialNumber='" + inv_SerialNumber + '\'' +
                ", inv_ElevatorNumber='" + inv_ElevatorNumber + '\'' +
                ", inv_BlockName='" + inv_BlockName + '\'' +
                ", inv_ColorCode='" + inv_ColorCode + '\'' +
                ", inv_Location='" + inv_Location + '\'' +
                ", inv_AccountId=" + inv_AccountId +
                ", inv_BuilderFirmId='" + inv_BuilderFirmId + '\'' +
                ", inv_Floor='" + inv_Floor + '\'' +
                ", inv_ClassId=" + inv_ClassId +
                ", inv_ElevatorId='" + inv_ElevatorId + '\'' +
                ", inv_WorkingStCode=" + inv_WorkingStCode +
                '}';
    }
}

