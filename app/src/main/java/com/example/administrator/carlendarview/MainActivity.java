package com.example.administrator.carlendarview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter;

import net.cachapa.expandablelayout.ExpandableLinearLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //private CalendarView calendarView;
    private MaterialCalendarView calendarView;
    private ImageView next;
    private TextView currenttime;
    private ImageView last;
    private SimpleDateFormat df;
    private ExpandableLinearLayout container;
    private LinearLayout calendartitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

    }

    private void initialize() {

        calendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        next = (ImageView) findViewById(R.id.next);
        container = (ExpandableLinearLayout) findViewById(R.id.container);
        currenttime = (TextView) findViewById(R.id.currenttime);
        last = (ImageView) findViewById(R.id.last);
        calendartitle = (LinearLayout) findViewById(R.id.calendartitle);
       // Long nowTime = calendarView.getDate();
         df = new SimpleDateFormat("yyyy年MM月");
        calendarView.setWeekDayFormatter(new ArrayWeekDayFormatter(new CharSequence[]{"日", "一", "二", "三", "四", "五", "六"}));
        calendarView.setSelectedDate(Calendar.getInstance());
        calendarView.setTopbarVisible(false);


        calendartitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.toggle();
            }
        });

        currenttime.setText(df.format(calendarView.getCurrentDate().getDate()));

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendarView.goToNext();



            }
        });

        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendarView.goToPrevious();

            }
        });


     /*   Calendar strDate = Calendar.getInstance();
        strDate.add(strDate.DATE,7);
        calendarView.selectRange(new CalendarDay(new Date()),new CalendarDay(strDate.getTime()));*/

        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

                currenttime.setText(df.format(date.getDate()));
            }
        });


        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(MaterialCalendarView widget, CalendarDay date, boolean selected) {
                SimpleDateFormat f = new SimpleDateFormat("yyyy年MM月dd日");

                Toast.makeText(MainActivity.this,f.format(date.getDate()),Toast.LENGTH_SHORT).show();
            }
        });

    }



}
