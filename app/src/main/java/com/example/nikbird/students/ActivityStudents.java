package com.example.nikbird.students;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.nikbird.students.fragments.FragmentStudents;

public class ActivityStudents extends AppCompatActivity {

    private FragmentStudents mFragmentStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        mFragmentStudents = (FragmentStudents) getSupportFragmentManager().findFragmentById(R.id.fragmentStudents);
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
