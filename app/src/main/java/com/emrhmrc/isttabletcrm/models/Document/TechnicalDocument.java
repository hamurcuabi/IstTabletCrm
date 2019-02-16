package com.emrhmrc.isttabletcrm.models.Document;

import com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata;

import java.util.List;

public class TechnicalDocument {

    private TableMetadata TableMetadata;
    private String ErrorMsg;
    private boolean Success;
    private List<TechnicDocument> TechnicDocuments;

    public TechnicalDocument() {
    }

    public com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata getTableMetadata() {
        return TableMetadata;
    }

    public void setTableMetadata(com.emrhmrc.isttabletcrm.models.CommonClass.TableMetadata tableMetadata) {
        TableMetadata = tableMetadata;
    }

    @Override
    public String toString() {
        return "TechnicalDocument{" +
                "TableMetadata=" + TableMetadata +
                ", ErrorMsg='" + ErrorMsg + '\'' +
                ", Success=" + Success +
                ", TechnicDocuments=" + TechnicDocuments +
                '}';
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public List<TechnicDocument> getTechnicDocuments() {
        return TechnicDocuments;
    }

    public void setTechnicDocuments(List<TechnicDocument> technicDocuments) {
        TechnicDocuments = technicDocuments;
    }
}
