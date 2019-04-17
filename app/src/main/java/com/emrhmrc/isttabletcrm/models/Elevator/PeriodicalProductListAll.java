package com.emrhmrc.isttabletcrm.models.Elevator;

import com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata;
import com.emrhmrc.isttabletcrm.models.ServApp.DefaultResponse;

import java.util.List;

public class PeriodicalProductListAll extends DefaultResponse {

    private TableMetadata TableMetadata;
    private List<PeriodicalProducts> PeriodicalProducts;

    public PeriodicalProductListAll() {
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }

    public com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata getTableMetadata() {
        return TableMetadata;
    }

    public void setTableMetadata(com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata tableMetadata) {
        TableMetadata = tableMetadata;
    }

    public List<com.emrhmrc.isttabletcrm.models.Elevator.PeriodicalProducts> getPeriodicalProducts() {
        return PeriodicalProducts;
    }

    public void setPeriodicalProducts(List<com.emrhmrc.isttabletcrm.models.Elevator.PeriodicalProducts> periodicalProducts) {
        PeriodicalProducts = periodicalProducts;
    }
}
