package Models;

import java.io.*;
import java.util.List;

/**
 * Created by nikbird on 13.06.2017.
 */
public class Student implements Externalizable {

    private GenderType gender;          // пол
    private String firstName;           // имя
    private String lastName;            // фамилия
    private String middleName;          // отчество
    private BirthDate birthDate;        // дата рождения
    private List<Contact> contacts;     // контакты
    private String groupName;           // название группы
    private String passportNum;         // серия и номер паспорта


    /**
     * Возможные варианты пола студента
     */
    public enum GenderType { MALE, FEMALE };

    /**
     * Класс для хранения даты рождения
     */
    public static class BirthDate implements Serializable {
        private int year;
        private int month;
        private int day;

        public BirthDate(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        @Override
        public int hashCode() {
            int result = 37 + year;
            result = 37 * result + month;
            result = 37 * result + day;
            return result;
        }
    }

    /**
     * Основной конструктор
     *
     * @param gender
     * @param firstName
     * @param lastName
     * @param middleName
     * @param birthDate
     * @param contacts
     * @param groupName
     * @param passportNum
     */
    public Student(GenderType gender, String firstName, String lastName,
                   String middleName, BirthDate birthDate, List<Contact> contacts,
                   String groupName, String passportNum) {
        this.gender = gender;
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.middleName = middleName.trim();
        this.birthDate = birthDate;
        this.contacts = contacts;
        this.groupName = groupName;
        this.passportNum = passportNum.replace(" ", "");
    }

    public Student() {

    }

    @Override
    public int hashCode() {
        return passportNum.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == this.getClass() && hashCode() == obj.hashCode();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(gender);
        out.writeObject(firstName);
        out.writeObject(lastName);
        out.writeObject(middleName);
        out.writeObject(birthDate);
        out.writeObject(contacts);
        out.writeObject(groupName);
        out.writeObject(passportNum);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        gender = (GenderType) in.readObject();
        firstName = (String) in.readObject();
        lastName = (String) in.readObject();
        middleName = (String) in.readObject();
        birthDate = (BirthDate) in.readObject();
        contacts = (List<Contact>) in.readObject();
        groupName = (String) in.readObject();
        passportNum = (String) in.readObject();
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public BirthDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(BirthDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public String getGroupName() {
        return groupName;
    }

    protected void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getPassportNum() {
        return passportNum;
    }

    protected void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }
}
