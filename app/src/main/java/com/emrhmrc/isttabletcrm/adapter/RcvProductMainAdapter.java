package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.GenericAdapter;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.filter.ProductMainGroupFilterAdapter;
import com.emrhmrc.isttabletcrm.models.Product.MainList;

public class RcvProductMainAdapter extends GenericAdapter<MainList,
        OnItemClickListener<MainList>,
        ProductMainViewHolder> implements Filterable {


    private ProductMainGroupFilterAdapter filter;

    public RcvProductMainAdapter(Context context, OnItemClickListener listener) {
        super(context, listener);
        filter = new ProductMainGroupFilterAdapter(this, getItemsFilter());
    }

    @Override
    public ProductMainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //viewtype göre ekelriz
        return new ProductMainViewHolder(inflate(R.layout.main_product_item, parent));
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