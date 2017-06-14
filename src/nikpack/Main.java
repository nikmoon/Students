package nikpack;

import Models.*;

import java.io.*;
import java.util.*;

public class Main {

    public static int index = 0;

    public static Student[] students = {
            new Student(Student.GenderType.MALE, "Григорий", "Распутин",
                    "Ефимович", new Student.BirthDate(1869, 1, 21),
                    new ArrayList<>(), "Пд-11", "7300 567682"),
            new Student(Student.GenderType.MALE, "Александр", "Бородач", "Родионович",
                    new Student.BirthDate(1985, 12, 1), new ArrayList<>(), "Пд-12", "7300 580680")
    };

    public static class ManagedStudent extends Student {
        @Override
        public void setPassportNum(String passportNum) {
            super.setPassportNum(passportNum);
        }
    }

    public static void main(String[] args) {
        //System.out.println(new GregorianCalendar(2017, Calendar.JANUARY, 1).get(Calendar.YEAR));
    }

    static void testSerialization(Student student) {
        System.out.println(student.hashCode());
        serializeToFile(student, "test.txt");
        Student newStudent = (Student) deserializeFromFile("test.txt");
        System.out.println(newStudent.hashCode());
    }

    static void serializeToFile(Object obj, String fileName) {
        try (
                OutputStream fileStream = new FileOutputStream(fileName);
                ObjectOutput out = new ObjectOutputStream(fileStream);
        ) {
            out.writeObject(obj);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    static Object deserializeFromFile(String fileName) {
        Object obj = null;
        try (
                InputStream fileStream = new FileInputStream(fileName);
                ObjectInput in = new ObjectInputStream(fileStream);
        ) {
            obj = in.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return obj;
    }
}