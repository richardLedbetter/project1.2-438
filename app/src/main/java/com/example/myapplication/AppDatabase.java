package com.example.myapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;

//====================================================================
//AppDatabase class
//====================================================================
@Database(entities = {User_db.class,User_db_Login.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract database_interface userDao();
}
//====================================================================