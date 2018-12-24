package com.emrhmrc.isttabletcrm.models.ServApp;

public class ServAppGetByIdServAppBreakdownTypes {
    private String inv_ServAppBreakdownTypeId;

    private String inv_BreakdownTypeId;

    public String getInv_ServAppBreakdownTypeId() {
        return inv_ServAppBreakdownTypeId;
    }

    public void setInv_ServAppBreakdownTypeId(String inv_ServAppBreakdownTypeId) {
        this.inv_ServAppBreakdownTypeId = inv_ServAppBreakdownTypeId;
    }

    public String getInv_BreakdownTypeId() {
        return inv_BreakdownTypeId;
    }

    public void setInv_BreakdownTypeId(String inv_BreakdownTypeId) {
        this.inv_BreakdownTypeId = inv_BreakdownTypeId;
    }

    @Override
    public String toString() {
        return "ServAppGetByIdServAppBreakdownTypes{" +
                "inv_ServAppBreakdownTypeId='" + inv_ServAppBreakdownTypeId + '\'' +
                ", inv_BreakdownTypeId='" + inv_BreakdownTypeId + '\'' +
                '}';
    }
}


