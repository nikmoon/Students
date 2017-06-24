package com.example.nikbird.students;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectionActivity extends AppCompatActivity {

    private Button buttonSelectStudents;
    private Button buttonSelectGroups;
    private Button buttonSelectLessons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        buttonSelectStudents = (Button) findViewById(R.id.btnSelectStudents);
        buttonSelectGroups = (Button) findViewById(R.id.btnSelectGroups);
        buttonSelectLessons = (Button) findViewById(R.id.btnSelectLessons);

        buttonSelectStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), StudentsActivity.class);
                startActivity(intent);
            }
        });

        buttonSelectGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), GroupsActivity.class);
                startActivity(intent);
            }
        });

        buttonSelectLessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LessonsActivity.class);
                startActivity(intent);
            }
        });
    }
}
