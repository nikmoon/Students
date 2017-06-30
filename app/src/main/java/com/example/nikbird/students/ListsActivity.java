package com.example.nikbird.students;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.nikbird.students.fragments.GroupList;
import com.example.nikbird.students.fragments.LessonList;
import com.example.nikbird.students.fragments.ListSelection;
import com.example.nikbird.students.fragments.StudentList;


public class ListsActivity extends AppCompatActivity implements ListSelection.OnSelectSpecificList {

    private StudentList fragmentStudents;
    private GroupList fragmentGroups;
    private LessonList fragmentLessons;

    private View layoutList;
    private View layoutSpecificList;

    private Fragment currentVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);

        layoutList = findViewById(R.id.layoutList);
        layoutSpecificList = findViewById(R.id.layoutSpecificList);

        FragmentManager fragManager = getSupportFragmentManager();

        // Активити загружена первый раз
        if (fragManager.findFragmentById(R.id.layoutList) == null) {
            fragmentStudents = new StudentList();
            fragmentGroups = new GroupList();
            fragmentLessons = new LessonList();

            fragManager.beginTransaction()
                    .add(R.id.layoutList, new ListSelection())
                    .add(R.id.layoutSpecificList, fragmentStudents, "students")
                    .add(R.id.layoutSpecificList, fragmentGroups, "groups")
                    .add(R.id.layoutSpecificList, fragmentLessons, "lessons")
                    .hide(fragmentStudents)
                    .hide(fragmentGroups)
                    .hide(fragmentLessons)
                    .commitNow();
            currentVisible = fragmentStudents;
        }

        // активити создается, имея сохраненное состояние
        else {
            fragmentStudents = (StudentList) fragManager.findFragmentByTag("students");
            fragmentGroups = (GroupList) fragManager.findFragmentByTag("groups");
            fragmentLessons = (LessonList) fragManager.findFragmentByTag("lessons");

            // вычисляем видимый фрагмент
            if (!fragmentStudents.isHidden())
                currentVisible = fragmentStudents;
            else if (!fragmentLessons.isHidden())
                currentVisible = fragmentLessons;
            else
                currentVisible = fragmentGroups;
        }
    }

    @Override
    public void onSelectSpecificList(int buttonID) {

        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragManager.beginTransaction();

        // в зависимости от нажатой кнопки делаем видимым соотв. фрагмент
        switch (buttonID) {
            case R.id.btnStudents: {
                transaction
                        .hide(currentVisible)
                        .show(fragmentStudents)
                        .commit();
                currentVisible = fragmentStudents;
                break;
            }
            case R.id.btnGroups: {
                transaction
                        .hide(currentVisible)
                        .show(fragmentGroups)
                        .commit();
                currentVisible = fragmentGroups;
                break;
            }
            case R.id.btnLessons: {
                transaction
                        .hide(currentVisible)
                        .show(fragmentLessons)
                        .commit();
                currentVisible = fragmentLessons;
                break;
            }
       }
        if (!isLandscape()) {
            layoutList.setVisibility(View.INVISIBLE);
            layoutSpecificList.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onBackPressed() {

        // портретная ориентация + отображается конкретный список
        if (!isLandscape() && layoutSpecificList.getVisibility() == View.VISIBLE) {

            // возвращаемся к общему списку
            layoutSpecificList.setVisibility(View.INVISIBLE);
            layoutList.setVisibility(View.VISIBLE);
        }
        else {
            super.onBackPressed();
        }
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }
}
