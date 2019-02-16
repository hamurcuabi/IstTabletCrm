package com.emrhmrc.isttabletcrm.models;

public class ItemsState {
    private boolean state;
    private int position;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ItemsState(boolean state, int position) {
        this.state = state;
        this.position = position;
    }
}
