package com.emrhmrc.isttabletcrm.models.Warehouse;

import com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata;

import java.util.List;

public class WarehouseItemListAll {
    private String ErrorMsg;
    private boolean Success;
    private TableMetadata TableMetadataWarehouseItem;
    private List<WarehouseItem> WarehouseItems;

    public WarehouseItemListAll() {
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public TableMetadata getTableMetadataWarehouseItem() {
        return TableMetadataWarehouseItem;
    }

    public void setTableMetadataWarehouseItem(TableMetadata tableMetadataWarehouseItem) {
        TableMetadataWarehouseItem = tableMetadataWarehouseItem;
    }

    public List<WarehouseItem> getWarehouseItems() {
        return WarehouseItems;
    }

    public void setWarehouseItems(List<WarehouseItem> warehouseItems) {
        WarehouseItems = warehouseItems;
    }
}
