package com.emrhmrc.isttabletcrm.models.Elevator;

import com.emrhmrc.isttabletcrm.models.CommonClass.Code;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;

public class ServAppDetails {
    private int inv_LineNo;
    private int inv_Price;
    private String inv_Description;
    private Code inv_WarrantyStatusCode;
    private boolean inv_WillBeBilled;
    private boolean IsDeleted;
    private String inv_ServiceAppDetailId;
    private Code inv_ApprovalStCode;
    private Inv_Id inv_UoMid;
    private String inv_ProductDescription;
    private Inv_Id inv_ProductId;
    private Inv_Id TransactionCurrencyId;
    private float inv_Quantity;

    public ServAppDetails() {
    }

    public int getInv_LineNo() {
        return inv_LineNo;
    }

    public void setInv_LineNo(int inv_LineNo) {
        this.inv_LineNo = inv_LineNo;
    }

    public int getInv_Price() {
        return inv_Price;
    }

    public void setInv_Price(int inv_Price) {
        this.inv_Price = inv_Price;
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

    public boolean isInv_WillBeBilled() {
        return inv_WillBeBilled;
    }

    public void setInv_WillBeBilled(boolean inv_WillBeBilled) {
        this.inv_WillBeBilled = inv_WillBeBilled;
    }

    public boolean isDeleted() {
        return IsDeleted;
    }

    public void setDeleted(boolean deleted) {
        IsDeleted = deleted;
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

    public Inv_Id getInv_UoMid() {
        return inv_UoMid;
    }

    public void setInv_UoMid(Inv_Id inv_UoMid) {
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

    public Inv_Id getTransactionCurrencyId() {
        return TransactionCurrencyId;
    }

    public void setTransactionCurrencyId(Inv_Id transactionCurrencyId) {
        TransactionCurrencyId = transactionCurrencyId;
    }

    public float getInv_Quantity() {
        return inv_Quantity;
    }

    public void setInv_Quantity(float inv_Quantity) {
        this.inv_Quantity = inv_Quantity;
    }
}

