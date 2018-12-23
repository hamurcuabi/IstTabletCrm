package com.emrhmrc.isttabletcrm.models.User;

public class Reset {

    private boolean Success;
    private String ErrorMsg;
    private String UserId;


    // Getter Methods

    public boolean getSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    @Override
    public String toString() {
        return "Reset{" +
                "Success=" + Success +
                ", ErrorMsg='" + ErrorMsg + '\'' +
                ", UserId='" + UserId + '\'' +
                '}';
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    // Setter Methods

    public void setErrorMsg(String ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }
}
