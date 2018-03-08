package com.mohbou.learningroom.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.mohbou.learningroom.model.User;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<User> users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(User...users);

    @Query("SELECT COUNT(*) from user")
    Single<Integer> usersCount();

    @Query("SELECT * from user ORDER BY firstName")
    Single<List<User>> allUsers();

    @Query("SELECT * FROM user WHERE id= :id")
    Single<User> findById(String id);
}
