package com.emrhmrc.isttabletcrm.api;


import com.emrhmrc.isttabletcrm.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonApi {

    @GET("Product/ListAll")
    Call<List<Product>> getProductListAll();
}
