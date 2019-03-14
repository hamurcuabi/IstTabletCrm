package com.emrhmrc.isttabletcrm.models.Elevator;

public class ElevatorChangingParts {

    private String ElevatorChangingPartId;
    private String PrdocutCode;
    private String WarrantyStartDate;
    private String WarrantyEndDate;
    private String ElevatorSerialNumber;
    private int WarrantyMonthCount;

    public ElevatorChangingParts() {
    }

    public String getElevatorChangingPartId() {
        return ElevatorChangingPartId;
    }

    public void setElevatorChangingPartId(String elevatorChangingPartId) {
        ElevatorChangingPartId = elevatorChangingPartId;
    }

    public String getPrdocutCode() {
        return PrdocutCode;
    }

    public void setPrdocutCode(String prdocutCode) {
        PrdocutCode = prdocutCode;
    }

    public String getWarrantyStartDate() {
        return WarrantyStartDate;
    }

    public void setWarrantyStartDate(String warrantyStartDate) {
        WarrantyStartDate = warrantyStartDate;
    }

    public String getWarrantyEndDate() {
        return WarrantyEndDate;
    }

    public void setWarrantyEndDate(String warrantyEndDate) {
        WarrantyEndDate = warrantyEndDate;
    }

    public String getElevatorSerialNumber() {
        return ElevatorSerialNumber;
    }

    public void setElevatorSerialNumber(String elevatorSerialNumber) {
        ElevatorSerialNumber = elevatorSerialNumber;
    }

    public int getWarrantyMonthCount() {
        return WarrantyMonthCount;
    }

    public void setWarrantyMonthCount(int warrantyMonthCount) {
        WarrantyMonthCount = warrantyMonthCount;
    }
}
