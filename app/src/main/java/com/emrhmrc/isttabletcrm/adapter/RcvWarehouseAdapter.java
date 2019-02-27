package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.GenericAdapter;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.filter.WarehouseFilterAdapter;
import com.emrhmrc.isttabletcrm.models.Warehouse.WarehouseItem;

public class RcvWarehouseAdapter extends GenericAdapter<WarehouseItem,
        OnItemClickListener<WarehouseItem>,
        RcvWarehouselViewHolder> implements Filterable {
    WarehouseFilterAdapter filterAdapter;

    public RcvWarehouseAdapter(Context context, OnItemClickListener listener) {
        super(context, listener);
        filterAdapter = new WarehouseFilterAdapter(this, getItemsFilter());
    }

    @Override
    public RcvWarehouselViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //viewtype göre ekelriz
        switch (viewType % 2) {
            case 0:
                return new RcvWarehouselViewHolder(inflate(R.layout.rcv_warehouse_item, parent));
            case 1:
                return new RcvWarehouselViewHolder(inflate(R.layout.rcv_warehouse_item_2, parent));
            default:
                return new RcvWarehouselViewHolder(inflate(R.layout.rcv_warehouse_item, parent));
        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        return filterAdapter;
    }
}