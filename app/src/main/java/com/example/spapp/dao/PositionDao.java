package com.example.spapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.spapp.models.Position;

import java.util.List;

@Dao
public interface PositionDao
{

    @Query("SELECT * FROM position")
    List<Position> getAll();

    @Query("SELECT * FROM position WHERE id IN (:positionIds)")
    List<Position> loadAllByIds(int[] positionIds);

    @Query("SELECT * FROM position WHERE day IN (:positionDays)")
    List<Position> loadAllByDays(long[] positionDays);

    @Insert
    void insert(Position position);

    @Delete
    void delete(Position position);
}
