package com.example.myapplication.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomWarnings;

import java.util.List;

@Dao

public interface UserDao {
    @Query("SELECT * FROM teams")
    List<Teams> getAll();

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT imageId,teamName FROM teams where teamName LIKE  :teamName")
    Teams getImage(String teamName);

    @Query("SELECT COUNT(*) from teams")
    int countUsers();

//    @Query("SELECT uid from teams  where teamName LIKE  :teamName")
//    Teams getUid(String teamName);
//
//    @Query("SELECT teamName from teams  where uid LIKE  :uid")
//    Teams getTeamName(int uid);

    @Insert
    void insertAll(Teams... teams);

    @Delete
    void delete(Teams teams);
}
