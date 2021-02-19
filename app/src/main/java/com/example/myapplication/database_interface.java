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

    @Query("SELECT * FROM User_db WHERE userID LIKE :userID")
    List<User_db> getAllCities(String userID);

    @Query("SELECT * FROM user_db WHERE count IN (:counts)")
    List<User_db> loadAllByIds(int[] counts);

    @Query("SELECT * FROM user_db WHERE userID LIKE :userID LIMIT 1")
    User_db findByName(String userID);

    @Query("SELECT * FROM User_db_Login WHERE userID LIKE :userID LIMIT 1")
    User_db_Login findByUserName(String userID);

    @Query("SELECT * FROM user_db_login WHERE userID LIKE :userID AND password Like :password LIMIT 1")
    User_db_Login login(String userID,String password);

    @Insert
    void insertAll(User_db... users);

    @Insert
    void insert (User_db_Login user_db);

    @Delete
    void delete(User_db user_db);

    @Delete
    void delete(User_db_Login user_db_login);
}