package com.emrhmrc.isttabletcrm.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.SweetDialog.DialogCreater;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.RcvImageAdapter;
import com.emrhmrc.isttabletcrm.adapter.SwipeToDeleteVertical;
import com.emrhmrc.isttabletcrm.api.APIHelper;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.helper.Methodes;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.helper.SingletonUser;
import com.emrhmrc.isttabletcrm.helper.ViewDialog;
import com.emrhmrc.isttabletcrm.models.ServApp.CreateUnsuitability;
import com.emrhmrc.isttabletcrm.models.ServApp.CustomerIdRequestId;
import com.emrhmrc.isttabletcrm.models.ServApp.DefaultResponse2;
import com.emrhmrc.isttabletcrm.models.ServApp.ElevatorIdRequestId;
import com.emrhmrc.isttabletcrm.models.ServApp.Notes;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetById;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static com.emrhmrc.isttabletcrm.helper.Methodes.checkAndRequestPermissions;

public class NewUnstabilityServAppFragment extends DialogFragment implements View.OnClickListener,
        OnItemClickListener {

    private static final String TAG = "NewUnstabilityServAppFr";
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
    private ViewDialog viewDialog;
    private Call<DefaultResponse2> createCall;

    private ServAppGetById servAppGetById;
    private CreateUnsuitability createUnsuitability;
    private String try_again = "Beklenmedik Bir Hata Oluştu, Lütfen Tekrar Deneyiniz";

    public static NewUnstabilityServAppFragment newInstance(ServAppGetById servAppGetById) {
        Bundle args = new Bundle();
        args.putSerializable("servAppGetById", servAppGetById);
        NewUnstabilityServAppFragment fragment = new NewUnstabilityServAppFragment();
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
        servAppGetById = (ServAppGetById) getArguments().getSerializable("servAppGetById");
        fillFields();

    }

    private void fillFields() {
        spn_account.setEnabled(false);
        spnElevator.setEnabled(false);
        spn_account.setText(servAppGetById.getServiceAppointment().getInv_CustomerId().getText());
        spnElevator.setText(servAppGetById.getServiceAppointment().getInv_ElevatorId().getText());
        createUnsuitability.setElevatorId(new ElevatorIdRequestId(servAppGetById.getServiceAppointment().getInv_ElevatorId().getId()));
        createUnsuitability.setCustomerId(new CustomerIdRequestId(servAppGetById.getServiceAppointment().getInv_CustomerId().getId()));
    }

    private void init(View view) {
        viewDialog = new ViewDialog(getActivity());
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
        createUnsuitability = new CreateUnsuitability();
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
        spn_account.setOnClickListener(view -> spn_account.showDropDown());
        spnElevator.setOnClickListener(view -> spnElevator.showDropDown());

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

                        edt_tarih.setText(dayOfMonth + "." + monthString + "." + year + " " + Methodes.getCurrentClock());

                    }
                }, yil, ay, gun);
        dpd.getDatePicker().setMinDate(takvim.getTime().getTime());
        dpd.setButton(DatePickerDialog.BUTTON_POSITIVE, "Seç", dpd);
        dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", dpd);
        dpd.show();
    }

    private void send() {

        createUnsuitability.setUserId(SingletonUser.getInstance().getUser().getUserId());
        createUnsuitability.setDescription(edt_descp.getText().toString());
        createUnsuitability.setSentOn(edt_tarih.getText().toString());
        createUnsuitability.setServAppId(ShareData.getInstance().getServAppId());
        createUnsuitability.setSubject("From ServApp");
        createUnsuitability.setUnsuitabilityNotes(adapter.getItems());
        if (checkFields(createUnsuitability)) {
            viewDialog.showDialog();
            createCall = jsonApi.createUnsuitabilityCall(createUnsuitability);
            APIHelper.enqueueWithRetry(createCall, new Callback<DefaultResponse2>() {
                @Override
                public void onResponse(Call<DefaultResponse2> call, Response<DefaultResponse2> response) {
                    if (response.isSuccessful()) {
                        if (response.body().isSucces()) {
                            dismiss();
                            DialogCreater.succesDialog(getActivity());
                        } else {
                            DialogCreater.errorDialog(getActivity(), response.body().getErrorMsg());
                        }

                    } else {

                        DialogCreater.errorDialog(getActivity(), try_again);
                    }
                    viewDialog.hideDialog();
                }

                @Override
                public void onFailure(Call<DefaultResponse2> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                    viewDialog.hideDialog();
                    if (getDialog() != null && getDialog().isShowing()) {
                        DialogCreater.errorDialog(getActivity(), try_again);
                    }
                }
            });
        } else {
            if (getDialog() != null && getDialog().isShowing()) {
                DialogCreater.errorDialog(getActivity(), "Eksik Alanları Doldurunuz");
            }

        }

    }

    private boolean checkFields(CreateUnsuitability item) {
        if (item.getDescription() == null || item.getDescription().isEmpty()) return false;
        else if (item.getSentOn() == null || item.getSentOn().isEmpty()) return false;
        else if (item.getSubject() == null || item.getSubject().isEmpty()) return false;
        else if (item.getUnsuitabilityNotes() == null) return false;
        else if (item.getUserId() == null || item.getUserId().isEmpty()) return false;
        else if (item.getCustomerId() == null) return false;
        else if (item.getElevatorId() == null) return false;
        else return true;
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
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Uri selectedImage = getImageUri(getActivity(), photo);
            String realPath = getRealPathFromURI(selectedImage);
            selectedImage = Uri.parse(realPath);

            /*ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();*/

            Bitmap bitmap = BitmapFactory.decodeFile(realPath, options);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            notes.setSelectedImageUri(selectedImage);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (createCall != null) createCall.cancel();

    }
}
