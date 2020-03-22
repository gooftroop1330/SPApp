package com.example.spapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.spapp.dao.PopulatedPositionsDao;
import com.example.spapp.dao.PositionDao;
import com.example.spapp.models.PopulatedPositions;
import com.example.spapp.models.Position;

@Database(entities = {Position.class, PopulatedPositions.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract PositionDao positionDao();
    public abstract PopulatedPositionsDao populatedPositionsDao();
}
