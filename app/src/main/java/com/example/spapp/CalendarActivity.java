package com.example.spapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity
{
    CalendarView calView;
    TextView date;
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
        selectedDate = getIntent().getStringExtra("selectedDate");
        calView = (CalendarView) findViewById(R.id.calendarView);
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
}
