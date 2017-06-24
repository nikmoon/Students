package nikpack.Students.Managers;

import nikpack.Students.Interfaces.IStudent;
import nikpack.Students.Models.Group;
import nikpack.Students.Models.Student;
import nikpack.utils.Contacts;
import nikpack.utils.DayDate;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by sa on 15.06.17.
 */
public class ManagerStudents implements Iterable<IStudent>, Iterator<IStudent> {

    private Set<Student> students;      // множество всех студентов
    Iterator<Student> iteratorStudents;

    public class InvalidPassportException extends Exception {}
    public class StudentExistsException extends Exception {}

    public ManagerStudents() {
        students = new LinkedHashSet<>();
    }

    /**
     *  Создание нового студента
     * @param gender
     * @param firstName
     * @param lastName
     * @param middleName
     * @param birthDate
     * @param contacts
     * @param group
     * @param passport
     * @return
     * @throws StudentExistsException
     * @throws InvalidPassportException
     */
    public IStudent createStudent(Student.GenderType gender, String firstName, String lastName, String middleName,
                                  DayDate birthDate, Contacts contacts, Group group, String passport)
            throws StudentExistsException, InvalidPassportException
    {
        // не должно быть нулевых параметров
        boolean check = firstName != null && lastName != null && middleName != null && birthDate != null &&
                contacts != null && group != null && passport != null;
        if (!check)
            throw new NullPointerException();

        passport = passport.replace(" ", "");
        if (!passport.matches("\\d{10}+"))
            throw new InvalidPassportException();

        Student student = new Student(gender, firstName, lastName, middleName, birthDate, contacts, group, passport);
        if (!students.add(student))
            throw new StudentExistsException();
        return student;
    }

    public IStudent[] getStudents() {
        return students.toArray(new IStudent[students.size()]);
    }

    @Override
    public Iterator<IStudent> iterator() {
        iteratorStudents = students.iterator();
        return this;
    }

    @Override
    public void remove() {
        iteratorStudents.remove();
    }

    @Override
    public boolean hasNext() {
        return iteratorStudents.hasNext();
    }

    @Override
    public IStudent next() {
        return iteratorStudents.next();
    }
}
