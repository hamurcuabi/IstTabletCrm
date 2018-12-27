package com.emrhmrc.isttabletcrm.models.Account;

import com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AccountListAll {
    @SerializedName("Accounts")
    private List<Account> Accounts;

    private String ErrorMsg;

    private boolean Success;

    private TableMetadata TableMetadata;

    public List<Account> getAccounts() {
        return Accounts;
    }

    public void setAccounts(List<Account> Accounts) {
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


}


