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

//========================================================================================================================================================
//CitySearchActivity: Will allow user to search for locations, as well as add them to their list of saved cities.
//========================================================================================================================================================
public class CitySearchActivity extends AppCompatActivity {

    //====================================================
    //Variables
    //====================================================
    private TextView textViewResult;
    private Button searchButton;
    private EditText single_city;

    //--------------------------------------------
    //global variable so search and add can access
    //--------------------------------------------
    private SingleCity currCity;
    //--------------------------------------------
    //====================================================


    @SuppressLint("WrongViewCast")

    //===============================================================================================================================================
    //onCreate class for CitySearchActivity
    //===============================================================================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_search);

        //-----------------------------------------------------
        //Components
        //-----------------------------------------------------
        textViewResult = findViewById(R.id.text_view_result);
        searchButton = findViewById(R.id.Search);
        single_city = findViewById(R.id.CityName);

        //-----------------------------------------------------------
        //Retrofit Setup
        //-----------------------------------------------------------
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //-----------------------------------------------------------

        //--------------------------------------------------------------------
        //Weather API Setup
        //--------------------------------------------------------------------

        WeatherApi weatherAPI = retrofit.create(WeatherApi.class);

        String apiKey = "d0355916b97057129ebc1ceb5327cd68"; //PUT API KEY HERE
        String unitsMeasure = "imperial";
        //---------------------------------------------------------------------

        //====================================================================================================================================
        //The Serach Button
        //====================================================================================================================================
        Button Search = findViewById(R.id.Search);

        //------------------------------------------------------------------------------------------------------------------------------
        //Search.setOnClickListener: Will take a provided string and search for data using api.
        //------------------------------------------------------------------------------------------------------------------------------
        Search.setOnClickListener(v1->{
            Call<SingleCity> call = weatherAPI.getSingleCityQuery(single_city.getText().toString(),apiKey,unitsMeasure); //give IDs here


            call.enqueue(new Callback<SingleCity>() {
                @Override
                public void onResponse(Call<SingleCity> call, Response<SingleCity> response) {
                    
                    //Should the response fail...
                    if (!response.isSuccessful()) {
                        textViewResult.setText("Code: " + response.code()); //...display the response code.
                        return;
                    }

                    //If response is successful...

                    currCity = response.body();                         //save result...
//                    currCity.getCityID();

                    String content = "";                                //create a string to store content...

                    content += "Name: "+currCity.getCityName()+"\n";    //...and then fill the content with the response!
                    content += "ID: "+currCity.getCityID()+"\n";
                    content += "Temp: "+currCity.getMainTemperture()+"\n\n";
                    textViewResult.append(content);
                }

                //Should request fail before response is given...
                @Override
                public void onFailure(Call<SingleCity> call, Throwable t) {
                    textViewResult.setText(t.getMessage()); //display error message.
                }
            });
        });
        //------------------------------------------------------------------------------------------------------------------------------
        //====================================================================================================================================


        //======================================================
        //The Add Button
        //======================================================
        Button Add = findViewById(R.id.add);

        //---------------------------------------------------
        //Add.setOnClickListener
        //---------------------------------------------------
        Add.setOnClickListener(v1->{
           Runnable task1 = ()->{   //attempt to call addCity
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
        //======================================================
    }
    //===============================================================================================================================================

    //===================================================================================
    //addCity(): Will attempt to add a city to the current user's list of saved cities.
    //===================================================================================
    public void add_city(){

        //----------------------------------------------------------------
        //Variables
        //----------------------------------------------------------------
        User curr_user = User.getInstance(this.getApplicationContext());
        User_db tmp_user = new User_db();
        database_interface db = curr_user.db.userDao();
  
        tmp_user.city = currCity.getCityID();
        ArrayList<User_db> counter = (ArrayList) db.getAll();
        //----------------------------------------------------------------

        //-------------------------------------------------------------------
        //Use temp_user to store values, and then add them to the database.
        //-------------------------------------------------------------------
        tmp_user.count = counter.get(counter.size()-1).count + 1;
        tmp_user.userID = curr_user.getID();
        try{
            Log.d("add",tmp_user.city);
            db.insertAll(tmp_user);
            Log.d("add","city");
        }catch(Exception e){
            e.printStackTrace();
            Log.d("add","failed");
        }
        //-------------------------------------------------------------------
    }
    //===================================================================================
    //========================================================================================================================================================

}