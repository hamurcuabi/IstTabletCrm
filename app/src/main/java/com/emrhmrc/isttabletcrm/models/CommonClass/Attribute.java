package com.emrhmrc.isttabletcrm.models.CommonClass;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Attribute {
    private String Name;

    private String RefEntity;

    private String Type;

    private String Label;

    @SerializedName("Options")
    private List<Code> Options;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getRefEntity() {
        return RefEntity;
    }

    public void setRefEntity(String RefEntity) {
        this.RefEntity = RefEntity;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String Label) {
        this.Label = Label;
    }

    public List<Code> getOptions() {
        return Options;
    }

    public void setOptions(List<Code> Options) {
        this.Options = Options;
    }

    @Override
    public String toString() {
        return "Attributes{" +
                "Name='" + Name + '\'' +
                ", RefEntity='" + RefEntity + '\'' +
                ", Type='" + Type + '\'' +
                ", Label='" + Label + '\'' +
                ", Options='" + Options + '\'' +
                '}';
    }
}

