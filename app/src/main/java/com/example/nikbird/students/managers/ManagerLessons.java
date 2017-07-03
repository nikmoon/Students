package com.example.nikbird.students.managers;

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

}
