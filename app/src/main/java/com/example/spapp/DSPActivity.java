package com.example.spapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
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
    Button like;
    Button dislike;
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
        //TextView description = findViewById(R.id.description);
        textView.setText(selectedDate);

        // Position name
        position_name.setText(position.getPosition());
        // Description
        //description.setText(position.getDescription());

        like = (Button) findViewById(R.id.like);
        dislike = (Button) findViewById(R.id.dislike);


        // Google Ads
        ad_view3 = (AdView) findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        ad_view3.loadAd(adRequest);

        //IT WONT KEEP THE COLOR! WHY???
        if(position.getLike() == 1)
        {
            like.setBackgroundResource(R.drawable.liked);
        }
        else if(position.getLike() == -1)
        {
            dislike.setBackgroundResource(R.drawable.disliked);
        }

    }

    // Clcik on calendar and it sends the date from dsp to calendar
    public void viewCalendar(View view)
    {
        Intent intent = new Intent(this, CalendarActivity.class);
        intent.putExtra("selectedDate", selectedDate);
        startActivity(intent);
    }

    public void like(View view)
    {
        like.setBackgroundResource(R.drawable.liked);
        dislike.setBackgroundResource(R.drawable.dislike_button);
        db.positionDao().likePosition(position.getId(), 1);
    }

    //860283

    public void dislike(View view)
    {
        dislike.setBackgroundResource(R.drawable.disliked);
        like.setBackgroundResource(R.drawable.like_button);
        db.positionDao().likePosition(position.getId(), -1);
    }

    public void info(View view)
    {
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("description",position.getDescription());
        intent.putExtra("position",position.getPosition());
        startActivity(intent);
    }

}
