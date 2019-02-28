package com.emrhmrc.isttabletcrm.calendar;

import android.graphics.Point;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.emrhmrc.isttabletcrm.R;
import com.emrhmrc.isttabletcrm.fragment.MapFragment;
import com.emrhmrc.isttabletcrm.helper.CalendarEventsSingleton;
import com.emrhmrc.isttabletcrm.helper.MapGo;
import com.emrhmrc.isttabletcrm.models.MapModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public abstract class BaseActivity extends AppCompatActivity implements WeekView
        .EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener,
        WeekView.EmptyViewLongPressListener,MapGo {
    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
    private int mWeekViewType = TYPE_THREE_DAY_VIEW;
    private WeekView mWeekView;
    private TextView txt_center_date;
    private Button btn_today, btn_next, btn_back;
    private Calendar now = Calendar.getInstance();
    private Calendar after = Calendar.getInstance();
    private ImageView img_gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mWeekView = findViewById(R.id.weekView);
        mWeekView.setOnEventClickListener(this);
        mWeekView.setMonthChangeListener(this);
        mWeekView.setEventLongPressListener(this);
        mWeekView.setEmptyViewLongPressListener(this);
        mWeekView.setMinTime(9);
        //  mWeekView.setMaxTime(19);
        setupDateTimeInterpreter(false);
        mWeekView.goToHour(9);
        //init
        txt_center_date = findViewById(R.id.txt_center_date);
        btn_today = findViewById(R.id.btn_today);
        btn_back = findViewById(R.id.btn_back);
        btn_next = findViewById(R.id.btn_next);
        img_gps = findViewById(R.id.img_gps);
        //Cliks
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                now.add(Calendar.DAY_OF_MONTH, 6);
                after.add(Calendar.DAY_OF_MONTH, 6);
                mWeekView.goToDate(now);
                txt_center_date.setText("" + sdf.format(now.getTime()) + " - " + sdf.format(after
                        .getTime()));

            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                now.add(Calendar.DAY_OF_MONTH, -6);
                after.add(Calendar.DAY_OF_MONTH, -6);
                mWeekView.goToDate(now);
                txt_center_date.setText("" + sdf.format(now.getTime()) + " - " + sdf.format(after
                        .getTime()));


            }
        });
        btn_today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWeekView.goToToday();
                now = Calendar.getInstance();
                after = Calendar.getInstance();
                after.add(Calendar.DAY_OF_MONTH, 6);
                txt_center_date.setText("" + sdf.format(now.getTime()) + " - " + sdf.format(after
                        .getTime()));


            }
        });

        img_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CalendarEventsSingleton.getInstance().getFilteredlist().size() > 0) {
                    Display display = getWindowManager().getDefaultDisplay();
                    Point size = new Point();
                    display.getSize(size);
                    int width = size.x - 100;
                    int height = size.y - 200;
                    ArrayList<MapModel> mapModels = new ArrayList<>();
                    for (WeekViewEvent m : CalendarEventsSingleton.getInstance().getFilteredlist()
                    ) {
                        MapModel map = new MapModel(m.getLatitude(), m.getLongitude(),
                                m.getName(),
                                m.getName());
                        mapModels.add(map);

                    }

                    MapFragment fragment = MapFragment.newInstance(mapModels, width, height);
                    fragment.show(getSupportFragmentManager(), "MapFragment");

                } else {
                    Toast.makeText(getApplicationContext(), "Bugun Konum Yok",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });
        after.add(Calendar.DAY_OF_MONTH, 6);
        txt_center_date.setText("" + sdf.format(now.getTime()) + " - " + sdf.format(after.getTime
                ()));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        setupDateTimeInterpreter(id == R.id.action_week_view);
        switch (id) {
            case R.id.action_today:
                mWeekView.goToToday();
                return true;
            case R.id.action_day_view:
                if (mWeekViewType != TYPE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(1);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_three_day_view:
                if (mWeekViewType != TYPE_THREE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_THREE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(3);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_week_view:
                if (mWeekViewType != TYPE_WEEK_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_WEEK_VIEW;
                    mWeekView.setNumberOfVisibleDays(7);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Set up a date time interpreter which will show short date values when in week view and long
     * date values otherwise.
     *
     * @param shortDate True if the date values should be short.
     */
    private void setupDateTimeInterpreter(final boolean shortDate) {
        mWeekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat(" d/M", Locale.getDefault());

                // All android api level do not have a standard way of getting the first letter of
                // the week day name. Hence we get the first char programmatically.
                // Details: http://stackoverflow.com/questions/16959502/get-one-letter-abbreviation-of-week-day-of-a-date-in-java#answer-16959657
                if (shortDate)
                    weekday = String.valueOf(weekday.charAt(0));
                return weekday.toUpperCase() + format.format(date.getTime());
            }

            @Override
            public String interpretTime(int hour) {
                return hour > 11 ? (hour - 12) + " PM" : (hour == 0 ? "12 AM" : hour + " AM");
            }
        });
    }

    protected String getEventTitle(Calendar time) {
//        return String.format("Test Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time
//                .get(Calendar.MINUTE), time.get(Calendar.MONTH)+1, time.get(Calendar.DAY_OF_MONTH));

        return "Test Event";
    }

    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(this, "Clicked " + event.getName(), Toast.LENGTH_SHORT).show();
        CalendarEventsSingleton.getInstance().getDayList(event.getStartTime());

    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(this, "Long pressed event: " + event.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmptyViewLongPress(Calendar time) {
        Toast.makeText(this, "Empty view long pressed: " + getEventTitle(time), Toast.LENGTH_SHORT).show();
    }

    public WeekView getWeekView() {
        return mWeekView;
    }

}