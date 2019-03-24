package com.emrhmrc.isttabletcrm.models.CommonClass;

public class Inv_Uom {
    private String UoMId;
    private String Name;

    public Inv_Uom() {
    }

    public String getUoMId() {
        return UoMId;
    }

    public void setUoMId(String uoMId) {
        UoMId = uoMId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return Name;
    }
}
