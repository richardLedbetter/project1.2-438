package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
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
    private Button searchButton;
    private EditText single_city;
    SingleCity currCity; //this is an empty city obj; search will fill it; add will get data from it
    Call<SingleCity> call;
    String apiKey = "d0355916b97057129ebc1ceb5327cd68";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_search);

        textViewResult = findViewById(R.id.text_view_result);
        searchButton = findViewById(R.id.Search);
        single_city = findViewById(R.id.CityName);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherApi weatherAPI = retrofit.create(WeatherApi.class);

        String userCities = "524901,703448,2643743";
        //String apiKey = "540c3fa023f7aa3ee8314f9fd1f6a425"; //PUT API KEY HERE
        String unitsMeasure = "imperial";


        call = weatherAPI.getSingleCityQuery(userCities,apiKey,unitsMeasure); //give IDs here

        call.enqueue(new Callback<SingleCity>() {
            @Override
            public void onResponse(Call<SingleCity> call, Response<SingleCity> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                currCity = response.body();
                currCity.getCityID();
            }

            @Override
            public void onFailure(Call<SingleCity> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

        Button Search = findViewById(R.id.Search);
        Search.setOnClickListener(v1->{

            call = weatherAPI.getSingleCityQuery(single_city.getText().toString(),apiKey,unitsMeasure);
            call.enqueue(new Callback<SingleCity>() {
                @Override
                public void onResponse(Call<SingleCity> call, Response<SingleCity> response) {
                    if (!response.isSuccessful()) {
                        textViewResult.setText("Code: " + response.code());
                        return;
                    }

                    currCity = response.body();
                    currCity.getCityID();
                    textViewResult.setText(currCity.getCityID());
                }

                @Override
                public void onFailure(Call<SingleCity> call, Throwable t) {
                    textViewResult.setText(t.getMessage());
                }
            });

            // cityName = "524901,703448,2643743";
            //I feel that usercities should be swapped for a cityNames variable, like in the example above

            //String apiKey = "540c3fa023f7aa3ee8314f9fd1f6a425"; //PUT API KEY HERE
           // String unitsMeasure = "imperial";

           // Call<CityList> call = weatherAPI.getUserCitiesQuery(userCities,apiKey,unitsMeasure); //give IDs here

        });

        Button Add = findViewById(R.id.add);
        Add.setOnClickListener(v1->{
           Runnable task1 = ()->{
               try {
                   add_city();
               }catch (Exception e){
                   Log.d("add","failed");
                   e.printStackTrace();
                   Log.d("add","failed");
               }
           };
           Thread thread_1 = new Thread(task1);
           thread_1.start();
            finish();
        });
    }
    public void add_city(){
        User curr_user = User.getInstance(this.getApplicationContext());
        User_db tmp_user = new User_db();
        database_interface db = curr_user.db.userDao();
        ArrayList<String> tmp = curr_user.getCities();
        if(currCity==null){
            Log.d("null","currCity");
        }else if (tmp==null){
            Log.d("null","tmp");
        }
        tmp.add(currCity.getCityID());
        tmp_user.city = currCity.getCityID();
        ArrayList counter = (ArrayList) db.getAll();
        tmp_user.count = counter.size();
        tmp_user.userID = curr_user.getID();
        try{
            Log.d("add","city");
            db.insertAll(tmp_user);
            Log.d("add","city");
        }catch(Exception e){
            Log.d("add","failed");
        }
    }


}
//=============================================================================