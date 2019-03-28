package com.emrhmrc.isttabletcrm.models.ServApp;

import com.emrhmrc.isttabletcrm.models.CommonClass.Code_Id;

public class ServiceAppointmentUpdate {

    private String ActivityId;
    private Code_Id inv_BreakdownCodeId;
    private Code_Id inv_BreakdownDefCodeid;
    private Code_Id inv_MainProductGroupid;
    private Code_Id inv_SubProductGroupid;

    public ServiceAppointmentUpdate() {
    }

    public String getActivityId() {
        return ActivityId;
    }

    public void setActivityId(String activityId) {
        ActivityId = activityId;
    }

    public Code_Id getInv_BreakdownCodeId() {
        return inv_BreakdownCodeId;
    }

    public void setInv_BreakdownCodeId(Code_Id inv_BreakdownCodeId) {
        this.inv_BreakdownCodeId = inv_BreakdownCodeId;
    }

    public Code_Id getInv_BreakdownDefCodeid() {
        return inv_BreakdownDefCodeid;
    }

    public void setInv_BreakdownDefCodeid(Code_Id inv_BreakdownDefCodeid) {
        this.inv_BreakdownDefCodeid = inv_BreakdownDefCodeid;
    }

    public Code_Id getInv_MainProductGroupid() {
        return inv_MainProductGroupid;
    }

    public void setInv_MainProductGroupid(Code_Id inv_MainProductGroupid) {
        this.inv_MainProductGroupid = inv_MainProductGroupid;
    }

    public Code_Id getInv_SubProductGroupid() {
        return inv_SubProductGroupid;
    }

    public void setInv_SubProductGroupid(Code_Id inv_SubProductGroupid) {
        this.inv_SubProductGroupid = inv_SubProductGroupid;
    }
}
