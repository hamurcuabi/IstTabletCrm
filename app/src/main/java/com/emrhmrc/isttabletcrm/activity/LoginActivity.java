package com.emrhmrc.isttabletcrm.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
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
import com.emrhmrc.isttabletcrm.SweetDialog.AnyDialog;
import com.emrhmrc.isttabletcrm.SweetDialog.DialogCreater;
import com.emrhmrc.isttabletcrm.SweetDialog.SweetAlertDialog;
import com.emrhmrc.isttabletcrm.api.APIHelper;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.databinding.ActivityLoginBinding;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.helper.SharedPref;
import com.emrhmrc.isttabletcrm.helper.SingletonUser;
import com.emrhmrc.isttabletcrm.models.User.EmailRequest;
import com.emrhmrc.isttabletcrm.models.User.UserForgotPassword;
import com.emrhmrc.isttabletcrm.models.User.UserLogin;
import com.emrhmrc.isttabletcrm.models.User.UserRequest;
import com.emrhmrc.isttabletcrm.util.StringUtil;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    @BindView(R.id.edt_nick)
    TextInputEditText edt_nick;
    @BindView(R.id.til_nick)
    TextInputLayout til_nick;
    @BindView(R.id.edt_pass)
    TextInputEditText edt_pass;
    @BindView(R.id.til_pass)
    TextInputLayout til_pass;
    @BindView(R.id.cb_rememberme)
    CheckBox cb_rememberme;
    @BindView(R.id.txt_forgatpass)
    TextView txt_forgatpass;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindString(R.string.error_nick)
    String error_nick;
    @BindString(R.string.error_pass)
    String error_pass;
    @BindString(R.string.loading)
    String loading;
    @BindString(R.string.try_again)
    String try_again;
    private ActivityLoginBinding binding;
    private JsonApi jsonApi;
    private SharedPref pref;
    private SweetAlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        ButterKnife.bind(this);
        init();
        setTexts();
    }

    private void setTexts() {
        if (pref.getRememberMe()) {
            binding.setUserName(pref.getUserMail());
            binding.setUserPass(pref.getUserPass());

        }
        binding.setRemember(pref.getRememberMe());
    }

    private void init() {
        pref = new SharedPref(getApplicationContext());
        jsonApi = ApiClient.getClient().create(JsonApi.class);

    }

    private void initDialog() {
        AnyDialog anyDialog = new AnyDialog(this);
        dialog = anyDialog.loading(loading);
    }

    private void doLogin() {
        String mail = edt_nick.getText().toString();
        String pass = edt_pass.getText().toString();
        if (StringUtil.validateStrings(mail, pass)) {
            initDialog();
            dialog.show();
            rememberMe(mail, pass);
            UserRequest userRequest = new UserRequest(mail, pass);
            btn_login.setEnabled(false);
            Call<UserLogin> call = jsonApi.userLogin(userRequest);
            APIHelper.enqueueWithRetry(call, new Callback<UserLogin>() {
                @Override
                public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                    if (response.isSuccessful()) {
                        if (response.body().Success) {
                            UserLogin model = response.body();
                            goHome(model);
                        } else {
                            DialogCreater.errorDialog(LoginActivity.this, response.body().getErrorMsg());

                        }


                    } else {
                        DialogCreater.errorDialog(LoginActivity.this, try_again);

                    }
                    dialog.dismissWithAnimation();
                    btn_login.setEnabled(true);

                }

                @Override
                public void onFailure(Call<UserLogin> call, Throwable t) {
                    dialog.dismissWithAnimation();
                    btn_login.setEnabled(true);
                    DialogCreater.errorDialog(LoginActivity.this, try_again);


                }
            });
        } else {
            Log.d(TAG, "doLogin: Alanlar Boş");
        }
    }

    private void rememberMe(String mail, String pass) {

        if (cb_rememberme.isChecked()) {
            pref.setUserMail(mail);
            pref.setUserPass(pass);
            pref.setRememberMe(true);
        } else pref.setRememberMe(false);

    }

    private void goHome(UserLogin model) {
        btn_login.setEnabled(true);
        ShareData.getInstance().setUserId(model.getUserId());
        SingletonUser.getInstance().setUser(model);
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }

    private void forgatPass() {
        String mail = edt_nick.getText().toString();
        if (StringUtil.validateStrings(mail)) {
            Call<UserForgotPassword> call = jsonApi.userForgotPassword(new EmailRequest(mail));
            call.enqueue(new Callback<UserForgotPassword>() {
                @Override
                public void onResponse(Call<UserForgotPassword> call, Response<UserForgotPassword> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getSuccess()) {
                            UserForgotPassword model = response.body();
                            DialogCreater.succesDialog(LoginActivity.this);
                        } else {
                            DialogCreater.errorDialog(LoginActivity.this, response.body().getErrorMsg());
                        }

                    }
                }

                @Override
                public void onFailure(Call<UserForgotPassword> call, Throwable t) {
                    DialogCreater.errorDialog(LoginActivity.this, try_again);
                    Log.d(TAG, "onFailure: " + t.getMessage());
                }
            });
        } else {
            DialogCreater.warningDialog(LoginActivity.this, "Mail Alanı Boş");
        }

    }

    @OnClick({R.id.txt_forgatpass, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_forgatpass:
                forgatPass();
                break;
            case R.id.btn_login:
                doLogin();
                break;
        }
    }


}
