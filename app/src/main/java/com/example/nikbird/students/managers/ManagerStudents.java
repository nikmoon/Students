package com.example.nikbird.students.managers;

import nikpack.Main;
import nikpack.Students.Interfaces.IGroup;
import nikpack.Students.Interfaces.IStudent;
import nikpack.Students.Models.Group;
import nikpack.Students.Models.Student;
import nikpack.utils.Contacts;
import nikpack.utils.DayDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sa on 15.06.17.
 */
public class ManagerStudents {

    public static class SingletonHolder {
        public static final ManagerStudents HOLDER_INSTANCE = new ManagerStudents();
    }

    public static ManagerStudents getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    private Map<String, Student> mapStudents;          // множество всех студентов
    private List<Student> listStudents;

    public class InvalidPassportException extends Exception {}
    public class StudentExistsException extends Exception {}

    private ManagerStudents() {
        mapStudents = new HashMap<>();
        listStudents = new ArrayList<>();
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
    public synchronized IStudent
    createStudent(Student.GenderType gender, String firstName, String lastName, String middleName,
                  DayDate birthDate, Contacts contacts, IGroup group, String passport, int photoIndex)
            throws StudentExistsException, InvalidPassportException
    {
        // валидация всех ссылок
        boolean check = firstName != null && lastName != null && middleName != null && birthDate != null &&
                contacts != null && group != null && passport != null;
        if (!check)
            throw new NullPointerException();

        // валидация номера паспорта
        passport = passport.replace(" ", "");
        if (!passport.matches("\\d{10}+"))
            throw new InvalidPassportException();
        if(mapStudents.containsKey(passport))
            throw new StudentExistsException();

        // создаем экземпляр студента и добавляем его в общее отображение
        Student student = new Student(gender, firstName, lastName, middleName, birthDate, contacts, group, passport, photoIndex);
        ManagerGroups.getInstance().setStudentGroup(student, group);
        mapStudents.put(student.getPassport(), student);
        listStudents.add(student);

        return student;
    }

    /**
     * Get IStudent by index
     */
    public synchronized IStudent getStudent(int index) {
        return listStudents.get(index);
    }

    /**
     * Get IStudent by passport
     * @return
     */
    public synchronized IStudent getStudent(String passport) {
        return mapStudents.get(passport);
    }

    /**
     * Get a copy of the list of all students
     * @return
     */
    public synchronized List<IStudent> getStudents() {
        List<IStudent> newList = new ArrayList<>(listStudents.size());
        for(int i = 0, size = listStudents.size(); i < size; i++)
            newList.add(listStudents.get(i));
        return newList;
    }

    /**
     * Текущее количество студентов
     */
    public int getCount() {
        return listStudents.size();
    }

}
