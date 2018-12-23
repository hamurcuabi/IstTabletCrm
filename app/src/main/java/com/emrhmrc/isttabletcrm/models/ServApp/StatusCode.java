package com.emrhmrc.isttabletcrm.models.ServApp;

public class StatusCode
{
    private String Text;

    private String Value;

    public String getText ()
    {
        return Text;
    }

    public void setText (String Text)
    {
        this.Text = Text;
    }

    public String getValue ()
    {
        return Value;
    }

    public void setValue (String Value)
    {
        this.Value = Value;
    }

    @Override
    public String toString() {
        return "StatusCode{" +
                "Text='" + Text + '\'' +
                ", Value='" + Value + '\'' +
                '}';
    }
}

