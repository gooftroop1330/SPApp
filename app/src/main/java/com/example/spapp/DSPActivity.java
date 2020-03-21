package com.example.spapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

// TODO: USE PRODUCTION AdUnitID
// PRODUCTION AdUnitID
// ad_view.setAdUnitId("ca-app-pub-6460192778031720/8190674303");
public class DSPActivity extends AppCompatActivity
{
    String selectedDate;
    private AdView ad_view3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        selectedDate = getIntent().getStringExtra("selectedDate");
        setContentView(R.layout.dsp);

        TextView textView = findViewById(R.id.date);
        TextView textView2 = findViewById(R.id.position);
        textView.setText(selectedDate);

        // Position name
        textView2.setText("ohhh yeahhhh sexy time");

        // Google Ads
        ad_view3 = (AdView) findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        ad_view3.loadAd(adRequest);


    }

    // Clcik on calendar and it sends the date from dsp to calendar
    public void viewCalendar(View view)
    {
        Intent intent = new Intent(this, CalendarActivity.class);
        intent.putExtra("selectedDate", selectedDate);
        startActivity(intent);
    }

}
