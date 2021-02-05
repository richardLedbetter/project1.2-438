package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

//===============================================================================================
//This view will be for listing saved cities.

//Traits: "List of Cities" Field: Display user's saved cities.
//          Behavior: Should display city names and their current weather. Cities are selectable.
//
//        "Delete City" Button: Deletes selected city from user list.
//
//        "City Search" Button: Take User to the CitySearchActivity.
//
//        "Logout" Button: Return user to the Login screen(MainActivity)
//===============================================================================================
public class SavedCitiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_cities);
    }
}
//===============================================================================================