package com.emrhmrc.isttabletcrm.models.CommonClass;

public class Inv_Id {
    private String LogicalName;

    private String Text;

    private String Id;

    public Inv_Id() {
    }

    public Inv_Id(String logicalName, String text, String id) {
        LogicalName = logicalName;
        Text = text;
        Id = id;
    }

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
        return Text;
    }
}
