package com.emrhmrc.isttabletcrm.models.BreakDown;

public class BreakdownCode {

    private String inv_BreakdownCodeId;
    private String inv_BreakdownCodeName;

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


    @Override
    public String toString() {
        return inv_BreakdownCodeName;
    }
}
