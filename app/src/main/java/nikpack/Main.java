package nikpack;

import com.example.nikbird.students.R;

import nikpack.Students.Interfaces.IGroup;
import nikpack.Students.Interfaces.IStudent;

import com.example.nikbird.students.managers.ManagerGroups;
import com.example.nikbird.students.managers.ManagerStudents;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import nikpack.Students.Models.Group;
import nikpack.Students.Models.Student;
import nikpack.utils.Contacts;
import nikpack.utils.DayDate;

public class Main {

    public static void main(String[] args) {
    }

    public static void fillManagers() {

        ManagerStudents managerStudents = ManagerStudents.getInstance();
        if (managerStudents.getCount() != 0)
            return;

        ManagerGroups managerGroups = ManagerGroups.getInstance();

        IGroup groupHistory = null;
        IGroup groupFairytale = null;
        IGroup groupRolyPoly = null;
        try {
            groupHistory = managerGroups.createGroup("Исторические персонажи", 2017);
            groupFairytale = managerGroups.createGroup("Сказочные персонажи", 2017);
            groupRolyPoly = managerGroups.createGroup("Из фильма \"Неваляшка\"", 2017);
        } catch (ManagerGroups.GroupException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        Contacts contacts = new Contacts();
        contacts.add(Contacts.ContactType.ADDRESS, "Домашний адрес студента");
        contacts.add(Contacts.ContactType.EMAIL, "student@email.ru");
        contacts.add(Contacts.ContactType.SKYPE, "studentsSkype");
        contacts.add(Contacts.ContactType.VK, "http://vk.ru/StudentsVK");
        contacts.add(Contacts.ContactType.TELEGRAM, "@StudentTelegram");

        try {
            IStudent[] students = new IStudent[] {
                    managerStudents.createStudent(
                            Student.GenderType.MALE,
                            "Григорий", "Распутин", "Ефимович",
                            new DayDate(1869, 1, 21), contacts, groupHistory, "7300 123456", R.drawable.grigori_rasputin_1916),
                    managerStudents.createStudent(
                            Student.GenderType.MALE,
                            "Александр", "Бородач", "Родионович",
                            new DayDate(1985, 12, 1), contacts, groupFairytale, "7300 123457", R.drawable.borodach),
                    managerStudents.createStudent(
                            Student.GenderType.FEMALE,
                            "Вера", "Брежнева", "Викторовна",
                            new DayDate(1982, 2, 3), contacts, groupRolyPoly, "7300 123458", R.drawable.vera_brejneva),
                    managerStudents.createStudent(
                            Student.GenderType.MALE,
                            "Константин","Меладзе", "Шотаевич",
                            new DayDate(1963, 5, 11), contacts, groupRolyPoly, "7300 123459", R.drawable.meladze),
                    managerStudents.createStudent(
                            Student.GenderType.MALE,
                            "Павел", "Деревянко", "Юрьевич",
                            new DayDate(1976, 7, 2), contacts, groupRolyPoly, "7300 123460", R.drawable.derevyanko),
                    managerStudents.createStudent(
                            Student.GenderType.MALE,
                            "Сергей", "Маковецкий", "Васильевич",
                            new DayDate(1958, 6, 13), contacts, groupRolyPoly, "7300 123461", R.drawable.makovetskiy_vodka),
                    managerStudents.createStudent(
                            Student.GenderType.FEMALE,
                            "Елена", "Николаева", "Андреевна",
                            new DayDate(1983, 2, 9), contacts, groupRolyPoly, "7300 123462", R.drawable.elena_nikolaeva),
                    managerStudents.createStudent(
                            Student.GenderType.FEMALE,
                            "Линда", "Нигматулина", "Талгатовна",
                            new DayDate(1983, 5, 14), contacts, groupRolyPoly, "7300 123463", R.drawable.linda_nigmatulina)

            };
        } catch (ManagerStudents.StudentExistsException e) {
            e.printStackTrace();
            return;
        } catch (ManagerStudents.InvalidPassportException e) {
            e.printStackTrace();
            return;
        }
    }

}