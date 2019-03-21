package com.emrhmrc.isttabletcrm.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.SweetDialog.SweetAlertDialog;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.RcvImageAdapter;
import com.emrhmrc.isttabletcrm.adapter.SwipeToDeleteVertical;
import com.emrhmrc.isttabletcrm.api.APIHelper;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.helper.SingletonUser;
import com.emrhmrc.isttabletcrm.models.Account.Account;
import com.emrhmrc.isttabletcrm.models.Account.AccountListAll;
import com.emrhmrc.isttabletcrm.models.Elevator.CustomerIdRequest;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorListAll;
import com.emrhmrc.isttabletcrm.models.Elevator.ElevatorsCustomer;
import com.emrhmrc.isttabletcrm.models.ServApp.CreateUnsuitability;
import com.emrhmrc.isttabletcrm.models.ServApp.DefaultResponse2;
import com.emrhmrc.isttabletcrm.models.ServApp.Notes;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static com.emrhmrc.isttabletcrm.helper.Methodes.checkAndRequestPermissions;

public class NewUnstabilityFragment extends DialogFragment implements View.OnClickListener,
        OnItemClickListener {

    private static final String TAG = "NewUnstabilityFragment";
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView img_close, img_add;
    private EditText edt_descp;
    private TextView edt_tarih;
    private Button btn_send;
    private JsonApi jsonApi;
    private AutoCompleteTextView spn_account, spnElevator;
    private RcvImageAdapter adapter;
    private RecyclerView rcv;
    private ProgressBar prog_account, prog_elevator;

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
        init(view);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);
        getAccountAll();
        focusing();

    }

    private void init(View view) {
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        btn_send = view.findViewById(R.id.btn_send);
        img_close = view.findViewById(R.id.img_close);
        edt_descp = view.findViewById(R.id.edt_descp);
        edt_tarih = view.findViewById(R.id.edt_tarih);
        spnElevator = view.findViewById(R.id.spnElevator);
        spn_account = view.findViewById(R.id.spn_account);
        img_add = view.findViewById(R.id.img_add);
        prog_account = view.findViewById(R.id.prog_account);
        prog_elevator = view.findViewById(R.id.prog_elevator);
        rcv = view.findViewById(R.id.rcv);
        btn_send.setOnClickListener(this);
        img_add.setOnClickListener(this);
        img_close.setOnClickListener(this);
        edt_tarih.setOnClickListener(this);
        if (getActivity() != null) {
            adapter = new RcvImageAdapter(getActivity(), this::onItemClicked);
            rcv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL
                    , false));
            ItemTouchHelper itemTouchHelper = new
                    ItemTouchHelper(new SwipeToDeleteVertical(adapter));
            itemTouchHelper.attachToRecyclerView(rcv);
            rcv.setAdapter(adapter);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = 3 * width / 5;
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
            case R.id.img_add:
                dispatchTakePictureIntent(REQUEST_IMAGE_CAPTURE);
                break;


        }

    }

    private void focusing() {
        spn_account.setOnFocusChangeListener((view, b) -> {
            if (b) spn_account.showDropDown();
            else spn_account.dismissDropDown();
        });
        spnElevator.setOnFocusChangeListener((view, b) -> {
            if (b) spnElevator.showDropDown();
            else spnElevator.dismissDropDown();
        });

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
                        String monthString = String.valueOf(month);
                        if (monthString.length() == 1) {
                            monthString = "0" + monthString;
                        }
                        edt_tarih.setText(dayOfMonth + "." + monthString + "." + year);

                    }
                }, yil, ay, gun);
        dpd.getDatePicker().setMinDate(takvim.getTime().getTime());
        dpd.setButton(DatePickerDialog.BUTTON_POSITIVE, "Seç", dpd);
        dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", dpd);
        dpd.show();
    }

    private void send() {
        CreateUnsuitability createUnsuitability = new CreateUnsuitability();
        createUnsuitability.setUserId(SingletonUser.getInstance().getUser().getUserId());
        createUnsuitability.setDescription(edt_descp.getText().toString());
        createUnsuitability.setSentOn(edt_tarih.getText().toString());
        createUnsuitability.setSubject("Test Subject");
        createUnsuitability.setUnsuitabilityNotes(adapter.getItems());
        if (checkFields(createUnsuitability)) {
            Call<DefaultResponse2> call = jsonApi.createUnsuitabilityCall(createUnsuitability);
            call.enqueue(new Callback<DefaultResponse2>() {
                @Override
                public void onResponse(Call<DefaultResponse2> call, Response<DefaultResponse2> response) {
                    if (response.isSuccessful()) {
                        dismiss();
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Başarılı")
                                .show();
                    } else Log.d(TAG, "onResponse: Fail" + response.message());
                }

                @Override
                public void onFailure(Call<DefaultResponse2> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                    if (getDialog() != null && getDialog().isShowing()) {
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                .setTitleText(getResources().getString(R.string.toast_error))
                                .show();
                    }
                }
            });
        } else {
            new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Eksik Alanları Doldurunuz")
                    .show();
        }

    }

    private boolean checkFields(CreateUnsuitability item) {
        if (item.getDescription() == null || item.getDescription().isEmpty()) return false;
        else if (item.getSentOn() == null || item.getSentOn().isEmpty()) return false;
        else if (item.getSubject() == null || item.getSubject().isEmpty()) return false;
        else if (item.getUnsuitabilityNotes() == null) return false;
        else if (item.getUserId() == null || item.getUserId().isEmpty()) return false;
        else return true;
    }

    private void getElevatorByCustomerAll(String id) {
        Log.d(TAG, "getElevatorByCustomerAll: " + id);
        prog_elevator.setVisibility(View.VISIBLE);
        CustomerIdRequest request = new CustomerIdRequest(id);
        Call<ElevatorListAll> call = jsonApi.elevatorGetByCustomerId(request);
        APIHelper.enqueueWithRetry(call, new Callback<ElevatorListAll>() {
            @Override
            public void onResponse(Call<ElevatorListAll> call, Response<ElevatorListAll> response) {
                if (response.isSuccessful()) {
                    ElevatorListAll listAll = response.body();
                    fillElevatorSpinner(listAll.getElevators());


                } else Log.d(TAG, "onResponse: ");
                prog_elevator.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ElevatorListAll> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                if (getDialog() != null && getDialog().isShowing()) {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(getResources().getString(R.string.toast_error))
                            .show();
                }
                prog_elevator.setVisibility(View.GONE);

            }
        });
    }

    private void fillElevatorSpinner(List<ElevatorsCustomer> list) {
        if (list.size() > 0 && list != null) {
            ArrayAdapter<ElevatorsCustomer> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    list);
            // spinnerArrayAdapter.setDropDownViewResource(android.R.layout
            // .simple_spinner_dropdown_item);
            spnElevator.setAdapter(spinnerArrayAdapter);
            spnElevator.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    spnElevator.showDropDown();
                }
            });
        } else {
            spnElevator.setAdapter(null);
            spnElevator.setOnClickListener(null);
        }

    }

    private void getAccountAll() {

        prog_account.setVisibility(View.VISIBLE);
        Call<AccountListAll> call = jsonApi.geAccountListAllCall();
        APIHelper.enqueueWithRetry(call, new Callback<AccountListAll>() {
            @Override
            public void onResponse(Call<AccountListAll> call, Response<AccountListAll> response) {
                if (response.isSuccessful()) {
                    AccountListAll listAll = response.body();
                    fillSpinner(listAll.getAccounts());

                } else Log.d(TAG, "onResponse: " + response.message());
                prog_account.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<AccountListAll> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                if (getDialog() != null && getDialog().isShowing()) {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(getResources().getString(R.string.toast_error))
                            .show();
                }
                prog_account.setVisibility(View.GONE);


            }
        });
    }

    private void fillSpinner(List<Account> list) {
        if (list.size() > 0 && list != null) {
            ArrayAdapter<Account> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    list);
            //spinnerArrayAdapter.setDropDownViewResource(android.R.layout
            // .simple_spinner_dropdown_item);
            spn_account.setAdapter(spinnerArrayAdapter);
            spn_account.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    spn_account.showDropDown();
                }
            });
            spn_account.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    getElevatorByCustomerAll(list.get(i).getAccountId());
                }
            });
        } else {
            spn_account.setAdapter(null);
            spn_account.setOnClickListener(null);
        }

    }

    private void dispatchTakePictureIntent(int i) {
        if (checkAndRequestPermissions(getActivity())) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
       /* if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, i);
        }*/
            this.startActivityForResult(takePictureIntent, i);
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Notes notes = new Notes();

            Bitmap photo = (Bitmap) data.getExtras().get("data");
            Uri selectedImage = getImageUri(getActivity(), photo);
            String realPath = getRealPathFromURI(selectedImage);
            selectedImage = Uri.parse(realPath);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            notes.setBitmap(photo);
            notes.setDocumentBody(Base64.encodeToString(byteArray, Base64.DEFAULT));
            notes.setDocument(true);
            notes.setFileName(realPath);
            notes.setMimeType("image/jpg");
            notes.setNoteText(null);
            notes.setSubject("Dosya Eki");
            adapter.add(notes);


        }
    }

    @Override
    public void onItemClicked(Object item, int positon) {

    }
}
