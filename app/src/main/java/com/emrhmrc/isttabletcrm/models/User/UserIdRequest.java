package com.emrhmrc.isttabletcrm.models.User;

public class UserIdRequest {
    private String UserId;

    public UserIdRequest(String userId) {
        UserId = userId;
    }

    public UserIdRequest() {

    }

    @Override
    public String toString() {
        return "UserIdRequest{" +
                "UserId='" + UserId + '\'' +
                '}';
    }

    public String getUserId() {

        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
