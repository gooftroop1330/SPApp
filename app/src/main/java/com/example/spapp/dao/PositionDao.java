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

    @Query("SELECT * FROM position WhERE id = (:id)")
    Position getPosition(int id);

    @Query("SELECT * FROM position WHERE `like` = 1")
    List<Position> getLikedPositions();

    @Query("UPDATE position SET `like` = (:like) WHERE id = (:id)")
    void likePosition(int id, int like);

    @Insert
    void insert(Position position);

    @Delete
    void delete(Position position);
}
