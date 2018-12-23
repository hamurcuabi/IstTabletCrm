package com.emrhmrc.isttabletcrm.models.BreakDown;


import java.util.Arrays;
import java.util.List;
import com.emrhmrc.isttabletcrm.models.CommonClass.*;
public class BreakDownTypeListAll {
    private String ErrorMsg;

    private boolean Success;

    private List<BreakdownTypes> BreakdownTypes;

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

    public List<BreakdownTypes> getBreakdownTypes() {
        return BreakdownTypes;
    }

    public void setBreakdownTypes(List<BreakdownTypes> BreakdownTypes) {
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
                ", BreakdownTypes=" + Arrays.toString(BreakdownTypes) +
                ", TableMetadata=" + TableMetadata +
                '}';
    }
}