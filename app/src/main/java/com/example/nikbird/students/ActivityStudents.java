package com.example.nikbird.students;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.nikbird.students.fragments.FragmentStudents;

import nikpack.utils.NameString;

public class ActivityStudents extends AppCompatActivity {

    public static final String EXTRA_GROUP_NAME = "com.example.nikbird.students.ActivityStudents.GROUP_NAME";
    public static final String EXTRA_GROUP_YEAR = "com.example.nikbird.students.ActivityStudents.GROUP_YEAR";

    private FragmentStudents mFragmentStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        mFragmentStudents = (FragmentStudents) getSupportFragmentManager().findFragmentById(R.id.fragmentStudents);
        filterByGroup(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        filterByGroup(intent);
    }

    private void filterByGroup(Intent intent) {
        String groupName = intent.getStringExtra(EXTRA_GROUP_NAME);
        if (groupName != null)
            mFragmentStudents.filterByGroup_Permanent(new NameString(groupName), intent.getIntExtra(EXTRA_GROUP_YEAR, -1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_students, menu);
        MenuItem searchItem = menu.findItem(R.id.search_last_name);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mFragmentStudents.filterByLastName_StartsWith(newText);
                return true;
            }
        });
        return true;
    }
}
