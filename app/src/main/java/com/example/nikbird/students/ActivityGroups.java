package com.example.nikbird.students;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nikbird.students.fragments.FragmentGroups;

public class ActivityGroups extends AppCompatActivity {

    private FragmentGroups mFragmentGroups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        mFragmentGroups = (FragmentGroups) getSupportFragmentManager().findFragmentById(R.id.fragmentGroups);
    }
}
