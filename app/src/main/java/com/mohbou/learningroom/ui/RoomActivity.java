package com.mohbou.learningroom.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.mohbou.learningroom.R;
import com.mohbou.learningroom.database.AppDatabase;
import com.mohbou.learningroom.database.UserDao;
import com.mohbou.learningroom.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RoomActivity extends AppCompatActivity {


    private AppDatabase database;

    private RecyclerView recyclerView;
    private ViewAdapter viewAdapter;
    private List<User> users = new ArrayList<>();
    private int userCount;

    private final CompositeDisposable mDisposable = new CompositeDisposable();
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        viewAdapter = new ViewAdapter(users);
        recyclerView.setAdapter(viewAdapter);

        allUsers();

    }

    @Override
    protected void onDestroy() {
        mDisposable.clear();
        AppDatabase.destroyMyInstance();
        super.onDestroy();
    }


    private UserDao getUserDao() {
        database = AppDatabase.getInstance(this);
        return database.userDao();

    }

    private int getCount() {

        mDisposable.add(getUserDao().usersCount()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(count -> userCount = count));

        return userCount;
    }

    //insert fake users if database is empty
    private List<User> allUsers() {

        mDisposable.add(getUserDao().allUsers()
                .map(users -> {
                            if (users.size() > 0) {
                                users.stream().forEach(user -> System.out.println("user Last name "+ user.getLastName()));
                            } else {
                                users = new ArrayList<>();
                                for (int i = 0; i < 10; i++) {
                                    User user = new User(UUID.randomUUID().toString(), "Moe " + i, "Joe " + i);
                                    users.add(user);
                                }
                                insertUsers(users);
                            }
                          return users;
                        }
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users1 -> {users = users1; viewAdapter.users =users;
                    viewAdapter.notifyDataSetChanged();findUserById();}));

        return users;
    }

    private User findUserById() {
        mDisposable.add(getUserDao().findById(users.get(0).getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(foundUser -> {
                    user = foundUser;
                    Log.d("TEST", "User find by ID using query for ID " + users.get(0).getId() + " is " + user.getFirstName() + " " + user.getLastName());
                }));

        return user;
    }

    private void insertUsers(final List<User> users) {
        mDisposable.add(Completable.fromAction(() ->
                getUserDao().insertAll(users))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> Log.d("test", "insertUsers: successful"),
                        throwable -> Log.e("test", "insertUsers: Unable to insert users", throwable)));

    }

}
