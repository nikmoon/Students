package Models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sa on 08.06.17.
 */
public class Group implements Iterable<Student>, WithID {

    private String name;
    private Long id;
    private List<Student> students;

    public Group(String name) {
        this(name, null);
    }

    public Group(String name, List<Student> students) {
        this.name = name;
        this.id = System.currentTimeMillis();
        this.students = (students == null) ? new ArrayList<>() : students;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Group)) return false;

        Group g = (Group)o;
        return (id == g.id);
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

    public Long getId() {
        return id;
    }

    // добавляем студента в группу
    public boolean addStudent(Student student) {
        if (student == null)
            return false;
        return this.students.add(student);
    }

    // удаляем студента из группы
    public boolean removeStudent(Student student) {
        if (student == null)
            return false;

        return this.students.remove(student);
    }

    @Override
    public Iterator<Student> iterator() {
        return this.students.iterator();
    }
}
