package com.mohbou.learningroom.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mohbou.learningroom.R;


public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView firstName;
    public TextView lastName;

    public ViewHolder(View itemView) {
        super(itemView);
        firstName = itemView.findViewById(R.id.firstName);
        lastName =itemView.findViewById(R.id.lastName);
    }
}
