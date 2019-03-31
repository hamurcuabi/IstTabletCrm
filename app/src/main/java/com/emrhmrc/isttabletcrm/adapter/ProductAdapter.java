package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.GenericAdapter;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.filter.ProductFilterAdapter;
import com.emrhmrc.isttabletcrm.models.Product.Product;

public class ProductAdapter extends GenericAdapter<Product,
        OnItemClickListener<Product>,
        ProductViewHolder> implements Filterable {

    private ProductFilterAdapter filter;
    private Context context;

    public ProductAdapter(Context context, OnItemClickListener listener) {
        super(context, listener);
        filter = new ProductFilterAdapter(this, getItemsFilter());
        this.context = context;

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //viewtype göre ekelriz
        return new ProductViewHolder(inflate(R.layout.workman_item, parent), context);
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