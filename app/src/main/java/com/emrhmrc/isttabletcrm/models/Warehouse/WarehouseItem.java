package com.emrhmrc.isttabletcrm.models.Warehouse;

import com.emrhmrc.isttabletcrm.models.CommonClass.Code;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;

public class WarehouseItem {
    private String inv_WarehouseItemId;
    private Code inv_WarehouseTypeCode;
    private Inv_Id inv_Warehouseid;
    private Inv_Id inv_Productid;
    private Inv_Id SubProductGroupid;
    private Inv_Id MainProductGroupid;
    private Inv_Id inv_Uomid;
    private int inv_Quantity;
    private String ProductNumber;
    private String Description;
    private String ImageMimeType;
    private Code inv_ShipmentStatusCode;

    public Code getInv_ShipmentStatusCode() {
        return inv_ShipmentStatusCode;
    }

    public void setInv_ShipmentStatusCode(Code inv_ShipmentStatusCode) {
        this.inv_ShipmentStatusCode = inv_ShipmentStatusCode;
    }

    public String getInv_WaybillNo() {
        return inv_WaybillNo;
    }

    public void setInv_WaybillNo(String inv_WaybillNo) {
        this.inv_WaybillNo = inv_WaybillNo;
    }

    private String inv_WaybillNo;

    public String getImageMimeType() {
        return ImageMimeType;
    }

    public void setImageMimeType(String imageMimeType) {
        ImageMimeType = imageMimeType;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    private String Image;

    public WarehouseItem() {
    }

    public Inv_Id getSubProductGroupid() {
        return SubProductGroupid;
    }

    public void setSubProductGroupid(Inv_Id subProductGroupid) {
        SubProductGroupid = subProductGroupid;
    }

    public Inv_Id getMainProductGroupid() {
        return MainProductGroupid;
    }

    public void setMainProductGroupid(Inv_Id mainProductGroupid) {
        MainProductGroupid = mainProductGroupid;
    }

    public String getProductNumber() {
        return ProductNumber;
    }

    public void setProductNumber(String productNumber) {
        ProductNumber = productNumber;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getInv_WarehouseItemId() {
        return inv_WarehouseItemId;
    }

    public void setInv_WarehouseItemId(String inv_WarehouseItemId) {
        this.inv_WarehouseItemId = inv_WarehouseItemId;
    }

    public Code getInv_WarehouseTypeCode() {
        return inv_WarehouseTypeCode;
    }

    public void setInv_WarehouseTypeCode(Code inv_WarehouseTypeCode) {
        this.inv_WarehouseTypeCode = inv_WarehouseTypeCode;
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

    public int getInv_Quantity() {
        return inv_Quantity;
    }

    public void setInv_Quantity(int inv_Quantity) {
        this.inv_Quantity = inv_Quantity;
    }
}
