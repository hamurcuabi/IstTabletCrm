package com.emrhmrc.isttabletcrm.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.Notification.Notification;

public class RcvAnnoucementViewHolder extends BaseViewHolder<Notification,
        OnItemClickListener<Notification>> {

    private TextView txt_detail, txt_date;

    public RcvAnnoucementViewHolder(View itemView) {
        super(itemView);
        txt_detail = itemView.findViewById(R.id.txt_detail);
        txt_date = itemView.findViewById(R.id.txt_date);
    }

    @Override
    public void onBind(final Notification item, @Nullable final OnItemClickListener<Notification> listener) {
        txt_detail.setText(item.getDescription());
        txt_date.setText(item.getScheduledStart());

        if (listener != null) {
            txt_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(item);
                }
            });

        }
    }




}

