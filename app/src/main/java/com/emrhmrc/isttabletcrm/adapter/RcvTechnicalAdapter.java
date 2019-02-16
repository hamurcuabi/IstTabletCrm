package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.GenericAdapter;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.filter.TechnicalFilterAdapter;
import com.emrhmrc.isttabletcrm.models.Document.TechnicDocument;

public class RcvTechnicalAdapter extends GenericAdapter<TechnicDocument,
        OnItemClickListener<TechnicDocument>,
        RcvTechnicalViewHolder> implements Filterable {
    TechnicalFilterAdapter filterAdapter;

    public RcvTechnicalAdapter(Context context, OnItemClickListener listener) {
        super(context, listener);
        filterAdapter = new TechnicalFilterAdapter(this, getItemsFilter());
    }

    @Override
    public RcvTechnicalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //viewtype göre ekelriz
        return new RcvTechnicalViewHolder(inflate(R.layout.technical_item, parent));
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