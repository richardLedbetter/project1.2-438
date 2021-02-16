package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



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

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_search);

        textViewResult = findViewById(R.id.text_view_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherApi weatherAPI = retrofit.create(WeatherApi.class);

        String userCities = "524901,703448,2643743";
        String apiKey = ""; //PUT API KEY HERE
        String unitsMeasure = "imperial";

        Call<CityList> call = weatherAPI.getUserCitiesQuery(userCities,apiKey,unitsMeasure); //give IDs here

        call.enqueue(new Callback<CityList>() {
            @Override
            public void onResponse(Call<CityList> call, Response<CityList> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                CityList cities = response.body();
                List<SingleCity> temp = cities.getList();

                for (SingleCity city: temp){
                    String content = "";
                    content += "ID: " + city.getCityID() + "\n";
                    content += "Name: " + city.getCityName() + "\n";
                    content += "Temp: " + city.getMainTemperture() + "\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<CityList> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });



        Button Search = findViewById(R.id.Search);
        Search.setOnClickListener(v1->{

        });
        Button Add = findViewById(R.id.add);
        Add.setOnClickListener(v1->{
            /*add city*/
            finish();
        });
    }


}
//=============================================================================