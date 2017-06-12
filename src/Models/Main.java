package Models;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created by sa on 12.06.17.
 */
public class Main {

    public static void main(String[] args) {
        Student student = new Student("Connor", "Reese", "John", new GregorianCalendar());
        XMLNode studentNode = XMLNode.createStudent(student);
        System.out.println(studentNode.serialize());
    }

    public static void testReflection() throws NoSuchFieldException, IllegalAccessException {
        Student student = new Student("Connor", "Reese", "John", new GregorianCalendar());

        for (Field field: student.getClass().getDeclaredFields()) {
            System.out.println(field.getName() + " " + field.getType().toString());
        }

        for(Method m: student.getClass().getDeclaredMethods()) {
            System.out.println(m.getName() + " " +
            m.getReturnType() + " " +
            m.getParameterTypes().length);
        }

        for(Annotation a: Student.class.getAnnotations()) {
            System.out.println(a.annotationType().toString() + " " +
                a.toString()
            );
        }

        Field name = student.getClass().getDeclaredField("firstName");
        name.setAccessible(true);

        try {
            System.out.println(name.get(student));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Field studentID = student.getClass().getDeclaredField("id");
        studentID.setAccessible(true);
        //studentID.setLong(student, studentID.getModifiers());
        studentID.set(student, new Long(100));
        System.out.println(studentID.get(student));

    }
}


class XMLNode {
    private String name;
    private Map<String, String> arguments;
    private List<XMLNode> nodes;

    public static XMLNode createStudent(Student student) {
        XMLNode node = new XMLNode("object");
        node.addArgument("type", Student.class.getName());

        for(Field field: Student.class.getDeclaredFields()) {
            try {
                node.addNode(XMLNode.createField(field, student));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return node;
    }

    public static XMLNode createField(Field field, Student student) throws IllegalAccessException {
        field.setAccessible(true);
        XMLNode node = new XMLNode("field");
        node.addArgument("type", field.getType().getName());
        node.addArgument("id", field.getName());
        Object value = field.get(student);
        if (value == null) {
            value = "null";
        }
        String stringValue = value.toString().replaceAll("\"", "\'");
        node.addArgument("value", stringValue);

        return node;
    }

    public XMLNode(String name) {
        this.name = name;
        arguments = new HashMap<>();
        nodes = new ArrayList<>();
    }

    public void addArgument(String name, String value) {
        arguments.put(name, value);
    }

    public void addNode(XMLNode node) {
        nodes.add(node);
    }

    public String serialize() {
        StringBuilder result = new StringBuilder();
        result.append("<" + name);

        // добавляем аргументы
        if (!arguments.isEmpty()) {
            for(Map.Entry<String, String> entry: arguments.entrySet()) {
                result.append(" " + entry.getKey() + "=\"" + entry.getValue() + "\"");
            }
        }

        // добавляем вложенные ноды
        if (!nodes.isEmpty()) {
            result.append(">\n");
            for(XMLNode node: nodes) {
                result.append(node.serialize());
            }
            result.append("</" + name + ">\n");
        } else {
            result.append("/>\n");
        }
        return result.toString();
    }

    @Override
    public String toString() {
        return serialize();
    }
}




