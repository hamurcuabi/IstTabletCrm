package com.emrhmrc.isttabletcrm.models.CommonClass;

public class FilterModel {
    private int value;
    private String name;

    public FilterModel() {
    }

    public FilterModel(int value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
