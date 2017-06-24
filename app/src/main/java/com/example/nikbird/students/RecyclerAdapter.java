package com.example.nikbird.students;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by nikbird on 24.06.17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.NameHolder> {

    private List<String> mNames;

    public static class NameHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mItemView;
        private Button mButton;
        private String mName;

        public NameHolder(View view) {
            super(view);

            mItemView = (TextView) view.findViewById(R.id.tvName);
            mButton = (Button) view.findViewById(R.id.button1);
            mButton.setOnClickListener(this);
        }

        public void bindName(String name) {
            mName = name;
            mItemView.setText(mName);
        }

        @Override
        public void onClick(View view) {
            Log.d("RecyclerView", "CLICK!");
            Toast.makeText(view.getContext(), mItemView.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    public RecyclerAdapter(List<String> mNames) {
        this.mNames = mNames;
    }

    @Override
    public NameHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflated = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_row, parent, false);
        return new NameHolder(inflated);
    }

    @Override
    public void onBindViewHolder(NameHolder holder, int position) {
        String itemName = mNames.get(position);
        holder.bindName(itemName);
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }
}
