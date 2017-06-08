package nikpack;

import Models.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();
        students.add(new Student("Вася", "Васильевич", "Пупкин", new Date(2017, 5, 5)));
        System.out.println(students.get(0).getFirstName());
        serializeStudents(students, "test.txt");
        List<Student> newStudents = deserializeStudents("test.txt");
        System.out.println(newStudents.get(0).getFirstName());
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