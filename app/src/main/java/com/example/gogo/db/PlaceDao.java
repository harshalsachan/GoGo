package com.example.gogo.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.gogo.model.PlaceEntity;

import java.util.List;

@Dao
public interface PlaceDao {

    @Query("SELECT * FROM places")
    List<PlaceEntity> getAll();

    @Query("SELECT * FROM places WHERE id = :id")
    PlaceEntity getById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PlaceEntity> places);

    @Query("DELETE FROM places")
    void deleteAll();
}
