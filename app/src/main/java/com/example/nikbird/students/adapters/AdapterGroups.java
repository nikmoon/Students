package com.example.nikbird.students.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.nikbird.students.R;

import java.util.List;

import nikpack.Students.Interfaces.IGroup;

/**
 * Created by nikbird on 02.07.17.
 */

public class AdapterGroups extends RecyclerView.Adapter {

    private List<? extends IGroup> mGroups;

    public static class GroupHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private IGroup mGroup;
        private Button mGroupName;
        private TextView mGroupSize;
        private TextView mGroupYear;

        public GroupHolder(View view) {
            super(view);
            mGroupName = view.findViewById(R.id.btnGroup);
            mGroupSize = view.findViewById(R.id.tvGroupSize);
            mGroupYear = view.findViewById(R.id.tvGroupYear);
        }

        public void bindGroup(IGroup group) {
            mGroup = group;
            mGroupName.setText(mGroup.getName());
            mGroupSize.setText(String.valueOf(mGroup.getSize()));
            mGroupYear.setText(String.valueOf(mGroup.getYear()));
        }

        @Override
        public void onClick(View view) {
            Context context = view.getContext();
//            Intent intent = new Intent(context, ActivityStudentProfile.class);
//            intent.putExtra(AdapterStudents.EXTRA_STUDENT_PASSPORT, student.getPassport());
//            context.startActivity(intent);
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
        ((GroupHolder)holder).bindGroup(mGroups.get(position));
    }

    @Override
    public int getItemCount() {
        return mGroups.size();
    }
}
