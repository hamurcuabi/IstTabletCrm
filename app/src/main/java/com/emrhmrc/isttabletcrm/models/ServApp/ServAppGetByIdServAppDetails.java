package com.emrhmrc.isttabletcrm.models.ServApp;


import com.emrhmrc.isttabletcrm.models.CommonClass.Code;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;

public class ServAppGetByIdServAppDetails {
    private int inv_LineNo;
    private int inv_Price;
    private String inv_Description;
    private Code inv_WarrantyStatusCode;
    private boolean inv_WillBeBilled;
    private String inv_ServiceAppDetailId;
    private Code inv_ApprovalStCode;
    private String inv_ProductDescription;
    private Inv_Id inv_Uomid;
    private Inv_Id inv_ProductId;
    private float inv_Quantity;

    private boolean isManuel = false;
    private boolean isManuelProduct = false;

    public ServAppGetByIdServAppDetails() {
    }

    public Integer getInv_Price() {
        return inv_Price;
    }

    public void setInv_Price(Integer inv_Price) {
        this.inv_Price = inv_Price;
    }

    public boolean isInv_WillBeBilled() {
        return inv_WillBeBilled;
    }

    public boolean isManuel() {
        return isManuel;
    }

    public void setManuel(boolean manuel) {
        isManuel = manuel;
    }

    public Integer getInv_LineNo() {
        return inv_LineNo;
    }

    public void setInv_LineNo(Integer inv_LineNo) {
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

    public String getInv_ProductDescription() {
        return inv_ProductDescription;
    }

    public void setInv_ProductDescription(String inv_ProductDescription) {
        this.inv_ProductDescription = inv_ProductDescription;
    }

    public Inv_Id getInv_Uomid() {
        return inv_Uomid;
    }

    public void setInv_Uomid(Inv_Id inv_Uomid) {
        this.inv_Uomid = inv_Uomid;
    }

    public Inv_Id getInv_ProductId() {
        return inv_ProductId;
    }

    public void setInv_ProductId(Inv_Id inv_ProductId) {
        this.inv_ProductId = inv_ProductId;
    }

    public float getInv_Quantity() {
        return inv_Quantity;
    }

    public void setInv_Quantity(float inv_Quantity) {
        this.inv_Quantity = inv_Quantity;
    }


    public boolean isManuelProduct() {
        return isManuelProduct;
    }

    public void setManuelProduct(boolean manuelProduct) {
        isManuelProduct = manuelProduct;
    }
}

