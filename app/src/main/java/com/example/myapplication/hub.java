package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class hub extends AppCompatActivity {

    ScrollView sc;
    LinearLayout ll;
    AppDatabase db;
    User local_user;



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);
        local_user = User.getInstance(this.getApplicationContext());
        Runnable task1 = ()->{
            load_user();
            fill_table();
        };
        Thread loader = new Thread(task1);
        loader.start();
        Button Logout = findViewById(R.id.Logout);
        Logout.setOnClickListener(v1->{
            User.RemoveInstance();
            finish();
        });
    }
    public void load_user(){
        /*grab city names from api
        * fill user table
        *
        * */



        database_interface local_inst = local_user.db.userDao();
        List<User_db> table =(local_inst.getAllCities(local_user.getID()));
        for(User_db itr:table){
            local_user.getCities().add(itr.city);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void fill_table(){
        LinearLayout ll = findViewById(R.id.favorites);
        try {
            for (String itr : local_user.getCities()) {
                Runnable R = () -> {
                    TextView t = new TextView(this);
                    t.setText(itr);
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