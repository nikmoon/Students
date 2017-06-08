package nikpack;

import Models.Group;
import Models.Journal;
import Models.Lesson;
import Models.Student;

import java.io.*;
import java.util.*;

public class Main {

    public static int index = 0;

    public static void main(String[] args) {
        testSerialization();
        //testJournal();
        //System.out.println(new GregorianCalendar(2017, Calendar.JANUARY, 1).get(Calendar.YEAR));
    }

    static void testJournal() {
        Student[] students = {
                new Student("Александр", "Родионович", "Бородач", new GregorianCalendar(1985, Calendar.DECEMBER, 1)),
                new Student("Владимир", "Ильич", "Ленин", new GregorianCalendar(1870, Calendar.APRIL, 23)),
                new Student("Фёдор", "Иванович", "Шаляпин", new GregorianCalendar(1873, Calendar.FEBRUARY, 1)),
                new Student("Никита-царь", "Сергеевич", "Мигалков", new GregorianCalendar(1945, Calendar.OCTOBER, 21))
        };
        Group groupA = new Group("Программисты");
        groupA.addStudent(students[0]);
        groupA.addStudent(students[1]);

        Group groupB = new Group("Филологи");
        groupB.addStudent(students[2]);
        groupB.addStudent(students[3]);

        List<Group> groups = new ArrayList<Group>();
        groups.add(groupA);
        groups.add(groupB);
        Lesson lesson = new Lesson("История", new GregorianCalendar(2017, Calendar.JUNE, 9, 10, 30), 1,
                "501", "Лекция о истории", "Древняя Русь", "Пушкин А.С.", groups);

        Journal journal = lesson.getJournal();
        journal.setPresence(students[0]);

        System.out.println("Были на лекции:");
        for(Student student: journal.wasOnLesson()) {
            System.out.println(student);
        }

        System.out.println("Отсутствовали на лекции");
        for(Student student: journal.missOnLesson()) {
            System.out.println(student);
        }
    }

    static void testSerialization() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Вася", "Васильевич", "Пупкин", new GregorianCalendar(2017, 5, 5)));
        students.add(new Student("Петя", "Петрович", "Попов", new GregorianCalendar(2017, 6, 6)));

        Main.index = 0;

        // сериализуем в файл
        serializeStudents(students, "test.txt");
        // десериализуем из файла
        List<Student> newStudents = deserializeStudents("test.txt");

        for(Student student: newStudents) {
            System.out.println(student);
        }
    }

    static void serializeStudents(List<Student> students, String fileName) {
        try (FileOutputStream fout = new FileOutputStream(fileName);
            ObjectOutputStream oout = new ObjectOutputStream(fout)) {

            oout.writeObject(students);

        } catch (IOException ex) {

        }

    }

    static List<Student> deserializeStudents(String fileName) {
        List<Student> students = null;
        try (FileInputStream fin = new FileInputStream(fileName);
            ObjectInputStream oin = new ObjectInputStream(fin)) {

            students = (List<Student>) oin.readObject();
        }
        catch (IOException ex) {

        }
        catch (ClassNotFoundException ex) {

        }
        return students;
    }
}