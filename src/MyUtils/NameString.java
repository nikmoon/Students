package MyUtils;

/**
 * Created by sa on 15.06.17.
 */
public class NameString {
    private String original;
    private String canonical;

    public static String toCanonical(String name) {
        return name.replace(" ", "").toUpperCase();
    }

    public NameString(String name) {
        this.original = name;
        this.canonical = toCanonical(name);
    }

    @Override
    public int hashCode() {
        return canonical.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof NameString && canonical.equals(((NameString) obj).canonical);
    }

    @Override
    public String toString() {
        return original;
    }
}
