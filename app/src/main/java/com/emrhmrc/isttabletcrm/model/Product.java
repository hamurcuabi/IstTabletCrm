package com.emrhmrc.isttabletcrm.model;

public class Product {
    private String Name;

    private Inv_SubGroupId inv_SubGroupId;

    private Inv_SalesGroupId inv_SalesGroupId;

    private String ProductNumber;

    private String inv_BillDefinition;

    private String ProductId;

    private Inv_MainGroupId inv_MainGroupId;

    private Inv_BrandId inv_BrandId;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Inv_SubGroupId getInv_SubGroupId() {
        return inv_SubGroupId;
    }

    public void setInv_SubGroupId(Inv_SubGroupId inv_SubGroupId) {
        this.inv_SubGroupId = inv_SubGroupId;
    }

    public Inv_SalesGroupId getInv_SalesGroupId() {
        return inv_SalesGroupId;
    }

    public void setInv_SalesGroupId(Inv_SalesGroupId inv_SalesGroupId) {
        this.inv_SalesGroupId = inv_SalesGroupId;
    }

    public String getProductNumber() {
        return ProductNumber;
    }

    public void setProductNumber(String ProductNumber) {
        this.ProductNumber = ProductNumber;
    }

    public String getInv_BillDefinition() {
        return inv_BillDefinition;
    }

    public void setInv_BillDefinition(String inv_BillDefinition) {
        this.inv_BillDefinition = inv_BillDefinition;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String ProductId) {
        this.ProductId = ProductId;
    }

    public Inv_MainGroupId getInv_MainGroupId() {
        return inv_MainGroupId;
    }

    public void setInv_MainGroupId(Inv_MainGroupId inv_MainGroupId) {
        this.inv_MainGroupId = inv_MainGroupId;
    }

    public Inv_BrandId getInv_BrandId() {
        return inv_BrandId;
    }

    public void setInv_BrandId(Inv_BrandId inv_BrandId) {
        this.inv_BrandId = inv_BrandId;
    }

    @Override
    public String toString() {
        return "[Name = " + Name + ", inv_SubGroupId = " + inv_SubGroupId + ", inv_SalesGroupId = " + inv_SalesGroupId + ", ProductNumber = " + ProductNumber + ", inv_BillDefinition = " + inv_BillDefinition + ", ProductId = " + ProductId + ", inv_MainGroupId = " + inv_MainGroupId + ", inv_BrandId = " + inv_BrandId + "]";
    }
}
