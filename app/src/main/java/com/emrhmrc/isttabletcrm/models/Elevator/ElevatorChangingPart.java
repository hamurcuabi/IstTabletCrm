package com.emrhmrc.isttabletcrm.models.Elevator;

import com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata;

import java.util.List;

public class ElevatorChangingPart {

    private String ErrorMsg;

    private String Success;

    private com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata TableMetadata;

    public ElevatorChangingPart() {
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }

    public com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata getTableMetadata() {
        return TableMetadata;
    }

    public void setTableMetadata(com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata tableMetadata) {
        TableMetadata = tableMetadata;
    }

    public List<com.emrhmrc.isttabletcrm.models.Elevator.ElevatorChangingParts> getElevatorChangingParts() {
        return ElevatorChangingParts;
    }

    public void setElevatorChangingParts(List<com.emrhmrc.isttabletcrm.models.Elevator.ElevatorChangingParts> elevatorChangingParts) {
        ElevatorChangingParts = elevatorChangingParts;
    }

    private List<ElevatorChangingParts> ElevatorChangingParts;
}
