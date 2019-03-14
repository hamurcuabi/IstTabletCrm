package com.emrhmrc.isttabletcrm.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.Notification.Notification;
import com.emrhmrc.isttabletcrm.util.StringUtil;

public class RcvAnnoucementViewHolder extends BaseViewHolder<Notification,
        OnItemClickListener<Notification>> {

    private TextView txt_detail, txt_date, txt_supervisor;
    private LinearLayout lnr;

    public RcvAnnoucementViewHolder(View itemView) {
        super(itemView);
        txt_detail = itemView.findViewById(R.id.txt_detail);
        txt_date = itemView.findViewById(R.id.txt_date);
        txt_supervisor = itemView.findViewById(R.id.txt_supervisor);
        lnr = itemView.findViewById(R.id.lnr);
    }

    @Override
    public void onBind(final Notification item,
                       @Nullable final OnItemClickListener<Notification> listener) {
        txt_detail.setText(item.getSubject());
        txt_date.setText(item.getScheduledStart());
        if (item.getFrom() != null)
            txt_supervisor.setText(item.getFrom().getText());
        else txt_supervisor.setText(StringUtil.returnNull());

        if (listener != null) {
            txt_detail.setOnClickListener(view -> listener.onItemClicked(item, getAdapterPosition()));
            lnr.setOnClickListener(view -> listener.onItemClicked(item, getAdapterPosition()));

        }
    }


}

