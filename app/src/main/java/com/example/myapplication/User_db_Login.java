package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//=======================================
//User_db_Login class
//=======================================
@Entity
public class User_db_Login {
    @PrimaryKey
    @NonNull
    public String userID;

    @ColumnInfo(name = "password")
    public String password;

}
//=======================================