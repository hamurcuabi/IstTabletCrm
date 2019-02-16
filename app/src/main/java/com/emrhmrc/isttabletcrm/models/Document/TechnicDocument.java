package com.emrhmrc.isttabletcrm.models.Document;

import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;

public class TechnicDocument {

    private String inv_TechnicalDocumentId;
    private String inv_TechnicalDocumentName;
    private String inv_Keywords;
    private String FileName;
    private String MimeType;
    private String DocumentBody;
    private int FileSize;
    private Inv_Id inv_DocumentTypeid;

    public TechnicDocument() {
    }

    @Override
    public String toString() {
        return "TechnicDocument{" +
                "inv_TechnicalDocumentId='" + inv_TechnicalDocumentId + '\'' +
                ", inv_TechnicalDocumentName='" + inv_TechnicalDocumentName + '\'' +
                ", inv_Keywords='" + inv_Keywords + '\'' +
                ", FileName='" + FileName + '\'' +
                ", MimeType='" + MimeType + '\'' +
                ", DocumentBody='" + DocumentBody + '\'' +
                ", FileSize=" + FileSize +
                ", inv_DocumentTypeid=" + inv_DocumentTypeid +
                '}';
    }

    public String getInv_TechnicalDocumentId() {
        return inv_TechnicalDocumentId;
    }

    public void setInv_TechnicalDocumentId(String inv_TechnicalDocumentId) {
        this.inv_TechnicalDocumentId = inv_TechnicalDocumentId;
    }

    public String getInv_TechnicalDocumentName() {
        return inv_TechnicalDocumentName;
    }

    public void setInv_TechnicalDocumentName(String inv_TechnicalDocumentName) {
        this.inv_TechnicalDocumentName = inv_TechnicalDocumentName;
    }

    public String getInv_Keywords() {
        return inv_Keywords;
    }

    public void setInv_Keywords(String inv_Keywords) {
        this.inv_Keywords = inv_Keywords;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getMimeType() {
        return MimeType;
    }

    public void setMimeType(String mimeType) {
        MimeType = mimeType;
    }

    public String getDocumentBody() {
        return DocumentBody;
    }

    public void setDocumentBody(String documentBody) {
        DocumentBody = documentBody;
    }

    public int getFileSize() {
        return FileSize;
    }

    public void setFileSize(int fileSize) {
        FileSize = fileSize;
    }

    public Inv_Id getInv_DocumentTypeid() {
        return inv_DocumentTypeid;
    }

    public void setInv_DocumentTypeid(Inv_Id inv_DocumentTypeid) {
        this.inv_DocumentTypeid = inv_DocumentTypeid;
    }
}
