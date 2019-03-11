package com.emrhmrc.isttabletcrm.models.Warehouse;

import com.emrhmrc.isttabletcrm.models.CommonClass.Code;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;

public class Warehouses {
    private String inv_WarehouseId;
    private String inv_WarehouseName;
    private Inv_Id inv_ParentWhid;
    private Code inv_WarehouseTypeCode;

    public Warehouses() {
    }

    public String getInv_WarehouseId() {
        return inv_WarehouseId;
    }

    public void setInv_WarehouseId(String inv_WarehouseId) {
        this.inv_WarehouseId = inv_WarehouseId;
    }

    public String getInv_WarehouseName() {
        return inv_WarehouseName;
    }

    public void setInv_WarehouseName(String inv_WarehouseName) {
        this.inv_WarehouseName = inv_WarehouseName;
    }

    public Inv_Id getInv_ParentWhid() {
        return inv_ParentWhid;
    }

    public void setInv_ParentWhid(Inv_Id inv_ParentWhid) {
        this.inv_ParentWhid = inv_ParentWhid;
    }

    public Code getInv_WarehouseTypeCode() {
        return inv_WarehouseTypeCode;
    }

    public void setInv_WarehouseTypeCode(Code inv_WarehouseTypeCode) {
        this.inv_WarehouseTypeCode = inv_WarehouseTypeCode;
    }

    @Override
    public String toString() {
        return inv_WarehouseName;
    }
}
