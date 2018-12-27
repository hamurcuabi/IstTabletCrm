package com.emrhmrc.isttabletcrm.models.Elevator;

import com.emrhmrc.isttabletcrm.models.CommonClass.Code;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;
import com.google.gson.annotations.SerializedName;

public class ServAppDetails {
    private String inv_LineNo;

    private String inv_Description;

    @SerializedName("Inv_WarrantyStatusCode")
    private Code inv_WarrantyStatusCode;

    private boolean inv_WillBeBilled;

    private String inv_ServiceAppDetailId;
    @SerializedName("Inv_ApprovalStCode")
    private Code inv_ApprovalStCode;

    private String inv_UoMid;

    private String inv_ProductDescription;
    @SerializedName("Inv_ProductId")
    private Inv_Id inv_ProductId;

    private String inv_Quantity;

    public String getInv_LineNo() {
        return inv_LineNo;
    }

    public void setInv_LineNo(String inv_LineNo) {
        this.inv_LineNo = inv_LineNo;
    }

    public String getInv_Description() {
        return inv_Description;
    }

    public void setInv_Description(String inv_Description) {
        this.inv_Description = inv_Description;
    }

    public Code getInv_WarrantyStatusCode() {
        return inv_WarrantyStatusCode;
    }

    public void setInv_WarrantyStatusCode(Code inv_WarrantyStatusCode) {
        this.inv_WarrantyStatusCode = inv_WarrantyStatusCode;
    }

    public boolean getInv_WillBeBilled() {
        return inv_WillBeBilled;
    }

    public void setInv_WillBeBilled(boolean inv_WillBeBilled) {
        this.inv_WillBeBilled = inv_WillBeBilled;
    }

    public String getInv_ServiceAppDetailId() {
        return inv_ServiceAppDetailId;
    }

    public void setInv_ServiceAppDetailId(String inv_ServiceAppDetailId) {
        this.inv_ServiceAppDetailId = inv_ServiceAppDetailId;
    }

    public Code getInv_ApprovalStCode() {
        return inv_ApprovalStCode;
    }

    public void setInv_ApprovalStCode(Code inv_ApprovalStCode) {
        this.inv_ApprovalStCode = inv_ApprovalStCode;
    }

    public String getInv_UoMid() {
        return inv_UoMid;
    }

    public void setInv_UoMid(String inv_UoMid) {
        this.inv_UoMid = inv_UoMid;
    }

    public String getInv_ProductDescription() {
        return inv_ProductDescription;
    }

    public void setInv_ProductDescription(String inv_ProductDescription) {
        this.inv_ProductDescription = inv_ProductDescription;
    }

    public Inv_Id getInv_ProductId() {
        return inv_ProductId;
    }

    public void setInv_ProductId(Inv_Id inv_ProductId) {
        this.inv_ProductId = inv_ProductId;
    }

    public String getInv_Quantity() {
        return inv_Quantity;
    }

    public void setInv_Quantity(String inv_Quantity) {
        this.inv_Quantity = inv_Quantity;
    }


    @Override
    public String toString() {
        return "ServAppDetails{" +
                "inv_LineNo='" + inv_LineNo + '\'' +
                ", inv_Description='" + inv_Description + '\'' +
                ", inv_WarrantyStatusCode=" + inv_WarrantyStatusCode +
                ", inv_WillBeBilled=" + inv_WillBeBilled +
                ", inv_ServiceAppDetailId='" + inv_ServiceAppDetailId + '\'' +
                ", inv_ApprovalStCode=" + inv_ApprovalStCode +
                ", inv_UoMid='" + inv_UoMid + '\'' +
                ", inv_ProductDescription='" + inv_ProductDescription + '\'' +
                ", inv_ProductId=" + inv_ProductId +
                ", inv_Quantity='" + inv_Quantity + '\'' +
                '}';
    }
}

