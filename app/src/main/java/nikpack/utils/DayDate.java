package nikpack.utils;

import java.io.Serializable;

/**
 * Класс для хранения даты с точностью до 1 дня
 */
public class DayDate implements Serializable {
    private int mYear;
    private int mMonth;
    private int mDay;

    public DayDate(int year, int month, int day) {
        this.mYear = year;
        this.mMonth = month;
        this.mDay = day;
    }

    @Override
    public int hashCode() {
        int result = 37 + mYear;
        result = 37 * result + mMonth;
        result = 37 * result + mDay;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof  DayDate))
            return false;
        return fieldEquals(obj);
    }

    protected boolean fieldEquals(Object obj) {
        DayDate date = (DayDate) obj;
        return mYear == date.mYear && mMonth == date.mMonth && mDay == date.mDay;
    }

    public int getYear() {
        return mYear;
    }

    public int getMonth() {
        return mMonth;
    }

    public int getDay() {
        return mDay;
    }
}
