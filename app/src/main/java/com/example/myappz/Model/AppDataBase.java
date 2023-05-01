package com.example.myappz.Model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Tasks.class,Days.class},exportSchema = false,version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public static AppDataBase appDataBase;

    public static AppDataBase getAppDataBase(Context context) {
        appDataBase= Room.databaseBuilder(context,AppDataBase.class,"dbMain")
                .allowMainThreadQueries()
                .build();
        return appDataBase;
    }
    public abstract AppDao getDao();
}
