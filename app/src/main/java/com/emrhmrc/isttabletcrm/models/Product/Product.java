package com.emrhmrc.isttabletcrm.models.Product;

import com.emrhmrc.isttabletcrm.models.CommonClass.Code;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;

import java.io.Serializable;

public class Product implements Serializable {
    private String Name;
    private Inv_Id inv_SubGroupId;
    private Inv_Id inv_SalesGroupId;
    private Inv_Id inv_MainGroupId;
    private Inv_Id inv_BrandId;
    private Inv_Id UoM;
    private String ProductNumber;
    private String inv_BillDefinition;
    private String ProductId;
    private String Image;
    private String ImageMimeType;
    private Code inv_TypeCode;

    public Inv_Id getUoM() {
        return UoM;
    }

    public void setUoM(Inv_Id uoM) {
        UoM = uoM;
    }

    public Code getInv_TypeCode() {
        return inv_TypeCode;
    }

    public void setInv_TypeCode(Code inv_TypeCode) {
        this.inv_TypeCode = inv_TypeCode;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getImageMimeType() {
        return ImageMimeType;
    }

    public void setImageMimeType(String imageMimeType) {
        ImageMimeType = imageMimeType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Inv_Id getInv_SubGroupId() {
        return inv_SubGroupId;
    }

    public void setInv_SubGroupId(Inv_Id inv_SubGroupId) {
        this.inv_SubGroupId = inv_SubGroupId;
    }

    public Inv_Id getInv_SalesGroupId() {
        return inv_SalesGroupId;
    }

    public void setInv_SalesGroupId(Inv_Id inv_SalesGroupId) {
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

    public Inv_Id getInv_MainGroupId() {
        return inv_MainGroupId;
    }

    public void setInv_MainGroupId(Inv_Id inv_MainGroupId) {
        this.inv_MainGroupId = inv_MainGroupId;
    }

    public Inv_Id getInv_BrandId() {
        return inv_BrandId;
    }

    public void setInv_BrandId(Inv_Id inv_BrandId) {
        this.inv_BrandId = inv_BrandId;
    }

    @Override
    public String toString() {
        return Name;
    }
}

