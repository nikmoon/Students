package com.example.nikbird.students.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nikbird.students.R;
import com.example.nikbird.students.ActivityStudentProfile;

import java.util.List;

import nikpack.Students.Interfaces.IStudent;

/**
 * Created by nikbird on 29.06.17.
 */
public class AdapterStudents extends RecyclerView.Adapter {

    public static final String EXTRA_STUDENT_PASSPORT = "com.example.nikbird.students.adapters.AdapterStudents.PASSPORT";

    private List<? extends IStudent> students;

    public static class StudentHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView studentPhoto;
        private TextView tvFirstName;
        private Button btnLastName;
        private TextView tvMiddleName;
        private Button btnGroupName;
        private IStudent student;

        public StudentHolder(View view) {
            super(view);

            studentPhoto = view.findViewById(R.id.ivStudentPhoto);
            tvFirstName = view.findViewById(R.id.tvFirstName);
            btnLastName = view.findViewById(R.id.btnLastName);
            tvMiddleName = view.findViewById(R.id.tvMiddleName);
            btnGroupName = view.findViewById(R.id.btnGroup);

            btnLastName.setOnClickListener(this);
            studentPhoto.setOnClickListener(this);
        }

        public void bindStudent(IStudent student) {
            tvFirstName.setText(student.getFirstName());
            btnLastName.setText(student.getLastName());
            tvMiddleName.setText(student.getMiddleName());
            btnGroupName.setText(student.getGroup().getName().toString());
            studentPhoto.setImageResource(student.getPhotoIndex());
            this.student = student;
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, ActivityStudentProfile.class);
            intent.putExtra(AdapterStudents.EXTRA_STUDENT_PASSPORT, student.getPassport());
            context.startActivity(intent);
        }
    }

    public AdapterStudents(List<? extends IStudent> students) {
        this.students = students;
    }

    @Override
    public StudentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflated = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rvrow_student, parent, false);
        return new AdapterStudents.StudentHolder(inflated);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((StudentHolder)holder).bindStudent(students.get(position));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

}