package com.emrhmrc.isttabletcrm.models.ServApp;


import com.emrhmrc.isttabletcrm.models.Elevator.Inv_ApprovalStCode;
import com.emrhmrc.isttabletcrm.models.Elevator.Inv_ProductId;
import com.emrhmrc.isttabletcrm.models.Elevator.Inv_WarrantyStatusCode;

public class ServAppGetByIdServAppDetails
{
    private Integer inv_LineNo;

    private String inv_Description;

    private Inv_WarrantyStatusCode inv_WarrantyStatusCode;

    private boolean inv_WillBeBilled;

    private String inv_ServiceAppDetailId;

    private Inv_ApprovalStCode inv_ApprovalStCode;

    private String inv_ProductDescription;

    private Inv_Uomid inv_Uomid;

    private Inv_ProductId inv_ProductId;

    private String inv_Quantity;

    public Integer getInv_LineNo ()
    {
        return inv_LineNo;
    }

    public void setInv_LineNo (Integer inv_LineNo)
    {
        this.inv_LineNo = inv_LineNo;
    }

    public String getInv_Description ()
    {
        return inv_Description;
    }

    public void setInv_Description (String inv_Description)
    {
        this.inv_Description = inv_Description;
    }

    public Inv_WarrantyStatusCode getInv_WarrantyStatusCode ()
    {
        return inv_WarrantyStatusCode;
    }

    public void setInv_WarrantyStatusCode (Inv_WarrantyStatusCode inv_WarrantyStatusCode)
    {
        this.inv_WarrantyStatusCode = inv_WarrantyStatusCode;
    }

    public boolean getInv_WillBeBilled ()
    {
        return inv_WillBeBilled;
    }

    public void setInv_WillBeBilled (boolean inv_WillBeBilled)
    {
        this.inv_WillBeBilled = inv_WillBeBilled;
    }

    public String getInv_ServiceAppDetailId ()
    {
        return inv_ServiceAppDetailId;
    }

    public void setInv_ServiceAppDetailId (String inv_ServiceAppDetailId)
    {
        this.inv_ServiceAppDetailId = inv_ServiceAppDetailId;
    }

    public Inv_ApprovalStCode getInv_ApprovalStCode ()
    {
        return inv_ApprovalStCode;
    }

    public void setInv_ApprovalStCode (Inv_ApprovalStCode inv_ApprovalStCode)
    {
        this.inv_ApprovalStCode = inv_ApprovalStCode;
    }

    public String getInv_ProductDescription ()
    {
        return inv_ProductDescription;
    }

    public void setInv_ProductDescription (String inv_ProductDescription)
    {
        this.inv_ProductDescription = inv_ProductDescription;
    }

    public Inv_Uomid getInv_Uomid ()
    {
        return inv_Uomid;
    }

    public void setInv_Uomid (Inv_Uomid inv_Uomid)
    {
        this.inv_Uomid = inv_Uomid;
    }

    public Inv_ProductId getInv_ProductId ()
    {
        return inv_ProductId;
    }

    public void setInv_ProductId (Inv_ProductId inv_ProductId)
    {
        this.inv_ProductId = inv_ProductId;
    }

    public String getInv_Quantity ()
    {
        return inv_Quantity;
    }

    public void setInv_Quantity (String inv_Quantity)
    {
        this.inv_Quantity = inv_Quantity;
    }


}

