package com.emrhmrc.isttabletcrm.helper;

import com.emrhmrc.isttabletcrm.calendar.WeekViewEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarEventsSingleton {
    private static final CalendarEventsSingleton ourInstance = new CalendarEventsSingleton();
    private List<WeekViewEvent> list = new ArrayList<>();
    private List<WeekViewEvent> filteredlist = new ArrayList<>();
    private Calendar selected;

    private CalendarEventsSingleton() {
    }

    public static CalendarEventsSingleton getInstance() {
        return ourInstance;
    }

    public Calendar getSelected() {
        return selected;
    }

    public void setSelected(Calendar selected) {
        this.selected = selected;
    }

    public List<WeekViewEvent> getList() {
        return list;
    }

    public void setList(List<WeekViewEvent> list) {
        this.list = list;
    }

    public List<WeekViewEvent> getFilteredlist() {
        return filteredlist;
    }

    public void setFilteredlist(List<WeekViewEvent> filteredlist) {
        this.filteredlist = filteredlist;
    }

    public void getDayList(Calendar date) {
        setSelected(date);
        filteredlist.clear();
        SimpleDateFormat targetFormat = new SimpleDateFormat("dd.MM.yyyy");
        List<WeekViewEvent> filtered = new ArrayList<>();
        if (list != null) {

            for (WeekViewEvent v : list
            ) {

                if (targetFormat.format(v.getStartTime().getTime()).equals(targetFormat.format(date.getTime())))
                    filteredlist.add(v);

            }

        }

    }
}
