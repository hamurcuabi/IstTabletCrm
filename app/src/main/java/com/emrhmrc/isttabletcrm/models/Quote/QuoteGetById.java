package com.emrhmrc.isttabletcrm.models.Quote;

public class QuoteGetById
{
    private TableMetadataQuote TableMetadataQuote;

    private Quote Quote;

    private TableMetadataQuoteDetails TableMetadataQuoteDetails;

    private String ErrorMsg;

    private String Success;

    public TableMetadataQuote getTableMetadataQuote ()
    {
        return TableMetadataQuote;
    }

    public void setTableMetadataQuote (TableMetadataQuote TableMetadataQuote)
    {
        this.TableMetadataQuote = TableMetadataQuote;
    }

    public Quote getQuote ()
    {
        return Quote;
    }

    public void setQuote (Quote Quote)
    {
        this.Quote = Quote;
    }

    public TableMetadataQuoteDetails getTableMetadataQuoteDetails ()
    {
        return TableMetadataQuoteDetails;
    }

    public void setTableMetadataQuoteDetails (TableMetadataQuoteDetails TableMetadataQuoteDetails)
    {
        this.TableMetadataQuoteDetails = TableMetadataQuoteDetails;
    }

    public String getErrorMsg ()
    {
        return ErrorMsg;
    }

    public void setErrorMsg (String ErrorMsg)
    {
        this.ErrorMsg = ErrorMsg;
    }

    public String getSuccess ()
    {
        return Success;
    }

    public void setSuccess (String Success)
    {
        this.Success = Success;
    }


}
