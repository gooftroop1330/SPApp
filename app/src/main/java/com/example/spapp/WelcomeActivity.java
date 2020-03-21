package com.example.spapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// TODO: USE PRODUCTION AdUnitID
// PRODUCTION AdUnitID
// ad_view.setAdUnitId("ca-app-pub-6460192778031720/8190674303");
public class WelcomeActivity extends AppCompatActivity
{

    public static SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    String currentDate = sdf.format(new Date());

    private AdView ad_view;
    private AdView ad_view2;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        ad_view = (AdView) findViewById(R.id.adView);
        ad_view2 = (AdView) findViewById(R.id.adView2);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        ad_view.loadAd(adRequest);
        ad_view2.loadAd(adRequest);

    }

    public void letsGetItOn(View view)
    {
        Intent intent = new Intent(this, DSPActivity.class);
        intent.putExtra("selectedDate", currentDate);
        startActivity(intent);
    }
}
