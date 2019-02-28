package com.emrhmrc.isttabletcrm.fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.helper.SingletonUser;
import com.emrhmrc.isttabletcrm.models.ServApp.CreateUnsuitability;
import com.emrhmrc.isttabletcrm.models.ServApp.DefaultResponse2;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewUnstabilityFragment extends DialogFragment implements View.OnClickListener {

    private static final String TAG = "NewUnstabilityFragment";
    private ImageView img_close;
    private EditText edt_tarih;
    private Button btn_send;
    private JsonApi jsonApi;

    public static NewUnstabilityFragment newInstance() {
        Bundle args = new Bundle();
        NewUnstabilityFragment fragment = new NewUnstabilityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.newunstability_fragment, container);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        btn_send = view.findViewById(R.id.btn_send);
        img_close = view.findViewById(R.id.img_close);
        edt_tarih = view.findViewById(R.id.edt_tarih);
        btn_send.setOnClickListener(this);
        img_close.setOnClickListener(this);
        edt_tarih.setOnClickListener(this);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);

    }

    @Override
    public void onResume() {
        super.onResume();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = width / 2;
        params.height = height;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                send();
                break;
            case R.id.img_close:
                dismiss();
                break;
            case R.id.edt_tarih:
                openDatePicker();
                break;

        }

    }

    private void openDatePicker() {
        // Şimdiki zaman bilgilerini alıyoruz. güncel yıl, güncel ay, güncel gün.
        final Calendar takvim = Calendar.getInstance();
        int yil = takvim.get(Calendar.YEAR);
        int ay = takvim.get(Calendar.MONTH);
        int gun = takvim.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // ay değeri 0 dan başladığı için (Ocak=0, Şubat=1,..,Aralık=11)
                        // değeri 1 artırarak gösteriyoruz.
                        month += 1;
                        edt_tarih.setText(dayOfMonth + "." + month + "." + year);

                    }
                }, yil, ay, gun);
        dpd.setButton(DatePickerDialog.BUTTON_POSITIVE, "Seç", dpd);
        dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", dpd);
        dpd.show();
    }

    private void send() {
        CreateUnsuitability createUnsuitability = new CreateUnsuitability();
        createUnsuitability.setUserId(SingletonUser.getInstance().getUser().getUserId());
        createUnsuitability.setDescription("Test Description");
        createUnsuitability.setSentOn(Calendar.getInstance().getTime());
        createUnsuitability.setSubject("Test Subject");
        createUnsuitability.setServAppId(ShareData.getInstance().getServAppId());
        Call<DefaultResponse2> call = jsonApi.createUnsuitabilityCall(createUnsuitability);
        call.enqueue(new Callback<DefaultResponse2>() {
            @Override
            public void onResponse(Call<DefaultResponse2> call, Response<DefaultResponse2> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: Succes");
                } else Log.d(TAG, "onResponse: Fail");
            }

            @Override
            public void onFailure(Call<DefaultResponse2> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }
}
