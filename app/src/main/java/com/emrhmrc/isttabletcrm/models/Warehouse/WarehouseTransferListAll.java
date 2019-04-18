package com.emrhmrc.isttabletcrm.models.Warehouse;

import com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata;
import com.emrhmrc.isttabletcrm.models.ServApp.DefaultResponse;

import java.util.List;

public class WarehouseTransferListAll extends DefaultResponse {

    private TableMetadata TableMetadataWarehouseTransfer;
    private List<WarehouseTransferItem> WarehouseTransfers;

    public WarehouseTransferListAll() {
    }

    public TableMetadata getTableMetadataWarehouseTransfer() {
        return TableMetadataWarehouseTransfer;
    }

    public void setTableMetadataWarehouseTransfer(TableMetadata tableMetadataWarehouseTransfer) {
        TableMetadataWarehouseTransfer = tableMetadataWarehouseTransfer;
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
