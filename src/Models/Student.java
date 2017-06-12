package Models;

import nikpack.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by sa on 08.06.17.
 */
@Deprecated
public class Student implements Externalizable, WithID {
    private String firstName;
    private String surname;
    private String secondName;
    private Calendar dateOfBirth;
    private Long id;
    private Long groupID;
    private Group group;
    private List<Contact> contacts;

    public Student() {

    }

    public Student(String firstName, String surname, String secondName, Calendar dateOfBirth) {
        this.firstName = firstName;
        this.surname = surname;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
        this.id = System.currentTimeMillis() + this.firstName.hashCode() + this.surname.hashCode();
        this.contacts = new ArrayList<>();
    }

    @Override
    public String toString() {
        return id.toString() + ": " + firstName + " " + surname + " " + secondName;
    }

    @Override
    public int hashCode() {
        return (int)(21 + id * 42);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof Student))
            return false;

        return (this.id == ((Student)obj).getId());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public Long getGroupID() {
        return groupID;
    }

    public void setGroupID(Long groupID) {
        this.groupID = groupID;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(firstName);
        out.writeObject(surname);
        out.writeObject(secondName);
        out.writeObject(id);
        out.writeInt(Main.index++);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        firstName = (String) in.readObject();
        surname = (String) in.readObject();
        secondName = (String) in.readObject();
        id = (Long) in.readObject();
        in.readInt();
    }
}
