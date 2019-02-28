package com.emrhmrc.isttabletcrm.models.Warehouse;

import com.emrhmrc.isttabletcrm.models.CommonClass.Code;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;

public class WarehouseTransferCreateRequest {

    private String UserId;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getInv_Description() {
        return inv_Description;
    }

    public void setInv_Description(String inv_Description) {
        this.inv_Description = inv_Description;
    }

    public String getInv_RequestDate() {
        return inv_RequestDate;
    }

    public void setInv_RequestDate(String inv_RequestDate) {
        this.inv_RequestDate = inv_RequestDate;
    }

    public String getInv_WarehouseTransferName() {
        return inv_WarehouseTransferName;
    }

    public void setInv_WarehouseTransferName(String inv_WarehouseTransferName) {
        this.inv_WarehouseTransferName = inv_WarehouseTransferName;
    }

    public Code getInv_TransferTypeCode() {
        return inv_TransferTypeCode;
    }

    public void setInv_TransferTypeCode(Code inv_TransferTypeCode) {
        this.inv_TransferTypeCode = inv_TransferTypeCode;
    }

    public Code getInv_ScrapReasonCode() {
        return inv_ScrapReasonCode;
    }

    public void setInv_ScrapReasonCode(Code inv_ScrapReasonCode) {
        this.inv_ScrapReasonCode = inv_ScrapReasonCode;
    }

    public Inv_Id getInv_Warehouseid() {
        return inv_Warehouseid;
    }

    public void setInv_Warehouseid(Inv_Id inv_Warehouseid) {
        this.inv_Warehouseid = inv_Warehouseid;
    }

    public Inv_Id getInv_Productid() {
        return inv_Productid;
    }

    public void setInv_Productid(Inv_Id inv_Productid) {
        this.inv_Productid = inv_Productid;
    }

    public Inv_Id getInv_Uomid() {
        return inv_Uomid;
    }

    public void setInv_Uomid(Inv_Id inv_Uomid) {
        this.inv_Uomid = inv_Uomid;
    }

    public String getInv_Quantity() {
        return inv_Quantity;
    }

    public void setInv_Quantity(String inv_Quantity) {
        this.inv_Quantity = inv_Quantity;
    }

    public WarehouseTransferCreateRequest() {
    }

    private String inv_Description;
    private String inv_RequestDate;
    private String inv_WarehouseTransferName;
    private Code inv_TransferTypeCode;
    private Code inv_ScrapReasonCode;
    private Inv_Id inv_Warehouseid;
    private Inv_Id inv_Productid;
    private Inv_Id inv_Uomid;
    private String inv_Quantity;
}
