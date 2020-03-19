package com.example.spapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.time.LocalDate;
import java.util.Date;


public class WelcomeActivity extends AppCompatActivity {


    public static final String theDate = "19 March 2020";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
    }

    public void letsGetItOn(View view)
    {
        Intent intent = new Intent(this, DSPActivity.class);
        startActivity(intent);
    }
}
