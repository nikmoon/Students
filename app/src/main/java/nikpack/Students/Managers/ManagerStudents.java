package nikpack.Students.Managers;

import nikpack.Students.Interfaces.IStudent;
import nikpack.Students.Models.Group;
import nikpack.Students.Models.Student;
import nikpack.utils.Contacts;
import nikpack.utils.DayDate;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sa on 15.06.17.
 */
public class ManagerStudents {

    public static ManagerStudents instance = new ManagerStudents();
    public static ManagerStudents getInstance() {
        return instance;
    }

    private Set<Student> students;          // множество всех студентов
    private List<Student> listStudents;
    private List<Student> filteredListStudents;
    private String filterString;

    public class InvalidPassportException extends Exception {}
    public class StudentExistsException extends Exception {}

    private ManagerStudents() {
        students = new LinkedHashSet<>();
        filteredListStudents = new ArrayList<>();
        listStudents = filteredListStudents;
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
    public synchronized IStudent createStudent(Student.GenderType gender, String firstName, String lastName, String middleName,
                                  DayDate birthDate, Contacts contacts, Group group, String passport, int photoIndex)
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

        Student student = new Student(gender, firstName, lastName, middleName, birthDate, contacts, group, passport, photoIndex);

        addStudent(student);

        return student;
    }

    private void addStudent(Student student) throws StudentExistsException {
        if (!students.add(student))
            throw new StudentExistsException();

        listStudents.add(student);

            // // TODO: 26.06.17 Добавление нового студента, в случае, если фильтрация активирована
    }

    /**
     * Получить "иммутабельный" экземпляр студента по индексу
     */
    public synchronized IStudent getStudent(int index) {
        return filteredListStudents.get(index);
    }

    // Полный новый список всех студентов
    public synchronized IStudent[] getStudents() {
            return students.toArray(new IStudent[students.size()]);
    }

    /**
     * Текущее количество студентов
     */
    public int getCount() {
        return filteredListStudents.size();
    }


    // задаем фильтр по фамилиям студентов
    public void setFilter(String filter) {
        if (filter == null || filter.equals("")) {
            filterString = null;
            filteredListStudents = listStudents;
            return;
        }

        // сохранили строку для фильтрования
        filterString = filter;

        // создаем новый список с отфильтрованными элементами
        filteredListStudents = new ArrayList<>();
        for(Student student: listStudents) {
            if (student.getLastName().toLowerCase().startsWith(filter)) {
                filteredListStudents.add(student);
            }
        }
    }

    // очищаем существующий фильтр по фамилиям студентов
    public void clearFilter() {
        setFilter(null);
    }
}
