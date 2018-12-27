package com.emrhmrc.isttabletcrm.models.BreakDown;


import java.util.Arrays;
import java.util.List;
import com.emrhmrc.isttabletcrm.models.CommonClass.*;
import com.google.gson.annotations.SerializedName;

public class BreakDownTypeListAll {
    private String ErrorMsg;

    private boolean Success;

    @SerializedName("BreakdownTypes")
    private List<BreakdownType> BreakdownTypes;

    private TableMetadata TableMetadata;

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }

    public boolean getSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public List<BreakdownType> getBreakdownTypes() {
        return BreakdownTypes;
    }

    public void setBreakdownTypes(List<BreakdownType> BreakdownTypes) {
        this.BreakdownTypes = BreakdownTypes;
    }

    public TableMetadata getTableMetadata() {
        return TableMetadata;
    }

    public void setTableMetadata(TableMetadata TableMetadata) {
        this.TableMetadata = TableMetadata;
    }

    @Override
    public String toString() {
        return "BreakDownTypeListAll{" +
                "ErrorMsg='" + ErrorMsg + '\'' +
                ", Success=" + Success +
                ", BreakdownTypes=" + BreakdownTypes +
                ", TableMetadata=" + TableMetadata +
                '}';
    }
}