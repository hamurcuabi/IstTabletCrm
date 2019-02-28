package com.emrhmrc.isttabletcrm.models.ServApp;


import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;

public class ServAppGetByIdServAppModernizationChecklists {
    private String inv_QuoteModernizationChecklistId;

    private Inv_Id inv_ChecklistDetailid;

    private String inv_Date;

    public ServAppGetByIdServAppModernizationChecklists() {
    }

    public String getInv_QuoteModernizationChecklistId() {
        return inv_QuoteModernizationChecklistId;
    }

    public void setInv_QuoteModernizationChecklistId(String inv_QuoteModernizationChecklistId) {
        this.inv_QuoteModernizationChecklistId = inv_QuoteModernizationChecklistId;
    }

    public Inv_Id getInv_ChecklistDetailid() {
        return inv_ChecklistDetailid;
    }

    public void setInv_ChecklistDetailid(Inv_Id inv_ChecklistDetailid) {
        this.inv_ChecklistDetailid = inv_ChecklistDetailid;
    }

    public String getInv_Date() {
        return inv_Date;
    }

    public void setInv_Date(String inv_Date) {
        this.inv_Date = inv_Date;
    }


}


