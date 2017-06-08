package Models;

/**
 * Created by sa on 08.06.17.
 */
public class Contact {

    private ContactType contactType;
    private String value;


    public Contact(ContactType contactType, String value) {

        this.contactType = contactType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Contact)) return false;

        Contact contact = (Contact) o;

        if (contactType != contact.contactType) return false;
        return value.equals(contact.value);

    }

    @Override
    public int hashCode() {
        int result = contactType.hashCode();
        result = (41 * result + 21) + (41 * value.hashCode() + 21);
        return result;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
