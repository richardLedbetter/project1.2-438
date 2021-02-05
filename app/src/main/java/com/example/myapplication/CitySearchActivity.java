package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


//=============================================================================
//This view will be for searching for/displaying cities.
//
//Traits: "Search" Field: Field to search for a city.
//
//        Results Field: Field to display matching cities.
//          Behavior: Cities can be selectable in this list.
//
//        "Favorite" Button: Adds selected city to user's list of saved cities.
//
//        "Saved Cities" Button: Takes user to the SavedCitiesActivity.
//
//        "Logout" Button: Return user to the Login screen(MainActivity)
//=============================================================================
public class CitySearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_search);
    }
}
//=============================================================================