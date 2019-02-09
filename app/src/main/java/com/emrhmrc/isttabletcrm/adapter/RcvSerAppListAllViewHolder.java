package com.emrhmrc.isttabletcrm.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.helper.Methodes;
import com.emrhmrc.isttabletcrm.models.ServApp.ServiceAppointments;

public class RcvSerAppListAllViewHolder extends BaseViewHolder<ServiceAppointments,
        OnItemClickListener<ServiceAppointments>> {

    private TextView txt_info, txt_start_date, txt_end_date, txt_oncelik, txt_count,
            txt_start_date_clock, txt_end_date_clock,txt_custumerid,txt_title;
    private ImageView img_state;

    public RcvSerAppListAllViewHolder(View itemView) {
        super(itemView);
        txt_info = itemView.findViewById(R.id.txt_info);
        txt_start_date = itemView.findViewById(R.id.txt_start_date);
        txt_end_date = itemView.findViewById(R.id.txt_end_date);
        txt_oncelik = itemView.findViewById(R.id.txt_oncelik);
        txt_count = itemView.findViewById(R.id.txt_count);
        txt_start_date_clock = itemView.findViewById(R.id.txt_start_date_clock);
        txt_end_date_clock = itemView.findViewById(R.id.txt_end_date_clock);
        txt_custumerid = itemView.findViewById(R.id.txt_custumerid);
        img_state = itemView.findViewById(R.id.img_state);
        txt_title = itemView.findViewById(R.id.txt_title);
    }

    @Override
    public void onBind(final ServiceAppointments item, final @Nullable
            OnItemClickListener<ServiceAppointments> listener) {

        if (listener != null) {
            txt_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(item);
                }
            });

            txt_info.setText(item.getSubject());
            txt_start_date.setText(Methodes.changeDateFormatToText(item.getScheduledStart()));
            txt_start_date_clock.setText(Methodes.changeDateFormatToClockText(item.getScheduledStart()));
            txt_end_date.setText(Methodes.changeDateFormatToText(item.getScheduledEnd()));
            txt_end_date_clock.setText(Methodes.changeDateFormatToClockText(item.getScheduledEnd()));
            txt_oncelik.setText(item.getPriortiyCode().getText());
            txt_custumerid.setText(item.getInv_CustomerId().getText());
            txt_title.setText(item.getInv_TypeCode().getText());
            switch (item.getPriortiyCode().getValue()) {

                case 0:
                    img_state.setImageResource(R.drawable.ic_az);
                    break;
                case 1:
                    img_state.setImageResource(R.drawable.ic_orta);
                    break;
                case 2:
                    img_state.setImageResource(R.drawable.ic_yuksek);
                    break;

            }
            txt_count.setText("" + (getAdapterPosition() + 1));

        }
    }
}
