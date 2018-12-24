package com.emrhmrc.isttabletcrm.models.Quote;

public class Inv_TypeCode {
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
        return "Inv_TypeCode{" +
                "Text='" + Text + '\'' +
                ", Value='" + Value + '\'' +
                '}';
    }
}
