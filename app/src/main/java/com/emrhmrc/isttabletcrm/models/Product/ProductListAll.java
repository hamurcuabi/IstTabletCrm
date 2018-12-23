package com.emrhmrc.isttabletcrm.models.Product;

import com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata;

import java.util.List;

public class ProductListAll {
    private List<Products> Products;

    private String ErrorMsg;

    private boolean Success;

    private TableMetadata TableMetadata;

    public List<Products> getProducts() {
        return Products;
    }

    public void setProducts(List<Products> Products) {
        this.Products = Products;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }

    public boolean getSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public TableMetadata getTableMetadata() {
        return TableMetadata;
    }

    public void setTableMetadata(TableMetadata TableMetadata) {
        this.TableMetadata = TableMetadata;
    }

    @Override
    public String toString() {
        return "ProductListAll{" +
                "Products=" + Products +
                ", ErrorMsg='" + ErrorMsg + '\'' +
                ", Success=" + Success +
                ", TableMetadata=" + TableMetadata +
                '}';
    }
}

