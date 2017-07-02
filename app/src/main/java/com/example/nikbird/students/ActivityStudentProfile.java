package com.example.nikbird.students;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nikbird.students.adapters.AdapterStudentlContacts;
import com.example.nikbird.students.adapters.AdapterStudents;

import nikpack.Students.Interfaces.IStudent;
import com.example.nikbird.students.managers.ManagerStudents;

public class ActivityStudentProfile extends AppCompatActivity {

    private RecyclerView contactsView;
    private RecyclerView journalsView;
    private IStudent mStudent;
    private String studentPassport;

    private TextView tvStudentLastName;
    private TextView tvStudentFirstName;
    private TextView tvStudentMiddleName;
    private ImageView ivStudentPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        studentPassport = getIntent().getStringExtra(AdapterStudents.EXTRA_STUDENT_PASSPORT);
        if ("".equals(studentPassport)) {
            finish();
            return;
        }

        mStudent = ManagerStudents.getInstance().getStudent(studentPassport);

        tvStudentFirstName = (TextView) findViewById(R.id.tvFirstName);
        tvStudentLastName = (TextView) findViewById(R.id.tvLastName);
        tvStudentMiddleName = (TextView) findViewById(R.id.tvMiddleName);
        ivStudentPhoto = (ImageView) findViewById(R.id.ivStudentPhoto);

        tvStudentFirstName.setText(mStudent.getFirstName());
        tvStudentLastName.setText(mStudent.getLastName());
        tvStudentMiddleName.setText(mStudent.getMiddleName());
        ivStudentPhoto.setImageResource(mStudent.getPhotoIndex());

        findViewById(R.id.btnStudentGroup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ActivityStudents.class);
                intent.putExtra(ActivityStudents.EXTRA_GROUP_NAME, mStudent.getGroup().getName().toString());
                intent.putExtra(ActivityStudents.EXTRA_GROUP_YEAR, mStudent.getGroup().getYear());
                startActivity(intent);

            }
        });

        contactsView = (RecyclerView) findViewById(R.id.recvContacts);
        contactsView.setAdapter(new AdapterStudentlContacts(mStudent.getContacts()));
    }
}
