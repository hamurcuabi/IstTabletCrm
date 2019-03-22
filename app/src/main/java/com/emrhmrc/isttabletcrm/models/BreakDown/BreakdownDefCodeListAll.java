package com.emrhmrc.isttabletcrm.models.BreakDown;

import com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata;

import java.util.List;

public class BreakdownDefCodeListAll {

    private String ErrorMsg;
    private boolean Success;
    private TableMetadata TableMetadata;

    private List<BreakdownDefCodes> BreakdownDefCodes;

    public BreakdownDefCodeListAll() {
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

    public com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata getTableMetadata() {
        return TableMetadata;
    }

    public void setTableMetadata(com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata tableMetadata) {
        TableMetadata = tableMetadata;
    }

    public List<com.emrhmrc.isttabletcrm.models.BreakDown.BreakdownDefCodes> getBreakdownDefCodes() {
        return BreakdownDefCodes;
    }

    public void setBreakdownDefCodes(List<com.emrhmrc.isttabletcrm.models.BreakDown.BreakdownDefCodes> breakdownDefCodes) {
        BreakdownDefCodes = breakdownDefCodes;
    }
}
