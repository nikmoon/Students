package com.example.nikbird.students.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.nikbird.students.R;
import com.example.nikbird.students.adapters.AdapterGroups;
import com.example.nikbird.students.managers.ManagerGroups;

import java.util.List;

import nikpack.Students.Interfaces.IGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGroups extends Fragment {

    private RecyclerView mViewGroups;
    private List<IGroup> mGroups;

    public FragmentGroups() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_groups, container, false);

        mGroups = ManagerGroups.getInstance().getGroups();
        mViewGroups = view.findViewById(R.id.rvGroups);
        mViewGroups.setAdapter(new AdapterGroups(mGroups));
        return view;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        IGroup group = ((AdapterGroups)mViewGroups.getAdapter()).getContextMenuFor();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setType("text/plain");
        intent.setData(Uri.parse("smsto:" + "+7 951 608 32 82"));
        intent.putExtra("sms_body", "Привет из программы Students");
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
        return true;
    }


}
