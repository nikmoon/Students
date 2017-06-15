package Interfaces;

import MyUtils.Contacts;
import Models.Group;
import Models.Student;
import MyUtils.DayDate;

/**
 * Created by nikbird on 13.06.2017.
 */

public interface IStudent {
    Student.GenderType getGender();
    String getFirstName();
    String getLastName();
    String getMiddleName();
    DayDate getBirthDate();
    Contacts getContacts();
    Group getGroup();
    Student.Status getStatus();
    String getPassport();
}
