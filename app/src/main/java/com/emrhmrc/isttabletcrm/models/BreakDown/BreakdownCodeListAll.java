package com.emrhmrc.isttabletcrm.models.BreakDown;

import com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata;

import java.util.List;

public class BreakdownCodeListAll {
    private String ErrorMsg;

    private boolean Success;
    private List<BreakdownCode> BreakdownCodes;

    private TableMetadata TableMetadata;

    public BreakdownCodeListAll() {
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public List<BreakdownCode> getBreakdownCodes() {
        return BreakdownCodes;
    }

    public void setBreakdownCodes(List<BreakdownCode> breakdownCodes) {
        BreakdownCodes = breakdownCodes;
    }

    public com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata getTableMetadata() {
        return TableMetadata;
    }

    public void setTableMetadata(com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata tableMetadata) {
        TableMetadata = tableMetadata;
    }
}
