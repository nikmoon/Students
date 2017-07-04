package nikpack.utils;

/**
 * Created by nikbird on 04.07.17.
 */

public class DayTime extends DayDate {

    private int mHour;
    private int mMinute;

    public DayTime(int year, int month, int day, int hour, int minute) {
        super(year, month, day);
        this.mHour = hour;
        this.mMinute = minute;
    }

    @Override
    public int hashCode() {
        int result = 37 * super.hashCode() + mHour;
        result = 37 * result + mMinute;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof  DayTime))
            return false;
        return fieldEquals(obj);
    }

    @Override
    protected boolean fieldEquals(Object obj) {
        DayTime date = (DayTime) obj;
        return super.fieldEquals(date) && mHour == date.mHour && mMinute == date.mMinute;
    }

    public int getHour() {
        return mHour;
    }

    public int getMinute() {
        return mMinute;
    }
}
