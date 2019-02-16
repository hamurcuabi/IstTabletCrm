package com.emrhmrc.isttabletcrm.adapter.filter;

import android.widget.Filter;

import com.emrhmrc.isttabletcrm.adapter.RcvTechnicalAdapter;
import com.emrhmrc.isttabletcrm.adapter.RcvUnstabilityAdapter;
import com.emrhmrc.isttabletcrm.models.Document.TechnicDocument;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppGetByIdServAppUnsuitabilities;

import java.util.ArrayList;
import java.util.List;

public class UstabilityFilterAdapter extends Filter {
    List<ServAppGetByIdServAppUnsuitabilities> filterList;
    RcvUnstabilityAdapter adapter;

    public UstabilityFilterAdapter(RcvUnstabilityAdapter adapter,
                                   List<ServAppGetByIdServAppUnsuitabilities> filterList) {
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
            List<ServAppGetByIdServAppUnsuitabilities> filtered = new ArrayList<>();

            for (int i = 0; i < adapter.getItemsFilter().size(); i++) {
                //CHECK
                if (adapter.getItemsFilter().get(i).getSubject().toLowerCase().contains(constraint)) {
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

        adapter.setItems((List<ServAppGetByIdServAppUnsuitabilities>) results.values, true);
    }
}