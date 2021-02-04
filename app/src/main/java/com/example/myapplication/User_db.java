package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class User_db  {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;
}

