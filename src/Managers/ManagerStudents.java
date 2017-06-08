package Managers;

import Models.*;
import java.util.*;

/**
 * Created by sa on 08.06.17.
 */
public class ManagerStudents {

    private Map<Long, Student> students;    // studentID: Student

    public ManagerStudents() {
        students = new HashMap<>();
    }

    public ManagerStudents(Map<Long, Student> students) {
        this.students = students;
    }

    public ManagerStudents(List<Student> students) {
        this();
        for(Student student: students) {
            this.students.put(student.getId(), student);
        }
    }

}
