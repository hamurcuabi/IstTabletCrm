package com.emrhmrc.isttabletcrm.models.Quote;

import com.emrhmrc.isttabletcrm.models.CommonClass.Attributes;

import java.util.List;

public class TableMetadataQuoteDetails {
    private String Name;

    private List<com.emrhmrc.isttabletcrm.models.CommonClass.Attributes> Attributes;

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
        return "TableMetadataQuoteDetails{" +
                "Name='" + Name + '\'' +
                ", Attributes=" + Attributes +
                ", Label='" + Label + '\'' +
                '}';
    }
}


