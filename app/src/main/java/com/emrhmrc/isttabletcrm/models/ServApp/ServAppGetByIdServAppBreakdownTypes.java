package com.emrhmrc.isttabletcrm.models.ServApp;

import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;

public class ServAppGetByIdServAppBreakdownTypes {
    private String inv_ServAppBreakdownTypeId;

    private Inv_Id inv_BreakdownTypeId;

    public ServAppGetByIdServAppBreakdownTypes() {
    }

    public String getInv_ServAppBreakdownTypeId() {
        return inv_ServAppBreakdownTypeId;
    }

    public void setInv_ServAppBreakdownTypeId(String inv_ServAppBreakdownTypeId) {
        this.inv_ServAppBreakdownTypeId = inv_ServAppBreakdownTypeId;
    }

    public Inv_Id getInv_BreakdownTypeId() {
        return inv_BreakdownTypeId;
    }

    public void setInv_BreakdownTypeId(Inv_Id inv_BreakdownTypeId) {
        this.inv_BreakdownTypeId = inv_BreakdownTypeId;
    }

    @Override
    public String toString() {
        return inv_BreakdownTypeId.getText();
    }
}


