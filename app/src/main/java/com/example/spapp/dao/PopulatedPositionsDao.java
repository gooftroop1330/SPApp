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
    List<PopulatedPositions> loadAllByIds(int[] populatedPositionIds);

    @Query("SELECT * FROM populated_positions WHERE date_assigned = (:date)")
    PopulatedPositions checkday(long date);

    @Query("SELECT pos_id FROM populated_positions WHERE date_assigned = (:time)")
    int getPositionID(long time);

    @Insert
    void insert(PopulatedPositions position);

    @Delete
    void delete(PopulatedPositions position);
}
