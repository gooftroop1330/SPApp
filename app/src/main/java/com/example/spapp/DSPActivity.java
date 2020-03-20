package com.example.spapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DSPActivity extends AppCompatActivity
{
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dsp);
        TextView textView = findViewById(R.id.date);
        String currentDate = sdf.format(new Date());
        textView.setText(currentDate);
    }

    public void viewCalendar(View view)
    {
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }
}
