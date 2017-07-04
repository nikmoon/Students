package com.example.nikbird.students.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nikbird.students.R;

import java.util.List;

import nikpack.Students.Interfaces.ILesson;

/**
 * Created by nikbird on 04.07.17.
 */

public class AdapterLessons extends RecyclerView.Adapter {

    private List<ILesson> mLessons;

    public static class LessonHolder extends RecyclerView.ViewHolder {

        private Button mLessonSubject;

        private ILesson mLesson;
        private AdapterLessons mAdapter;

        public LessonHolder(View view) {
            super(view);

            mLessonSubject = findView(view, R.id.btnLessonSubject);

        }

        public final <T extends View> T findView(View container, int id) {
            T view = container.findViewById(id);
            return view;
        }

        public void bindLesson(ILesson lesson, AdapterLessons adapter) {
            mAdapter = adapter;
            mLesson = lesson;

            mLessonSubject.setText(lesson.getSubject().toString());
        }
    }

    public AdapterLessons(List<ILesson> lessons) {
        mLessons = lessons;
    }

    @Override
    public LessonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflated = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rvrow_lesson, parent, false);
        return new LessonHolder(inflated);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((LessonHolder)holder).bindLesson(mLessons.get(position), this);
    }

    @Override
    public int getItemCount() {
        return mLessons.size();
    }

}
