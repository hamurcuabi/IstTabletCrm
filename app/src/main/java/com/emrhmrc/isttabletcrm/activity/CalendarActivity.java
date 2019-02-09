package com.emrhmrc.isttabletcrm.activity;

import android.util.Log;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.api.ApiClient;
import com.emrhmrc.isttabletcrm.api.JsonApi;
import com.emrhmrc.isttabletcrm.calendar.BaseActivity;
import com.emrhmrc.isttabletcrm.calendar.WeekViewEvent;
import com.emrhmrc.isttabletcrm.helper.Methodes;
import com.emrhmrc.isttabletcrm.helper.ShareData;
import com.emrhmrc.isttabletcrm.models.ServApp.ServAppListAll;
import com.emrhmrc.isttabletcrm.models.ServApp.ServiceAppointments;
import com.emrhmrc.isttabletcrm.models.User.UserIdRequest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarActivity extends BaseActivity {
    private static final String TAG = "CalendarActivity";
    boolean calledNetwork = false;
    private List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
    private JsonApi jsonApi;

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        if (!calledNetwork) {
            jsonApi = ApiClient.getClient().create(JsonApi.class);
            UserIdRequest userIdRequest = new UserIdRequest(ShareData.getInstance().getUserId());
            Call<ServAppListAll> call = jsonApi.servAppListAll(userIdRequest);
            call.enqueue(new Callback<ServAppListAll>() {
                @Override
                public void onResponse(Call<ServAppListAll> call, Response<ServAppListAll> response) {
                    if (response.isSuccessful()) {

                        final ServAppListAll temp = response.body();
                        setCalendarEvents(temp.getServiceAppointments());


                    }
                }

                @Override
                public void onFailure(Call<ServAppListAll> call, Throwable t) {
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

    private void setCalendarEvents(List<ServiceAppointments> list) {

        long i = 0;
        for (ServiceAppointments current : list) {

            if (current.getStatusCode() != null) {
                if (current.getStatusCode().getValue() == 1) {

                    if (current.getScheduledStart() != null && current.getScheduledEnd() != null) {
                        Calendar startTime = Methodes.changeDateFormatToDate(current.getScheduledStart());
                        Calendar endTime = Methodes.changeDateFormatToDate(current.getScheduledEnd());
                        Log.d(TAG, "setCalendarEvents: " + startTime.getTime().toString());

                        WeekViewEvent event = new WeekViewEvent(i,
                                current.getInv_TypeCode().getText() + "\n" + current.getSubject(),
                                startTime,
                                endTime);
                        int colorid;
                        switch (current.getInv_TypeCode().getValue()) {

                            case 1:
                                colorid = R.color.bakim;
                                break;
                            case 2:
                                colorid = R.color.ariza;
                                break;
                            case 3:
                                colorid = R.color.modernizasyon;
                                break;
                            case 4:
                                colorid = R.color.yedek_parca_degisimi;
                                break;
                            case 5:
                                //Burası Yok
                                colorid = R.color.yedek_parca_degisimi;
                                break;
                            case 7:
                                //Burası Yok
                                colorid = R.color.yedek_parca_degisimi;
                                break;
                            default:
                                colorid = R.color.bakim;
                                break;


                        }
                        event.setColor(getResources().getColor(colorid));
                        events.add(event);
                        i++;

                        ShareData.getInstance().setLatitude(current.getInv_Latitude());
                        ShareData.getInstance().setLongitude(current.getInv_Longitude());

                    }
                }
            }
        }
       /* Calendar startTime = Calendar.getInstance();
        Calendar endTime = (Calendar) startTime.clone();

        endTime.add(Calendar.HOUR, 2);
        WeekViewEvent event = new WeekViewEvent(1, "Arıza", startTime, endTime);
        event.setColor(getResources().getColor(R.color.ariza));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.add(Calendar.DAY_OF_MONTH, 1);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR, 14);
        event = new WeekViewEvent(2, "Bakım\nDeniz Binası", startTime, endTime);
        event.setColor(getResources().getColor(R.color.bakim));
        events.add(event);*/
        getWeekView().notifyDatasetChanged();

    }

    private boolean eventMatches(WeekViewEvent event, int year, int month) {
        return (event.getStartTime().get(Calendar.YEAR) == year && event.getStartTime().get(Calendar.MONTH) == month - 1) || (event.getEndTime().get(Calendar.YEAR) == year && event.getEndTime().get(Calendar.MONTH) == month - 1);
    }

}
