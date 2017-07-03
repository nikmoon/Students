package com.example.nikbird.students.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nikbird.students.ActivityStudents;
import com.example.nikbird.students.R;
import com.example.nikbird.students.ActivityStudentProfile;

import java.util.ArrayList;
import java.util.List;

import nikpack.Students.Interfaces.IGroup;
import nikpack.Students.Interfaces.IStudent;

/**
 * Created by nikbird on 29.06.17.
 */
public class AdapterStudents extends RecyclerView.Adapter {

    public static final String EXTRA_STUDENT_PASSPORT = "com.example.nikbird.students.adapters.AdapterStudents.PASSPORT";

    private List<? extends IStudent> students;
    private IStudent mContextMenuFor;

    public static class StudentHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnCreateContextMenuListener {

        private List<View> mViews;
        private ImageView mStudentPhoto;
        private TextView mFirstName;
        private Button mLastName;
        private TextView mMiddleName;
        private Button mGroupName;

        private IStudent mStudent;
        private AdapterStudents mAdapter;

        public StudentHolder(View view) {
            super(view);

            mViews = new ArrayList<>(5);
            mStudentPhoto = findView(view, R.id.ivStudentPhoto);
            mFirstName = findView(view, R.id.tvFirstName);
            mLastName = findView(view, R.id.btnLastName);
            mMiddleName = findView(view, R.id.tvMiddleName);
            mGroupName = findView(view, R.id.btnGroup);
            mViews.add(view);

            mLastName.setOnClickListener(this);
            mStudentPhoto.setOnClickListener(this);
            mGroupName.setOnClickListener(this);

            for(View v: mViews) {
                v.setOnCreateContextMenuListener(this);
            }
        }

        public final <T extends View> T findView(View container, int id) {
            T view = container.findViewById(id);
            mViews.add(view);
            return view;
        }

        public void bindStudent(IStudent student, AdapterStudents adapter) {
            mFirstName.setText(student.getFirstName());
            mLastName.setText(student.getLastName());
            mMiddleName.setText(student.getMiddleName());
            mGroupName.setText(student.getGroup().getName().toString());
            mStudentPhoto.setImageResource(student.getPhotoIndex());
            mStudent = student;
            mAdapter = adapter;
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            if (view.getId() == R.id.btnGroup) {
                IGroup group = mStudent.getGroup();
                Intent intent = new Intent(context, ActivityStudents.class);
                intent.putExtra(ActivityStudents.EXTRA_GROUP_NAME, group.getName().toString());
                intent.putExtra(ActivityStudents.EXTRA_GROUP_YEAR, group.getYear());
                context.startActivity(intent);
            }
            else {
                Intent intent = new Intent(context, ActivityStudentProfile.class);
                intent.putExtra(AdapterStudents.EXTRA_STUDENT_PASSPORT, mStudent.getPassport());
                context.startActivity(intent);
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            if (view.getId() != R.id.layoutStudentHolder) {
                MenuInflater inflater = new MenuInflater(view.getContext());
                inflater.inflate(R.menu.menu_context_students, contextMenu);
                mAdapter.mContextMenuFor = mStudent;
            }
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
        ((StudentHolder)holder).bindStudent(students.get(position), this);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }


    public IStudent getContextMenuFor() {
        return mContextMenuFor;
    }

}