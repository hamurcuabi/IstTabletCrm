package com.emrhmrc.isttabletcrm.models.ServApp;

public class Inv_WorkListDetailId {
    private String LogicalName;

    private String Text;

    private String Id;

    public String getLogicalName() {
        return LogicalName;
    }

    public void setLogicalName(String LogicalName) {
        this.LogicalName = LogicalName;
    }

    public String getText() {
        return Text;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    @Override
    public String toString() {
        return "Inv_WorkListDetailId{" +
                "LogicalName='" + LogicalName + '\'' +
                ", Text='" + Text + '\'' +
                ", Id='" + Id + '\'' +
                '}';
    }
}
