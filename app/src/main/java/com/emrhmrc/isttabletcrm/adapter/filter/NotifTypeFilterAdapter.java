package com.emrhmrc.isttabletcrm.adapter.filter;

import android.widget.Filter;

import com.emrhmrc.isttabletcrm.adapter.RcvAnnouncementAdapter;
import com.emrhmrc.isttabletcrm.models.Notification.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotifTypeFilterAdapter extends Filter {
    List<Notification> filterList;
    RcvAnnouncementAdapter adapter;

    public NotifTypeFilterAdapter(RcvAnnouncementAdapter adapter,
                                  List<Notification> filterList) {
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
            List<Notification> filtered = new ArrayList<>();

            for (int i = 0; i < adapter.getItemsFilter().size(); i++) {
                //CHECK
                if (adapter.getItemsFilter().get(i).getNotificationType() != null)
                    if (adapter.getItemsFilter().get(i).getNotificationType().getValue() == Integer.parseInt(constraint.toString()) || Integer.parseInt(constraint.toString()) == 0) {
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

        adapter.setItems((List<Notification>) results.values, true);
    }
}