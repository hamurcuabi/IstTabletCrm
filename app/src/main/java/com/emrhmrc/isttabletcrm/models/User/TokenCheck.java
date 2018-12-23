package com.emrhmrc.isttabletcrm.models.User;

public class TokenCheck {
    private boolean Success;
    private String ErrorMsg;
    private String Email;

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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    @Override
    public String toString() {
        return "TokenCheck{" +
                "Success=" + Success +
                ", ErrorMsg='" + ErrorMsg + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }
}

