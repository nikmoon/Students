package com.example.nikbird.students.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nikbird.students.R;
import com.example.nikbird.students.adapters.AdapterStudents;

import com.example.nikbird.students.managers.ManagerStudents;


import java.util.ArrayList;
import java.util.List;

import nikpack.Students.Interfaces.IStudent;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentStudents extends Fragment {

    private RecyclerView mViewStudents;
    private List<IStudent> mStudents;

    public FragmentStudents() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_students, container, false);
        mStudents = ManagerStudents.getInstance().getStudents();
        mViewStudents = view.findViewById(R.id.rvStudents);
        mViewStudents.setAdapter(new AdapterStudents(mStudents));
        return view;
    }

    public void filterByLastName_Contains(String filter) {
        filter = filter.toUpperCase();
        List<IStudent> students = new ArrayList<>();
        for(IStudent student: mStudents) {
            if (student.getLastName().toUpperCase().contains(filter))
                students.add(student);
        }
        mViewStudents.setAdapter(new AdapterStudents(students));
    }

    public void filterByLastName_StartsWith(String filter) {
        filter = filter.toUpperCase();
        List<IStudent> students = new ArrayList<>();
        for(IStudent student: mStudents) {
            if (student.getLastName().toUpperCase().startsWith(filter))
                students.add(student);
        }
        mViewStudents.setAdapter(new AdapterStudents(students));
    }


    public void filterByLastName_Equals(String filter) {
        filter = filter.toUpperCase();
        List<IStudent> students = new ArrayList<>();
        for(IStudent student: mStudents) {
            if (student.getLastName().toUpperCase().equals(filter))
                students.add(student);
        }
        mViewStudents.setAdapter(new AdapterStudents(students));
    }

}
