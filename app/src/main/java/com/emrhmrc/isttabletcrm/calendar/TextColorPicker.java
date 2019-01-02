package com.emrhmrc.isttabletcrm.calendar;

import android.support.annotation.ColorInt;

public interface TextColorPicker {

    @ColorInt
    int getTextColor(WeekViewEvent event);

}