package com.emrhmrc.isttabletcrm.models.ServApp;

public class ServAppIdRequest {
    private String ServAppId;

    public ServAppIdRequest() {

    }

    public ServAppIdRequest(String servAppId) {

        ServAppId = servAppId;
    }

    @Override
    public String toString() {
        return "ServAppIdRequest{" +
                "ServAppId='" + ServAppId + '\'' +
                '}';
    }

    public String getServAppId() {
        return ServAppId;
    }

    public void setServAppId(String servAppId) {
        ServAppId = servAppId;
    }
}
