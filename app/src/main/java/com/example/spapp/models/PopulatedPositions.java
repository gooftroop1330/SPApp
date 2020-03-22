package com.example.spapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "populated_positions")
public class PopulatedPositions
{
    @PrimaryKey
    public int id;

    @ForeignKey(entity = Position.class, parentColumns = "id", childColumns = "pos_id")
    @ColumnInfo(name = "pos_id")
    public int pos_id;

    @ColumnInfo(name = "date_assigned")
    public String date_assigned;
}
