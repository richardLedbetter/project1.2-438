package com.example.myapplication;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class User {
    //Singleton
    public String ID;
    public String password;
    public ArrayList<String> cities;
    public AppDatabase db;
    private Context activity;

    private static User instance;

    public String getID() {
        return ID;
    }

    public void set_activity(Context a){
        activity=a;
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

    public void setCities(ArrayList<String> cities) {
        this.cities = cities;
    }

    private User(String ID, String password){
        this.ID = ID;
        this.password = password;
        db = Room.databaseBuilder(activity ,
                AppDatabase.class, "database-name").build();
    }
    private User(String ID, String password,Context activity2){
        this.ID = ID;
        this.password = password;
        this.activity = activity2;
        db = Room.databaseBuilder(activity ,
                AppDatabase.class, "database-name").build();
    }

    public static void RemoveInstance(){
        instance = null;
    }

    public static User getInstance(Context activity2){
        if(instance == null){
            instance = new User("defaultID","defaultpass",activity2);
        }
        return instance;
    }
    private void success_log_in(){
        database_interface userDao = db.userDao();
        List<User_db> users = userDao.getAll();
        String username = getID();
        for (User_db el : users){
            if(el.userID.equals(username)){
                cities.add(el.city);
            }
        }
    }
    public boolean signIn(String ID, String password)
    {

        if(false){
            success_log_in();
        }
        //needs database to continue
        return false;
    }

    public void signOut()
    {
        //needs database to continue
    }

    public void addCity(String singleCity){
        cities.add(singleCity);
    }

    private void delCity(String cityToDelete){
        cities.remove(cities.indexOf(cityToDelete));
    }

}
