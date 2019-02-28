package com.emrhmrc.isttabletcrm.api;


import com.emrhmrc.isttabletcrm.models.Account.AccountListAll;
import com.emrhmrc.isttabletcrm.models.BreakDown.BreakDownTypeListAll;
import com.emrhmrc.isttabletcrm.models.CommonClass.NotificationCountResponse;
import com.emrhmrc.isttabletcrm.models.CommonClass.ServAppCountResponse;
import com.emrhmrc.isttabletcrm.models.Document.TechnicalDocument;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorGetById;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorIdRequest;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorListAll;
import com.emrhmrc.isttabletcrm.models.Notification.GetByIdWithSurvey;
import com.emrhmrc.isttabletcrm.models.Notification.NotificationIdRequest;
import com.emrhmrc.isttabletcrm.models.Notification.NotificationListAll;
import com.emrhmrc.isttabletcrm.models.Notification.SurveyUpdateById;
import com.emrhmrc.isttabletcrm.models.Product.MainProductList;
import com.emrhmrc.isttabletcrm.models.Product.ProductListAll;
import com.emrhmrc.isttabletcrm.models.Product.SubGroupProductsRequest;
import com.emrhmrc.isttabletcrm.models.Product.SubGroupRequest;
import com.emrhmrc.isttabletcrm.models.Product.SubProductList;
import com.emrhmrc.isttabletcrm.models.Quote.QuoteGetById;
import com.emrhmrc.isttabletcrm.models.ServApp.CompleteByIdRequest;
import com.emrhmrc.isttabletcrm.models.ServApp.CreateUnsuitability;
import com.emrhmrc.isttabletcrm.models.ServApp.DefaultResponse;
import com.emrhmrc.isttabletcrm.models.ServApp.DefaultResponse2;
import com.emrhmrc.isttabletcrm.models.ServApp.GetServFormById;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetById;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppIdRequest;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppListAll;
import com.emrhmrc.isttabletcrm.models.ServApp.ServiceAppIdRequest;
import com.emrhmrc.isttabletcrm.models.ServApp.UpdateServFormById;
import com.emrhmrc.isttabletcrm.models.ServApp.UpsertByIdRequest;
import com.emrhmrc.isttabletcrm.models.Unsuitablity.UnsuitabilityListAll;
import com.emrhmrc.isttabletcrm.models.User.EmailRequest;
import com.emrhmrc.isttabletcrm.models.User.UserForgotPassword;
import com.emrhmrc.isttabletcrm.models.User.UserIdRequest;
import com.emrhmrc.isttabletcrm.models.User.UserLogin;
import com.emrhmrc.isttabletcrm.models.User.UserRequest;
import com.emrhmrc.isttabletcrm.models.User.UserReset;
import com.emrhmrc.isttabletcrm.models.Warehouse.WarehouseItemListAll;
import com.emrhmrc.isttabletcrm.models.Warehouse.WarehouseTransferCreateRequest;
import com.emrhmrc.isttabletcrm.models.Warehouse.WarehouseTransferListAll;

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
    @POST("Product/ListAll")
    Call<ProductListAll> productListAll(@Body SubGroupProductsRequest request);

    //OK
    @GET("MainProductGroup/ListAll")
    Call<MainProductList> getMainProductListCall();

    //OK
    @POST("SubProductGroup/ListAllByParentId")
    Call<SubProductList> getSubProductListCall(@Body SubGroupRequest subGroupRequest);

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
    @POST("ServApp/UpsertByIdRequest")
    Call<DefaultResponse2> upsertById(@Body UpsertByIdRequest upsertById);

    //OK
    @POST("ServApp/GetById")
    Call<ServAppGetById> servAppGetById(@Body ServAppIdRequest servAppId);

    //OK
    @POST("ServApp/CompleteById")
    Call<DefaultResponse> servAppCompleteById(@Body CompleteByIdRequest servAppCompleteById);

    //OK
    @POST("ServApp/GetServFormById")
    Call<GetServFormById> getServFormById(@Body ServiceAppIdRequest serviceAppId);

    @POST("ServApp/CreateUnsuitability")
    Call<DefaultResponse2> createUnsuitabilityCall(@Body CreateUnsuitability createUnsuitability);

    //OK
    @POST("Notification/ListAll")
    Call<NotificationListAll> getNotificationListAll(@Body UserIdRequest userId);

    //OK
    @GET("Account/ListAll")
    Call<AccountListAll> geAccountListAllCall();

    //OK
    @POST("Notification/SurveyUpdateById")
    Call<SurveyUpdateById> updateSurveyById(@Body SurveyUpdateById surveyUpdateById);


    @POST("Notification/GetByIdWithSurvey")
    Call<GetByIdWithSurvey> getByIdWithSurvey(@Body UserIdRequest userId, @Body
            NotificationIdRequest notificationIdRequest);

    //Fail from Server
    @POST("ServApp/UpdateServFormById")
    Call<DefaultResponse> updateServFormById(@Body UpdateServFormById updateServFormById);

    //Fail from Server
    @GET("Quote/GetById")
    Call<QuoteGetById> quoteGetById(@Query("QuoteId") String quoteId);

    @GET("TechnicalDocument/ListAll")
    Call<TechnicalDocument> getTechnicalDocumentAll();

    @POST("ServApp/GetCount")
    Call<ServAppCountResponse> getServappCount(@Body UserIdRequest userIdRequest);

    @POST("Notification/GetCount")
    Call<NotificationCountResponse> getNotifCount(@Body UserIdRequest userIdRequest);

    @POST("Unsuitability/ListAll")
    Call<UnsuitabilityListAll> getUnsuitabilityListAllCall(@Body UserIdRequest userId);

    @POST("WarehouseItem/ListAll")
    Call<WarehouseItemListAll> getWarehouseItemListAllCall(@Body UserIdRequest userId);

    @POST("WarehouseTransfer/ListAll")
    Call<WarehouseTransferListAll> getWarehouseTransferListAllCall(@Body UserIdRequest userId);

    @POST("WarehouseTransfer/Create")
    Call<DefaultResponse> createTransfer(@Body WarehouseTransferCreateRequest request);


}
