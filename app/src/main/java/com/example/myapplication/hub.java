package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

public class hub extends AppCompatActivity {

    ScrollView sc;
    LinearLayout ll;
    AppDatabase db;
    User local_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);
        local_user = User.getInstance(this.getApplicationContext());
    }
    public void load_user(){
        database_interface local_inst = local_user.db.userDao();
        List<User_db> table =(local_inst.getAllCities(local_user.getID()));
        for(User_db itr:table){
            local_user.getCities().add(itr.city);
        }
    }
}