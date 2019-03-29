package com.emrhmrc.isttabletcrm.models.ServApp;

import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id_Id;

public class ServAppDetailsList {

    private String inv_ServiceAppDetailId;
    private String inv_ProductDescription;
    private String inv_Description;
    private Inv_Id_Id inv_ProductId;
    private Inv_Id_Id inv_Uomid;
    private Integer inv_Quantity;
    private Integer inv_Price;
    private boolean inv_WillBeBilled;

    public ServAppDetailsList() {
        inv_ServiceAppDetailId = "";
        inv_ProductDescription = "";
        inv_Description = "";
        inv_Price = 0;
        inv_Quantity = 0;
        inv_WillBeBilled = false;

    }

    public String getInv_ServiceAppDetailId() {
        return inv_ServiceAppDetailId;
    }

    public void setInv_ServiceAppDetailId(String inv_ServiceAppDetailId) {
        this.inv_ServiceAppDetailId = inv_ServiceAppDetailId;
    }

    public String getInv_ProductDescription() {
        return inv_ProductDescription;
    }

    public void setInv_ProductDescription(String inv_ProductDescription) {
        this.inv_ProductDescription = inv_ProductDescription;
    }

    public String getInv_Description() {
        return inv_Description;
    }

    public void setInv_Description(String inv_Description) {
        this.inv_Description = inv_Description;
    }

    public Inv_Id_Id getInv_ProductId() {
        return inv_ProductId;
    }

    public void setInv_ProductId(Inv_Id_Id inv_ProductId) {
        this.inv_ProductId = inv_ProductId;
    }

    public Inv_Id_Id getInv_Uomid() {
        return inv_Uomid;
    }

    public void setInv_Uomid(Inv_Id_Id inv_Uomid) {
        this.inv_Uomid = inv_Uomid;
    }

    public int getInv_Quantity() {
        return inv_Quantity;
    }

    public void setInv_Quantity(int inv_Quantity) {
        this.inv_Quantity = inv_Quantity;
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

    public void setInv_WillBeBilled(boolean inv_WillBeBilled) {
        this.inv_WillBeBilled = inv_WillBeBilled;
    }

}
