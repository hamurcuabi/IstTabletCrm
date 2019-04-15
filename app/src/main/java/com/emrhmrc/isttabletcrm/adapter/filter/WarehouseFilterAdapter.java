package com.emrhmrc.isttabletcrm.adapter.filter;

import android.widget.Filter;

import com.emrhmrc.isttabletcrm.adapter.RcvWarehouseAdapter;
import com.emrhmrc.isttabletcrm.models.Warehouse.WarehouseItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WarehouseFilterAdapter extends Filter {
    private static final String TAG = "WarehouseFilterAdapter";
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
            HashMap<Integer, String> hashMap = new HashMap<>();
            for (int i = 0; i < adapter.getItemsFilter().size(); i++) {
                //CHECK
                try {
                    int k = Integer.parseInt(constraint.toString());
                    if (k == 0 || k == 1) {
                        if (adapter.getItemsFilter().get(i).getInv_WarehouseTypeCode() != null) {
                            if (adapter.getItemsFilter().get(i).getInv_WarehouseTypeCode().getValue() == k) {
                                //ADD DATA TO FILTERED DATA
                                filtered.add(adapter.getItemsFilter().get(i));
                            } else if (Integer.parseInt(constraint.toString()) == 0) {
                                filtered.add(adapter.getItemsFilter().get(i));
                            }

                        }

                    } else {
                        filtered.add(adapter.getItemsFilter().get(i));
                    }
                } catch (Exception ex) {

                    if (adapter.getItemsFilter().get(i).getInv_Productid() != null) {

                        if (adapter.getItemsFilter().get(i).getInv_Productid().getText().toLowerCase().contains(constraint)) {
                           if(!hashMap.containsKey(i)) {
                               hashMap.put(i,"");
                               filtered.add(adapter.getItemsFilter().get(i));
                           }

                        }
                    }
                    if (adapter.getItemsFilter().get(i).getSubProductGroupid() != null) {

                        if (adapter.getItemsFilter().get(i).getSubProductGroupid().getText().toLowerCase().contains(constraint)) {
                            if(!hashMap.containsKey(i)) {
                                hashMap.put(i,"");
                                filtered.add(adapter.getItemsFilter().get(i));
                            }

                        }
                    }
                    if (adapter.getItemsFilter().get(i).getMainProductGroupid() != null) {

                        if (adapter.getItemsFilter().get(i).getMainProductGroupid().getText().toLowerCase().contains(constraint)) {
                            if(!hashMap.containsKey(i)) {
                                hashMap.put(i,"");
                                filtered.add(adapter.getItemsFilter().get(i));
                            }

                        }
                    }
                    if (adapter.getItemsFilter().get(i).getProductNumber() != null) {

                        if (adapter.getItemsFilter().get(i).getProductNumber().toLowerCase().contains(constraint)) {
                            if(!hashMap.containsKey(i)) {
                                hashMap.put(i,"");
                                filtered.add(adapter.getItemsFilter().get(i));
                            }

                        }
                    }
                    if (adapter.getItemsFilter().get(i).getInv_WarehouseTypeCode() != null) {

                        if (adapter.getItemsFilter().get(i).getInv_WarehouseTypeCode().getText().toLowerCase().contains(constraint)) {
                            if(!hashMap.containsKey(i)) {
                                hashMap.put(i,"");
                                filtered.add(adapter.getItemsFilter().get(i));
                            }

                        }
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

        adapter.setItems((List<WarehouseItem>) results.values, true);
    }
}