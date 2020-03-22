package com.example.spapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "populated_positions")
public class PopulatedPositions
{
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ForeignKey(entity = Position.class, parentColumns = "id", childColumns = "pos_id")
    @ColumnInfo(name = "pos_id")
    public int pos_id;

    @ColumnInfo(name = "date_assigned")
    public long date_assigned;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public int getPos_id()
    {
        return pos_id;
    }

    public void setPos_id(int pos_id)
    {
        this.pos_id = pos_id;
    }

    public long getDate_assigned()
    {
        return date_assigned;
    }

    public void setDate_assigned(long date_assigned)
    {
        this.date_assigned = date_assigned;
    }
}
