package com.example.nikbird.students.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nikbird.students.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListSelection extends Fragment implements View.OnClickListener {

    private Button mSelectStudents;
    private Button mSelectGroups;
    private Button mSelectLessons;

    private OnSelectSpecificList mClickListener;

    public interface OnSelectSpecificList {
        void onSelectSpecificList(int buttonId);
    }

    public FragmentListSelection() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_list_selection, container, false);

        mSelectStudents = viewGroup.findViewById(R.id.btnStudents);
        mSelectGroups = viewGroup.findViewById(R.id.btnGroups);
        mSelectLessons = viewGroup.findViewById(R.id.btnLessons);

        mSelectStudents.setOnClickListener(this);
        mSelectGroups.setOnClickListener(this);
        mSelectLessons.setOnClickListener(this);

        return viewGroup;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mClickListener = (OnSelectSpecificList) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnSelectSpecificList");
        }
    }

    @Override
    public void onClick(View view) {
        mClickListener.onSelectSpecificList(view.getId());
    }


}
