package com.example.spapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "position")
public class Position
{
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "position_name")
    public String position;

    @ColumnInfo(name="description")
    public String description;

    @ColumnInfo(name = "day")
    public int day;

}
