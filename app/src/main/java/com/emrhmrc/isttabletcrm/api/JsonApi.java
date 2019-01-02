package com.emrhmrc.isttabletcrm.api;


import com.emrhmrc.isttabletcrm.models.Account.AccountListAll;
import com.emrhmrc.isttabletcrm.models.BreakDown.BreakDownTypeListAll;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;
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

    //Fail from Server
    @GET("User/Login")
    Call<UserLogin> userLogin(@Query("Email") String email, @Query("PassWord") String password);

    //Fail from Server
    @GET("User/UserForgotPassword")
    Call<UserForgotPassword> userForgotPassword(@Query("Email") String email);

    //Fail from Server
    @GET("User/TokenCheck")
    Call<UserForgotPassword> userTokenCheck(@Query("Token") String token);

    //Fail from Server
    @GET("User/Reset")
    Call<UserReset> userReset(@Query("Email") String email, @Query("PassWord") String password);

    //OK
    @GET("BreakdownType/ListAll")
    Call<BreakDownTypeListAll> breakDownTypeListAll();

    //OK
    @GET("Product/ListAll")
    Call<ProductListAll> productListAll();

    //OK
    @GET("Account/ListAll")
    Call<AccountListAll> accountListAll();

    //OK
    @GET("Elevator/ListAll")
    Call<ElevatorListAll> elevatorListAll();

    //Fail from Server
    @GET("Elevator/GetById")
    Call<ElevatorGetById> elevatorGetById(@Query("ElevatorId") String elevatorId);

    //Fail from Server
    @GET("ServApp/ListAll")
    Call<ServAppListAll> servAppListAll(@Query("UserId") String userId);

    //Fail from Server
    @GET("ServApp/GetById")
    Call<ServAppListAll> servAppGetById(@Query("ServAppId") String servAppId);

    //Fail from Server
    @POST("ServApp/CompleteById")
    Call<Inv_Id> servAppCompleteById(@Body Inv_Id servAppCompleteById);

    //Fail from Server
    @GET("ServApp/GetServFormById")
    Call<GetServFormById> getServFormById(@Query("ServiceAppId") String serviceAppId);

    //Fail from Server
    @POST("ServApp/UpdateServFormById")
    Call<DefaultResponse> updateServFormById(@Body UpdateServFormById updateServFormById);

    //Fail from Server
    @GET("Quote/GetById")
    Call<QuoteGetById> quoteGetById(@Query("QuoteId") String quoteId);


}
