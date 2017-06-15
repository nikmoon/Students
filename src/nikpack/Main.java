package nikpack;

import nikpack.Students.Interfaces.IStudent;
import nikpack.Students.Managers.ManagerStudents;
import nikpack.Students.Models.Group;
import nikpack.Students.Models.Student;
import nikpack.utils.Contacts;
import nikpack.utils.DayDate;

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


    public static void main(String[] args) {
        ManagerStudents managerStudents = new ManagerStudents();

        Group group = new Group("Пд-11");
        try {
            IStudent[] students = new IStudent[] {
                    managerStudents.createStudent(Student.GenderType.MALE, "Григорий", "Распутин",
                            "Ефимович", new DayDate(1869, 1, 21), new Contacts(), group, "7300 123456"),
                    managerStudents.createStudent(Student.GenderType.MALE, "Александр", "Бородач",
                            "Родионович", new DayDate(1985, 12, 1), new Contacts(), group, "7300 123457")
            };
            for(IStudent student: managerStudents) {
                System.out.println(student.getFullName());
                System.out.println(student.getPassport());
            }
        } catch (ManagerStudents.StudentExistsException e) {
            e.printStackTrace();
        } catch (ManagerStudents.InvalidPassportException e) {
            e.printStackTrace();
        }

//        System.out.println(new GregorianCalendar(2017, Calendar.JANUARY, 1).get(Calendar.YEAR));
//        String s = "1232343452";
        // true, если в строке ровно 10 цифр
//        System.out.println(s.matches("\\d{10}+"));
    }

}