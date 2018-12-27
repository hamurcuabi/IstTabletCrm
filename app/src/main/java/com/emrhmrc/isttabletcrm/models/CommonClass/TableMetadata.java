package com.emrhmrc.isttabletcrm.models.CommonClass;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TableMetadata {
    private String Name;

    @SerializedName("Attributes")
    private List<Attribute> Attributes;

    private String Label;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public List<Attribute> getAttributes() {
        return Attributes;
    }

    public void setAttributes(List<Attribute> Attributes) {
        this.Attributes = Attributes;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String Label) {
        this.Label = Label;
    }

    @Override
    public String toString() {
        return "TableMetadata{" +
                "Name='" + Name + '\'' +
                ", Attributes=" + Attributes +
                ", Label='" + Label + '\'' +
                '}';
    }
}