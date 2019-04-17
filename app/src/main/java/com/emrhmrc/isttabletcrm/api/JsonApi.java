package com.emrhmrc.isttabletcrm.api;


import com.emrhmrc.isttabletcrm.models.Account.AccountListAll;
import com.emrhmrc.isttabletcrm.models.BreakDown.BreakDownTypeListAll;
import com.emrhmrc.isttabletcrm.models.BreakDown.BreakdownCodeGetByFilter;
import com.emrhmrc.isttabletcrm.models.BreakDown.BreakdownDefCodeListAll;
import com.emrhmrc.isttabletcrm.models.BreakDown.BreakdownDefCodeRequest;
import com.emrhmrc.isttabletcrm.models.BreakDown.BreakdownFilterRequest;
import com.emrhmrc.isttabletcrm.models.CommonClass.NotificationCountResponse;
import com.emrhmrc.isttabletcrm.models.CommonClass.ServAppCountResponse;
import com.emrhmrc.isttabletcrm.models.CommonClass.UomListAll;
import com.emrhmrc.isttabletcrm.models.Document.TechnicalDocument;
import com.emrhmrc.isttabletcrm.models.Elevator.CustomerIdRequest;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorChangingPart;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorGetById;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorIdRequest;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorListAll;
import com.emrhmrc.isttabletcrm.models.Elevator.PeriodicalProductListAll;
import com.emrhmrc.isttabletcrm.models.Notification.GetByIdWithSurvey;
import com.emrhmrc.isttabletcrm.models.Notification.NotificationIdRequest;
import com.emrhmrc.isttabletcrm.models.Notification.NotificationListAll;
import com.emrhmrc.isttabletcrm.models.Notification.SurveyUpdateById;
import com.emrhmrc.isttabletcrm.models.Product.MainProductList;
import com.emrhmrc.isttabletcrm.models.Product.ProductListAll;
import com.emrhmrc.isttabletcrm.models.Product.SubGroupProductsRequest;
import com.emrhmrc.isttabletcrm.models.Product.SubGroupRequest;
import com.emrhmrc.isttabletcrm.models.Product.SubProductGroupIdRequest;
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
import com.emrhmrc.isttabletcrm.models.ServApp.ServappCheckinRequest;
import com.emrhmrc.isttabletcrm.models.ServApp.ServappGetByElevatorId;
import com.emrhmrc.isttabletcrm.models.ServApp.ServappSendToSuperVisorRequest;
import com.emrhmrc.isttabletcrm.models.ServApp.ServiceAppIdRequest;
import com.emrhmrc.isttabletcrm.models.ServApp.UpdateServFormById;
import com.emrhmrc.isttabletcrm.models.ServApp.UpsertByIdCreateRequest;
import com.emrhmrc.isttabletcrm.models.ServApp.UpsertByIdUpdateRequest;
import com.emrhmrc.isttabletcrm.models.Unsuitablity.UnsuitabilityListAll;
import com.emrhmrc.isttabletcrm.models.User.EmailRequest;
import com.emrhmrc.isttabletcrm.models.User.UserForgotPassword;
import com.emrhmrc.isttabletcrm.models.User.UserIdRequest;
import com.emrhmrc.isttabletcrm.models.User.UserLogin;
import com.emrhmrc.isttabletcrm.models.User.UserRequest;
import com.emrhmrc.isttabletcrm.models.User.UserReset;
import com.emrhmrc.isttabletcrm.models.Warehouse.WareHouseListAll;
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
    @POST("Product/ListAll")
    Call<ProductListAll> productListAll(@Body UserIdRequest request);


    //OK
    @POST("Product/ListAll")
    Call<ProductListAll> productListAllNoParam();

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
    @POST("Elevator/GetByCustomerId")
    Call<ElevatorListAll> elevatorGetByCustomerId(@Body CustomerIdRequest customerIdRequest);

    //OK
    @POST("ServApp/ListAll")
    Call<ServAppListAll> servAppListAll(@Body UserIdRequest userId);

    //Not Even Tried :D
    @POST("ServApp/UpsertById")
    Call<DefaultResponse2> upsertById(@Body UpsertByIdCreateRequest upsertById);

    //OK
    @POST("ServApp/GetById")
    Call<ServAppGetById> servAppGetById(@Body ServAppIdRequest servAppId);

    //OK
    @POST("BreakdownDefCode/ListAll")
    Call<BreakdownDefCodeListAll> breakdownDefCodeListAllCall(@Body BreakdownDefCodeRequest request);

    //OK
    @POST("BreakdownCode/GetByFilter")
    Call<BreakdownCodeGetByFilter> geBreakdownCodeGetByFilterCall(@Body BreakdownFilterRequest servAppId);

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
    @POST("BreakdownDefCode/ListAll")
    Call<BreakdownDefCodeListAll> defListAllCall(@Body SubProductGroupIdRequest request);

    //OK
    @GET("Account/ListAll")
    Call<AccountListAll> geAccountListAllCall();

    //OK
    @GET("Uom/ListAll")
    Call<UomListAll> getUomListAllCall();

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

    @POST("Warehouse/ListAll")
    Call<WareHouseListAll> getWareHouseListAllCall(@Body UserIdRequest userId);

    @POST("WarehouseItem/ListAll")
    Call<WarehouseItemListAll> getWarehouseItemListAllCall(@Body UserIdRequest userId);

    @POST("WarehouseTransfer/ListAll")
    Call<WarehouseTransferListAll> getWarehouseTransferListAllCall(@Body UserIdRequest userId);

    @POST("WarehouseTransfer/Create")
    Call<DefaultResponse> createTransfer(@Body WarehouseTransferCreateRequest request);

    @POST("ElevatorChangingPart/ListAll")
    Call<ElevatorChangingPart> getElevatorChangingPartCall(@Body ElevatorIdRequest request);

    @POST("ServApp/UpsertById")
    Call<DefaultResponse2> createServapp(@Body UpsertByIdCreateRequest request);

    @POST("ServApp/UpsertById")
    Call<DefaultResponse2> updateServapp(@Body UpsertByIdUpdateRequest request);

    @POST("ServApp/GetByElevatorId")
    Call<ServappGetByElevatorId> getServappGetByElevatorIdCall(@Body ElevatorIdRequest request);

    @POST("ServApp/SendToSuperVisor")
    Call<DefaultResponse2> sendToSupervisor(@Body ServappSendToSuperVisorRequest request);

    @POST("ServApp/CheckIn")
    Call<DefaultResponse> checkInCall(@Body ServappCheckinRequest request);

    @POST("PeriodicalProduct/ListAll")
    Call<PeriodicalProductListAll> periodicalProductListAllCall(@Body ElevatorIdRequest request);


}
