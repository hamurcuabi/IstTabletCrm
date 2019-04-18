package com.emrhmrc.isttabletcrm.models.Product;

import com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata;
import com.emrhmrc.isttabletcrm.models.ServApp.DefaultResponse;

import java.util.List;

public class MainProductList extends DefaultResponse {
    private List<MainList> MainProductGroups;

    private TableMetadata TableMetadata;

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }

    public boolean getSuccess() {
        return Success;
    }

    public TableMetadata getTableMetadata() {
        return TableMetadata;
    }

    public void setTableMetadata(TableMetadata TableMetadata) {
        this.TableMetadata = TableMetadata;
    }

    @Override
    public String toString() {
        return "MainProductList{" +
                "MainProductGroups=" + MainProductGroups +
                ", ErrorMsg='" + ErrorMsg + '\'' +
                ", Success=" + Success +
                ", TableMetadata=" + TableMetadata +
                '}';
    }

    public List<MainList> getMainProductGroups() {
        return MainProductGroups;
    }

    public void setMainProductGroups(List<MainList> mainProductGroups) {
        MainProductGroups = mainProductGroups;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }
}

