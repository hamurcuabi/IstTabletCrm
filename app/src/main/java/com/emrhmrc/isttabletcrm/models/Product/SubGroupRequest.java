package com.emrhmrc.isttabletcrm.models.Product;

public class SubGroupRequest {
    private String inv_MainGroupid;

    public SubGroupRequest() {
    }

    public SubGroupRequest(String inv_MainGroupid) {
        this.inv_MainGroupid = inv_MainGroupid;
    }

    public String getInv_MainGroupid() {
        return inv_MainGroupid;
    }

    @Override
    public String toString() {
        return "SubGroupRequest{" +
                "inv_MainGroupid='" + inv_MainGroupid + '\'' +
                '}';
    }

    public void setInv_MainGroupid(String inv_MainGroupid) {
        this.inv_MainGroupid = inv_MainGroupid;

    }
}
