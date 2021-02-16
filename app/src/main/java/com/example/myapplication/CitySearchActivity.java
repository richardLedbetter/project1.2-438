package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;


//Don't forget to uncomment the SingleCity






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

        //==============================================
        //This code is for testing purposes.
        //==============================================
        City city1 = new City("First City", 35);
        City city2 = new City("Second City", 48);
        City city3 = new City("Third City", 63);

        List<City> cityList =  new ArrayList<City>();
        cityList.add(city1);
        cityList.add(city2);
        cityList.add(city3);


    }
}
//=============================================================================