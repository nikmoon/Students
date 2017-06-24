package com.example.nikbird.students;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private List<String> mNames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        // привязываем вьюшки
        mRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);

        LinearLayoutManager man;

        mNames = new ArrayList<>();
        mNames.add("Вася");
        mNames.add("Петя");
        mNames.add("Коля");
        mNames.add("Галя");

        mAdapter = new RecyclerAdapter(mNames);
        mRecyclerView.setAdapter(mAdapter);

        setRecyclerViewItemTouchListener();

    }

    private void setRecyclerViewItemTouchListener() {
        ItemTouchHelper.SimpleCallback itemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                mNames.remove(position);
                mRecyclerView.getAdapter().notifyItemRemoved(position);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

}
