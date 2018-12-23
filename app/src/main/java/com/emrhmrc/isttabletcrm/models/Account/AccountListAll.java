package com.emrhmrc.isttabletcrm.models.Account;

import java.util.Arrays;
import java.util.List;

import com.emrhmrc.isttabletcrm.models.CommonClass.*;
public class AccountListAll {
    private List<Accounts> Accounts;

    private String ErrorMsg;

    private boolean Success;

    private TableMetadata TableMetadata;

    public List<Accounts> getAccounts() {
        return Accounts;
    }

    public void setAccounts(List<Accounts> Accounts) {
        this.Accounts = Accounts;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }

    public boolean getSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public TableMetadata getTableMetadata() {
        return TableMetadata;
    }

    public void setTableMetadata(TableMetadata TableMetadata) {
        this.TableMetadata = TableMetadata;
    }

    @Override
    public String toString() {
        return "AccountListAll{" +
                "Accounts=" + Arrays.toString(Accounts) +
                ", ErrorMsg='" + ErrorMsg + '\'' +
                ", Success='" + Success + '\'' +
                ", TableMetadata=" + TableMetadata +
                '}';
    }
}


