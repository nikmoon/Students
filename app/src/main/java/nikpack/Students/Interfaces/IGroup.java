package nikpack.Students.Interfaces;

import nikpack.utils.NameString;

/**
 * Created by sa on 08.06.17.
 */


public interface IGroup extends Iterable<IStudent> {
    NameString getName();
    int getYear();
    int getSize();
    IStudent getStudent(int index);
    int contains(IStudent student);
}
