package com.emrhmrc.isttabletcrm.models.User;

public class EmailRequest {
    private String Email;

    public EmailRequest() {
    }

    public EmailRequest(String email) {

        Email = email;
    }

    public String getEmail() {

        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
