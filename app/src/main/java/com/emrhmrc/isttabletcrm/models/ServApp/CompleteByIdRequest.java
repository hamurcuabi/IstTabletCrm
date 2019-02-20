package com.emrhmrc.isttabletcrm.models.ServApp;

public class CompleteByIdRequest {
    private String ServiceAppId;
    private String UserId;
    private boolean CompleteType;

    public boolean isCompleteType() {
        return CompleteType;
    }

    public void setCompleteType(boolean completeType) {
        CompleteType = completeType;
    }

    public CompleteByIdRequest(String serviceAppId, String userId) {
        ServiceAppId = serviceAppId;
        UserId = userId;
    }

    public CompleteByIdRequest() {

    }

    public String getServiceAppId() {

        return ServiceAppId;
    }

    public void setServiceAppId(String serviceAppId) {
        ServiceAppId = serviceAppId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
