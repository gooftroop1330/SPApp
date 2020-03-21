package com.example.spapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.spapp.database.AppDatabase;
import com.example.spapp.models.Position;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

public class SplashActivity extends AppCompatActivity
{
    private static int SPLASH_SCREEN_TIMEOUT = 2000;
    AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        createPositionJSON();

        setContentView(R.layout.splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {

                Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);

                startActivity(intent);

                finish();
            }
        }, SPLASH_SCREEN_TIMEOUT);

        MobileAds.initialize(this, new OnInitializationCompleteListener()
        {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus)
            {
            }
        });
    }

    public void createPositionJSON()
    {
        String currLine = "";
        String split = ",";
        JSONArray allPositionInfo = new JSONArray();
        try
        {
            InputStream is = getResources().openRawResource(R.raw.rescraped);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            currLine = br.readLine();
            while ((currLine = br.readLine()) != null)
            {
                String[] position = currLine.split(split, 3);
                int id = Integer.parseInt((position[1].split(". ", 2))[0]);
                String position_name = (position[1].split(". ", 2))[1];
                String description = position[2];

                JSONObject positionInfo = new JSONObject();
                positionInfo.put("id", id);
                positionInfo.put("position_name", position_name);
                positionInfo.put("description", description);
                allPositionInfo.put(positionInfo);
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    private void populateDatabase(List<Position> position_list)
    {
        db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "dsp_db").build();
        for(Position pos : position_list)
        {
            db.positionDao().insert(pos);
        }
    }
}



