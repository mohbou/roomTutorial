package com.mohbou.learningroom.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mohbou.learningroom.R;
import com.mohbou.learningroom.model.User;

import java.util.List;


public class ViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    public ViewAdapter(List<User> users) {
        this.users = users;
    }

    public List<User> users;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = users.get(position);
        holder.firstName.setText(user.getFirstName());
        holder.lastName.setText(user.getLastName());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
