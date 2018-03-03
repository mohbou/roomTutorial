package com.mohbou.learningroom.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.mohbou.learningroom.R;
import com.mohbou.learningroom.database.AppDatabase;
import com.mohbou.learningroom.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoomActivity extends AppCompatActivity {


    private AppDatabase database;

    private RecyclerView recyclerView;
    private ViewAdapter viewAdapter;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        database = AppDatabase.getInstance(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        int userCount = database.userDao().usersCount();

        if(userCount>0) {
            Toast.makeText(this, "Users found", Toast.LENGTH_LONG).show();
            database.userDao().allUsers().stream().forEach(user ->System.out.println(user.getLastName()));
        }
        else {
            Toast.makeText(this, "Users not found - create users", Toast.LENGTH_LONG).show();

            users = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                User user= new User(UUID.randomUUID().toString(),"Moe "+i,"Joe "+i);
                users.add(user);
            }
            database.userDao().insertAll(users);
        }

        viewAdapter =  new ViewAdapter(database.userDao().allUsers());
        recyclerView.setAdapter(viewAdapter);


    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyMyInstance();
        super.onDestroy();
    }
}
