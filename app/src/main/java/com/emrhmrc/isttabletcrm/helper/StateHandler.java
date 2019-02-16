package com.emrhmrc.isttabletcrm.helper;

import com.emrhmrc.isttabletcrm.models.ItemsState;

import java.util.ArrayList;
import java.util.List;

public class StateHandler {
    private static final StateHandler ourInstance = new StateHandler();
    private List<ItemsState> stateList;

    private StateHandler() {
    }

    public static StateHandler getInstance() {
        return ourInstance;
    }

    public List<ItemsState> getStateList() {
        return stateList;
    }

    public void setStateList(int size) {
        stateList = new ArrayList<>();
        stateList.clear();
        for (int i = 0; i < size; i++) {
            ItemsState itemsState = new ItemsState(false, i);
            stateList.add(itemsState);
        }

    }
}
