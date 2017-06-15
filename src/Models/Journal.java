package Models;

import Interfaces.IJournal;
import Interfaces.ILesson;
import Interfaces.IStudent;
import MyUtils.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by sa on 08.06.17.
 */
public class Journal implements IJournal {

    private Lesson lesson;
    private IStudent[] mustBe;
    private boolean[] wasBe;

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
        return Utils.contains(mustBe, student);
    }

    @Override
    public boolean wasOnLesson(IStudent student) {
        int index = contains(student);
        if (index != -1)
            return wasBe[index];
        return false;
    }

    @Override
    public boolean wasOnLesson(int index) {
        return wasBe[index];
    }

}
