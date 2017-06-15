package nikpack.Students.Interfaces;

import nikpack.Students.Models.Lesson;

import java.util.Date;

/**
 * Created by sa on 15.06.17.
 */
public interface ILesson {
    Lesson.LessonType getType();
    Lesson.LessonSubject getSubject();
    String getTheme();
    String getTeacher();
    Date getDate();
    int getLength();
}
