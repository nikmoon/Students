package com.example.nikbird.students;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.nikbird.students.adapters.AdapterLessons;
import com.example.nikbird.students.managers.ManagerLessons;

import java.util.List;

import nikpack.Students.Interfaces.ILesson;

public class ActivityLessons extends AppCompatActivity {

    private RecyclerView mViewLessons;
    private volatile List<ILesson> mLessons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);

        mLessons = ManagerLessons.getInstance().getLessons();
        mViewLessons = (RecyclerView) findViewById(R.id.rvLessons);
        mViewLessons.setAdapter(new AdapterLessons(mLessons));
    }
}
