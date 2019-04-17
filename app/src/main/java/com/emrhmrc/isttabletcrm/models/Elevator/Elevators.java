package com.emrhmrc.isttabletcrm.models.Elevator;

public class Elevators {
    private String inv_ElevatorId;
    private String inv_ElevatorName;

    public String getInv_BlockName() {
        return inv_BlockName;
    }

    public void setInv_BlockName(String inv_BlockName) {
        this.inv_BlockName = inv_BlockName;
    }

    private String inv_BlockName;

    public Elevators() {
    }

    public String getInv_ElevatorId() {
        return inv_ElevatorId;
    }

    public void setInv_ElevatorId(String inv_ElevatorId) {
        this.inv_ElevatorId = inv_ElevatorId;
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
}
