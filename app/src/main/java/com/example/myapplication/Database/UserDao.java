package com.example.myapplication.Database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RoomWarnings;

@Dao

public interface UserDao {
    @Query("SELECT * FROM teams")
    List<Teams> getAll();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT imageId,teamName FROM teams where teamName LIKE  :teamName")
    Teams getImage(String teamName);

    @Query("SELECT COUNT(*) from teams")
    int countUsers();

    @Insert
    void insertAll(Teams... teams);

    @Delete
    void delete(Teams teams);
}
