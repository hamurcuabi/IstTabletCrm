package com.emrhmrc.isttabletcrm.models.Quote;

import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;
import com.google.gson.annotations.SerializedName;

public class QuoteDetails {
    private String ExtendedAmount;

    private String Quantity;

    private String QuoteDetailId;

    private String Tax;

    private String PricePerUnit;

    private String ProductDescription;

    private String BaseAmount;

    @SerializedName("UomId")
    private Inv_Id UomId;

    private String TransactionCurrencyId;
    @SerializedName("ProductId")
    private Inv_Id ProductId;

    public String getExtendedAmount() {
        return ExtendedAmount;
    }

    public void setExtendedAmount(String ExtendedAmount) {
        this.ExtendedAmount = ExtendedAmount;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String Quantity) {
        this.Quantity = Quantity;
    }

    public String getQuoteDetailId() {
        return QuoteDetailId;
    }

    public void setQuoteDetailId(String QuoteDetailId) {
        this.QuoteDetailId = QuoteDetailId;
    }

    public String getTax() {
        return Tax;
    }

    public void setTax(String Tax) {
        this.Tax = Tax;
    }

    public String getPricePerUnit() {
        return PricePerUnit;
    }

    public void setPricePerUnit(String PricePerUnit) {
        this.PricePerUnit = PricePerUnit;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String ProductDescription) {
        this.ProductDescription = ProductDescription;
    }

    public String getBaseAmount() {
        return BaseAmount;
    }

    public void setBaseAmount(String BaseAmount) {
        this.BaseAmount = BaseAmount;
    }

    public Inv_Id getUomId() {
        return UomId;
    }

    public void setUomId(Inv_Id UomId) {
        this.UomId = UomId;
    }

    public String getTransactionCurrencyId() {
        return TransactionCurrencyId;
    }

    public void setTransactionCurrencyId(String TransactionCurrencyId) {
        this.TransactionCurrencyId = TransactionCurrencyId;
    }

    public Inv_Id getProductId() {
        return ProductId;
    }

    public void setProductId(Inv_Id ProductId) {
        this.ProductId = ProductId;
    }

    @Override
    public String toString() {
        return "QuoteDetails{" +
                "ExtendedAmount='" + ExtendedAmount + '\'' +
                ", Quantity='" + Quantity + '\'' +
                ", QuoteDetailId='" + QuoteDetailId + '\'' +
                ", Tax='" + Tax + '\'' +
                ", PricePerUnit='" + PricePerUnit + '\'' +
                ", ProductDescription='" + ProductDescription + '\'' +
                ", BaseAmount='" + BaseAmount + '\'' +
                ", UomId=" + UomId +
                ", TransactionCurrencyId='" + TransactionCurrencyId + '\'' +
                ", ProductId=" + ProductId +
                '}';
    }
}

