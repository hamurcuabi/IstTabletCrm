package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.GenericAdapter;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.filter.WarehouseFilterTransferAdapter;
import com.emrhmrc.isttabletcrm.models.Warehouse.WarehouseTransferItem;

public class RcvWarehouseTransferAdapter extends GenericAdapter<WarehouseTransferItem,
        OnItemClickListener<WarehouseTransferItem>,
        RcvWarehouseTransferViewHolder> implements Filterable {
    WarehouseFilterTransferAdapter filterAdapter;

    public RcvWarehouseTransferAdapter(Context context, OnItemClickListener listener) {
        super(context, listener);
        filterAdapter = new WarehouseFilterTransferAdapter(this, getItemsFilter());
    }

    @Override
    public RcvWarehouseTransferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //viewtype göre ekelriz
        switch (viewType % 2) {
            case 0:
                return new RcvWarehouseTransferViewHolder(inflate(R.layout.rcv_warehouse_transfer_item,
                        parent));
            case 1:
                return new RcvWarehouseTransferViewHolder(inflate(R.layout.rcv_warehouse_transefer_item_2,
                        parent));
            default:
                return new RcvWarehouseTransferViewHolder(inflate(R.layout.rcv_warehouse_transfer_item,
                        parent));
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