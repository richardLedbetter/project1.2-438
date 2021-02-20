package com.example.myapplication;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

//===============================================================================================
//User class
//===============================================================================================
public class User {
    //----------------------------------
    //Singleton
    //----------------------------------
    public String ID;
    public String password;
    public ArrayList<String> cities;
    public AppDatabase db;
    private Context activity;

    private static User instance;
    //----------------------------------

    //--------------------------------------------------------------------
    //getters and setters
    //--------------------------------------------------------------------
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getCities() {
        return cities;
    }

    public static User getInstance(Context activity2){
        if(instance == null){
            instance = new User("defaultID","defaultpass",activity2);
        }
        return instance;
    }
    //--------------------------------------------------------------------

    //-----------------------------------------------------------
    //constructor
    //-----------------------------------------------------------
    private User(String ID, String password,Context activity2){
        this.ID = ID;
        this.password = password;
        this.activity = activity2;
        db = Room.databaseBuilder(activity ,
                AppDatabase.class, "database-name").build();
        this.cities = new ArrayList<String>();
    }
    //-----------------------------------------------------------

    //-------------------------------
    //clear out past user
    //-------------------------------
    public void RemoveInstance(){
        this.cities.clear();
    }
    //-------------------------------
}
//===============================================================================================
