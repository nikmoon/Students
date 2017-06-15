package MyUtils;

import java.util.List;

/**
 * Created by sa on 15.06.17.
 */
public class Utils {

    public static int contains(List<?> list, Object item) {
        for(int i = 0; i < list.size(); i++)
            if (list.get(i) == item)
                return i;
        return -1;
    }

    public static int contains(Object[] arr, Object item) {
        for(int i = 0; i < arr.length; i++)
            if (arr[i] == item)
                return i;
        return -1;
    }
}
