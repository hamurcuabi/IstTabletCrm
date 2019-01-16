package com.emrhmrc.isttabletcrm.models.ServApp;

public class ServiceAppIdRequest {
    private String ServiceAppId;

    public ServiceAppIdRequest() {

    }

    public ServiceAppIdRequest(String serviceAppId) {

        ServiceAppId = serviceAppId;
    }

    public String getServiceAppId() {
        return ServiceAppId;
    }

    public void setServiceAppId(String serviceAppId) {
        ServiceAppId = serviceAppId;
    }
}
