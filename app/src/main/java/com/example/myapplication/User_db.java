package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//=====================================
//User_db class
//=====================================
@Entity
public class User_db  {
    @PrimaryKey
    public int count;

    @ColumnInfo(name = "userID")
    public String userID;

    @ColumnInfo(name = "city")
    public String city;
}
//=====================================

