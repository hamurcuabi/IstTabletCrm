package com.emrhmrc.isttabletcrm.models.BreakDown;

public class BreakdownCode {

    private String inv_BreakdownCodeId;
    private String inv_BreakdownCodeName;
    private String inv_SubProductGroupId;

    public BreakdownCode() {
    }

    public String getInv_BreakdownCodeId() {
        return inv_BreakdownCodeId;
    }

    public void setInv_BreakdownCodeId(String inv_BreakdownCodeId) {
        this.inv_BreakdownCodeId = inv_BreakdownCodeId;
    }

    public String getInv_BreakdownCodeName() {
        return inv_BreakdownCodeName;
    }

    public void setInv_BreakdownCodeName(String inv_BreakdownCodeName) {
        this.inv_BreakdownCodeName = inv_BreakdownCodeName;
    }

    public String getInv_SubProductGroupId() {
        return inv_SubProductGroupId;
    }

    public void setInv_SubProductGroupId(String inv_SubProductGroupId) {
        this.inv_SubProductGroupId = inv_SubProductGroupId;
    }

    @Override
    public String toString() {
        return inv_BreakdownCodeName;
    }
}
