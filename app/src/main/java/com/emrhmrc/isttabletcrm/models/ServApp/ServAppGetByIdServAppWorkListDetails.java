package com.emrhmrc.isttabletcrm.models.ServApp;


import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;

public class ServAppGetByIdServAppWorkListDetails {
    private String inv_ServAppWorkListDetailId;

    private float inv_CompletePercentage;

    private String inv_Date;

    private Inv_Id inv_WorkListDetailId;

    public ServAppGetByIdServAppWorkListDetails() {
    }

    public String getInv_ServAppWorkListDetailId() {
        return inv_ServAppWorkListDetailId;
    }

    public void setInv_ServAppWorkListDetailId(String inv_ServAppWorkListDetailId) {
        this.inv_ServAppWorkListDetailId = inv_ServAppWorkListDetailId;
    }

    public float getInv_CompletePercentage() {
        return inv_CompletePercentage;
    }

    public void setInv_CompletePercentage(float inv_CompletePercentage) {
        this.inv_CompletePercentage = inv_CompletePercentage;
    }

    public String getInv_Date() {
        return inv_Date;
    }

    public void setInv_Date(String inv_Date) {
        this.inv_Date = inv_Date;
    }

    public Inv_Id getInv_WorkListDetailId() {
        return inv_WorkListDetailId;
    }

    public void setInv_WorkListDetailId(Inv_Id inv_WorkListDetailId) {
        this.inv_WorkListDetailId = inv_WorkListDetailId;
    }


}

