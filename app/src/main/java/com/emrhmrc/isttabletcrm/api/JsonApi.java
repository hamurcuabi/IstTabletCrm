package com.emrhmrc.isttabletcrm.api;


import com.emrhmrc.isttabletcrm.models.Account.AccountListAll;
import com.emrhmrc.isttabletcrm.models.BreakDown.BreakDownTypeListAll;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorGetById;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorIdRequest;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorListAll;
import com.emrhmrc.isttabletcrm.models.Product.ProductListAll;
import com.emrhmrc.isttabletcrm.models.Quote.QuoteGetById;
import com.emrhmrc.isttabletcrm.models.ServApp.CompleteByIdRequest;
import com.emrhmrc.isttabletcrm.models.ServApp.DefaultResponse;
import com.emrhmrc.isttabletcrm.models.ServApp.DefaultResponse2;
import com.emrhmrc.isttabletcrm.models.ServApp.GetServFormById;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetById;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppIdRequest;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppListAll;
import com.emrhmrc.isttabletcrm.models.ServApp.ServiceAppIdRequest;
import com.emrhmrc.isttabletcrm.models.ServApp.UpdateServFormById;
import com.emrhmrc.isttabletcrm.models.ServApp.UpsertById;
import com.emrhmrc.isttabletcrm.models.User.EmailRequest;
import com.emrhmrc.isttabletcrm.models.User.UserForgotPassword;
import com.emrhmrc.isttabletcrm.models.User.UserIdRequest;
import com.emrhmrc.isttabletcrm.models.User.UserLogin;
import com.emrhmrc.isttabletcrm.models.User.UserRequest;
import com.emrhmrc.isttabletcrm.models.User.UserReset;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JsonApi {

    //OK
    @POST("User/Login")
    Call<UserLogin> userLogin(@Body UserRequest user);

    //OK
    @POST("User/ForgotPassWord")
    Call<UserForgotPassword> userForgotPassword(@Body EmailRequest email);

    //Fail from Server
    @POST("User/TokenCheck")
    Call<UserForgotPassword> userTokenCheck(@Query("Token") String token);

    //OK
    @POST("User/Reset")
    Call<UserReset> userReset(@Body UserRequest user);

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

    //OK
    @POST("Elevator/GetById")
    Call<ElevatorGetById> elevatorGetById(@Body ElevatorIdRequest elevatorId);

    //OK
    @POST("ServApp/ListAll")
    Call<ServAppListAll> servAppListAll(@Body UserIdRequest userId);

    //Not Even Tried
    @POST("ServApp/UpsertById")
    Call<DefaultResponse2> upsertById(@Body UpsertById upsertById);

    //OK
    @POST("ServApp/GetById")
    Call<ServAppGetById> servAppGetById(@Body ServAppIdRequest servAppId);

    //OK
    @POST("ServApp/CompleteById")
    Call<DefaultResponse> servAppCompleteById(@Body CompleteByIdRequest servAppCompleteById);

    //OK
    @POST("ServApp/GetServFormById")
    Call<GetServFormById> getServFormById(@Body ServiceAppIdRequest serviceAppId);

    //Fail from Server
    @POST("ServApp/UpdateServFormById")
    Call<DefaultResponse> updateServFormById(@Body UpdateServFormById updateServFormById);

    //Fail from Server
    @GET("Quote/GetById")
    Call<QuoteGetById> quoteGetById(@Query("QuoteId") String quoteId);


}
