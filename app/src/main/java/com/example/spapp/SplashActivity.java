package com.example.spapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.text.format.DateUtils;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;

import com.example.spapp.database.AppDatabase;
import com.example.spapp.models.PopulatedPositions;
import com.example.spapp.models.Position;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.TimeZone;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN_TIMEOUT = 2000;
    private AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);

        getApplicationContext().deleteDatabase("dsp_db");

        db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "dsp_db").allowMainThreadQueries().build();


         Date newDate = new Date();
         SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
         String currDate = sdf.format(new Date());

         try {
         newDate = sdf.parse(currDate);
         } catch (ParseException e) {
         e.printStackTrace();
         }

        assert newDate != null;
        long time = newDate.getTime() - (newDate.getTime() % DateUtils.DAY_IN_MILLIS);
        boolean db_exists = db.positionDao().getAll().isEmpty();

        if (db_exists)
        {
            initializePositions();
        }
        else if(db.populatedPositionsDao().checkday(time + (DateUtils.WEEK_IN_MILLIS * 5)) == null)
        {
            createMorePositions(time, db.populatedPositionsDao().getAll().size());
        }


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Shows splash screen for 2 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);

                startActivity(intent);

                finish();
            }
        }, SPLASH_SCREEN_TIMEOUT);

        // Initializes the ads in the app
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
    }

    // only runs once on first time opening app, parses csv and call populate database
    private void initializePositions() {
        List<Position> allPositions = new ArrayList<>();
        List<PopulatedPositions> popPositions = new ArrayList<>();

        Date startDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy", Locale.getDefault());
        String currDate = sdf.format(new Date());
        try {
            startDate = sdf.parse(currDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String currLine = "";
        String split = ",";

        try {
            InputStream is = getResources().openRawResource(R.raw.rescraped);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            currLine = br.readLine();

            int i = 0;
            List<Integer> shuffledList = createSuffledNumbers(2020);

            while ((currLine = br.readLine()) != null) {
                String[] position = currLine.split(split, 3);
                int id = Integer.parseInt((position[1].split(". ", 2))[0]);
                String position_name = (position[1].split(". ", 2))[1];
                String description = position[2];
                long useThis = ((DateUtils.DAY_IN_MILLIS * (shuffledList.get(i) - 1)) + startDate.getTime());
                long day = useThis - (useThis % DateUtils.DAY_IN_MILLIS);

                Position positionTBA = new Position();
                PopulatedPositions pop_pos = new PopulatedPositions();

                positionTBA.setId(id);
                positionTBA.setPosition(position_name);
                positionTBA.setDescription(description);
                positionTBA.setDay(day);
                positionTBA.setImage("pos" + id);
                allPositions.add(positionTBA);

                pop_pos.setPos_id(id);
                pop_pos.setDate_assigned(day);
                popPositions.add(pop_pos);

                i++;

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }

        populateDatabase(allPositions, popPositions);
    }

    private void createMorePositions(long time, int seed) {
        List<PopulatedPositions> popPositions = new ArrayList<>();
        List<Position> positions = db.positionDao().getAll();

        int i = 0;
        List<Integer> shuffledList = createSuffledNumbers(seed);

           for(Position pos : positions)
           {
                int id = pos.getId();
                long dateAssigned = (DateUtils.DAY_IN_MILLIS * shuffledList.get(i)) + time;
                PopulatedPositions pop_pos = new PopulatedPositions();
                pop_pos.setPos_id(id);
                pop_pos.setDate_assigned(dateAssigned);
                popPositions.add(pop_pos);

                i++;
            }

        updateDatabase(popPositions);
    }


    private List<Integer> createSuffledNumbers (int seed) {
        List<Integer> firstShuffle = new ArrayList<>();
        List<Integer> lastShuffle = new ArrayList<>();

        int base = (seed / 10) % 400;

        int a = 0;
        int b = base - 1;
        int c = base;
        int d = 399;

        int counter = 0;

        int aPartition = (b - a) / 2;
        int aCounter = 0;
        int bPartition = (b - a) / 2;
        int bCounter = 0;
        int cPartition = (d - c) / 2;
        int cCounter = 0;
        int dPartition = (d - c) / 2;
        int dCounter = 0;

        a = a + aPartition;
        d = d - dPartition;

        while (counter < 400) {
            if (cCounter <= cPartition) {
                firstShuffle.add(c);
                c++;
                cCounter++;
                counter++;
            }
            if (dCounter <= dPartition) {
                firstShuffle.add(d);
                d++;
                dCounter++;
                counter++;
            }
            if (aCounter <= aPartition) {
                firstShuffle.add(a);
                a--;
                aCounter++;
                counter++;
            }
            if (bCounter <= bPartition) {
                firstShuffle.add(b);
                b--;
                bCounter++;
                counter++;
            }
        }

        a = 0 + aPartition;
        b = base - 1;
        c = base;
        d = 399 - dPartition;

        counter = 0;
        aCounter = 0;
        bCounter = 0;
        cCounter = 0;
        dCounter = 0;

        while (counter < 400) {
            if (cCounter <= cPartition) {
                lastShuffle.add(firstShuffle.get(c));
                c++;
                cCounter++;
                counter++;
            }
            if (dCounter <= dPartition) {
                lastShuffle.add(firstShuffle.get(d));
                d++;
                dCounter++;
                counter++;
            }
            if (aCounter <= aPartition) {
                lastShuffle.add(firstShuffle.get(a));
                a--;
                aCounter++;
                counter++;
            }
            if (bCounter <= bPartition) {
                lastShuffle.add(firstShuffle.get(b));
                b--;
                bCounter++;
                counter++;
            }
        }

        return lastShuffle;
    }

    // Runs only first time app starts
    private void populateDatabase(List<Position> position_list,List<PopulatedPositions> pop_list) {
        for(Position pos : position_list)
        {
            db.positionDao().insert(pos);
        }
        for(PopulatedPositions pos : pop_list)
        {
            db.populatedPositionsDao().insert(pos);
        }
        List<Position> posss = db.positionDao().getAll();
        List<PopulatedPositions> pop_posss = db.populatedPositionsDao().getAll();
    }

    // Updates the PopulatedPositions table with 400 new days when it reaches a certain date
    private void updateDatabase(List<PopulatedPositions> pop_list) {

        for(PopulatedPositions pos : pop_list)
        {
            db.populatedPositionsDao().insert(pos);
        }
    }

    private static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }
}




