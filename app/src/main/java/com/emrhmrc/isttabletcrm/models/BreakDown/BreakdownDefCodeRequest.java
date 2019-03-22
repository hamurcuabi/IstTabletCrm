package com.emrhmrc.isttabletcrm.models.BreakDown;

public class BreakdownDefCodeRequest {
    private String SubProductGroupId;

    public String getSubProductGroupId() {
        return SubProductGroupId;
    }

    public void setSubProductGroupId(String subProductGroupId) {
        SubProductGroupId = subProductGroupId;
    }

    public BreakdownDefCodeRequest() {
    }

    public BreakdownDefCodeRequest(String subProductGroupId) {
        SubProductGroupId = subProductGroupId;
    }
}
