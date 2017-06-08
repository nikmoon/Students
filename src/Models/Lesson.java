package Models;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by sa on 08.06.17.
 */
public class Lesson {

    private Long id;
    private String name;
    private Calendar date;
    private Integer duration;
    private String room;
    private String description;
    private String subject; //  тема лекции
    private String lector;
    private List<Group> groups;
    private Journal journal;

    public Lesson(String name, Calendar date, Integer duration, String room, String description, String subject, String lector, List<Group> groups) {
        this.id = System.currentTimeMillis();
        this.name = name;
        this.date = date;
        this.duration = duration;
        this.room = room;
        this.description = description;
        this.subject = subject;
        this.lector = lector;
        this.groups = groups;
        this.journal = new Journal(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Lesson)) return false;

        Lesson lesson = (Lesson) o;
        return (lesson.id == id);
    }

    @Override
    public int hashCode() {
        return (int)(id * 41 + 21);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLector() {
        return lector;
    }

    public void setLector(String lector) {
        this.lector = lector;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Journal getJournal() {
        return journal;
    }
}
