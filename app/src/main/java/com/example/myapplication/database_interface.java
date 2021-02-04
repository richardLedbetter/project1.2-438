package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface database_interface {
    @Query("SELECT * FROM User_db")
    List<User_db> getAll();

    @Query("SELECT * FROM user_db WHERE uid IN (:userIds)")
    List<User_db> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user_db WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    User_db findByName(String first, String last);

    @Insert
    void insertAll(User_db... users);

    @Delete
    void delete(User_db user_db);
}