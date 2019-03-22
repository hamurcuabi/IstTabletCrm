package com.emrhmrc.isttabletcrm.models.BreakDown;

public class BreakdownFilterRequest {
    private String BreakdownDefCodeId;
    private String SubProductCodeId;

    public BreakdownFilterRequest() {
    }

    public BreakdownFilterRequest(String breakdownDefCodeId, String subProductCodeId) {
        BreakdownDefCodeId = breakdownDefCodeId;
        SubProductCodeId = subProductCodeId;
    }

    public String getBreakdownDefCodeId() {
        return BreakdownDefCodeId;
    }

    public void setBreakdownDefCodeId(String breakdownDefCodeId) {
        BreakdownDefCodeId = breakdownDefCodeId;
    }

    public String getSubProductCodeId() {
        return SubProductCodeId;
    }

    public void setSubProductCodeId(String subProductCodeId) {
        SubProductCodeId = subProductCodeId;
    }
}
