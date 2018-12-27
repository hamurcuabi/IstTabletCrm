package com.emrhmrc.isttabletcrm.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String TAG = "ApiClient";
    private static Retrofit retrofit = null;
    private static String Base_Url = "http://dpmdcturkliftmobiltest.innova.com.tr/api/";

    public static Retrofit getClient() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor
                (new BasicAuthInterceptor("Creatif", "Crm365@!")).build();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Base_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
            return retrofit;
        }
        return retrofit;

    }
}
