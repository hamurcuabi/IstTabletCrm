package com.emrhmrc.isttabletcrm.fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.RcvControlListAdapter;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppModernizationChecklists;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ControlListFragment extends DialogFragment implements View.OnClickListener, OnItemClickListener {

    private ImageView img_close;
    private RcvControlListAdapter adapter;
    private RecyclerView rcv;
    private List<ServAppGetByIdServAppModernizationChecklists> source;

    public static ControlListFragment newInstance(List<ServAppGetByIdServAppModernizationChecklists> items) {
        Bundle args = new Bundle();
        ControlListFragment fragment = new ControlListFragment();
        args.putSerializable("items", (Serializable) items);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.control_list_frag, container);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);
        img_close = view.findViewById(R.id.img_close);
        rcv = view.findViewById(R.id.rcv);
        img_close.setOnClickListener(this);
        adapter = new RcvControlListAdapter(getActivity(), this::onItemClicked);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcv.setAdapter(adapter);
        source = (List<ServAppGetByIdServAppModernizationChecklists>) getArguments().getSerializable("items");
        adapter.setItems(source);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.img_close:
                this.dismiss();
                break;

        }
    }

    @Override
    public void onItemClicked(Object item, int positon) {
        ServAppGetByIdServAppModernizationChecklists model =
                (ServAppGetByIdServAppModernizationChecklists) item;
        openDatePicker(positon);

    }

    private void openDatePicker(int pos) {
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
                        // year, month ve dayOfMonth değerleri seçilen tarihin değerleridir.
                        // Edittextte bu değerleri gösteriyoruz.
                        String monthString = String.valueOf(month);
                        if (monthString.length() == 1) {
                            monthString = "0" + monthString;
                        }
                        DateFormat df = new SimpleDateFormat("HH:mm");
                        String date = df.format(Calendar.getInstance().getTime());
                        String text = monthString + "." + dayOfMonth + "." + year+" "+date;
                        adapter.getItems().get(pos).setInv_Date(text);
                        adapter.notifyItemChanged(pos);


                    }
                }, yil, ay, gun);
        dpd.getDatePicker().setMinDate(takvim.getTime().getTime());
        dpd.setButton(DatePickerDialog.BUTTON_POSITIVE, "Seç", dpd);
        dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", dpd);
        dpd.show();
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
        getDialog().getWindow().setAttributes((WindowManager.LayoutParams) params);
    }
}
