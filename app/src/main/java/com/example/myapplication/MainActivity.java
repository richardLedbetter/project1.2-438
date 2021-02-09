package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {
    AppDatabase db;
    User local_user;
    Thread T_database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        local_user = User.getInstance(this.getApplicationContext());
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();
        Button Login = findViewById(R.id.LogIN);
        Login.setOnClickListener(v1->{
            if(log_in()){
                Intent intent = new Intent(this.getApplication(),hub.class);
                startActivity(intent);
            }
        });


    }
    private boolean log_in(){
        database_interface local_inst = local_user.db.userDao();
        EditText ID = findViewById(R.id.ID);
        EditText Password = findViewById(R.id.Password);
        User_db_Login tmp_user = new User_db_Login();
        tmp_user.password = "password";
        tmp_user.userID = "admin";
        AtomicBoolean login_status = new AtomicBoolean(false);
        Runnable check = ()->{
            local_inst.insert(tmp_user);
            local_user.setID(ID.getText().toString());
            local_user.setPassword( Password.getText().toString());
            if(local_inst.login(local_user.getID(),local_user.getPassword())!=null){
                login_status.set(true);
            }
        };
        T_database = new Thread(check);
        T_database.start();
        try {
            T_database.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return login_status.get();
    }

}