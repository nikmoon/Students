package nikpack.Students.Interfaces;

import nikpack.Students.Models.Lesson;
import nikpack.utils.DayTime;

import java.util.Date;

/**
 * Created by sa on 15.06.17.
 */
public interface ILesson {
    Lesson.LessonType getType();
    Lesson.LessonSubject getSubject();
    String getTheme();
    String getTeacher();
    DayTime getDate();
    int getLength();
    IJournal getJournal();
}
