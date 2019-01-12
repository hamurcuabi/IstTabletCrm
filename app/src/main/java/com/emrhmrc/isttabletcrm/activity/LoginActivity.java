package com.emrhmrc.isttabletcrm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.helper.Methodes;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.helper.SharedPref;
import com.emrhmrc.isttabletcrm.models.User.EmailRequest;
import com.emrhmrc.isttabletcrm.models.User.UserForgotPassword;
import com.emrhmrc.isttabletcrm.models.User.UserLogin;
import com.emrhmrc.isttabletcrm.models.User.UserRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";
    private JsonApi jsonApi;
    private TextInputEditText edt_nick, edt_pass;
    private TextInputLayout til_nick, til_pass;
    private TextView txt_forgatpass;
    private Button btn_login;
    private CheckBox cb_rememberme;
    private SharedPref pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        setTexts();
    }

    private void setTexts() {
        edt_nick.setText(pref.getUserMail());
        edt_pass.setText(pref.getUserPass());

    }

    private void init() {
        pref = new SharedPref(getApplicationContext());
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        edt_nick = findViewById(R.id.edt_nick);
        edt_pass = findViewById(R.id.edt_pass);
        til_nick = findViewById(R.id.til_nick);
        til_pass = findViewById(R.id.til_pass);
        txt_forgatpass = findViewById(R.id.txt_forgatpass);
        btn_login = findViewById(R.id.btn_login);
        cb_rememberme = findViewById(R.id.cb_rememberme);
        btn_login.setOnClickListener(this);
        txt_forgatpass.setOnClickListener(this);

    }

    private void doLogin() {
        btn_login.setEnabled(false);
        String mail = edt_nick.getText().toString();
        String pass = edt_pass.getText().toString();

        if (!Methodes.validateInput(mail)) {
            til_nick.setError(getResources().getString(R.string.error_nick));
            btn_login.setEnabled(true);
        } else if (!Methodes.validateInput(pass)) {
            til_pass.setError(getResources().getString(R.string.error_pass));
            btn_login.setEnabled(true);
        } else {
            rememberMe(mail, pass);
            UserRequest userRequest = new UserRequest(mail, pass);
            Call<UserLogin> call = jsonApi.userLogin(userRequest);
            call.enqueue(new Callback<UserLogin>() {
                @Override
                public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                    if (response.isSuccessful()) {
                        UserLogin model = response.body();
                        ShareData.getInstance().setUserId(model.getUserId());
                        goHome();
                    } else {
                        btn_login.setEnabled(true);
                    }
                }

                @Override
                public void onFailure(Call<UserLogin> call, Throwable t) {

                    Log.d(TAG, "onFailure: " + t.getMessage());

                }
            });


        }
    }

    private void rememberMe(String mail, String pass) {

        if (cb_rememberme.isChecked()) {
            pref.setUserMail(mail);
            pref.setUserPass(pass);
        }

    }


    private void goHome() {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.txt_forgatpass:
                //Henüz Kullanılmadı
                forgatPass("test@test.com");
                break;
            case R.id.btn_login:
                doLogin();
                break;


        }
    }

    private void forgatPass(String mail) {

        Call<UserForgotPassword> call = jsonApi.userForgotPassword(new EmailRequest(mail));
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
}
