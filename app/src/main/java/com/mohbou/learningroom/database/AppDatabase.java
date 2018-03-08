package com.mohbou.learningroom.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.mohbou.learningroom.model.User;

@Database(entities = {User.class},version = 1)
public abstract class AppDatabase extends RoomDatabase{

    private static volatile AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
            instance =  Room.databaseBuilder(context.getApplicationContext(),
                             AppDatabase.class,"myapp-db")
                            .allowMainThreadQueries()
                            .build();
            }
        }
        return instance;
    }

    public static void destroyMyInstance() {
        instance = null;
    }

    public abstract UserDao userDao();

}
