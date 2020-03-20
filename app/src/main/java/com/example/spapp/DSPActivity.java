package com.example.spapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class DSPActivity extends AppCompatActivity
{
    String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        selectedDate = getIntent().getStringExtra("selectedDate");
        setContentView(R.layout.dsp);
        TextView textView = findViewById(R.id.date);
        textView.setText(selectedDate);
    }

    public void viewCalendar(View view)
    {
        Intent intent = new Intent(this, CalendarActivity.class);
        intent.putExtra("selectedDate", selectedDate);
        startActivity(intent);
    }
}
