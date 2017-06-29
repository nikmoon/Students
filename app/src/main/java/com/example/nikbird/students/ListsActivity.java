package com.example.nikbird.students;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nikbird.students.fragment.GroupList;
import com.example.nikbird.students.fragment.LessonList;
import com.example.nikbird.students.fragment.ListSelection;
import com.example.nikbird.students.fragment.StudentList;


public class ListsActivity extends AppCompatActivity implements ListSelection.OnSelectSpecificList {

    private StudentList fragmentStudents;
    private GroupList fragmentGroups;
    private LessonList fragmentLessons;

    private Fragment currentVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);

        FragmentManager fragManager = getSupportFragmentManager();

        // создаем и добавляем все фрагменты
        if (fragManager.findFragmentById(R.id.layoutList) == null) {
            fragManager.beginTransaction()
                    .add(R.id.layoutList, new ListSelection())
                    .commit();
            fragmentStudents = new StudentList();
            fragmentGroups = new GroupList();
            fragmentLessons = new LessonList();

            fragManager.beginTransaction()
                    .add(R.id.layoutSpecificList, fragmentStudents, "students")
                    .hide(fragmentStudents)
                    .add(R.id.layoutSpecificList, fragmentGroups, "groups")
                    .hide(fragmentGroups)
                    .add(R.id.layoutSpecificList, fragmentLessons, "lessons")
                    .hide(fragmentLessons)
                    .commit();
        }
        else {
            fragmentStudents = (StudentList) fragManager.findFragmentByTag("students");
            fragmentGroups = (GroupList) fragManager.findFragmentByTag("groups");
            fragmentLessons = (LessonList) fragManager.findFragmentByTag("lessons");
        }

        if (!fragmentStudents.isHidden())
            currentVisible = fragmentStudents;
        else if (!fragmentLessons.isHidden())
            currentVisible = fragmentLessons;
        else
            currentVisible = fragmentGroups;
    }

    @Override
    public void onSelectSpecificList(int buttonID) {

        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragManager.beginTransaction();

        if (isLandscape()) {
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
        }
    }


    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }
}
