package com.emrhmrc.isttabletcrm.models.Elevator;

import com.emrhmrc.isttabletcrm.models.CommonClass.Code;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;

public class ElevatorsCustomer {
    private String inv_ElevatorId;
    private String inv_ElevatorNumber;
    private String inv_SerialNumber;
    private String inv_BlockName;
    private String inv_Location;
    private int inv_Floor;
    private int inv_Capacity;
    private Inv_Id inv_AccountId;
    private Inv_Id inv_BuilderFirmId;
    private Inv_Id inv_ClassId;
    private Code inv_WorkingStCode;
    private Code inv_ColorCode;
    private double inv_Latitude;
    private double inv_Longitude;
    private String inv_ElevatorName;
    public ElevatorsCustomer() {
    }

    public String getInv_ElevatorName() {
        return inv_ElevatorName;
    }

    public void setInv_ElevatorName(String inv_ElevatorName) {
        this.inv_ElevatorName = inv_ElevatorName;
    }

    @Override
    public String toString() {
        return inv_ElevatorName;
    }

    public String getInv_ElevatorId() {
        return inv_ElevatorId;
    }

    public void setInv_ElevatorId(String inv_ElevatorId) {
        this.inv_ElevatorId = inv_ElevatorId;
    }

    public String getInv_ElevatorNumber() {
        return inv_ElevatorNumber;
    }

    public void setInv_ElevatorNumber(String inv_ElevatorNumber) {
        this.inv_ElevatorNumber = inv_ElevatorNumber;
    }

    public String getInv_SerialNumber() {
        return inv_SerialNumber;
    }

    public void setInv_SerialNumber(String inv_SerialNumber) {
        this.inv_SerialNumber = inv_SerialNumber;
    }

    public String getInv_BlockName() {
        return inv_BlockName;
    }

    public void setInv_BlockName(String inv_BlockName) {
        this.inv_BlockName = inv_BlockName;
    }

    public String getInv_Location() {
        return inv_Location;
    }

    public void setInv_Location(String inv_Location) {
        this.inv_Location = inv_Location;
    }

    public int getInv_Floor() {
        return inv_Floor;
    }

    public void setInv_Floor(int inv_Floor) {
        this.inv_Floor = inv_Floor;
    }

    public int getInv_Capacity() {
        return inv_Capacity;
    }

    public void setInv_Capacity(int inv_Capacity) {
        this.inv_Capacity = inv_Capacity;
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

    public Inv_Id getInv_ClassId() {
        return inv_ClassId;
    }

    public void setInv_ClassId(Inv_Id inv_ClassId) {
        this.inv_ClassId = inv_ClassId;
    }

    public Code getInv_WorkingStCode() {
        return inv_WorkingStCode;
    }

    public void setInv_WorkingStCode(Code inv_WorkingStCode) {
        this.inv_WorkingStCode = inv_WorkingStCode;
    }

    public Code getInv_ColorCode() {
        return inv_ColorCode;
    }

    public void setInv_ColorCode(Code inv_ColorCode) {
        this.inv_ColorCode = inv_ColorCode;
    }

    public double getInv_Latitude() {
        return inv_Latitude;
    }

    public void setInv_Latitude(double inv_Latitude) {
        this.inv_Latitude = inv_Latitude;
    }

    public double getInv_Longitude() {
        return inv_Longitude;
    }

    public void setInv_Longitude(double inv_Longitude) {
        this.inv_Longitude = inv_Longitude;
    }
}
