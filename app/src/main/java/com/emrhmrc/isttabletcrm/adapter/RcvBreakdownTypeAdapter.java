package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.GenericAdapter;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.filter.ProductMainGroupFilterAdapter;
import com.emrhmrc.isttabletcrm.models.BreakDown.BreakdownType;
import com.emrhmrc.isttabletcrm.models.Product.MainList;

public class RcvBreakdownTypeAdapter extends GenericAdapter<BreakdownType,
        OnItemClickListener<BreakdownType>,
        BreakdownTypeHolder>  {



    public RcvBreakdownTypeAdapter(Context context, OnItemClickListener listener) {
        super(context, listener);

    }

    @Override
    public BreakdownTypeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //viewtype göre ekelriz
        return new BreakdownTypeHolder(inflate(R.layout.breakdown_code_item, parent));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}