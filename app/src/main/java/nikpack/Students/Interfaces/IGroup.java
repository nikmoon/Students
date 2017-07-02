package nikpack.Students.Interfaces;

/**
 * Created by sa on 08.06.17.
 */


public interface IGroup extends Iterable<IStudent> {
    String getName();
    int getYear();
    int getSize();
    int contains(IStudent student);
}
