package nikpack.utils;

import java.util.*;

/**
 * Created by sa on 14.06.17.
 */
public class Contacts implements Iterable<Map.Entry<Contacts.ContactType, List<String>>> {

    private Map<ContactType, List<String>> contacts = new HashMap<>();

    public enum ContactType {
        ADDRESS,
        PHONE,
        EMAIL,
        TELEGRAM,
        SKYPE,
        VK,
        FACEBOOK,
        LINKEDIN,
        ODNOKLASSNIKI
    }

    public void add(ContactType contactType, String value) {
        List<String> values = contacts.get(contactType);
        if (values == null) {
            values = new ArrayList<>();
            contacts.put(contactType, values);
        }
        values.add(value);
    }

    public List<String> get(ContactType contactType) {
        List<String> values = contacts.get(contactType);
        if (values == null) {
            values = new ArrayList<>();
            contacts.put(contactType, values);
        }
        return values;
    }

    public void set(ContactType contactType, List<String> values) {
        contacts.put(contactType, values);
    }

    @Override
    public Iterator<Map.Entry<ContactType, List<String>>> iterator() {
        return contacts.entrySet().iterator();
    }

}
