package com.example.spapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class InfoActivity extends AppCompatActivity
{
    String position;
    String description;
    private AdView ad_view5;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        position = getIntent().getStringExtra("position");
        description = getIntent().getStringExtra("description");

        TextView posTV = (TextView) findViewById(R.id.position);
        TextView descTV = (TextView) findViewById(R.id.description);

        posTV.setText(position);
        descTV.setText(description);

        // Google Ads
        ad_view5 = (AdView) findViewById(R.id.adView5);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        ad_view5.loadAd(adRequest);
    }
}
