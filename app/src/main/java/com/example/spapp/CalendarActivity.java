package com.example.spapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.format.DateUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity
{
    CalendarView calView;
    TextView date;
    String selectedDate;
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    String currDate = sdf.format(new Date());
    long setter, now, past, future;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
        selectedDate = getIntent().getStringExtra("selectedDate");
        initializeCalView(selectedDate);
        date = (TextView) findViewById(R.id.selectedDate);
        date.setText(selectedDate);

        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth)
            {
                selectedDate = sdf.format(new Date(year-1900,month,dayOfMonth));
                date.setText(selectedDate);
            }
        });
    }

    public void selectDate(View view)
    {
        Intent intent = new Intent(this, DSPActivity.class);
        intent.putExtra("selectedDate", selectedDate);
        startActivity(intent);
    }

    public void initializeCalView(String date) {
        try {

            Date heh = sdf.parse(date);
            assert heh != null;
            setter = heh.getTime();

            Date currentDate = sdf.parse(currDate);
            assert currentDate != null;
            now = currentDate.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        calView = (CalendarView) findViewById(R.id.calendarView);
        calView.setDate(setter, true, true);
        past = now - (DateUtils.YEAR_IN_MILLIS / 12);
        future = now + (DateUtils.YEAR_IN_MILLIS / 12);
        calView.setMaxDate(future);
        calView.setMinDate(past);
    }
}
