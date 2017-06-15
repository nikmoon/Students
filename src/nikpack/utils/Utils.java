package nikpack.utils;

import java.io.*;
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

    public static void serializeToFile(Object obj, String fileName) {
        try (
                OutputStream fileStream = new FileOutputStream(fileName);
                ObjectOutput out = new ObjectOutputStream(fileStream);
        ) {
            out.writeObject(obj);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static Object deserializeFromFile(String fileName) {
        Object obj = null;
        try (
                InputStream fileStream = new FileInputStream(fileName);
                ObjectInput in = new ObjectInputStream(fileStream);
        ) {
            obj = in.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return obj;
    }
}
