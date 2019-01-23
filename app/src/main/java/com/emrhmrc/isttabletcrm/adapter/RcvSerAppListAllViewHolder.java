package com.emrhmrc.isttabletcrm.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.ServApp.ServiceAppointments;

public class RcvSerAppListAllViewHolder extends BaseViewHolder<ServiceAppointments,
        OnItemClickListener<ServiceAppointments>> {

    private TextView txt_info, txt_start_date, txt_end_date, txt_oncelik, txt_count;

    public RcvSerAppListAllViewHolder(View itemView) {
        super(itemView);
        txt_info = itemView.findViewById(R.id.txt_info);
        txt_start_date = itemView.findViewById(R.id.txt_start_date);
        txt_end_date = itemView.findViewById(R.id.txt_end_date);
        txt_oncelik = itemView.findViewById(R.id.txt_oncelik);
        txt_count = itemView.findViewById(R.id.txt_count);
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
            txt_start_date.setText(item.getActualStart());
            txt_end_date.setText(item.getActualStart());
            txt_oncelik.setText(item.getPriortiyCode().getText());
            switch (item.getPriortiyCode().getValue()) {

                case 0:
                    txt_oncelik.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0,
                            R.drawable.ic_az);
                    break;
                case 1:
                    txt_oncelik.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.ic_orta);
                    break;
                case 2:
                    txt_oncelik.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable
                            .ic_yuksek);
                    break;

            }
            txt_count.setText("" + (getAdapterPosition() + 1));

        }
    }
}
