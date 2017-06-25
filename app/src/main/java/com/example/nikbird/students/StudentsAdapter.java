package com.example.nikbird.students;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import nikpack.Students.Interfaces.IStudent;
import nikpack.Students.Managers.ManagerStudents;

/**
 * Created by nikbird on 24.06.17.
 */

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentHolder> {

    private List<String> mNames;
    private ManagerStudents managerStudents;

//    public static class StudentHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static class StudentHolder extends RecyclerView.ViewHolder{
        private TextView mFirstName;
        private TextView mLastName;
        private TextView mMiddleName;
        private IStudent mStudent;

        private TextView mItemView;
        private Button mButton;
        private String mName;

        public StudentHolder(View view) {
            super(view);

            mFirstName = (TextView) view.findViewById(R.id.tvFirstName);
            mLastName = (TextView) view.findViewById(R.id.tvLastName);
            mMiddleName = (TextView) view.findViewById(R.id.tvMiddleName);

//            mItemView = (TextView) view.findViewById(R.id.tvName);
//            mButton = (Button) view.findViewById(R.id.button1);
//            mButton.setOnClickListener(this);
        }

        public void bindStudent(IStudent student) {
            mStudent = student;
            mFirstName.setText(mStudent.getFirstName());
            mLastName.setText(mStudent.getLastName());
            mMiddleName.setText(mStudent.getMiddleName());
        }

//        public void bindName(String name) {
//            mName = name;
//            mItemView.setText(mName);
//        }

//        @Override
//        public void onClick(View view) {
//            Log.d("RecyclerView", "CLICK!");
//            Toast.makeText(view.getContext(), mItemView.getText(), Toast.LENGTH_SHORT).show();
//        }
    }

    public StudentsAdapter(ManagerStudents managerStudents) {
        this.managerStudents = managerStudents;
//        this.mNames = mNames;
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
        holder.bindStudent(student);
//        String itemName = mNames.get(position);
//        holder.bindName(itemName);
    }

    @Override
    public int getItemCount() {
        return managerStudents.getCount();
//        return mNames.size();
    }
}
