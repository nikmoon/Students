package com.example.nikbird.students.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nikbird.students.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLessons extends Fragment {

    public FragmentLessons() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lesson_list, container, false);
    }

}
