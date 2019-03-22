package com.emrhmrc.isttabletcrm.models.Account;

public class Account {
    private String Name;

    private String AccountId;
    private Integer inv_LocationSequence;

    public Integer getInv_LocationSequence() {
        return inv_LocationSequence;
    }

    public void setInv_LocationSequence(Integer inv_LocationSequence) {
        this.inv_LocationSequence = inv_LocationSequence;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAccountId() {
        return AccountId;
    }

    public void setAccountId(String AccountId) {
        this.AccountId = AccountId;
    }

    @Override
    public String toString() {
        return Name;
    }
}


