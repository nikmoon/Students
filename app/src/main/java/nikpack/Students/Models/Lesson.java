package nikpack.Students.Models;

import nikpack.Students.Interfaces.ILesson;
import nikpack.utils.NameString;

import java.util.Date;

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
    private Date date;                  // дата занятия
    private int length;             // продолжительность в минутах


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

        private String name;

        LessonSubject(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public Lesson(LessonType type, LessonSubject subject, NameString theme, NameString teacher, Date date, int length) {
        this.type = type;
        this.subject = subject;
        this.theme = theme;
        this.teacher = teacher;
        this.date = date;
        this.length = length;
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
    public Date getDate() {
        return new Date(date.getTime());
    }

    @Override
    public int getLength() {
        return length;
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

    public void setDate(Date date) {
        this.date = new Date(date.getTime());
    }

    public void setLength(int length) {
        this.length = length;
    }
}
