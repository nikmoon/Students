package Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sa on 08.06.17.
 */
public class Student {
    private String firstName;
    private String surname;
    private String secondName;
    private Date dateOfBirth;
    private Long id;
    private Long groupID;
    private Group group;
    private List<Contact> contacts;

    public Student(String firstName, String surname, String secondName, Date dateOfBirth) {
        this.firstName = firstName;
        this.surname = surname;
        this.secondName = secondName;
        this.dateOfBirth = dateOfBirth;
        this.id = System.currentTimeMillis();
        this.contacts = new ArrayList<>();
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


}
