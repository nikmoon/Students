package com.example.nikbird.students;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by nikbird on 25.06.17.
 */

public class StudentDetailContactsAdapter extends RecyclerView.Adapter<StudentDetailContactsAdapter.StudentHolder> {

    public static class StudentHolder extends RecyclerView.ViewHolder {

        public StudentHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public StudentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(StudentHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
