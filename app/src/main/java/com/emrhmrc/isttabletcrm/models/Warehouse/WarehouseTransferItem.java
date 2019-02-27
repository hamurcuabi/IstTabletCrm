package com.emrhmrc.isttabletcrm.models.Warehouse;

import com.emrhmrc.isttabletcrm.models.CommonClass.Code;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;

public class WarehouseTransferItem {
    private String inv_WarehouseTransferId;
    private String inv_WarehouseTransferName;
    private String inv_Description;
    private String inv_RejectDescription;
    private String ConfirmUserId;
    private Code inv_TransferTypeCode;
    private Code inv_ScrapReasonCode;
    private Code StatusCode;
    private String inv_RequestDate;
    private Inv_Id inv_FromWarehouseid;
    private Inv_Id inv_ToWarehouseid;
    private Inv_Id inv_Productid;
    private Inv_Id inv_Uomid;
    private int inv_Quantity;

    public WarehouseTransferItem() {
    }

    public String getInv_WarehouseTransferId() {
        return inv_WarehouseTransferId;
    }

    public void setInv_WarehouseTransferId(String inv_WarehouseTransferId) {
        this.inv_WarehouseTransferId = inv_WarehouseTransferId;
    }

    public String getInv_WarehouseTransferName() {
        return inv_WarehouseTransferName;
    }

    public void setInv_WarehouseTransferName(String inv_WarehouseTransferName) {
        this.inv_WarehouseTransferName = inv_WarehouseTransferName;
    }

    public String getInv_Description() {
        return inv_Description;
    }

    public void setInv_Description(String inv_Description) {
        this.inv_Description = inv_Description;
    }

    public String getInv_RejectDescription() {
        return inv_RejectDescription;
    }

    public void setInv_RejectDescription(String inv_RejectDescription) {
        this.inv_RejectDescription = inv_RejectDescription;
    }

    public String getConfirmUserId() {
        return ConfirmUserId;
    }

    public void setConfirmUserId(String confirmUserId) {
        ConfirmUserId = confirmUserId;
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

    public Code getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(Code statusCode) {
        StatusCode = statusCode;
    }

    public String getInv_RequestDate() {
        return inv_RequestDate;
    }

    public void setInv_RequestDate(String inv_RequestDate) {
        this.inv_RequestDate = inv_RequestDate;
    }

    public Inv_Id getInv_FromWarehouseid() {
        return inv_FromWarehouseid;
    }

    public void setInv_FromWarehouseid(Inv_Id inv_FromWarehouseid) {
        this.inv_FromWarehouseid = inv_FromWarehouseid;
    }

    public Inv_Id getInv_ToWarehouseid() {
        return inv_ToWarehouseid;
    }

    public void setInv_ToWarehouseid(Inv_Id inv_ToWarehouseid) {
        this.inv_ToWarehouseid = inv_ToWarehouseid;
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

    public int getInv_Quantity() {
        return inv_Quantity;
    }

    public void setInv_Quantity(int inv_Quantity) {
        this.inv_Quantity = inv_Quantity;
    }
}
