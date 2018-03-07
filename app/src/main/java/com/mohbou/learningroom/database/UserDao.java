package com.mohbou.learningroom.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.mohbou.learningroom.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<User> users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(User...users);

    @Query("SELECT COUNT(*) from user")
    int usersCount();

    @Query("SELECT * from user ORDER BY firstName")
    List<User>  allUsers();

    @Query("SELECT * FROM user WHERE id= :id")
    User findById(String id);
}
