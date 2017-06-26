package nikpack.Students.Interfaces;

import nikpack.utils.Contacts;
import nikpack.Students.Models.Group;
import nikpack.Students.Models.Student;
import nikpack.utils.DayDate;

/**
 * Created by nikbird on 13.06.2017.
 */

public interface IStudent {
    Student.GenderType getGender();
    String getFullName();
    String getFirstName();
    String getLastName();
    String getMiddleName();
    DayDate getBirthDate();
    Contacts getContacts();
    Group getGroup();
    Student.Status getStatus();
    String getPassport();
    int getPhotoIndex();
}
