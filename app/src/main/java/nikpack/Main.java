package nikpack;

import com.example.nikbird.students.R;

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

        Group groupNuts = new Group("Разное", 2017);
        Group groupRolyPoly = new Group("Неваляшка", 2017);

        try {
            IStudent[] students = new IStudent[] {
                    managerStudents.createStudent(
                            Student.GenderType.MALE,
                            "Григорий", "Распутин", "Ефимович",
                            new DayDate(1869, 1, 21), new Contacts(), groupNuts, "7300 123456", R.drawable.grigori_rasputin_1916),
                    managerStudents.createStudent(
                            Student.GenderType.MALE,
                            "Александр", "Бородач", "Родионович",
                            new DayDate(1985, 12, 1), new Contacts(), groupNuts, "7300 123457", R.drawable.borodach),
                    managerStudents.createStudent(
                            Student.GenderType.FEMALE,
                            "Вера", "Брежнева", "Викторовна",
                            new DayDate(1982, 2, 3), new Contacts(), groupRolyPoly, "7300 123458", R.drawable.vera_brejneva),
                    managerStudents.createStudent(
                            Student.GenderType.MALE,
                            "Константин","Меладзе", "Шотаевич",
                            new DayDate(1963, 5, 11), new Contacts(), groupRolyPoly, "7300 123459", R.drawable.meladze),
                    managerStudents.createStudent(
                            Student.GenderType.MALE,
                            "Павел", "Деревянко", "Юрьевич",
                            new DayDate(1976, 7, 2), new Contacts(), groupRolyPoly, "7300 123460", R.drawable.derevyanko),
                    managerStudents.createStudent(
                            Student.GenderType.MALE,
                            "Сергей", "Маковецкий", "Васильевич",
                            new DayDate(1958, 6, 13), new Contacts(), groupRolyPoly, "7300 123461", R.drawable.makovetskiy_vodka),
                    managerStudents.createStudent(
                            Student.GenderType.FEMALE,
                            "Елена", "Николаева", "Андреевна",
                            new DayDate(1983, 2, 9), new Contacts(), groupRolyPoly, "7300 123462", R.drawable.elena_nikolaeva),
                    managerStudents.createStudent(
                            Student.GenderType.FEMALE,
                            "Линда", "Нигматулина", "Талгатовна",
                            new DayDate(1983, 5, 14), new Contacts(), groupRolyPoly, "7300 123463", R.drawable.linda_nigmatulina)

            };
        } catch (ManagerStudents.StudentExistsException e) {
            e.printStackTrace();
            return;
        } catch (ManagerStudents.InvalidPassportException e) {
            e.printStackTrace();
            return;
        }

        Contacts rasputin = managerStudents.getStudent(0).getContacts();
        rasputin.add(Contacts.ContactType.EMAIL, "rasputin@moskow.dr");
        rasputin.add(Contacts.ContactType.SKYPE, "rasputin");
        rasputin.add(Contacts.ContactType.VK, "http://vk.ru/rasputin");
        rasputin.add(Contacts.ContactType.TELEGRAM, "@rasputin");
    }

}