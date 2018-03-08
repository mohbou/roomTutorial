package com.mohbou.learningroom.ui;

import android.arch.lifecycle.ViewModel;

import com.mohbou.learningroom.database.UserDao;
import com.mohbou.learningroom.model.User;

import java.util.List;

import io.reactivex.Single;

class UserViewModel  extends ViewModel{

    private final UserDao userDao;

    public UserViewModel(UserDao userDao) {
        this.userDao = userDao;
    }

    public Single<Integer> getCount() {
        return userDao.usersCount();
    }

    public Single<List<User>> allUsers() {
        return userDao.allUsers();

    }
    public Single<User> findUserById(String userId) {
        return userDao.findById(userId);
    }

    public void insertUsers(final List<User> users) {
        userDao.insertAll(users);
    }

}
