package nikpack.Students.Models;

import nikpack.Students.Interfaces.IGroup;
import nikpack.Students.Interfaces.IStudent;
import nikpack.utils.NameString;
import nikpack.utils.Utils;

import java.util.*;

public class Group implements IGroup {

    private NameString name;                // название группы
    private int year;                       // год существования группы
    private List<IStudent> students;        // список студентов в группе

    public Group(String name, int year) {
        this.name = new NameString(name);
        this.year = year;
        students = new ArrayList<>();
    }

    @Override
    public int hashCode() {
        int result = 37 + name.hashCode();
        result = 37 * result + year;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof  Group))
            return false;
        Group other = (Group) obj;
        return name.equals(other.name) && year == other.year;
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
    public int getYear() {
        return year;
    }

    @Override
    public int getSize() {
        return students.size();
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

