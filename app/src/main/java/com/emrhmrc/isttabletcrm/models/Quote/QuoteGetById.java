package com.emrhmrc.isttabletcrm.models.Quote;

import com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata;
import com.google.gson.annotations.SerializedName;

public class QuoteGetById {
    @SerializedName("TableMetadataQuote")
    private TableMetadata TableMetadataQuote;

    private Quote Quote;
    @SerializedName("TableMetadataQuoteDetails")
    private TableMetadata TableMetadataQuoteDetails;

    private String ErrorMsg;

    private String Success;

    public TableMetadata getTableMetadataQuote() {
        return TableMetadataQuote;
    }

    public void setTableMetadataQuote(TableMetadata TableMetadataQuote) {
        this.TableMetadataQuote = TableMetadataQuote;
    }

    public Quote getQuote() {
        return Quote;
    }

    public void setQuote(Quote Quote) {
        this.Quote = Quote;
    }

    public TableMetadata getTableMetadataQuoteDetails() {
        return TableMetadataQuoteDetails;
    }

    public void setTableMetadataQuoteDetails(TableMetadata TableMetadataQuoteDetails) {
        this.TableMetadataQuoteDetails = TableMetadataQuoteDetails;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String Success) {
        this.Success = Success;
    }


}
