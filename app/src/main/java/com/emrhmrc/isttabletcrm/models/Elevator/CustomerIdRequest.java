package com.emrhmrc.isttabletcrm.models.Elevator;

public class CustomerIdRequest {
    private String CustomerId;

    public CustomerIdRequest() {
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public CustomerIdRequest(String customerId) {
        CustomerId = customerId;
    }
}
