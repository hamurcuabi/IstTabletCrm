package com.emrhmrc.isttabletcrm.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.GenericAdapter;
import com.emrhmrc.isttabletcrm.adapter.GenericRcwAdapter.OnItemClickListener;
import com.emrhmrc.isttabletcrm.adapter.filter.ElevatorFilterRequestAdapter;
import com.emrhmrc.isttabletcrm.models.Elevator.PeriodicalProducts;
import com.emrhmrc.isttabletcrm.models.ServApp.ServiceAppointmentElevator;

public class RcvElevatorOmurluAdapter extends GenericAdapter<PeriodicalProducts,
        OnItemClickListener<PeriodicalProducts>,
        RcvElevatorOmurluViewHolder> {


    public RcvElevatorOmurluAdapter(Context context, OnItemClickListener listener) {
        super(context, listener);

    }

    @Override
    public RcvElevatorOmurluViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //viewtype göre ekelriz
        switch (viewType % 2) {
            case 0:
                return new RcvElevatorOmurluViewHolder(inflate(R.layout.omurlu_parcalar_item,
                        parent));
            case 1:
                return new RcvElevatorOmurluViewHolder(inflate(R.layout.omurlu_parcalar_item_2,
                        parent));
            default:
                return new RcvElevatorOmurluViewHolder(inflate(R.layout.omurlu_parcalar_item,
                        parent));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


}