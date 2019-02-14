package com.emrhmrc.isttabletcrm.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.BaseViewHolder;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.databinding.ServappCareItemBinding;
import com.emrhmrc.isttabletcrm.bindingModel.ServiceAppointments;
import com.emrhmrc.isttabletcrm.util.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RcvSerAppListAllViewHolder extends BaseViewHolder<ServiceAppointments,
        OnItemClickListener<ServiceAppointments>> {
    @BindView(R.id.txt_count)
    TextView txtCount;
    @BindView(R.id.rlf)
    LinearLayout rlf;
    @BindView(R.id.imageView5)
    ImageView imageView5;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_custumerid)
    TextView txtCustumerid;
    @BindView(R.id.txt_descp)
    TextView txtDescp;
    @BindView(R.id.txt_start_date)
    TextView txtStartDate;
    @BindView(R.id.txt_start_date_clock)
    TextView txtStartDateClock;
    @BindView(R.id.txt_end_date)
    TextView txtEndDate;
    @BindView(R.id.txt_end_date_clock)
    TextView txtEndDateClock;
    @BindView(R.id.txt_oncelik)
    TextView txtOncelik;
    @BindView(R.id.img_state)
    ImageView imgState;

    private ServappCareItemBinding binding;

    public RcvSerAppListAllViewHolder(ServappCareItemBinding binding) {
        super(binding.getRoot());
        ButterKnife.bind(this, binding.getRoot());
        this.binding = binding;

    }


    @Override
    public void onBind(final ServiceAppointments item, final @Nullable
            OnItemClickListener<ServiceAppointments> listener) {
        binding.setServappItem(item);
        binding.setCount(StringUtil.convertIntToString(getAdapterPosition() + 1));
        if (listener != null) {
            rlf.setOnClickListener(view -> listener.onItemClicked(item));
        }

    }
}
