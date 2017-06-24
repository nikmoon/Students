package nikpack.Students.Interfaces;

/**
 * Created by sa on 15.06.17.
 */
public interface IJournal {
    ILesson getLesson();
    int contains(IStudent student);
    boolean wasOnLesson(IStudent student);
    boolean wasOnLesson(int index);
}
