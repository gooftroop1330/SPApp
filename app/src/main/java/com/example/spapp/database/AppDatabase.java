package com.example.spapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.spapp.dao.PositionDao;
import com.example.spapp.models.Position;

@Database(entities = {Position.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract PositionDao positionDao();
}
