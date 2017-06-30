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
public class ListSelection extends Fragment implements View.OnClickListener {

    private Button btnSelectStudentList;
    private Button btnSelectGroupList;
    private Button btnSelectLessonList;

    private OnSelectSpecificList clickListener;

    public interface OnSelectSpecificList {
        public void onSelectSpecificList(int buttonID);
    }

    public ListSelection() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_list_selection, container, false);

        btnSelectStudentList = (Button) viewGroup.findViewById(R.id.btnStudents);
        btnSelectGroupList = (Button) viewGroup.findViewById(R.id.btnGroups);
        btnSelectLessonList = (Button) viewGroup.findViewById(R.id.btnLessons);

        btnSelectStudentList.setOnClickListener(this);
        btnSelectGroupList.setOnClickListener(this);
        btnSelectLessonList.setOnClickListener(this);

        return viewGroup;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            clickListener = (OnSelectSpecificList) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnSelectSpecificList");
        }
    }

    @Override
    public void onClick(View view) {
        clickListener.onSelectSpecificList(view.getId());
    }


}
