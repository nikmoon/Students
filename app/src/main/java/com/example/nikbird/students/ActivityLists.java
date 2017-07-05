package com.example.nikbird.students;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import nikpack.Main;


public class ActivityLists extends ActivityTestId {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);

        Main.fillManagers();
    }

    public void onClickStudents(View view) {
        startActivity(new Intent(this, ActivityStudents.class));
    }

    public void onClickGroups(View view) {
        startActivity(new Intent(this, ActivityGroups.class));
    }

    public void onClickLessons(View view) {
        startActivity(new Intent(this, ActivityLessons.class));
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }
}
