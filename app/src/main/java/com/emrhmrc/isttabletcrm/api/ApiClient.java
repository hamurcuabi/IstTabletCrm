package com.emrhmrc.isttabletcrm.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final int TIME_OUT = 15;
    private static final String TAG = "ApiClient";
    private static final String USER_NAME = "Creatif";
    private static final String PASSWORD = "Crm365@!";
    private static Retrofit retrofit = null;
    private static String Base_Url = "http://dpmdcturkliftmobiltest.innova.com.tr/api/";

    public static Retrofit getClient() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor
                (new BasicAuthInterceptor(USER_NAME, PASSWORD))
                .callTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();

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
