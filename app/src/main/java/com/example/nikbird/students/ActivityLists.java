package com.example.nikbird.students;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.nikbird.students.fragments.FragmentGroups;
import com.example.nikbird.students.fragments.FragmentLessons;
import com.example.nikbird.students.fragments.FragmentListSelection;
import com.example.nikbird.students.fragments.FragmentStudents;


public class ActivityLists extends AppCompatActivity implements FragmentListSelection.OnSelectSpecificList {

    public static final String TAG_STUDENTS = "TAG_STUDENTS";
    public static final String TAG_GROUPS = "TAG_GROUPS";
    public static final String TAG_LESSONS = "TAG_LESSONS";

    private Fragment mStudents;
    private Fragment mGroups;
    private Fragment mLessons;
    private Fragment mVisible;

    private View mLayoutLists;
    private View mLayoutSpecificList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);

        ActionBar actionBar = getSupportActionBar();

        mLayoutLists = findViewById(R.id.layoutList);
        mLayoutSpecificList = findViewById(R.id.layoutSpecificList);

        if (getSupportFragmentManager().findFragmentById(R.id.layoutList) == null)
            onFirstCreate();
        else
            restoreFragments();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ((FragmentStudents)mStudents).filterByLastName(newText.toLowerCase());
                return true;
            }
        });
        return true;
    }

    private void onFirstCreate() {
        mStudents = new FragmentStudents();
        mGroups = new FragmentGroups();
        mLessons = new FragmentLessons();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.layoutList, new FragmentListSelection())
                .add(R.id.layoutSpecificList, mStudents, TAG_STUDENTS).hide(mStudents)
                .add(R.id.layoutSpecificList, mGroups, TAG_GROUPS).hide(mGroups)
                .add(R.id.layoutSpecificList, mLessons, TAG_LESSONS).hide(mLessons)
                .commitNow();
        mVisible = mStudents;
    }

    private void restoreFragments() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        mStudents = fragmentManager.findFragmentByTag(TAG_STUDENTS);
        mGroups = fragmentManager.findFragmentByTag(TAG_GROUPS);
        mLessons = fragmentManager.findFragmentByTag(TAG_LESSONS);

        // вычисляем видимый фрагмент
        if (!mStudents.isHidden())
            mVisible = mStudents;
        else if (!mLessons.isHidden())
            mVisible = mLessons;
        else
            mVisible = mGroups;
    }


    @Override
    public void onSelectSpecificList(int buttonID) {

        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragManager.beginTransaction();

        // в зависимости от нажатой кнопки делаем видимым соотв. фрагмент
        switch (buttonID) {
            case R.id.btnStudents: {
                transaction.hide(mVisible).show(mStudents).commit();
                mVisible = mStudents;
                break;
            }
            case R.id.btnGroups: {
                transaction.hide(mVisible).show(mGroups).commit();
                mVisible = mGroups;
                break;
            }
            case R.id.btnLessons: {
                transaction.hide(mVisible).show(mLessons).commit();
                mVisible = mLessons;
                break;
            }
        }
        if (!isLandscape()) {
            mLayoutLists.setVisibility(View.INVISIBLE);
            mLayoutSpecificList.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onBackPressed() {

        // портретная ориентация + отображается конкретный список
        if (!isLandscape() && mLayoutSpecificList.getVisibility() == View.VISIBLE) {

            // возвращаемся к общему списку
            mLayoutSpecificList.setVisibility(View.INVISIBLE);
            mLayoutLists.setVisibility(View.VISIBLE);
        }
        else {
            super.onBackPressed();
        }
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }
}
