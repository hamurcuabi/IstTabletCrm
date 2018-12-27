package com.emrhmrc.isttabletcrm.models.BreakDown;

public class BreakdownType
{
    private String inv_BreakdownTypeName;

    private String inv_BreakdownTypeId;

    public String getInv_BreakdownTypeName ()
    {
        return inv_BreakdownTypeName;
    }

    public void setInv_BreakdownTypeName (String inv_BreakdownTypeName)
    {
        this.inv_BreakdownTypeName = inv_BreakdownTypeName;
    }

    public String getInv_BreakdownTypeId ()
    {
        return inv_BreakdownTypeId;
    }

    public void setInv_BreakdownTypeId (String inv_BreakdownTypeId)
    {
        this.inv_BreakdownTypeId = inv_BreakdownTypeId;
    }

    @Override
    public String toString() {
        return "BreakdownTypes{" +
                "inv_BreakdownTypeName='" + inv_BreakdownTypeName + '\'' +
                ", inv_BreakdownTypeId='" + inv_BreakdownTypeId + '\'' +
                '}';
    }
}

