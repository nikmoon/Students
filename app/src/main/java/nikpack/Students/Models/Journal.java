package nikpack.Students.Models;

import nikpack.Students.Interfaces.IJournal;
import nikpack.Students.Interfaces.ILesson;
import nikpack.Students.Interfaces.IStudent;
import nikpack.utils.Utils;

import java.util.List;


/**
 * Created by sa on 08.06.17.
 */
public class Journal implements IJournal {

    private ILesson mLesson;
    private IStudent[] mStudents;
    private boolean[] mPresence;

    public Journal(Lesson lesson) {
        this.mLesson = lesson;
    }

    @Override
    public int hashCode() {
        return mLesson.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return mLesson.equals(obj);
    }

    @Override
    public ILesson getLesson() {
        return mLesson;
    }

    @Override
    public int contains(IStudent student) {
        return Utils.contains(mStudents, student);
    }

    @Override
    public boolean wasOnLesson(IStudent student) {
        int index = contains(student);
        if (index != -1)
            return mPresence[index];
        return false;
    }

    @Override
    public boolean wasOnLesson(int index) {
        return mPresence[index];
    }

    public void setStudents(List<IStudent> students) {
        mStudents = students.toArray(new IStudent[students.size()]);
        mPresence = new boolean[mStudents.length];
    }

    public int setPresence(IStudent student) {
        int index = contains(student);
        if (index == -1)
            return -1;
        mPresence[index] = true;
        return index;
    }
}
