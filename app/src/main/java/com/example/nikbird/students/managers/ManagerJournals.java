package com.example.nikbird.students.managers;

/**
 * Created by sa on 16.06.17.
 */
public class ManagerJournals {


    public static class SingletonHolder {
        public static final ManagerJournals HOLDER_INSTANCE = new ManagerJournals();
    }

    public static ManagerJournals getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

}
