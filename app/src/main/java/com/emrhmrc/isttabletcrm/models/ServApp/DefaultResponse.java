package com.emrhmrc.isttabletcrm.models.ServApp;

public class DefaultResponse {
    private boolean Succes;
    private String ErrorMsg;

    @Override
    public String toString() {
        return "ServAppCompleteById{" +
                "Succes=" + Succes +
                ", ErrorMsg='" + ErrorMsg + '\'' +
                '}';
    }

    public boolean isSucces() {
        return Succes;
    }

    public void setSucces(boolean succes) {
        Succes = succes;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }
}
