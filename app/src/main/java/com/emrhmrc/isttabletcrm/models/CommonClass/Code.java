package com.emrhmrc.isttabletcrm.models.CommonClass;

public class Code {
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
        return "Code{" +
                "Text='" + Text + '\'' +
                ", Value='" + Value + '\'' +
                '}';
    }
}
