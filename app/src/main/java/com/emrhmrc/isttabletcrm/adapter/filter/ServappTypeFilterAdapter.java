package com.emrhmrc.isttabletcrm.adapter.filter;

import android.widget.Filter;

import com.emrhmrc.isttabletcrm.adapter.RcvServAppListAllAdapter;
import com.emrhmrc.isttabletcrm.bindingModel.ServiceAppointments;

import java.util.ArrayList;
import java.util.List;

public class ServappTypeFilterAdapter extends Filter {
    List<ServiceAppointments> filterList;
    List<ServiceAppointments> subfilterList;
    RcvServAppListAllAdapter adapter;

    public ServappTypeFilterAdapter(RcvServAppListAllAdapter adapter,
                                    List<ServiceAppointments> filterList) {
        this.adapter = adapter;
        this.filterList = filterList;
        subfilterList = new ArrayList<>();

    }

    //FILTERING OCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        String from = "";

        if (constraint != null && constraint.length() > 0) {

            constraint = constraint.toString().toLowerCase();
            from = ((String) constraint).substring(0, 1);
            constraint = ((String) constraint).substring(1);

            List<ServiceAppointments> filtered = new ArrayList<>();

            if (from.equals("b")) {
                for (int i = 0; i < subfilterList.size(); i++) {

                    if (Integer.parseInt(constraint.toString()) == -1) {
                        filtered.add(subfilterList.get(i));
                    } else if (subfilterList.get(i).getStatusCode() != null) {
                        if (subfilterList.get(i).getStatusCode().getValue() == Integer.parseInt(constraint.toString())) {
                            filtered.add(subfilterList.get(i));
                        }
                    }

                }
            } else if (from.equals("a")) {

                subfilterList.clear();
                for (int i = 0; i < adapter.getItemsFilter().size(); i++) {

                    if (Integer.parseInt(constraint.toString()) == -1) {
                        filtered.add(adapter.getItemsFilter().get(i));
                        subfilterList.add(adapter.getItemsFilter().get(i));
                    } else {
                        if (adapter.getItemsFilter().get(i).getInv_TypeCode() != null) {

                            if (adapter.getItemsFilter().get(i).getInv_TypeCode().getValue() == Integer.parseInt(constraint.toString())) {
                                filtered.add(adapter.getItemsFilter().get(i));
                                subfilterList.add(adapter.getItemsFilter().get(i));

                            } else if (adapter.getItemsFilter().get(i).getInv_TypeCode().getValue() == Integer.parseInt(constraint.toString())) {
                                filtered.add(adapter.getItemsFilter().get(i));
                                subfilterList.add(adapter.getItemsFilter().get(i));
                            }

                        }
                    }
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