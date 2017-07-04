package nikpack.Students.Models;

import nikpack.Students.Interfaces.IGroup;
import nikpack.Students.Interfaces.IStudent;
import nikpack.utils.NameString;
import nikpack.utils.Utils;

import java.util.*;

public class Group implements IGroup {

    private NameString mName;               // название группы
    private int mYear;                       // год существования группы
    private List<IStudent> mStudents;        // список студентов в группе

    public Group(String name, int year) {
        this.mName = new NameString(name);
        this.mYear = year;
        mStudents = new ArrayList<>();
    }

    @Override
    public int hashCode() {
        int result = 37 + mName.hashCode();
        result = 37 * result + mYear;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof  Group))
            return false;
        Group other = (Group) obj;
        return mName.equals(other.mName) && mYear == other.mYear;
    }

    @Override
    public IStudent getStudent(int index) {
        if (index < 0 || index >= mStudents.size())
            return null;
        return mStudents.get(index);
    }

    @Override
    public int contains(IStudent student) {
        return Utils.contains(mStudents, student);
    }

    @Override
    public NameString getName() {
        return mName;
    }

    public int getYear() {
        return mYear;
    }

    @Override
    public int getSize() {
        return mStudents.size();
    }

    @Override
    public Iterator<IStudent> iterator() {
        return mStudents.iterator();
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
        return mStudents.add(student);
    }

    /**
     *  Удаление студента из группы
     */
    public boolean remove(IStudent student) {
        int index = contains(student);
        if (index == -1)
            return false;
        mStudents.remove(index);
        return true;
    }
}

