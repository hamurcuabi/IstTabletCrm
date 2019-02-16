package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.GenericAdapter;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.filter.ServappTypeFilterAdapter;
import com.emrhmrc.isttabletcrm.bindingModel.ServiceAppointments;
import com.emrhmrc.isttabletcrm.databinding.ServappCareItemBinding;

public class RcvServAppListAllAdapter extends GenericAdapter<ServiceAppointments,
        OnItemClickListener<ServiceAppointments>,
        RcvSerAppListAllViewHolder> implements Filterable {
    ServappTypeFilterAdapter filterAdapter;

    public RcvServAppListAllAdapter(Context context, OnItemClickListener listener) {
        super(context, listener);
        filterAdapter = new ServappTypeFilterAdapter(this, getItemsFilter());

    }

    @Override
    public RcvSerAppListAllViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ServappCareItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.servapp_care_item, parent, false);
        return new RcvSerAppListAllViewHolder(binding);

    }


    @Override
    public int getItemViewType(int position) {
        final ServiceAppointments item = getItem(position);
        return item.getStatusCode().getValue();
    }

    @Override
    public Filter getFilter() {
        return filterAdapter;
    }
}
