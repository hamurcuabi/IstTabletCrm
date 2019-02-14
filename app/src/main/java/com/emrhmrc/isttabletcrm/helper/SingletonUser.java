package com.emrhmrc.isttabletcrm.helper;

import com.emrhmrc.isttabletcrm.models.User.UserLogin;

public class SingletonUser {
    private static final SingletonUser ourInstance = new SingletonUser();

    public static SingletonUser getInstance() {
        return ourInstance;
    }
    private UserLogin user;

    public UserLogin getUser() {
        return user;
    }

    public void setUser(UserLogin user) {
        this.user = user;
    }

    private SingletonUser() {
    }

}
