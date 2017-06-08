package Models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by sa on 08.06.17.
 */
public class Journal {
    private Long id;
    private Lesson lesson;
    private Set<Long> presence;

    public Journal(Lesson lesson) {
        this.lesson = lesson;
        this.id = System.currentTimeMillis() + (long)(Math.random() * Integer.MAX_VALUE);

        // изначальное состояние журнала
        // студент считается присутствующим, если он есть в множестве presence
        presence = new HashSet<>();
    }

    public void setPresence(Student student) {
        presence.add(student.getId());
    }

    public List<Student> wasOnLesson() {
        List<Student> students = new ArrayList<>();
        for(Group group: lesson.getGroups()) {
            for(Student student: group) {
                if (presence.contains(student.getId()))
                    students.add(student);
            }
        }
        return students;
    }

    public List<Student> missOnLesson() {
        List<Student> students = new ArrayList<>();
        for(Group group: lesson.getGroups()) {
            for(Student student: group) {
                if (!presence.contains(student.getId()))
                    students.add(student);
            }
        }
        return students;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Journal)) return false;

        Journal journal = (Journal) o;
        return (id == journal.id);

    }

    @Override
    public int hashCode() {
        return (int)(41 * id + 21);
    }
}
