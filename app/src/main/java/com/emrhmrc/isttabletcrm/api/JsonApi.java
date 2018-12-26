package com.emrhmrc.isttabletcrm.api;


import com.emrhmrc.isttabletcrm.models.Account.AccountListAll;
import com.emrhmrc.isttabletcrm.models.BreakDown.BreakDownTypeListAll;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorGetById;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorListAll;
import com.emrhmrc.isttabletcrm.models.Product.ProductListAll;
import com.emrhmrc.isttabletcrm.models.Quote.QuoteGetById;
import com.emrhmrc.isttabletcrm.models.ServApp.DefaultResponse;
import com.emrhmrc.isttabletcrm.models.ServApp.GetServFormById;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppListAll;
import com.emrhmrc.isttabletcrm.models.ServApp.UpdateServFormById;
import com.emrhmrc.isttabletcrm.models.User.UserForgotPassword;
import com.emrhmrc.isttabletcrm.models.User.UserLogin;
import com.emrhmrc.isttabletcrm.models.User.UserReset;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JsonApi {

    @GET("Product/ListAll")
    Call<ProductListAll> getProductListAll();

    @GET("User/Login")
    Call<UserLogin> userLogin(@Query("Email") String email, @Query("PassWord") String password);

    @GET("User/UserForgotPassword")
    Call<UserForgotPassword> userForgotPassword(@Query("Email") String email);

    @GET("User/TokenCheck")
    Call<UserForgotPassword> userTokenCheck(@Query("Token") String token);

    @GET("User/Reset")
    Call<UserReset> userReset(@Query("Email") String email, @Query("PassWord") String password);

    @GET("BreakdownType/ListAll")
    Call<BreakDownTypeListAll> breakDownTypeListAll();

    @GET("Product/ListAll")
    Call<ProductListAll> productListAll();

    @GET("Account/ListAll")
    Call<AccountListAll> accountListAll();

    @GET("Elevator/ListAll")
    Call<ElevatorListAll> elevatorListAll();

    @GET("Elevator/GetById")
    Call<ElevatorGetById> elevatorGetById(@Query("ElevatorId") String elevatorId);

    @GET("ServApp/ListAll")
    Call<ServAppListAll> servAppListAll(@Query("UserId") String userId);

    @GET("ServApp/GetById")
    Call<ServAppListAll> servAppGetById(@Query("ServAppId") String servAppId);

    @POST("ServApp/CompleteById")
    Call<ServAppCompleteById> servAppCompleteById(@Body ServAppCompleteById servAppCompleteById);

    @GET("ServApp/GetServFormById")
    Call<GetServFormById> getServFormById(@Query("ServiceAppId") String serviceAppId);

    @POST("ServApp/UpdateServFormById")
    Call<DefaultResponse> updateServFormById(@Body UpdateServFormById updateServFormById);

    @GET("Quote/GetById")
    Call<QuoteGetById> quoteGetById(@Query("QuoteId") String quoteId);

}
