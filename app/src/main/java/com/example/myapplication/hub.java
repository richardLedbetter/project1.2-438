package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class hub extends AppCompatActivity {

    ScrollView sc;
    LinearLayout ll;
    AppDatabase db;
    User local_user;
    Button single_city_search;
    CityList currCityList;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onResume(){
        super.onResume();
        Runnable task1 = ()->{
            load_user();

        };
        Thread loader = new Thread(task1);
        loader.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);
        single_city_search =findViewById(R.id.singleSearch);
        local_user = User.getInstance(this.getApplicationContext());
        Runnable task1 = ()->{
            load_user();

        };
        Thread loader = new Thread(task1);
        loader.start();
        Button Logout = findViewById(R.id.Logout);
        Logout.setOnClickListener(v1->{
            User.RemoveInstance();
            finish();
        });


        single_city_search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(hub.this, CitySearchActivity.class);
                startActivity(intent);
            }
        });
    }

    public void add_city(){
        Intent intent = new Intent(this.getApplicationContext(),CitySearchActivity.class);
        startActivity(intent);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void load_user(){
        /*grab city names from api
        * fill user table
        *
        * */

        database_interface local_inst = local_user.db.userDao();
        List<User_db> table =(local_inst.getAllCities(local_user.getID()));
        if (local_user.getCities().isEmpty()){
            for(User_db itr:table){
                local_user.getCities().add(itr.city);
            }
        }
        String builder ="";
        for (String itr:local_user.getCities()){
            builder = builder+itr+",";
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherApi weatherAPI = retrofit.create(WeatherApi.class);

        String apiKey = "d0355916b97057129ebc1ceb5327cd68"; //PUT API KEY HERE
        String unitsMeasure = "imperial";

        Button Search = findViewById(R.id.Search);
            Call<CityList> call = weatherAPI.getUserCitiesQuery(builder,apiKey,unitsMeasure); //give IDs here

             Context screen = this.getApplicationContext();
            call.enqueue(new Callback<CityList>() {
                TextView textViewResult = new TextView(screen);
                @Override
                public void onResponse(Call<CityList> call, Response<CityList> response) {
                    if (!response.isSuccessful()) {
                        textViewResult.setText("Code: " + response.code());
                        return;
                    }
                        currCityList = response.body();
                      Log.d("test",currCityList.getList().get(0).getCityID());
                    fill_table();
                    //runOnUiThread(()->{ll.addView(textViewResult);});

                }
                @Override
                public void onFailure(Call<CityList> call, Throwable t) {
                    textViewResult.setText(t.getMessage());
                }
            });

        }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void fill_table(){

        LinearLayout ll = findViewById(R.id.favorites);
        try {
            for (SingleCity itr : currCityList.getList()) {
                Runnable R = () -> {
                    TextView t = new TextView(this);
                    String c = itr.getCityName()+'\n'+itr.getMainTemperture()+'\n';
                    Log.d("city",c);
                    t.setText(c);
                    t.setTextAlignment(t.TEXT_ALIGNMENT_CENTER);
                    ll.addView(t);
                };
                runOnUiThread(R);
            }
        }catch (Exception e){
            String itr = "You have not added any favorite cities";
            Runnable R = () -> {
                TextView t = new TextView(this);
                t.setText(itr);
                t.setTextAlignment(t.TEXT_ALIGNMENT_CENTER);
                ll.addView(t);
            };
            runOnUiThread(R);
        }

    }
}