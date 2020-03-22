package com.example.spapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;

import com.example.spapp.database.AppDatabase;
import com.example.spapp.models.Position;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

// TODO: USE PRODUCTION AdUnitID
// PRODUCTION AdUnitID
// ad_view.setAdUnitId("ca-app-pub-6460192778031720/8190674303");
public class DSPActivity extends AppCompatActivity
{
    private AppDatabase db;
    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    Position position;
    String selectedDate;
    private AdView ad_view3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        selectedDate = getIntent().getStringExtra("selectedDate");
        db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "dsp_db").allowMainThreadQueries().build();

        try
        {
            long time = sdf.parse(selectedDate).getTime();
            time = time - (time % DateUtils.DAY_IN_MILLIS);
            int posID = db.populatedPositionsDao().getPositionID(time);
            position = db.positionDao().getPosition(posID);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }

        setContentView(R.layout.dsp);

        TextView textView = findViewById(R.id.date);
        TextView position_name = findViewById(R.id.position);
        TextView description = findViewById(R.id.description);
        textView.setText(selectedDate);

        // Position name
        position_name.setText(position.position);
        // Description
        description.setText(position.description);

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
