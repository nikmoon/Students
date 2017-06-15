package Models;

import Interfaces.IGroup;
import Interfaces.IStudent;
import MyUtils.NameString;
import MyUtils.Utils;

import java.util.*;

public class Group implements IGroup {

    private NameString name;                // название группы
    private List<IStudent> students;        // список студентов

    public Group(String name) {
        this.name = new NameString(name);
        students = new ArrayList<>();
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof  Group))
            return false;
        Group other = (Group) obj;
        return name.equals(other.name);
    }

    @Override
    public int contains(IStudent student) {
        return Utils.contains(students, student);
    }

    @Override
    public String getName() {
        return name.toString();
    }

    @Override
    public Iterator<IStudent> iterator() {
        return students.iterator();
    }

    /**
     * Добавление студента в группу
     *
     * @param student
     * @return
     */
    public boolean add(IStudent student) {
        int index = contains(student);
        if (index != -1)
            return false;
        return students.add(student);
    }

    /**
     *  Удаление студента из группы
     */
    public boolean remove(IStudent student) {
        int index = contains(student);
        if (index == -1)
            return false;
        students.remove(index);
        return true;
    }
}

