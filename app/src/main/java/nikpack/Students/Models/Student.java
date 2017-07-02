package nikpack.Students.Models;

import nikpack.Students.Interfaces.IGroup;
import nikpack.Students.Interfaces.IStudent;
import nikpack.utils.Contacts;
import nikpack.utils.DayDate;
import nikpack.utils.NameString;

import java.io.*;

public class Student implements IStudent, Externalizable {

    private GenderType gender;          // пол
    private NameString firstName;           // имя
    private NameString lastName;            // фамилия
    private NameString middleName;          // отчество
    private DayDate birthDate;          // дата рождения

    private Contacts contacts;          // контакты
    private IGroup group;               // группа, в которой числится студент
    private Status status;              // текущий статус
    private int photoIndex;         // индекс Drawable

    private String passport;         // серия и номер паспорта

    /**
     * Возможные варианты пола студента
     */
    public enum GenderType { MALE, FEMALE }

    /**
     * Текущий статус студента
     */
    public enum Status { ACTIVE, INACTIVE }

    /**
     * Основной конструктор
     *
     * @param gender
     * @param firstName
     * @param lastName
     * @param middleName
     * @param birthDate
     * @param contacts
     * @param group
     * @param passport
     */
    public Student(GenderType gender, String firstName, String lastName,
                   String middleName, DayDate birthDate, Contacts contacts,
                   IGroup group, String passport, int photoIndex) {
        this.gender = gender;
        this.firstName = new NameString(firstName);
        this.lastName = new NameString(lastName);
        this.middleName = new NameString(middleName);
        this.birthDate = birthDate;
        this.contacts = contacts;
        this.group = group;
        this.passport = passport;
        this.photoIndex = photoIndex;
    }



    public Student() {
    }

    @Override
    public int hashCode() {
        return passport.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Student))
            return false;
        Student other = (Student) obj;
        return passport.equals(other.passport);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(gender);
        out.writeObject(firstName.toString());
        out.writeObject(lastName.toString());
        out.writeObject(middleName.toString());
        out.writeObject(birthDate);
        out.writeObject(contacts);
        out.writeObject(group);
        out.writeObject(passport);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        gender = (GenderType) in.readObject();
        firstName = new NameString((String) in.readObject());
        lastName = new NameString((String) in.readObject());
        middleName = new NameString((String) in.readObject());
        birthDate = (DayDate) in.readObject();
        contacts = (Contacts) in.readObject();
        group = (Group) in.readObject();
        passport = (String) in.readObject();
    }

    @Override
    public GenderType getGender() {
        return gender;
    }

    @Override
    public String getFullName() {
        return getLastName() + " " + getFirstName() + " " + getMiddleName();
    }

    @Override
    public String getFirstName() {
        return firstName.toString();
    }

    @Override
    public String getLastName() {
        return lastName.toString();
    }

    @Override
    public String getMiddleName() {
        return middleName.toString();
    }

    @Override
    public DayDate getBirthDate() {
        return birthDate;
    }

    @Override
    public Contacts getContacts() {
        return contacts;
    }

    @Override
    public IGroup getGroup() {
        return group;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public String getPassport() {
        return passport;
    }

    @Override
    public int getPhotoIndex() {
        return photoIndex;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public void setFirstName(String firstName) {
        this.firstName = new NameString(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = new NameString(lastName);
    }

    public void setMiddleName(String middleName) {
        this.middleName = new NameString(middleName);
    }

    public void setBirthDate(DayDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
}


