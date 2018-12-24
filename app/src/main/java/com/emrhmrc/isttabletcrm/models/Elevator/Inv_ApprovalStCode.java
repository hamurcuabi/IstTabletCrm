package com.emrhmrc.isttabletcrm.models.Elevator;

public class Inv_ApprovalStCode {
    private Integer Value;
    private String Text;

    @Override
    public String toString() {
        return "Inv_ApprovalStCode{" +
                "Value=" + Value +
                ", Text='" + Text + '\'' +
                '}';
    }

    public Integer getValue() {
        return Value;
    }

    public void setValue(Integer value) {
        Value = value;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}
