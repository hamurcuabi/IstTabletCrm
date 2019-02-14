package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.GenericAdapter;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.models.Product.MainList;

import butterknife.BindView;

public class RcvProductMainAdapter extends GenericAdapter<MainList,
        OnItemClickListener<MainList>,
        ProductMainViewHolder> {



    public RcvProductMainAdapter(Context context, OnItemClickListener listener) {
        super(context, listener);
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
}