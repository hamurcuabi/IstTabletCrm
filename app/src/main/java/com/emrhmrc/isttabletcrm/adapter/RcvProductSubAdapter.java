package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.GenericAdapter;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.filter.ProductSubGroupFilterAdapter;
import com.emrhmrc.isttabletcrm.models.Product.SubList;

public class RcvProductSubAdapter extends GenericAdapter<SubList,
        OnItemClickListener<SubList>,
        ProductSubViewHolder> implements Filterable {


    private ProductSubGroupFilterAdapter filter;

    public RcvProductSubAdapter(Context context, OnItemClickListener listener) {
        super(context, listener);
        filter = new ProductSubGroupFilterAdapter(this, getItemsFilter());
    }

    @Override
    public ProductSubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //viewtype göre ekelriz
        return new ProductSubViewHolder(inflate(R.layout.main_product_item, parent));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
}