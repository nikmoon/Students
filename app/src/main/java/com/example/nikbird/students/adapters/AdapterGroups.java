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
import android.widget.TextView;

import com.example.nikbird.students.ActivityStudents;
import com.example.nikbird.students.R;

import java.util.List;

import nikpack.Students.Interfaces.IGroup;

/**
 * Created by nikbird on 02.07.17.
 */

public class AdapterGroups extends RecyclerView.Adapter {

    private List<? extends IGroup> mGroups;
    private IGroup mContextMenuFor;

    public static class GroupHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnCreateContextMenuListener {

        private Button mGroupName;
        private TextView mGroupSize;
        private TextView mGroupYear;

        private IGroup mGroup;
        private AdapterGroups mAdapter;

        public GroupHolder(View view) {
            super(view);
            mGroupName = view.findViewById(R.id.btnGroup);
            mGroupSize = view.findViewById(R.id.tvGroupSize);
            mGroupYear = view.findViewById(R.id.tvGroupYear);

            mGroupName.setOnCreateContextMenuListener(this);
            mGroupSize.setOnCreateContextMenuListener(this);
            mGroupYear.setOnCreateContextMenuListener(this);
            view.setOnCreateContextMenuListener(this);
        }

        public void bindGroup(IGroup group, AdapterGroups adapter) {
            mGroup = group;
            mAdapter = adapter;
            mGroupName.setText(mGroup.getName().toString());
            mGroupName.setOnClickListener(this);
            mGroupSize.setText(String.valueOf(mGroup.getSize()) + " чел.");
            mGroupYear.setText(String.valueOf(mGroup.getYear()));
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, ActivityStudents.class);
            intent.putExtra(ActivityStudents.EXTRA_GROUP_NAME, mGroup.getName().toString());
            intent.putExtra(ActivityStudents.EXTRA_GROUP_YEAR, mGroup.getYear());
            context.startActivity(intent);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            if (view.getId() != R.id.layoutGroupHolder) {
                MenuInflater inflater = new MenuInflater(view.getContext());
                inflater.inflate(R.menu.menu_context_groups, contextMenu);
                mAdapter.mContextMenuFor = mGroup;
            }

        }
    }


    public AdapterGroups(List<IGroup> groups) {
        mGroups = groups;
    }

    @Override
    public GroupHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflated = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rvrow_group, parent, false);
        return new GroupHolder(inflated);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((GroupHolder)holder).bindGroup(mGroups.get(position), this);
    }

    @Override
    public int getItemCount() {
        return mGroups.size();
    }

    public IGroup getContextMenuFor() {
        return mContextMenuFor;
    }
}
