package com.emrhmrc.isttabletcrm.api;


import com.emrhmrc.isttabletcrm.models.Product.ProductListAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonApi {

    @GET("Product/ListAll")
    Call<ProductListAll> getProductListAll();
}
