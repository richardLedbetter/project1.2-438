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

    @Query("SELECT * FROM User_db WHERE userID = :userID")
    List<User_db> getAllCities(String userID);


    @Query("SELECT * FROM User_db_Login WHERE userID LIKE :userID LIMIT 1")
    User_db_Login findByUserName(String userID);

    @Query("SELECT * FROM user_db_login WHERE userID LIKE :userID AND password Like :password LIMIT 1")
    User_db_Login login(String userID,String password);

    @Insert//insert city
    void insertAll(User_db users);

    @Insert//insert user
    void insert (User_db_Login user_db);

    @Delete//delete user city
    void delete(User_db user_db);

    @Delete//delete user
    void delete(User_db_Login user_db_login);
}