package com.emrhmrc.isttabletcrm.models.Elevator;

import com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata;

import java.util.List;

public class ElevatorListAll {

    private List<ElevatorsCustomer> Elevators;

    private String ErrorMsg;

    private boolean Success;

    private TableMetadata TableMetadata;

    public List<ElevatorsCustomer> getElevators() {
        return Elevators;
    }

    public void setElevators(List<ElevatorsCustomer> Elevators) {
        this.Elevators = Elevators;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public TableMetadata getTableMetadata() {
        return TableMetadata;
    }

    public void setTableMetadata(TableMetadata TableMetadata) {
        this.TableMetadata = TableMetadata;
    }


}

