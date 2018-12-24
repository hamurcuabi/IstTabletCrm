package com.emrhmrc.isttabletcrm.models.ServApp;

public class UpdateServFormById {
    private Integer Filesize;

    private String MimeType;

    private String DocumentBody;

    private String ServiceAppId;

    private String UserId;

    public Integer getFilesize() {
        return Filesize;
    }

    public void setFilesize(Integer Filesize) {
        this.Filesize = Filesize;
    }

    public String getMimeType() {
        return MimeType;
    }

    public void setMimeType(String MimeType) {
        this.MimeType = MimeType;
    }

    public String getDocumentBody() {
        return DocumentBody;
    }

    public void setDocumentBody(String DocumentBody) {
        this.DocumentBody = DocumentBody;
    }

    public String getServiceAppId() {
        return ServiceAppId;
    }

    public void setServiceAppId(String ServiceAppId) {
        this.ServiceAppId = ServiceAppId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }


}


