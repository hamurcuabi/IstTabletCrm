package com.emrhmrc.isttabletcrm.models.Elevator;

public class ElevatorIdRequest {
    private String ElevatorId;

    public ElevatorIdRequest() {
    }

    public ElevatorIdRequest(String elevatorId) {

        ElevatorId = elevatorId;
    }

    @Override
    public String toString() {
        return "ElevatorIdRequest{" +
                "ElevatorId='" + ElevatorId + '\'' +
                '}';
    }

    public String getElevatorId() {

        return ElevatorId;
    }

    public void setElevatorId(String elevatorId) {
        ElevatorId = elevatorId;
    }
}
