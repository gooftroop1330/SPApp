package com.example.spapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;


public class WelcomeActivity extends AppCompatActivity
{

    public static SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    String currentDate = sdf.format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
    }

    public void letsGetItOn(View view)
    {
        Intent intent = new Intent(this, DSPActivity.class);
        intent.putExtra("selectedDate", currentDate);
        startActivity(intent);
    }
}
