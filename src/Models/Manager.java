package Models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sa on 08.06.17.
 */
public class Manager<T> {

//    private Map<Long, T> items;
//
//    public Manager() {
//        items = new HashMap<>();
//    }
//
//    public Manager(Map<Long, T> items) {
//        this.items = items != null ? items : new HashMap<>();
//    }
//
//    public Manager(List<T> items) {
//        this();
//        for(T item: items) {
//            this.items.put(item.getId(), item);
//        }
//    }
//
//    public T getItem(Long id) {
//        return items.get(id);
//    }
//
//    public void addItem(T item) throws IllegalArgumentException, NullPointerException {
//        if (item == null)
//            throw new NullPointerException();
//
//        // нельзя добавлять элементы с одинаковым ID
//        if (items.containsKey(item.getId()))
//            throw new IllegalArgumentException();
//
//        items.put(item.getId(), item);
//    }
//
//    public T remove(Long id) {
//        return items.remove(id);
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (o == null) return false;
//        if (!(o instanceof Manager<?>)) return false;
//
//        Manager<T> that = (Manager<T>) o;
//        return items.equals(that.items);
//
//    }
//
//    @Override
//    public int hashCode() {
//        return items.hashCode();
//    }
}
