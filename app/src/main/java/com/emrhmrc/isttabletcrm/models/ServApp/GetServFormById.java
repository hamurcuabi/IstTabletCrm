package com.emrhmrc.isttabletcrm.models.ServApp;

import java.io.Serializable;

public class GetServFormById implements Serializable {

    private String MimeType;

    private String DocumentBody;

    private String ErrorMsg;

    private boolean Success;


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

    @Override
    public String toString() {
        return "GetServFormById{" +
                ", MimeType='" + MimeType + '\'' +
                ", DocumentBody='" + DocumentBody + '\'' +
                ", ErrorMsg='" + ErrorMsg + '\'' +
                ", Success=" + Success +
                '}';
    }
}

