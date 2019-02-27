package com.emrhmrc.isttabletcrm.adapter.filter;

import android.widget.Filter;

import com.emrhmrc.isttabletcrm.adapter.RcvWarehouseAdapter;
import com.emrhmrc.isttabletcrm.models.Warehouse.WarehouseItem;

import java.util.ArrayList;
import java.util.List;

public class WarehouseFilterAdapter extends Filter {
    List<WarehouseItem> filterList;
    RcvWarehouseAdapter adapter;

    public WarehouseFilterAdapter(RcvWarehouseAdapter adapter,
                                  List<WarehouseItem> filterList) {
        this.adapter = adapter;
        this.filterList = filterList;

    }

    //FILTERING OCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();

        //CHECK CONSTRAINT VALIDITY
        if (constraint != null && constraint.length() > 0) {
            //CHANGE TO UPPER
            constraint = constraint.toString().toLowerCase();
            //STORE OUR FILTERED PLAYERS
            List<WarehouseItem> filtered = new ArrayList<>();

            for (int i = 0; i < adapter.getItemsFilter().size(); i++) {
                //CHECK
                if (adapter.getItemsFilter().get(i).getInv_Productid().getText().toLowerCase().contains(constraint)) {
                    //ADD DATA TO FILTERED DATA
                    filtered.add(adapter.getItemsFilter().get(i));
                }

            }

            results.count = filtered.size();
            results.values = filtered;
        } else {
            results.count = adapter.getItemsFilter().size();
            results.values = adapter.getItemsFilter();

        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.setItems((List<WarehouseItem>) results.values, true);
    }
}