package nikpack.Students.Models;

import java.util.List;

import nikpack.Students.Interfaces.IJournal;
import nikpack.Students.Interfaces.ILesson;
import nikpack.Students.Interfaces.IStudent;
import nikpack.utils.DayTime;
import nikpack.utils.NameString;

/**
 * Created by sa on 08.06.17.
 *
 * Учебное занятие
 */
public class Lesson implements ILesson {

    private LessonType type;            // тип занятия
    private LessonSubject subject;      // название предмета
    private NameString theme;               // тема занятия
    private NameString teacher;             // преподаватель
    private DayTime date;           // дата занятия
    private int length;             // продолжительность в минутах
    private Journal mJournal;       // журнал для лекции


    /**
     * Типы занятий
     */
    public enum LessonType {
        LECTURE,                // лекция
        PRACTICE                // практика
    }

    /**
     * Названия предметов
     */
    public enum LessonSubject {
        RUSSIAN_LANG("Русский язык"),
        MATHEMATICS("Математика"),
        PHYSICS("Физика");

        private String mName;

        LessonSubject(String name) {
            this.mName = name;
        }

        @Override
        public String toString() {
            return mName;
        }
    }

    public Lesson(LessonType type, LessonSubject subject, NameString theme, NameString teacher, DayTime date, int length) {
        this.type = type;
        this.subject = subject;
        this.theme = theme;
        this.teacher = teacher;
        this.date = date;
        this.length = length;
        mJournal = new Journal(this);
    }

    @Override
    public int hashCode() {
        int result = 37 + teacher.hashCode();
        result = 37 * result + date.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Lesson))
            return false;
        Lesson other = (Lesson) obj;
        return teacher.equals(other.teacher) && date.equals(other.date);
    }

    @Override
    public LessonType getType() {
        return type;
    }

    @Override
    public LessonSubject getSubject() {
        return subject;
    }

    @Override
    public String getTheme() {
        return theme.toString();
    }

    @Override
    public String getTeacher() {
        return teacher.toString();
    }

    @Override
    public DayTime getDate() {
        return date;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public IJournal getJournal() {
        return mJournal;
    }

    public void setStudents(List<IStudent> students) {
        mJournal.setStudents(students);
    }

    public int setPresence(IStudent student) {
        return mJournal.setPresence(student);
    }


    public void setType(LessonType type) {
        this.type = type;
    }

    public void setSubject(LessonSubject subject) {
        this.subject = subject;
    }

    public void setTheme(String theme) {
        this.theme = new NameString(theme);
    }

    public void setTeacher(String teacher) {
        this.teacher = new NameString(teacher);
    }

    public void setDate(DayTime date) {
        this.date = date;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
