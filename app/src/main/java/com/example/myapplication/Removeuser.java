package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class Removeuser extends AppCompatActivity {
    User tmp_user;
    database_interface db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_removeuser);
        tmp_user = User.getInstance(this);
        db = tmp_user.db.userDao();
        Button remover = findViewById(R.id.remover);
        remover.setOnClickListener(v1->{
            EditText tmp2 = findViewById(R.id.userremover);
            try {
                Thread task1 = new Thread(()->{
                    User_db_Login t = db.findByUserName(tmp2.getText().toString());
                    if (t==null){
                        Log.d("couldn't","find user"+tmp2.getText().toString());
                    }else{
                        Log.d("user", t.userID);
                        db.delete(t);
                    }
                });
                task1.start();
            }catch(Exception e){
                Log.d("error","error");
                e.printStackTrace();
            }
        });
    }

}