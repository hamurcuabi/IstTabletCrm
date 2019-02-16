package com.emrhmrc.isttabletcrm.helper;

import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppUnsuitabilities;

import java.util.List;

public class SingletonListUnsuitability {
    private static final SingletonListUnsuitability ourInstance = new SingletonListUnsuitability();
    private List<ServAppGetByIdServAppUnsuitabilities> unsuitabilities;

    private SingletonListUnsuitability() {
    }

    public static SingletonListUnsuitability getInstance() {
        return ourInstance;
    }

    public List<ServAppGetByIdServAppUnsuitabilities> getByIdServAppUnsuitabilities() {
        return unsuitabilities;
    }

    public void setUnsuitabilities(List<ServAppGetByIdServAppUnsuitabilities> unsuitabilities) {
        this.unsuitabilities = unsuitabilities;
    }

}
