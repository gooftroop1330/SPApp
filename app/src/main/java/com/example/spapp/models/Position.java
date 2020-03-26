package com.example.spapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "position")
public class Position
{
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "position_name")
    private String position;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "day")
    private long day;

    @ColumnInfo(name = "like")
    private int like;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDay() {
        return day;
    }

    public void setDay(long day) {
        this.day = day;
    }

    public int getLike() { return like; }

    public void setLike(int like) { this.like = like; }
}
