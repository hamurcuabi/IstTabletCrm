package com.emrhmrc.isttabletcrm.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.helper.Methodes;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppUnsuitabilities;
import com.emrhmrc.isttabletcrm.models.Unsuitablity.Unsuitabilities;
import com.emrhmrc.isttabletcrm.util.StringUtil;

public class RcvUnstabilityViewHolder extends BaseViewHolder<Unsuitabilities,
        OnItemClickListener<Unsuitabilities>> {

    private TextView txt_baslik, txt_asansor_no, txt_tarih, txt_musteri_no;

    public RcvUnstabilityViewHolder(View itemView) {
        super(itemView);
        txt_baslik = itemView.findViewById(R.id.txt_baslik);
        txt_asansor_no = itemView.findViewById(R.id.txt_asansor_no);
        txt_tarih = itemView.findViewById(R.id.txt_tarih);
        txt_musteri_no = itemView.findViewById(R.id.txt_musteri_no);
    }

    @Override
    public void onBind(final Unsuitabilities item,
                       @Nullable final OnItemClickListener<Unsuitabilities> listener) {
        txt_baslik.setText(StringUtil.nullToString(item.getSubject()));
        if(item.getElevatorId()!=null)
        txt_asansor_no.setText(StringUtil.nullToString(item.getElevatorId().getText()));
        else txt_asansor_no.setText(StringUtil.returnNull());
        if(item.getCustomerId()!=null)
        txt_musteri_no.setText(StringUtil.nullToString(item.getCustomerId().getText()));
        else txt_musteri_no.setText(StringUtil.returnNull());
        txt_tarih.setText(Methodes.changeDateFormatToText(StringUtil.nullToString(item.getScheduledStart())));


        if (listener != null) {
            txt_asansor_no.setOnClickListener(view -> listener.onItemClicked(item, getAdapterPosition()));

        }
    }


}

