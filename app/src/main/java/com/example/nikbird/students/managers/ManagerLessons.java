package com.example.nikbird.students.managers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nikpack.Students.Interfaces.IGroup;
import nikpack.Students.Interfaces.ILesson;
import nikpack.Students.Interfaces.IStudent;
import nikpack.Students.Models.Group;
import nikpack.Students.Models.Journal;
import nikpack.Students.Models.Lesson;
import nikpack.utils.DayTime;
import nikpack.utils.NameString;

/**
 * Created by sa on 16.06.17.
 */
public class ManagerLessons {

    public static class SingletonHolder {
        public static final ManagerLessons HOLDER_INSTANCE = new ManagerLessons();
    }

    public static ManagerLessons getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }
    private ManagerLessons() {
        mLessons = new HashSet<>();
    }

    private Set<Lesson> mLessons;

    public ILesson
    createLesson(Lesson.LessonType type, Lesson.LessonSubject subject, NameString theme,
                 NameString teacher, DayTime date, int length) {


        Lesson lesson = new Lesson(type, subject, theme, teacher, date, length);
        mLessons.add(lesson);
        return lesson;
    }

    public synchronized List<ILesson> getLessons() {
        List<ILesson> lessons = new ArrayList<>();
        for(Lesson lesson: mLessons)
            lessons.add(lesson);
        return lessons;
    }

    public void setStudents(ILesson lesson, IGroup... groups) {
        List<IStudent> students = new ArrayList<>();
        if (mLessons.contains(lesson)) {
            for(IGroup group: groups) {
                for(IStudent student: group) {
                    students.add(student);
                }
            }
            ((Lesson) lesson).setStudents(students);
        }
    }

    public void setPresence(IStudent student, ILesson lesson) {
        if (mLessons.contains(lesson)) {
            ((Journal) lesson.getJournal()).setPresence(student);
        }
    }

}
