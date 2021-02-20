package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

//=================================================================================================================
//Database interface
//=================================================================================================================
@Dao
public interface database_interface {

    //----------------------------------
    //"get all users"
    //----------------------------------
    @Query("SELECT * FROM User_db")
    List<User_db> getAll();
    //----------------------------------

    //-----------------------------------------------------
    //"Get all cities saved by *this* user."
    @Query("SELECT * FROM User_db WHERE userID = :userID")
    List<User_db> getAllCities(String userID);
    //-----------------------------------------------------

    //------------------------------------------------------------------------
    //"check if username exists"
    //------------------------------------------------------------------------
    @Query("SELECT * FROM User_db_Login WHERE userID LIKE :userID LIMIT 1")
    User_db_Login findByUserName(String userID);
    //------------------------------------------------------------------------

    //---------------------------------------------------------------------------------------------------
    //"Check if this username/password pairing is valid"
    //---------------------------------------------------------------------------------------------------
    @Query("SELECT * FROM user_db_login WHERE userID LIKE :userID AND password Like :password LIMIT 1")
    User_db_Login login(String userID,String password);
    //---------------------------------------------------------------------------------------------------

    //-----------------------------------------
    //"add city to user's list of saved cities"
    //-----------------------------------------
    @Insert
    void insertAll(User_db users);
    //----------------------------------------

    //------------------------------------
    //"insert user into user database"
    //------------------------------------
    @Insert
    void insert (User_db_Login user_db);
    //------------------------------------

    //------------------------------------------------
    //"delete city from user's list of saved cities."
    //------------------------------------------------
    @Delete
    void delete(User_db user_db);
    //------------------------------------------------

    //-----------------------------------------
    //"delete user from user database."
    //-----------------------------------------
    @Delete//delete user
    void delete(User_db_Login user_db_login);
    //-----------------------------------------
}
//=================================================================================================================