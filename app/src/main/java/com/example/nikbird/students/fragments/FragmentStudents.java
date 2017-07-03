package com.example.nikbird.students.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.nikbird.students.R;
import com.example.nikbird.students.adapters.AdapterStudents;

import com.example.nikbird.students.managers.ManagerStudents;


import java.util.ArrayList;
import java.util.List;

import nikpack.Students.Interfaces.IGroup;
import nikpack.Students.Interfaces.IStudent;
import nikpack.utils.Contacts;
import nikpack.utils.NameString;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentStudents extends Fragment {

    private RecyclerView mViewStudents;
    private volatile List<IStudent> mStudents;

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

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        IStudent student = ((AdapterStudents)mViewStudents.getAdapter()).getContextMenuFor();
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + student.getContacts().get(Contacts.ContactType.PHONE)));
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
        return true;
    }

    public void filterByGroup_Permanent(NameString groupName, int groupYear) {
        List<IStudent> students = new ArrayList<>();
        for(IStudent student: ManagerStudents.getInstance().getStudents()) {
            IGroup group = student.getGroup();
            if (group.getName().equals(groupName) && (group.getYear() == groupYear))
                students.add(student);
        }
        synchronized (mStudents) {
            mStudents = students;
        }
        mViewStudents.setAdapter(new AdapterStudents(mStudents));
    }

    public void filterByLastName_StartsWith(String filter) {
        filter = filter.toUpperCase();
        List<IStudent> students = new ArrayList<>();
        List<IStudent> current = mStudents;
        for(IStudent student: current) {
            if (student.getLastName().toUpperCase().startsWith(filter))
                students.add(student);
        }
        mViewStudents.setAdapter(new AdapterStudents(students));
    }
}
