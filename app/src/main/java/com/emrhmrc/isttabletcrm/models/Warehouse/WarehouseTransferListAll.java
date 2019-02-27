package com.emrhmrc.isttabletcrm.models.Warehouse;

import com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata;

import java.util.List;

public class WarehouseTransferListAll {

    private TableMetadata TableMetadataWarehouseTransfer;
    private boolean Succes;
    private String ErrorMsg;
    private List<WarehouseTransferItem> WarehouseTransfers;

    public WarehouseTransferListAll() {
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

    public List<WarehouseTransferItem> getWarehouseTransfers() {
        return WarehouseTransfers;
    }

    public void setWarehouseTransfers(List<WarehouseTransferItem> warehouseTransfers) {
        WarehouseTransfers = warehouseTransfers;
    }
}
