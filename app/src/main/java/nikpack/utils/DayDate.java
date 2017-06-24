package nikpack.utils;

import java.io.Serializable;

/**
 * Класс для хранения даты с точностью до 1 дня
 */
public class DayDate implements Serializable {
    private int year;
    private int month;
    private int day;

    public DayDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public int hashCode() {
        int result = 37 + year;
        result = 37 * result + month;
        result = 37 * result + day;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof  DayDate))
            return false;
        DayDate date = (DayDate) obj;
        return year == date.year && month == date.month && day == date.day;
    }
}
