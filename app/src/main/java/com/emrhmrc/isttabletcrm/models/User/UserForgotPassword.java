package com.emrhmrc.isttabletcrm.models.User;

public class UserForgotPassword {
    private boolean Success;
    private String ErrorMsg;
    // Getter Methods

    public boolean getSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    // Setter Methods

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }

    @Override
    public String toString() {
        return "UserForgotPassword{" +
                "Success=" + Success +
                ", ErrorMsg='" + ErrorMsg + '\'' +
                '}';
    }
}
