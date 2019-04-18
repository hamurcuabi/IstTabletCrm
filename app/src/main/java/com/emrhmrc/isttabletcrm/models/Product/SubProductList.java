package com.emrhmrc.isttabletcrm.models.Product;

import com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata;
import com.emrhmrc.isttabletcrm.models.ServApp.DefaultResponse;

import java.util.List;

public class SubProductList extends DefaultResponse {
    private List<SubList> SubProductGroups;

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
        return "SubProductList{" +
                "SubProductGroups=" + SubProductGroups +
                ", ErrorMsg='" + ErrorMsg + '\'' +
                ", Success=" + Success +
                ", TableMetadata=" + TableMetadata +
                '}';
    }

    public List<SubList> getSubProductGroups() {
        return SubProductGroups;
    }

    public void setSubProductGroups(List<SubList> subProductGroups) {
        SubProductGroups = subProductGroups;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }
}

