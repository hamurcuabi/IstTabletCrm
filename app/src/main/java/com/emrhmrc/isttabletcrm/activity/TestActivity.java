package com.emrhmrc.isttabletcrm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.TestAdapter;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.models.Account.AccountListAll;
import com.emrhmrc.isttabletcrm.models.BreakDown.BreakDownTypeListAll;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorGetById;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorIdRequest;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorListAll;
import com.emrhmrc.isttabletcrm.models.Product.ProductListAll;
import com.emrhmrc.isttabletcrm.models.ServApp.CompleteByIdRequest;
import com.emrhmrc.isttabletcrm.models.ServApp.DefaultResponse;
import com.emrhmrc.isttabletcrm.models.ServApp.GetServFormById;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetById;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppIdRequest;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppListAll;
import com.emrhmrc.isttabletcrm.models.ServApp.ServiceAppIdRequest;
import com.emrhmrc.isttabletcrm.models.User.EmailRequest;
import com.emrhmrc.isttabletcrm.models.User.UserForgotPassword;
import com.emrhmrc.isttabletcrm.models.User.UserIdRequest;
import com.emrhmrc.isttabletcrm.models.User.UserLogin;
import com.emrhmrc.isttabletcrm.models.User.UserRequest;
import com.emrhmrc.isttabletcrm.models.User.UserReset;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TestActivity extends AppCompatActivity implements OnItemClickListener {

    private static final String TAG = "TestActivity";

    private JsonApi jsonApi;
    private RecyclerView rcw;
    private TestAdapter adapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main);

        init();

    }


    private void init() {
        jsonApi = ApiClient.getClient().create(JsonApi.class);
       // rcw = findViewById(R.id.rcw_test);
        rcw.setHasFixedSize(true);
        adapter = new TestAdapter(getApplicationContext());
        adapter.setListener(this);
        rcw.setAdapter(adapter);
        rcw.setLayoutManager(new LinearLayoutManager(this));
    }


    public void servAppCompleteById(View view) {

        Call<DefaultResponse> call = jsonApi.servAppCompleteById(new CompleteByIdRequest
                ("4a7f1a7c-1cc3-e811-8103-005056b66d80", "206b43b9-75bd-e811-8103-005056b66d80"));
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.isSuccessful()) {
                    DefaultResponse model = response.body();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });


    }

    public void userReset(View view) {
        Call<UserReset> call = jsonApi.userReset(new UserRequest("test@test.com", "1234567"));
        call.enqueue(new Callback<UserReset>() {
            @Override
            public void onResponse(Call<UserReset> call, Response<UserReset> response) {
                if (response.isSuccessful()) {
                    UserReset model = response.body();
                }
            }

            @Override
            public void onFailure(Call<UserReset> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void userForgotPassWord(View view) {

        Call<UserForgotPassword> call = jsonApi.userForgotPassword(new EmailRequest("test@test.com"));
        call.enqueue(new Callback<UserForgotPassword>() {
            @Override
            public void onResponse(Call<UserForgotPassword> call, Response<UserForgotPassword> response) {
                if (response.isSuccessful()) {

                    UserForgotPassword model = response.body();


                }
            }

            @Override
            public void onFailure(Call<UserForgotPassword> call, Throwable t) {

                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });


    }

    public void getServFormById(View view) {
        Call<GetServFormById> call = jsonApi.getServFormById(new ServiceAppIdRequest
                ("b7cf0efe-84c5-e811-8103-005056b66d80"));
        call.enqueue(new Callback<GetServFormById>() {
            @Override
            public void onResponse(Call<GetServFormById> call, Response<GetServFormById> response) {
                if (response.isSuccessful()) {

                    GetServFormById model = response.body();


                }
            }

            @Override
            public void onFailure(Call<GetServFormById> call, Throwable t) {

                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void getServAppById(View view) {
        Call<ServAppGetById> call = jsonApi.servAppGetById(new ServAppIdRequest("4a7f1a7c-1cc3-e811-8103-005056b66d80"));
        call.enqueue(new Callback<ServAppGetById>() {
            @Override
            public void onResponse(Call<ServAppGetById> call, Response<ServAppGetById> response) {
                if (response.isSuccessful()) {
                    ServAppGetById model = response.body();
                }
            }

            @Override
            public void onFailure(Call<ServAppGetById> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });


    }

    public void getElevatorById(View view) {

        Call<ElevatorGetById> call = jsonApi.elevatorGetById(new ElevatorIdRequest("063c38b7-4f8b-e811-80fc-005056b66d80"));
        call.enqueue(new Callback<ElevatorGetById>() {
            @Override
            public void onResponse(Call<ElevatorGetById> call, Response<ElevatorGetById> response) {
                if (response.isSuccessful()) {

                    ElevatorGetById model = response.body();

                }
            }

            @Override
            public void onFailure(Call<ElevatorGetById> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    public void login(View view) {

        Call<UserLogin> call = jsonApi.userLogin(new UserRequest("test@test.com", "1234567"));
        call.enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                if (response.isSuccessful()) {
                    UserLogin userLogin = response.body();
                    Log.d(TAG, "onResponse: " + userLogin.toString());
                }
            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void getProductListAll(View view) {

        Call<ProductListAll> call = jsonApi.productListAll();
        call.enqueue(new Callback<ProductListAll>() {
            @Override
            public void onResponse(Call<ProductListAll> call, Response<ProductListAll> response) {
                if (response.isSuccessful()) {
                    ProductListAll temp = response.body();
                    adapter.setItems(temp.getProducts());

                }

            }

            @Override
            public void onFailure(Call<ProductListAll> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());


            }
        });


    }

    public void getBreakdowTypeListAll(View view) {

        Call<BreakDownTypeListAll> call = jsonApi.breakDownTypeListAll();
        call.enqueue(new Callback<BreakDownTypeListAll>() {
            @Override
            public void onResponse(Call<BreakDownTypeListAll> call, Response<BreakDownTypeListAll> response) {
                if (response.isSuccessful()) {

                    BreakDownTypeListAll temp = response.body();



                }
            }

            @Override
            public void onFailure(Call<BreakDownTypeListAll> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });


    }

    public void getAccountListAll(View view) {

        Call<AccountListAll> call = jsonApi.accountListAll();
        call.enqueue(new Callback<AccountListAll>() {
            @Override
            public void onResponse(Call<AccountListAll> call, Response<AccountListAll> response) {
                if (response.isSuccessful()) {
                    AccountListAll temp = response.body();


                }
            }

            @Override
            public void onFailure(Call<AccountListAll> call, Throwable t) {

                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });


    }

    public void getEvelatorListAll(View view) {
        Call<ElevatorListAll> call = jsonApi.elevatorListAll();
        call.enqueue(new Callback<ElevatorListAll>() {
            @Override
            public void onResponse(Call<ElevatorListAll> call, Response<ElevatorListAll> response) {
                if (response.isSuccessful()) {

                    final ElevatorListAll temp = response.body();
                    // txt_json.setText("" + temp.getSuccess());


                }
            }

            @Override
            public void onFailure(Call<ElevatorListAll> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    public void getServAppListAll(View view) {
        UserIdRequest userIdRequest = new UserIdRequest("206b43b9-75bd-e811-8103-005056b66d80");
        Call<ServAppListAll> call = jsonApi.servAppListAll(userIdRequest);
        call.enqueue(new Callback<ServAppListAll>() {
            @Override
            public void onResponse(Call<ServAppListAll> call, Response<ServAppListAll> response) {
                if (response.isSuccessful()) {

                    final ServAppListAll temp = response.body();


                }
            }

            @Override
            public void onFailure(Call<ServAppListAll> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    @Override
    public void onItemClicked(Object item) {
        Toast.makeText(getApplicationContext(), item.toString(), Toast.LENGTH_SHORT).show();
    }
}