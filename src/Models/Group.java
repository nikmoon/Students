package Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sa on 08.06.17.
 */
public class Group {

    private String name;
    private Long id;
    private List<Student> students;

    public Group(String name) {
        this.name = name;
        this.id = System.currentTimeMillis();
        this.students = new ArrayList<>();
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
}
