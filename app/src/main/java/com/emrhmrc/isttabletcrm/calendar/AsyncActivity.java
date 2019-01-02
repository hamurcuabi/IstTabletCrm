package com.emrhmrc.isttabletcrm.calendar;

import android.util.Log;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.models.BreakDown.BreakDownTypeListAll;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsyncActivity extends BaseActivity {

    private static final String TAG = "AsyncActivity";
    boolean calledNetwork = false;
    private List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
    private JsonApi jsonApi;

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        if (!calledNetwork) {

            jsonApi = ApiClient.getClient().create(JsonApi.class);
            Call<BreakDownTypeListAll> call = jsonApi.breakDownTypeListAll();
            call.enqueue(new Callback<BreakDownTypeListAll>() {
                @Override
                public void onResponse(Call<BreakDownTypeListAll> call, Response<BreakDownTypeListAll> response) {
                    if (response.isSuccessful()) {

                        BreakDownTypeListAll temp = response.body();
                        Log.d(TAG, "onResponse: ");
                        Calendar startTime = Calendar.getInstance();
                        startTime.set(Calendar.HOUR_OF_DAY, 3);
                        startTime.set(Calendar.MINUTE, 0);
                        Calendar endTime = (Calendar) startTime.clone();
                        endTime.add(Calendar.HOUR, 1);
                        WeekViewEvent event = new WeekViewEvent(1, getEventTitle(startTime), startTime, endTime);
                        event.setColor(getResources().getColor(R.color.event_color_01));
                        events.add(event);
                        getWeekView().notifyDatasetChanged();


                    }
                }

                @Override
                public void onFailure(Call<BreakDownTypeListAll> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                }
            });
            calledNetwork = true;
        }

        // Return only the events that matches newYear and newMonth.
        List<WeekViewEvent> matchedEvents = new ArrayList<WeekViewEvent>();
        for (WeekViewEvent event : events) {
            if (eventMatches(event, newYear, newMonth)) {
                matchedEvents.add(event);
            }
        }
        return matchedEvents;
    }

    private boolean eventMatches(WeekViewEvent event, int year, int month) {
        return (event.getStartTime().get(Calendar.YEAR) == year && event.getStartTime().get(Calendar.MONTH) == month - 1) || (event.getEndTime().get(Calendar.YEAR) == year && event.getEndTime().get(Calendar.MONTH) == month - 1);
    }


}
