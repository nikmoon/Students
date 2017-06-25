package nikpack;

import nikpack.Students.Interfaces.IStudent;
import nikpack.Students.Managers.ManagerStudents;
import nikpack.Students.Models.Group;
import nikpack.Students.Models.Student;
import nikpack.utils.Contacts;
import nikpack.utils.DayDate;

public class Main {

    public static void main(String[] args) {
    }

    public static void fillManagerStudents(ManagerStudents managerStudents) {

        Group group = new Group("Пд-11", 2017);

        try {
            IStudent[] students = new IStudent[] {
                    managerStudents.createStudent(
                            Student.GenderType.MALE,
                            "Григорий", "Распутин", "Ефимович",
                            new DayDate(1869, 1, 21), new Contacts(), group, "7300 123456"),
                    managerStudents.createStudent(
                            Student.GenderType.MALE,
                            "Александр", "Бородач", "Родионович",
                            new DayDate(1985, 12, 1), new Contacts(), group, "7300 123457"),
                    managerStudents.createStudent(
                            Student.GenderType.FEMALE,
                            "Вера", "Брежнева", "Викторовна",
                            new DayDate(1982, 2, 3), new Contacts(), group, "7300 123458"),
                    managerStudents.createStudent(
                            Student.GenderType.MALE,
                            "Константин","Меладзе", "Шотаевич",
                            new DayDate(1963, 5, 11), new Contacts(), group, "7300 123459"),
                    managerStudents.createStudent(
                            Student.GenderType.MALE,
                            "Павел", "Деревянко", "Юрьевич",
                            new DayDate(1976, 7, 2), new Contacts(), group, "7300 123460"),
                    managerStudents.createStudent(
                            Student.GenderType.MALE,
                            "Сергей", "Маковецкий", "Васильевич",
                            new DayDate(1958, 6, 13), new Contacts(), group, "7300 123461"),
                    managerStudents.createStudent(
                            Student.GenderType.FEMALE,
                            "Елена", "Николаева", "Андреевна",
                            new DayDate(1983, 2, 9), new Contacts(), group, "7300 123462"),
                    managerStudents.createStudent(
                            Student.GenderType.FEMALE,
                            "Линда", "Нигматулина", "Талгатовна",
                            new DayDate(1983, 5, 14), new Contacts(), group, "7300 123463")

            };
        } catch (ManagerStudents.StudentExistsException e) {
            e.printStackTrace();
        } catch (ManagerStudents.InvalidPassportException e) {
            e.printStackTrace();
        }
    }

}