package com.example.nikbird.students.adapter;

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
import com.example.nikbird.students.StudentDetailActivity;

import java.util.List;

import nikpack.Students.Interfaces.IStudent;
import nikpack.Students.Managers.ManagerStudents;

/**
 * Created by nikbird on 29.06.17.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentHolder> implements ManagerStudents.ISubscriber {

    public static final String EXTRA_STUDENT_PASSPORT = "com.example.nikbird.students.adapter..StudentAdapter.STUDENT_PASSPORT";

    private ManagerStudents managerStudents;
    private List<IStudent> students;

    private String filterLastName;

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
            btnGroupName.setText(student.getGroup().getName());
            studentPhoto.setImageResource(student.getPhotoIndex());
            this.student = student;
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, StudentDetailActivity.class);
            intent.putExtra(StudentAdapter.EXTRA_STUDENT_PASSPORT, student.getPassport());
            context.startActivity(intent);
        }
    }

    public StudentAdapter(ManagerStudents managerStudents) {
        this.managerStudents = managerStudents;
        students = managerStudents.getStudents(this);
        filterLastName = null;
    }

    @Override
    public StudentAdapter.StudentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflated = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_row, parent, false);
        return new StudentAdapter.StudentHolder(inflated);
    }

    @Override
    public void onBindViewHolder(StudentAdapter.StudentHolder holder, int position) {
        holder.bindStudent(students.get(position));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }


    @Override
    public void onRemoveStudent(IStudent student) {

    }

    @Override
    public void onAddStudent(IStudent student) {

    }

    public void filter(String filter) {
        // чтобы не делать много лишней ненужной работы при повороте экрана
        if (!filter.equals(filterLastName)) {
            filterLastName = filter;

            if (filter.equals(""))
                students = managerStudents.getStudents(null);
            else {
                students = managerStudents.getFilteredList(filter);
            }
            notifyDataSetChanged();
        }
    }
}