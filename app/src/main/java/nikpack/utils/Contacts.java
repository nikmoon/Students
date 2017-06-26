package nikpack.utils;

import com.example.nikbird.students.R;

import java.util.*;

/**
 * Created by sa on 14.06.17.
 */
public class Contacts implements Iterable<Map.Entry<Contacts.ContactType, List<String>>> {

    private Map<ContactType, List<String>> contacts = new HashMap<>();

    public enum ContactType {
        ADDRESS(R.drawable.address_icon),
        PHONE(R.drawable.phone_icon),
        EMAIL(R.drawable.email_icon),
        TELEGRAM(R.drawable.telegram_icon),
        SKYPE(R.drawable.skype_icon),
        VK(R.drawable.vk_icon);

        private int imageIndex;

        ContactType(int imageIndex) {
            this.imageIndex = imageIndex;
        }

        public int getImageIndex() {
            return imageIndex;
        }

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

    public int getCount() {
        return contacts.size();
    }

    public ContactType[] getContactTypes() {
        return contacts.keySet().toArray(new ContactType[contacts.size()]);
    }

}
