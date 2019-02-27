package com.emrhmrc.isttabletcrm.models.CommonClass;

public class ServAppCountResponse {

    private int ServAppCount;
    private String ErrorMsg;
    private boolean Success;

    public ServAppCountResponse() {
    }

    public int getServAppCount() {
        return ServAppCount;
    }

    public void setServAppCount(int servAppCount) {
        ServAppCount = servAppCount;
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
}
