package com.mohbou.learningroom.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.mohbou.learningroom.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertAll(List<User> users);

    @Insert
    void insertAll(User...users);

    @Query("SELECT COUNT(*) from user")
    int usersCount();

    @Query("SELECT * from user")
    List<User>  allUsers();
}
