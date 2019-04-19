package com.emrhmrc.isttabletcrm.models.Elevator;

import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppDetails;

public class ServAppDetails extends ServAppGetByIdServAppDetails {

    private boolean IsDeleted;
    private Inv_Id TransactionCurrencyId;


    public ServAppDetails() {
    }

    public boolean isDeleted() {
        return IsDeleted;
    }

    public void setDeleted(boolean deleted) {
        IsDeleted = deleted;
    }

    public Inv_Id getTransactionCurrencyId() {
        return TransactionCurrencyId;
    }

    public void setTransactionCurrencyId(Inv_Id transactionCurrencyId) {
        TransactionCurrencyId = transactionCurrencyId;
    }


}

