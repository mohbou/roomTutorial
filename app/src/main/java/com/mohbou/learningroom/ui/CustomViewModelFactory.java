package com.mohbou.learningroom.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.mohbou.learningroom.database.UserDao;

public class CustomViewModelFactory  implements ViewModelProvider.Factory {

    private final UserDao userDao;

    public CustomViewModelFactory(UserDao userDao) {
        this.userDao = userDao;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new UserViewModel(userDao);
    }
}
