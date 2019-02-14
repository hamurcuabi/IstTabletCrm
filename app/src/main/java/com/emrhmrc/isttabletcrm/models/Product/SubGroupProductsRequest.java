package com.emrhmrc.isttabletcrm.models.Product;

public class SubGroupProductsRequest {
    private String inv_SubGroupid;

    public SubGroupProductsRequest() {
    }

    public SubGroupProductsRequest(String inv_SubGroupid) {
        this.inv_SubGroupid = inv_SubGroupid;
    }

    public String getInv_MainGroupid() {
        return inv_SubGroupid;
    }

    public void setInv_MainGroupid(String inv_SubGroupid) {
        this.inv_SubGroupid = inv_SubGroupid;

    }

    @Override
    public String toString() {
        return "SubGroupRequest{" +
                "inv_MainGroupid='" + inv_SubGroupid + '\'' +
                '}';
    }
}
