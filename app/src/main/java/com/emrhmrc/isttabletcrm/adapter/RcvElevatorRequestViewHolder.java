package com.emrhmrc.isttabletcrm.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.ServApp.ServiceAppointmentElevator;
import com.emrhmrc.isttabletcrm.util.StringUtil;

public class RcvElevatorRequestViewHolder extends BaseViewHolder<ServiceAppointmentElevator,
        OnItemClickListener<ServiceAppointmentElevator>> {

    private TextView txt_servistipi, txt_tarih, txt_ustaadi, txt_statu;
    private ImageView img_statu;


    public RcvElevatorRequestViewHolder(View itemView) {
        super(itemView);
        txt_servistipi = itemView.findViewById(R.id.txt_servistipi);
        img_statu = itemView.findViewById(R.id.img_statu);
        txt_tarih = itemView.findViewById(R.id.txt_tarih);
        txt_ustaadi = itemView.findViewById(R.id.txt_ustaadi);
        txt_statu = itemView.findViewById(R.id.txt_statu);


    }

    @Override
    public void onBind(final ServiceAppointmentElevator item,
                       @Nullable final OnItemClickListener<ServiceAppointmentElevator> listener) {

        if (item.getInv_TypeCode() != null) {
            txt_servistipi.setText(StringUtil.nullToString(item.getInv_TypeCode().getText()));
        }
        if (item.getOwnerId() != null) {
            txt_ustaadi.setText(item.getOwnerId().getText());
        }
        if (item.getStatusCode() != null) {
            txt_statu.setText(StringUtil.nullToString(item.getStatusCode().getText()));
            GlideBindingAdapters.setStatusTransfer(img_statu, item.getStatusCode().getValue());
        }
        txt_tarih.setText(StringUtil.nullToString(item.getScheduledStart()));


    }


}

