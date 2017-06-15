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

    private Lesson lesson;
    private IStudent[] mustbeArray;
    private boolean[] wasonArray;

    public Journal(Lesson lesson) {
        this.lesson = lesson;
    }

    @Override
    public int hashCode() {
        return lesson.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return lesson.equals(obj);
    }

    @Override
    public ILesson getLesson() {
        return lesson;
    }

    @Override
    public int contains(IStudent student) {
        return Utils.contains(mustbeArray, student);
    }

    @Override
    public boolean wasOnLesson(IStudent student) {
        int index = contains(student);
        if (index != -1)
            return wasonArray[index];
        return false;
    }

    @Override
    public boolean wasOnLesson(int index) {
        return wasonArray[index];
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public void setMustBe(List<IStudent> students) {
        mustbeArray = (IStudent[]) students.toArray();
        wasonArray = new boolean[mustbeArray.length];
    }

    public int setWasOn(IStudent student) {
        int index = contains(student);
        if (index == -1)
            return -1;
        wasonArray[index] = true;
        return index;
    }
}
