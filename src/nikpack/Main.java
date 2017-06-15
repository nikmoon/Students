package nikpack;

import Models.*;
import MyUtils.DayDate;

import java.io.*;

public class Main {

    public static int index = 0;

//    public static Student[] students = {
//            new Student(Student.GenderType.MALE, "Григорий", "Распутин",
//                    "Ефимович", new DayDate(1869, 1, 21),
//                    new Contacts(), "ПД-11", "7300 567682"),
//            new Student(Student.GenderType.FEMALE, "Александр", "Бородач",
//                    "Родионович", new DayDate(1985, 12, 1),
//                    new Contacts(), "ПД-12", "7300 580680")
//    };

    interface IMain {
        void set(String string);
        String get();
    }

    private static class CMain implements IMain {

        private String string;

        public CMain() {
            string = "";
        }

        public CMain(String string) {
            this.string = string;
        }

        @Override
        public void set(String string) {
            this.string = string;
        }

        @Override
        public String get() {
            return string;
        }
    }

    public static void main(String[] args) {

        IMain someIMain;
        CMain someCMain = new CMain("Hello");

        someIMain = someCMain;
        System.out.println(someCMain == someIMain);



//        System.out.println(new GregorianCalendar(2017, Calendar.JANUARY, 1).get(Calendar.YEAR));
//        String s = "1232343452";
        // true, если в строке ровно 10 цифр
//        System.out.println(s.matches("\\d{10}+"));
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