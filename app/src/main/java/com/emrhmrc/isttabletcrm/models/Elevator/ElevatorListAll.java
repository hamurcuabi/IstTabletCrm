package com.emrhmrc.isttabletcrm.models.Elevator;

import java.util.Arrays;
import java.util.List;
import com.emrhmrc.isttabletcrm.models.CommonClass.*;
public class ElevatorListAll {
    private List<Elevators> Elevators;

    private String ErrorMsg;

    private String Success;

    private TableMetadata TableMetadata;

    public List<Elevators> getElevators() {
        return Elevators;
    }

    public void setElevators(List<Elevators> Elevators) {
        this.Elevators = Elevators;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String Success) {
        this.Success = Success;
    }

    public TableMetadata getTableMetadata() {
        return TableMetadata;
    }

    public void setTableMetadata(TableMetadata TableMetadata) {
        this.TableMetadata = TableMetadata;
    }

    @Override
    public String toString() {
        return "ElevatorListAll{" +
                "Elevators=" + Arrays.toString(Elevators) +
                ", ErrorMsg='" + ErrorMsg + '\'' +
                ", Success='" + Success + '\'' +
                ", TableMetadata=" + TableMetadata +
                '}';
    }
}

