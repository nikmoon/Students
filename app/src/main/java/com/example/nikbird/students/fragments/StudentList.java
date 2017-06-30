package com.example.nikbird.students.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nikbird.students.R;
import com.example.nikbird.students.adapters.StudentAdapter;

import com.example.nikbird.students.managers.ManagerStudents;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentList extends Fragment {

    private Context context;
    private ManagerStudents managerStudents;
    private RecyclerView studentsView;
    private SearchView searchView;
    private StudentAdapter studentsAdapter;

    public StudentList() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

        managerStudents = ManagerStudents.getInstance();
        studentsAdapter = new StudentAdapter(managerStudents);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_list, container, false);

        studentsView = view.findViewById(R.id.vStudents);
        studentsView.setAdapter(studentsAdapter);

        searchView = view.findViewById(R.id.searchView);

        // при каждом изменении фильтра будем обновлять список студентов
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                studentsAdapter.filter(newText.toLowerCase());
                return true;
            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((SearchView) view).setIconified(false);
            }
        });

        return view;
    }

}
