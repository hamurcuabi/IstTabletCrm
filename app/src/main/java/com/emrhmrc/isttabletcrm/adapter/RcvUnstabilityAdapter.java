package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.GenericAdapter;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.filter.UstabilityFilterAdapter;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppUnsuitabilities;
import com.emrhmrc.isttabletcrm.models.Unsuitablity.Unsuitabilities;
import com.emrhmrc.isttabletcrm.models.Unsuitablity.UnsuitabilityListAll;

public class RcvUnstabilityAdapter extends GenericAdapter<Unsuitabilities,
        OnItemClickListener<Unsuitabilities>,
        RcvUnstabilityViewHolder> implements Filterable {
    UstabilityFilterAdapter filterAdapter;

    public RcvUnstabilityAdapter(Context context, OnItemClickListener listener) {
        super(context, listener);
        filterAdapter = new UstabilityFilterAdapter(this, getItemsFilter());
    }

    @Override
    public RcvUnstabilityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //viewtype göre ekelriz
        return new RcvUnstabilityViewHolder(inflate(R.layout.unstability_item, parent));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public Filter getFilter() {
        return filterAdapter;
    }
}