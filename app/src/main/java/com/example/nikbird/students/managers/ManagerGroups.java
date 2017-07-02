package com.example.nikbird.students.managers;

import nikpack.Main;
import nikpack.Students.Interfaces.IGroup;
import nikpack.Students.Interfaces.IStudent;
import nikpack.Students.Models.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by sa on 15.06.17.
 */
public class ManagerGroups {

    public static ManagerGroups instance = new ManagerGroups();
    public static ManagerGroups getInstance() {
        return instance;
    }


    private Set<Group> mGroups;
    private List<Group> mListGroups;

    public ManagerGroups() {
        mGroups = new HashSet<>();
        mListGroups = new ArrayList<>();
    }

    public class GroupException extends Exception {}
    public class EmptyGroupName extends GroupException {}
    public class InvalidGroupYear extends GroupException {}
    public class GroupExists extends GroupException {}

    public synchronized IGroup createGroup(String name, int year) throws GroupException {
        if (name == null)
            throw new NullPointerException();
        if (name.equals(""))
            throw new EmptyGroupName();
        if (year < 1900)
            throw new InvalidGroupYear();

        Group group = new Group(name, year);

        if (mGroups.contains(group))
            throw new GroupExists();

        mGroups.add(group);
        mListGroups.add(group);

        return group;
    }

    public synchronized void setStudentGroup(IStudent student, IGroup group) {
        ((Group) student.getGroup()).remove(student);
        ((Group)group).add(student);
    }

    public synchronized List<IGroup> getGroups() {
        List<IGroup> newList = new ArrayList<>(mListGroups.size());
        for(int i = 0, size = mListGroups.size(); i < size; i++)
            newList.add(mListGroups.get(i));
        return newList;
    }

}
