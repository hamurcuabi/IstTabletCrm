package com.emrhmrc.isttabletcrm.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.SweetDialog.AnyDialog;
import com.emrhmrc.isttabletcrm.SweetDialog.SweetAlertDialog;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.RcvServAppDetailAdapter;
import com.emrhmrc.isttabletcrm.adapter.SwipeToDelete;
import com.emrhmrc.isttabletcrm.api.APIHelper;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.bindingModel.ServiceAppointment;
import com.emrhmrc.isttabletcrm.databinding.ActivityServAppDetailBinding;
import com.emrhmrc.isttabletcrm.fragment.AddManuelProductFragment;
import com.emrhmrc.isttabletcrm.fragment.BeforeAfterPicFragment;
import com.emrhmrc.isttabletcrm.fragment.BreakdownTypeCodeFragment;
import com.emrhmrc.isttabletcrm.fragment.ControlListFragment;
import com.emrhmrc.isttabletcrm.fragment.DefaultMapFragment;
import com.emrhmrc.isttabletcrm.fragment.DetailServAppFormFragment;
import com.emrhmrc.isttabletcrm.fragment.ReasonAndAddenBreakdownFragment;
import com.emrhmrc.isttabletcrm.fragment.ReasonOfBreakdownFragment;
import com.emrhmrc.isttabletcrm.helper.AddManuelProduct;
import com.emrhmrc.isttabletcrm.helper.CreateSubServAppSingleton;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.helper.SingletonUser;
import com.emrhmrc.isttabletcrm.models.CommonClass.Inv_Id;
import com.emrhmrc.isttabletcrm.models.MapModel;
import com.emrhmrc.isttabletcrm.models.Product.Product;
import com.emrhmrc.isttabletcrm.models.ServApp.CompleteByIdRequest;
import com.emrhmrc.isttabletcrm.models.ServApp.DefaultResponse;
import com.emrhmrc.isttabletcrm.models.ServApp.DefaultResponse2;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetById;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdNotes;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppDetails;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppIdRequest;
import com.emrhmrc.isttabletcrm.models.ServApp.UpsertByIdRequest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServAppDetailActivity extends AppCompatActivity implements OnItemClickListener, AddManuelProduct {

    private static final String TAG = "ServAppDetailActivity";
    ActivityServAppDetailBinding binding;
    @BindView(R.id.rcv)
    RecyclerView rcv;
    @BindView(R.id.txt_aciklamanot)
    TextView txt_aciklamanot;
    @BindString(R.string.loading)
    String loading;
    @BindString(R.string.sure)
    String sure;
    @BindString(R.string.doit)
    String doit;
    @BindString(R.string.dont)
    String dont;
    @BindString(R.string.close_servapp)
    String close;
    @BindString(R.string.toast_error)
    String error;
    @BindString(R.string.succes)
    String succes;
    private JsonApi jsonApi;
    private RcvServAppDetailAdapter adapter;
    private ShareData shareData;
    private List<ServAppGetByIdNotes> notes;
    private SweetAlertDialog dialog;
    private ServAppGetById model;
    private boolean isOk;
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            Product product = (Product) intent.getSerializableExtra("product");
            String id = intent.getStringExtra("id");

            if (product != null) {
                Log.d(TAG, "Product not null");
                boolean exist = false;
                for (ServAppGetByIdServAppDetails details : adapter.getItems()
                ) {
                    if (details.getInv_ProductId().getText().equals(product.getProductId())) {
                        exist = true;
                    }

                }
                if (!exist) {
                    Log.d(TAG, "Product Not Exist ");
                    ServAppGetByIdServAppDetails add = new ServAppGetByIdServAppDetails();
                    add.setInv_ProductId(new Inv_Id("inv_subproductgroup", product.getName(), product.getProductId()));
                    add.setManuel(true);
                    adapter.add(add);
                }
            }

            if (id != null) {
                Log.d(TAG, "Id not null " + id);
                for (int k = 0; k < adapter.getItems().size(); k++) {

                    if (adapter.getItems().get(k).getInv_ProductId().getId().equals(id)) {
                        adapter.remove(k);
                    }

                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_serv_app_detail);
        ButterKnife.bind(this);
        init();
        getServAppById(shareData.getServAppId());
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-event-name"));
    }

    private void openServappFormFragment() {

        DetailServAppFormFragment fragment = DetailServAppFormFragment.newInstance(ShareData.getInstance().getServAppId());
        fragment.show(getSupportFragmentManager(), "DetailServAppFormFragment");
    }

    private void openBreakdownFormFragment() {

        BreakdownTypeCodeFragment fragment = BreakdownTypeCodeFragment.newInstance();
        fragment.show(getSupportFragmentManager(), "BreakdownTypeCodeFragment");
    }

    private void openAddManuelProductFragment() {

        AddManuelProductFragment fragment = AddManuelProductFragment.newInstance();
        fragment.show(getSupportFragmentManager(), "AddManuelProductFragment");
    }

    private void mapFragment() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x - 100;
        int height = size.y - 200;
        MapModel map = new MapModel(ShareData.getInstance().getLatitude(), ShareData
                .getInstance().getLongitude(),
                "Title",
                "Description");
        ArrayList<MapModel> mapModels = new ArrayList<>();
        mapModels.add(map);
        DefaultMapFragment fragment = DefaultMapFragment.newInstance(mapModels, width, height);
        fragment.show(getSupportFragmentManager(), "MpaFragment");
    }

    private void init() {
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        adapter = new RcvServAppDetailAdapter(getApplicationContext(), this);
        adapter.setListener(this);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(this));
        rcv.setAdapter(adapter);
        shareData = ShareData.getInstance();
        ItemTouchHelper itemTouchHelper = new
                ItemTouchHelper(new SwipeToDelete(adapter));
        itemTouchHelper.attachToRecyclerView(rcv);
        AnyDialog anyDialog = new AnyDialog(this);
        dialog = anyDialog.loading(loading);
    }

    private void getServAppById(String id) {
        dialog.show();
        Call<ServAppGetById> call = jsonApi.servAppGetById(new ServAppIdRequest(id));
        APIHelper.enqueueWithRetry(call, new Callback<ServAppGetById>() {
            @Override
            public void onResponse(Call<ServAppGetById> call, Response<ServAppGetById> response) {
                if (response.isSuccessful()) {
                    model = response.body();
                    CreateSubServAppSingleton.getInstance().setServAppGetById(model);
                    notes = new ArrayList<>();
                    notes = model.getServiceAppointment().getServAppGetByIdNotes();
                    if (notes.size() > 0)
                        txt_aciklamanot.setText(notes.get(notes.size() - 1).getNoteText());

                    setModelToBind(model.getServiceAppointment());
                    ShareData.getInstance().setLongitude(model.getServiceAppointment().getInv_Longitude());
                    ShareData.getInstance().setLatitude(model.getServiceAppointment().getInv_Latitude());
                    adapter.setItems(model.getServiceAppointment().getServAppGetByIdServAppDetails());
                }
                dialog.dismissWithAnimation();
            }

            @Override
            public void onFailure(Call<ServAppGetById> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                CreateSubServAppSingleton.getInstance().setServAppGetById(null);
                dialog.dismissWithAnimation();
            }
        });


    }

    private void setModelToBind(ServiceAppointment model) {
        binding.setModel(model);
        ShareData.getInstance().setElevatorId(model.getInv_ElevatorId().getId());
        Log.d(TAG, "setElevatorId: " + model.getInv_ElevatorId().getId());
        adapter.setItems(model.getServAppGetByIdServAppDetails());

    }

    private void cancelServApp() {

        CompleteByIdRequest request = new CompleteByIdRequest();
        request.setServiceAppId(ShareData.getInstance().getServAppId());
        request.setUserId(SingletonUser.getInstance().getUser().getUserId());
        request.setCompleteType(false);
        Call<DefaultResponse> call = jsonApi.servAppCompleteById(request);
        APIHelper.enqueueWithRetry(call, new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.isSuccessful()) {
                    new SweetAlertDialog(ServAppDetailActivity.this,
                            SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText(succes)
                            .setContentText("")
                            .show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                new SweetAlertDialog(ServAppDetailActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText(error)
                        .setContentText(t.getMessage())
                        .show();
            }
        });


    }

    private void closeServApp() {
        if (checkProductAmount()) {
            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText(sure)
                    .setContentText(close)
                    .setCancelText(dont)
                    .setConfirmText(doit)
                    .showCancelButton(true)
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            CompleteByIdRequest request = new CompleteByIdRequest();
                            request.setServiceAppId(ShareData.getInstance().getServAppId());
                            request.setUserId(SingletonUser.getInstance().getUser().getUserId());
                            request.setCompleteType(true);
                            Call<DefaultResponse> call = jsonApi.servAppCompleteById(request);
                            APIHelper.enqueueWithRetry(call, new Callback<DefaultResponse>() {
                                @Override
                                public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                                    sweetAlertDialog.dismissWithAnimation();
                                    if (response.isSuccessful()) {

                                    }
                                }

                                @Override
                                public void onFailure(Call<DefaultResponse> call, Throwable t) {
                                    sweetAlertDialog.dismissWithAnimation();

                                }
                            });
                        }
                    })
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.cancel();
                        }
                    })
                    .show();

        }


    }

    @Override
    public void onItemClicked(Object item, int positon) {

    }

    public void newServApp() {

        startActivity(new Intent(ServAppDetailActivity.this, CreateServAppActivity.class));
    }

    public void openControlList() {

        ControlListFragment fragment = ControlListFragment.newInstance();
        fragment.setCancelable(false);
        fragment.show(getSupportFragmentManager(), "KontrolListesi");

    }

    private void openAddPiece() {
        ShareData.getInstance().setAdd_sub_piece(true);
        startActivity(new Intent(ServAppDetailActivity.this, AddPieceActivity.class));
    }

    private void openElevatorDetail() {
        startActivity(new Intent(ServAppDetailActivity.this, ElevatorDetailActivity.class));
    }

    private void openBeforeAfter() {
        BeforeAfterPicFragment fragment = BeforeAfterPicFragment.newInstance();
        fragment.show(getSupportFragmentManager(), "beforeafter");
    }

    private void openReasonOfBreakdown() {
        ReasonOfBreakdownFragment fragment =
                ReasonOfBreakdownFragment.newInstance(new ArrayList<>(notes));
        fragment.show(getSupportFragmentManager(), "reasonbrekadown");
    }

    private void openReasonAndAddedBreakdown() {
        ReasonAndAddenBreakdownFragment fragment =
                ReasonAndAddenBreakdownFragment.newInstance();
        fragment.show(getSupportFragmentManager(), "ReasonAndAddenBreakdownFragment");
    }

    @OnClick({R.id.img_cancel, R.id.txt_cancel, R.id.btn_closejob, R.id.txt_yeni, R.id.img_yeni,
            R.id.img_add, R.id.txt_add, R.id.btn_beforeafter, R.id.txt_aciklamanot,
            R.id.txt_asansorno, R.id.txt_servis_raporu, R.id.img_servis_raporu, R.id.img_gps,
            R.id.img_menu, R.id.txt_kaydet, R.id.img_kaydet, R.id.btn_ariza_kodu,
            R.id.btn_ariza_nedeni, R.id.add_job_2, R.id.img_add_3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_cancel:
                cancelServApp();
                break;
            case R.id.txt_cancel:
                cancelServApp();
                break;
            case R.id.btn_beforeafter:
                openBeforeAfter();
                break;
            case R.id.img_gps:
                mapFragment();
                break;
            case R.id.txt_asansorno:
                openElevatorDetail();
                break;
            case R.id.add_job:
                openAddPiece();
                break;
            case R.id.btn_kontrol_listesi:
                openControlList();
                break;
            case R.id.txt_yeni:
                newServApp();
                break;
            case R.id.img_yeni:
                newServApp();
                break;
            case R.id.btn_closejob:
                closeServApp();
                break;
            case R.id.img_add:
                openAddPiece();
                break;
            case R.id.txt_add:
                openAddPiece();
                break;
            case R.id.txt_aciklamanot:
                openReasonOfBreakdown();
                break;
            case R.id.txt_servis_raporu:
                openServappFormFragment();
                break;
            case R.id.img_servis_raporu:
                openServappFormFragment();
                break;
            case R.id.btn_ariza_kodu:
                openBreakdownFormFragment();
                break;
            case R.id.img_menu:
                onBackPressed();
                break;
            case R.id.txt_kaydet:
                checkUpsertById();
                break;
            case R.id.img_kaydet:
                checkUpsertById();
                break;
            case R.id.btn_ariza_nedeni:
                openReasonAndAddedBreakdown();
                break;
            case R.id.add_job_2:
                openAddManuelProductFragment();
                break;
            case R.id.img_add_3:
                openAddManuelProductFragment();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        CreateSubServAppSingleton.getInstance().setServAppGetById(null);
        super.onBackPressed();
    }

    private void checkUpsertById() {
        isOk = false;
        if (checkProductAmount()) {

            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText(sure)
                    .setContentText(close)
                    .setCancelText(dont)
                    .setConfirmText(doit)
                    .showCancelButton(true)
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            isOk = true;
                            sweetAlertDialog.cancel();
                            doUpsert();
                        }
                    })
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.cancel();
                        }
                    })
                    .show();
        }

    }

    private void doUpsert() {
        UpsertByIdRequest request = new UpsertByIdRequest();
        List<String> changed = new ArrayList<>();
        changed.add("ServAppDetails");
        if (model != null)
            request.setServiceApp(model.getServiceAppointment());
        request.setServAppChangedFields(changed);
        request.setUserId(SingletonUser.getInstance().getUser().getUserId());
        request.setNotes(notes);
        request.setServAppDetails(adapter.getItems());
        Call<DefaultResponse2> call = jsonApi.upsertById(request);
        APIHelper.enqueueWithRetry(call, new Callback<DefaultResponse2>() {
            @Override
            public void onResponse(Call<DefaultResponse2> call, Response<DefaultResponse2> response) {
                if (response.isSuccessful()) {


                }
                Log.d(TAG, "ActvityonResponse: ");
            }

            @Override
            public void onFailure(Call<DefaultResponse2> call, Throwable t) {
                Log.d(TAG, "ActivtyonFailure: " + t.getMessage());
            }
        });
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        CreateSubServAppSingleton.getInstance().setServAppGetById(null);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    private void openDialog() {
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Hata...")
                .setContentText("Miktarları belirleyiniz !")
                .show();
    }

    private boolean checkProductAmount() {


        for (ServAppGetByIdServAppDetails details : adapter.getItems()
        ) {
            if (details.getInv_Quantity() == 0) {
                openDialog();
                return false;
            }

        }


        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            Bitmap photo = (Bitmap) data.getExtras().get("data");

            Uri selectedImage = getImageUri(this, photo);
            String realPath = getRealPathFromURI(selectedImage);
            selectedImage = Uri.parse(realPath);
            Log.d(TAG, "onActivityResult: " + selectedImage.toString());

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
            cursor = getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private void uploadFile(Uri fileUri) {

        String filePath = getRealPathFromURIPath(fileUri, this);
        File file = new File(filePath);
        Log.d(TAG, "Filename " + file.getName());
        //RequestBody mFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

    }

    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    @Override
    public void addProduct(ServAppGetByIdServAppDetails product) {
        adapter.add(product);

    }
}
