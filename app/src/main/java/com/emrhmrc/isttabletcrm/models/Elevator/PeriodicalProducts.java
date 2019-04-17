package com.emrhmrc.isttabletcrm.models.Elevator;

import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;

public class PeriodicalProducts {
    private String ProductId;
    private String ProductNumber;
    private String Name;
    private Inv_Id inv_MainGroupId;
    private Inv_Id inv_SubGroupId;
    private Integer RemainingDays;

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductNumber() {
        return ProductNumber;
    }

    public void setProductNumber(String productNumber) {
        ProductNumber = productNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Inv_Id getInv_MainGroupId() {
        return inv_MainGroupId;
    }

    public void setInv_MainGroupId(Inv_Id inv_MainGroupId) {
        this.inv_MainGroupId = inv_MainGroupId;
    }

    public Inv_Id getInv_SubGroupId() {
        return inv_SubGroupId;
    }

    public void setInv_SubGroupId(Inv_Id inv_SubGroupId) {
        this.inv_SubGroupId = inv_SubGroupId;
    }

    public Integer getRemainingDays() {
        return RemainingDays;
    }

    public void setRemainingDays(Integer remainingDays) {
        RemainingDays = remainingDays;
    }

    public PeriodicalProducts() {
    }
}
