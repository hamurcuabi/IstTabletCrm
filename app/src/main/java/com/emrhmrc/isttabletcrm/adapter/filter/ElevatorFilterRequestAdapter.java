package com.emrhmrc.isttabletcrm.adapter.filter;

import android.widget.Filter;

import com.emrhmrc.isttabletcrm.adapter.RcvElevatorRequestAdapter;
import com.emrhmrc.isttabletcrm.models.ServApp.ServiceAppointmentElevator;

import java.util.ArrayList;
import java.util.List;

public class ElevatorFilterRequestAdapter extends Filter {
    List<ServiceAppointmentElevator> filterList;
    RcvElevatorRequestAdapter adapter;

    public ElevatorFilterRequestAdapter(RcvElevatorRequestAdapter adapter,
                                        List<ServiceAppointmentElevator> filterList) {
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
            List<ServiceAppointmentElevator> filtered = new ArrayList<>();

            for (int i = 0; i < adapter.getItemsFilter().size(); i++) {


                if (Integer.parseInt(constraint.toString()) == -1) {
                    filtered.add(adapter.getItemsFilter().get(i));
                } else if (adapter.getItemsFilter().get(i).getStatusCode() != null) {
                    if (adapter.getItemsFilter().get(i).getStatusCode().getValue() == Integer.parseInt(constraint.toString())) {
                        filtered.add(adapter.getItemsFilter().get(i));
                    }


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

        adapter.setItems((List<ServiceAppointmentElevator>) results.values, true);
    }
}