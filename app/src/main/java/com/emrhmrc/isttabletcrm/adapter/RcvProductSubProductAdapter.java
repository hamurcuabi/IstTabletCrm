package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.GenericAdapter;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.filter.ProductSubGroupProductFilterAdapter;
import com.emrhmrc.isttabletcrm.models.Product.Product;

public class RcvProductSubProductAdapter extends GenericAdapter<Product,
        OnItemClickListener<Product>,
        ProductSubProductViewHolder> implements Filterable {

    private ProductSubGroupProductFilterAdapter filter;

    public RcvProductSubProductAdapter(Context context, OnItemClickListener listener) {
        super(context, listener);
        filter = new ProductSubGroupProductFilterAdapter(this, getItemsFilter());

    }

    @Override
    public ProductSubProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //viewtype göre ekelriz

        return new ProductSubProductViewHolder(inflate(R.layout.sub_product_item, parent));
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