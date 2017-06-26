package com.example.nikbird.students;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;

import nikpack.Main;
import nikpack.Students.Managers.ManagerStudents;



public class StudentsActivity extends AppCompatActivity {

    private RecyclerView studentsView;
    private SearchView searchView;
    private ManagerStudents managerStudents;
    private StudentsAdapter studentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        managerStudents = ManagerStudents.getInstance();
        Main.fillManagerStudents(managerStudents);

        studentsView = (RecyclerView) findViewById(R.id.vStudents);
        studentsAdapter = new StudentsAdapter(managerStudents);
        studentsView.setAdapter(studentsAdapter);

        searchView = (SearchView) findViewById(R.id.searchLastName);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i("MYSEARCHVIEW", "Message: [" + newText + "]");
                studentsAdapter.filter(newText);
                return true;
            }
        });

    }
}
