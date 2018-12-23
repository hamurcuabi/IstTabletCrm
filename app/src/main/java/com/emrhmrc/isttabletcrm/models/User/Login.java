package com.emrhmrc.isttabletcrm.models.User;

public class Login {
    private boolean Success;
    private String ErrorMsg = null;
    private String UserId;

    // Getter Methods

    public boolean getSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
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

    @Override
    public String toString() {
        return "UserLogin{" +
                "Success=" + Success +
                ", ErrorMsg='" + ErrorMsg + '\'' +
                ", UserId='" + UserId + '\'' +
                '}';
    }
}