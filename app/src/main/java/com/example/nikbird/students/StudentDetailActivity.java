package com.example.nikbird.students;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nikbird.students.adapter.StudentAdapter;

import nikpack.Students.Interfaces.IStudent;
import nikpack.Students.Managers.ManagerStudents;

public class StudentDetailActivity extends AppCompatActivity {

    private RecyclerView contactsView;
    private RecyclerView journalsView;
    private ManagerStudents managerStudents;
    private String studentPassport;

    private TextView tvStudentLastName;
    private TextView tvStudentFirstName;
    private TextView tvStudentMiddleName;
    private ImageView ivStudentPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        studentPassport = getIntent().getStringExtra(StudentAdapter.EXTRA_STUDENT_PASSPORT);
        if ("".equals(studentPassport)) {
            return;
        }

        managerStudents = ManagerStudents.getInstance();
        IStudent student = managerStudents.getStudent(studentPassport);

        tvStudentFirstName = (TextView) findViewById(R.id.tvFirstName);
        tvStudentLastName = (TextView) findViewById(R.id.tvLastName);
        tvStudentMiddleName = (TextView) findViewById(R.id.tvMiddleName);
        ivStudentPhoto = (ImageView) findViewById(R.id.ivStudentPhoto);

        tvStudentFirstName.setText(student.getFirstName());
        tvStudentLastName.setText(student.getLastName());
        tvStudentMiddleName.setText(student.getMiddleName());
        ivStudentPhoto.setImageResource(student.getPhotoIndex());

        contactsView = (RecyclerView) findViewById(R.id.recvContacts);
        contactsView.setAdapter(new StudentDetailContactsAdapter(student.getContacts()));
    }
}
