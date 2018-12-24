package com.emrhmrc.isttabletcrm.models.ServApp;

import com.emrhmrc.isttabletcrm.models.CommonClass.Attributes;

import java.util.List;

public class TableMetadataServApps {
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
        return "TableMetadataServApps{" +
                "Name='" + Name + '\'' +
                ", Attributes=" + Attributes +
                ", Label='" + Label + '\'' +
                '}';
    }
}
