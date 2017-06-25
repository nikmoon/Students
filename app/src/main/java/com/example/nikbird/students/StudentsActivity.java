package com.example.nikbird.students;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import nikpack.Main;
import nikpack.Students.Managers.ManagerStudents;



public class StudentsActivity extends AppCompatActivity {

    private RecyclerView studentsView;
    ManagerStudents managerStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        managerStudents = ManagerStudents.getInstance();
        Main.fillManagerStudents(managerStudents);

        studentsView = (RecyclerView) findViewById(R.id.vStudents);
        studentsView.setAdapter(new StudentsAdapter(managerStudents));

    }
}
