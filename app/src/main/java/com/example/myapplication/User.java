package com.example.myapplication;

import java.util.ArrayList;

public class User {
    //Singleton
    public String ID;
    public String password;
    public ArrayList<String> cities;

    private static User instance;

    private User(String ID, String password){
        this.ID = ID;
        this.password = password;
    }

    public static User getInstance(){
        if(instance == null){
            instance = new User("defaultID","defaultpass");
        }
        return instance;
    }

    public boolean signIn(String ID, String password)
    {
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
