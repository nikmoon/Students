package Interfaces;

import java.util.List;

/**
 * Created by sa on 08.06.17.
 */


public interface IGroup extends Iterable<IStudent> {
    String getName();
    int contains(IStudent student);
}
