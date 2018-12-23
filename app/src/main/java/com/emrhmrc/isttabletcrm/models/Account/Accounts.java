package com.emrhmrc.isttabletcrm.models.Account;

public class Accounts {
    private String Name;

    private String AccountId;

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
        return "Accounts{" +
                "Name='" + Name + '\'' +
                ", AccountId='" + AccountId + '\'' +
                '}';
    }
}


