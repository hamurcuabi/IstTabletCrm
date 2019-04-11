package com.emrhmrc.isttabletcrm.models.ServApp;

public class DefaultResponse {
    public boolean Success;
    public String ErrorMsg;

    @Override
    public String toString() {
        return "ServAppCompleteById{" +
                "Succes=" + Success +
                ", ErrorMsg='" + ErrorMsg + '\'' +
                '}';
    }

    public boolean isSucces() {
        return Success;
    }

    public void setSucces(boolean succes) {
        Success = succes;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }
}
