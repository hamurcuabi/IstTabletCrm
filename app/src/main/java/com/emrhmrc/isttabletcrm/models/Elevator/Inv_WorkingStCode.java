package com.emrhmrc.isttabletcrm.models.Elevator;

public class Inv_WorkingStCode {
    private String Text;

    private String Value;

    public String getText() {
        return Text;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String Value) {
        this.Value = Value;
    }

    @Override
    public String toString() {
        return "Inv_WorkingStCode{" +
                "Text='" + Text + '\'' +
                ", Value='" + Value + '\'' +
                '}';
    }
}
