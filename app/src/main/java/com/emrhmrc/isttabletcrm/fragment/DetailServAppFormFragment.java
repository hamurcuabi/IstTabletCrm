package com.emrhmrc.isttabletcrm.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.SweetDialog.DialogCreater;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.models.ServApp.GetServFormById;
import com.emrhmrc.isttabletcrm.models.ServApp.ServiceAppIdRequest;
import com.emrhmrc.isttabletcrm.util.StringUtil;
import com.joanzapata.pdfview.PDFView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindString;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailServAppFormFragment extends DialogFragment implements View.OnClickListener {
    private static final String TAG = "DetailTechnicalFragment";
    @BindString(R.string.aciklama)
    String header;
    @BindString(R.string.try_again)
    String try_again;
    private ProgressBar prog;
    private ImageView img_close;
    private TextView txt_header, txt_content;
    private GetServFormById document;
    private JsonApi jsonApi;
    private String id;
    private PDFView pdfView;
    private Call<GetServFormById> getServFormByIdCall;

    public static DetailServAppFormFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString("id", id);
        DetailServAppFormFragment fragment = new DetailServAppFormFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_servapp_form_fragment, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        img_close = view.findViewById(R.id.img_close);
        prog = view.findViewById(R.id.prog);
        img_close.setOnClickListener(this);
        txt_header = view.findViewById(R.id.txt_header);
        txt_content = view.findViewById(R.id.txt_content);
        pdfView = view.findViewById(R.id.pdfview);
        id = getArguments().getString("id");
        Log.d(TAG, "onViewCreated: " + id);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);
        jsonApi = ApiClient.getClient().create(JsonApi.class);
        getServFormByIdCall = jsonApi.getServFormById(new ServiceAppIdRequest(id));
        getServFormByIdCall.enqueue(new Callback<GetServFormById>() {
            @Override
            public void onResponse(Call<GetServFormById> call, Response<GetServFormById> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess()) {
                        document = response.body();
                        if (document.getMimeType().equals(StringUtil.PDF)) {
                            pdfView.setVisibility(View.VISIBLE);
                            txt_content.setVisibility(View.GONE);
                            final File dwldsPath =
                                    new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + "detay.pdf");
                            byte[] pdfAsBytes = Base64.decode(document.getDocumentBody(), 0);
                            FileOutputStream os = null;
                            try {
                                os = new FileOutputStream(dwldsPath, false);
                                os.write(pdfAsBytes);
                                os.flush();
                                os.close();
                            } catch (IOException e) {
                                Log.d(TAG, "Create File Error " + e.getMessage());
                                e.printStackTrace();
                            }
                            String filename = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) +
                                    "/" + "detay.pdf";
                            File file = new File(filename);
                            txt_content.setVisibility(View.GONE);
                            if (file.exists()) {
                                pdfView.fromFile(file)
                                        .defaultPage(1)
                                        .enableSwipe(true)
                                        .showMinimap(true)
                                        .load();
                            }
                        } else {
                            pdfView.setVisibility(View.GONE);
                            txt_content.setVisibility(View.VISIBLE);
                            txt_content.setText(StringUtil.base64ToString(StringUtil.nullToString(document
                                    .getDocumentBody())));
                        }
                    } else {
                        DialogCreater.errorDialog(getActivity(), response.body().getErrorMsg());
                    }
                } else {
                    DialogCreater.errorDialog(getActivity(), try_again);
                }

                prog.setVisibility(View.GONE);

            }


            @Override
            public void onFailure(Call<GetServFormById> call, Throwable t) {
                DialogCreater.errorDialog(getActivity(), try_again);
                prog.setVisibility(View.GONE);
                txt_content.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = 9 * width / 10;
        params.height = 9 * height / 10;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_close:
                dismiss();
            default:
                ;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getServFormByIdCall != null) getServFormByIdCall.cancel();
    }
}
