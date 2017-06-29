package com.example.nikbird.students;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;

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

        // заполняем менеджер только по необходимости
        if (managerStudents.getCount() == 0)
            Main.fillManagerStudents(managerStudents);

        studentsView = (RecyclerView) findViewById(R.id.vStudents);
        studentsAdapter = new StudentsAdapter(managerStudents);
        studentsView.setAdapter(studentsAdapter);
        searchView = (SearchView) findViewById(R.id.searchView);

        // при каждом изменении фильтра будем обновлять список студентов
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                studentsAdapter.filter(newText.toLowerCase());
                return true;
            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.setIconified(false);
            }
        });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
