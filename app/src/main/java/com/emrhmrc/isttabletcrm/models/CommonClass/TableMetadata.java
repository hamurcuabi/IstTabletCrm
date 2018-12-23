package com.emrhmrc.isttabletcrm.models.CommonClass;

import java.util.Arrays;

public class TableMetadata {
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
        return "TableMetadata{" +
                "Name='" + Name + '\'' +
                ", Attributes=" + Arrays.toString(Attributes) +
                ", Label='" + Label + '\'' +
                '}';
    }
}