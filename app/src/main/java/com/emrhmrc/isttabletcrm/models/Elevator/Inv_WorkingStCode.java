package com.emrhmrc.isttabletcrm.models.Elevator;

public class Inv_WorkingStCode {
    private String Text;

    private Integer Value;

    public String getText() {
        return Text;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    public Integer getValue() {
        return Value;
    }

    public void setValue(Integer Value) {
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
