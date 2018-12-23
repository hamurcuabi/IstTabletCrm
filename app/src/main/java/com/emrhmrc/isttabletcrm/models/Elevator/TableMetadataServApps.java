package com.emrhmrc.isttabletcrm.models.Elevator;

import com.emrhmrc.isttabletcrm.models.CommonClass.Attributes;

public class TableMetadataServApps {
    private String Name;

    private Attributes Attributes;

    private String Label;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Attributes getAttributes() {
        return Attributes;
    }

    public void setAttributes(Attributes Attributes) {
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
        return "TableMetadataElevator{" +
                "Name='" + Name + '\'' +
                ", Attributes=" + Attributes +
                ", Label='" + Label + '\'' +
                '}';
    }
}
