package com.emrhmrc.isttabletcrm.models.User;

public class UserRequest {
    private String Email;
    private String PassWord;

    public UserRequest(String email, String passWord) {
        Email = email;
        PassWord = passWord;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }
}
