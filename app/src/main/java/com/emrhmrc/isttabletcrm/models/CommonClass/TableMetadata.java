package com.emrhmrc.isttabletcrm.models.CommonClass;

import java.util.List;

public class TableMetadata {
    private String Name;

    private List<Attributes> Attributes;

    private String Label;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public List<Attributes> getAttributes() {
        return Attributes;
    }

    public void setAttributes(List<Attributes> Attributes) {
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