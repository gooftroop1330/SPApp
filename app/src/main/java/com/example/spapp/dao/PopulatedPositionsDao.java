package com.example.spapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.spapp.models.PopulatedPositions;
import com.example.spapp.models.Position;

import java.util.List;

@Dao
public interface PopulatedPositionsDao
{

    @Query("SELECT * FROM populated_positions")
    List<PopulatedPositions> getAll();

    @Query("SELECT * FROM populated_positions WHERE id IN (:populatedPositionIds)")
    List<Position> loadAllByIds(int[] populatedPositionIds);

    @Query("SELECT * FROM populated_positions WHERE date_assigned IN (:populatePositionDates)")
    List<Position> loadAllByDays(long[] populatePositionDates);

    @Insert
    void insert(PopulatedPositions position);

    @Delete
    void delete(PopulatedPositions position);
}
