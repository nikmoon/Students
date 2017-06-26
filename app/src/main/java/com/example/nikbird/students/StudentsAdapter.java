package com.example.nikbird.students;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import nikpack.Students.Interfaces.IStudent;
import nikpack.Students.Managers.ManagerStudents;

/**
 * Created by nikbird on 24.06.17.
 */

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentHolder> {

    public static final String EXTRA_STUDENT_INDEX = "com.example.nikbird.students.STUDENT_INDEX";

    private ManagerStudents managerStudents;

    public static class StudentHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mFirstName;
        private Button mLastName;
        private TextView mMiddleName;
        private Button mGroupName;
        private IStudent mStudent;
        private int mStudentIndex;

        public StudentHolder(View view) {
            super(view);

            mFirstName = (TextView) view.findViewById(R.id.tvFirstName);
            mLastName = (Button) view.findViewById(R.id.btnLastName);
            mMiddleName = (TextView) view.findViewById(R.id.tvMiddleName);
            mGroupName = (Button) view.findViewById(R.id.btnGroup);

            mLastName.setOnClickListener(this);
        }

        public void bindStudent(IStudent student, int studentIndex) {
            mStudent = student;
            mStudentIndex = studentIndex;
            mFirstName.setText(mStudent.getFirstName());
            mLastName.setText(mStudent.getLastName());
            mMiddleName.setText(mStudent.getMiddleName());
            mGroupName.setText(mStudent.getGroup().getName());
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, StudentDetailActivity.class);
            intent.putExtra(StudentsAdapter.EXTRA_STUDENT_INDEX, mStudentIndex);
            context.startActivity(intent);
        }
    }

    public StudentsAdapter(ManagerStudents managerStudents) {
        this.managerStudents = managerStudents;
    }

    @Override
    public StudentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflated = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_row, parent, false);
        return new StudentHolder(inflated);
    }

    @Override
    public void onBindViewHolder(StudentHolder holder, int position) {
        IStudent student = managerStudents.getStudent(position);
        holder.bindStudent(student, position);
    }

    @Override
    public int getItemCount() {
        return managerStudents.getCount();
    }
}
