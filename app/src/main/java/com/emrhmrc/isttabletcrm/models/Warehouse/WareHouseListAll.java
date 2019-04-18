package com.emrhmrc.isttabletcrm.models.Warehouse;

import com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata;
import com.emrhmrc.isttabletcrm.models.ServApp.DefaultResponse;

import java.util.List;

public class WareHouseListAll extends DefaultResponse {
    private TableMetadata TableMetadataWarehouseTransfer;
    private List<Warehouses> Warehouses;

    public WareHouseListAll() {
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

    public List<com.emrhmrc.isttabletcrm.models.Warehouse.Warehouses> getWarehouses() {
        return Warehouses;
    }

    public void setWarehouses(List<com.emrhmrc.isttabletcrm.models.Warehouse.Warehouses> warehouses) {
        Warehouses = warehouses;
    }
}
