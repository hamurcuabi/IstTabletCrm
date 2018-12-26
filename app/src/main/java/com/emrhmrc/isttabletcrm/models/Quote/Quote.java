package com.emrhmrc.isttabletcrm.models.Quote;


import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Quote {
    private StatusCode StatusCode;

    private String inv_CustomerApprovalStCode;

    private String inv_RequestDate;

    private String Quote;

    @SerializedName("Elevators")
    private List<Inv_Id> Elevators;

    private Inv_TypeCode inv_TypeCode;

    private List<QuoteDetails> QuoteDetails;

    public StatusCode getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(StatusCode StatusCode) {
        this.StatusCode = StatusCode;
    }

    public String getInv_CustomerApprovalStCode() {
        return inv_CustomerApprovalStCode;
    }

    public void setInv_CustomerApprovalStCode(String inv_CustomerApprovalStCode) {
        this.inv_CustomerApprovalStCode = inv_CustomerApprovalStCode;
    }

    public String getInv_RequestDate() {
        return inv_RequestDate;
    }

    public void setInv_RequestDate(String inv_RequestDate) {
        this.inv_RequestDate = inv_RequestDate;
    }

    public String getQuote() {
        return Quote;
    }

    public void setQuote(String Quote) {
        this.Quote = Quote;
    }

    public List<Inv_Id> getElevators() {
        return Elevators;
    }

    public void setElevators(List<Inv_Id> Elevators) {
        this.Elevators = Elevators;
    }

    public Inv_TypeCode getInv_TypeCode() {
        return inv_TypeCode;
    }

    public void setInv_TypeCode(Inv_TypeCode inv_TypeCode) {
        this.inv_TypeCode = inv_TypeCode;
    }

    public List<QuoteDetails> getQuoteDetails() {
        return QuoteDetails;
    }

    public void setQuoteDetails(List<QuoteDetails> QuoteDetails) {
        this.QuoteDetails = QuoteDetails;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "StatusCode=" + StatusCode +
                ", inv_CustomerApprovalStCode='" + inv_CustomerApprovalStCode + '\'' +
                ", inv_RequestDate='" + inv_RequestDate + '\'' +
                ", Quote='" + Quote + '\'' +
                ", Elevators=" + Elevators +
                ", inv_TypeCode=" + inv_TypeCode +
                ", QuoteDetails=" + QuoteDetails +
                '}';
    }
}
