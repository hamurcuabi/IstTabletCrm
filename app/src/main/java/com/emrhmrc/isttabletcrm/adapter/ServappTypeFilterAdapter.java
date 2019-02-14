package com.emrhmrc.isttabletcrm.adapter;

import android.widget.Filter;

import com.emrhmrc.isttabletcrm.models.ServiceAppointments;

import java.util.ArrayList;
import java.util.List;

public class ServappTypeFilterAdapter extends Filter {
    List<ServiceAppointments> filterList;
    RcvServAppListAllAdapter adapter;

    public ServappTypeFilterAdapter(RcvServAppListAllAdapter adapter,
                                    List<ServiceAppointments> filterList) {
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
            List<ServiceAppointments> filtered = new ArrayList<>();

            for (int i = 0; i < adapter.getItemsFilter().size(); i++) {
                //CHECK
                if (adapter.getItemsFilter().get(i).getInv_TypeCode().getValue() == Integer.parseInt(constraint.toString()) || Integer.parseInt(constraint.toString()) == 0) {
                    //ADD DATA TO FILTERED DATA
                    filtered.add(adapter.getItemsFilter().get(i));
                }

            }

            results.count = filtered.size();
            results.values = filtered;
        } else {
            results.count = filterList.size();
            results.values = filterList;

        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.setItems((List<ServiceAppointments>) results.values, true);
    }
}