package nikpack.Students.Managers;

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

    public static ManagerStudents instance = new ManagerStudents();
    public static ManagerStudents getInstance() {
        return instance;
    }

    private Map<String, Student> mapStudents;          // множество всех студентов
    private List<Student> listStudents;
    private List<Student> filteredListStudents;
    private String filterString;

    private List<ISubscriber> subscribers;

    public class InvalidPassportException extends Exception {}
    public class StudentExistsException extends Exception {}

    public static interface ISubscriber {
        void onRemoveStudent(IStudent student);
        void onAddStudent(IStudent student);
    }

    private ManagerStudents() {
        mapStudents = new HashMap<>();
        listStudents = new ArrayList<>();
        subscribers = new ArrayList<>();
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
        addStudent(student);

        return student;
    }

    // Добавление нового студента в общее отображение
    private synchronized void addStudent(Student student){
        mapStudents.put(student.getPassport(), student);
        listStudents.add(student);

        // рассылаем оповещения всем подписчикам
        for(ISubscriber subscriber: subscribers) {
            subscriber.onAddStudent(student);
        }
    }

    /**
     * Получить "иммутабельный" экземпляр студента по индексу
     */
    public synchronized IStudent getStudent(int index) {
        return listStudents.get(index);
    }

    /**
     * Получить "иммутабельный" экземпляр студента по номеру паспорта
     * @return
     */
    public synchronized IStudent getStudent(String passport) {
        return mapStudents.get(passport);
    }

    // Полный новый список всех студентов
    public synchronized List<IStudent> getStudents(ISubscriber subscriber) {
        if (subscriber != null)
            subscribers.add(subscriber);

        List<IStudent> newList = new ArrayList<>(listStudents.size());
        for(int i = 0; i < listStudents.size(); i++)
            newList.add(listStudents.get(i));
        return newList;
    }

    public synchronized List<IStudent> getFilteredList(String filter) {
        List<IStudent> newList = new ArrayList<>(listStudents.size() / 2);
        for(int i = 0; i < listStudents.size(); i++)
            if (listStudents.get(i).getLastName().toLowerCase().startsWith(filter))
                newList.add(listStudents.get(i));
        return newList;
    }

    /**
     * Текущее количество студентов
     */
    public int getCount() {
        return listStudents.size();
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
}
