package com.emrhmrc.isttabletcrm.models.Warehouse;

import com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata;

import java.util.List;

public class WareHouseListAll {
    private TableMetadata TableMetadataWarehouseTransfer;
    private boolean Succes;
    private String ErrorMsg;
    private List<Warehouses> Warehouses;

    public WareHouseListAll() {
    }

    public TableMetadata getTableMetadataWarehouseTransfer() {
        return TableMetadataWarehouseTransfer;
    }

    public void setTableMetadataWarehouseTransfer(TableMetadata tableMetadataWarehouseTransfer) {
        TableMetadataWarehouseTransfer = tableMetadataWarehouseTransfer;
    }

    public boolean isSucces() {
        return Succes;
    }

    public void setSucces(boolean succes) {
        Succes = succes;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }

    public List<com.emrhmrc.isttabletcrm.models.Warehouse.Warehouses> getWarehouses() {
        return Warehouses;
    }

    public void setWarehouses(List<com.emrhmrc.isttabletcrm.models.Warehouse.Warehouses> warehouses) {
        Warehouses = warehouses;
    }
}
