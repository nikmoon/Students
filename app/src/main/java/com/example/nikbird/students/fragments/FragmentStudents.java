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

    private static final String FILTER_TAG = "com.example.nikbird.students.fragments.FragmentStudents.FILTER";

    private RecyclerView mViewStudents;
    private List<IStudent> mStudents;
    private String mFilter;

    public FragmentStudents() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_students, container, false);
        mViewStudents = view.findViewById(R.id.vStudents);

        mStudents = ManagerStudents.getInstance().getStudents();
        mFilter = savedInstanceState == null ? "" : savedInstanceState.getString(FILTER_TAG, "");

        filterByLastName(mFilter);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(FILTER_TAG, mFilter);
    }

    public void filterByLastName(String filter) {
        List<IStudent> students = new ArrayList<>();
        for(IStudent student: mStudents) {
            if (student.getLastName().toLowerCase().startsWith(filter))
                students.add(student);
        }
        mViewStudents.setAdapter(new AdapterStudents(students));
    }
}
