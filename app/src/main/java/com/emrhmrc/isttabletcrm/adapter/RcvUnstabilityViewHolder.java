package com.emrhmrc.isttabletcrm.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppUnsuitabilities;
import com.emrhmrc.isttabletcrm.util.StringUtil;

public class RcvUnstabilityViewHolder extends BaseViewHolder<ServAppGetByIdServAppUnsuitabilities,
        OnItemClickListener<ServAppGetByIdServAppUnsuitabilities>> {

    private TextView txt_baslik, txt_asansor_no, txt_tarih, txt_musteri_no;

    public RcvUnstabilityViewHolder(View itemView) {
        super(itemView);
        txt_baslik = itemView.findViewById(R.id.txt_baslik);
        txt_asansor_no = itemView.findViewById(R.id.txt_asansor_no);
        txt_tarih = itemView.findViewById(R.id.txt_tarih);
        txt_musteri_no = itemView.findViewById(R.id.txt_musteri_no);
    }

    @Override
    public void onBind(final ServAppGetByIdServAppUnsuitabilities item,
                       @Nullable final OnItemClickListener<ServAppGetByIdServAppUnsuitabilities> listener) {
        txt_baslik.setText(StringUtil.nullToString(item.getSubject()));
        txt_asansor_no.setText(StringUtil.nullToString(item.getDescription()));
        txt_tarih.setText(StringUtil.nullToString(item.getSentOn()==null ? "":item.getSentOn().toString()));

        if (listener != null) {
            txt_asansor_no.setOnClickListener(view -> listener.onItemClicked(item, getAdapterPosition()));

        }
    }


}

