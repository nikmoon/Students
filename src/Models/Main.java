package Models;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * Created by sa on 12.06.17.
 */
public class Main {

    public static void main(String[] args) {
        Student student = new Student("Connor", "Reese", "John", new GregorianCalendar());
        student.setGroup(new Group("История"));

        XMLNode studentNode = XMLNode.createNode(student);
        System.out.println(studentNode);
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

    private static int offsetStep = 4;

    public static XMLNode createNode(WithID obj, String nodeName) {
        Class<? extends WithID> cl = obj.getClass();

        XMLNode node = new XMLNode(nodeName.replace("Models.", ""));
        node.addArgument("type", cl.getName().replace("Models.", ""));

        for(Field field: cl.getDeclaredFields()) {
            try {
                node.addNode(XMLNode.createField(field, obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        for(Method method: cl.getDeclaredMethods()) {
            node.addNode(XMLNode.createMethod(method));
        }
        return node;
    }

    public static XMLNode createNode(WithID obj) {
        return createNode(obj, "object");
    }

    public static XMLNode createMethod(Method method) {
        method.setAccessible(true);
        XMLNode node = new XMLNode("method");

        node.addArgument("id", method.getName());
        node.addArgument("return", method.getReturnType().getName());

        Parameter[] parameters = method.getParameters();
        if (parameters.length > 0) {
            for (Parameter param : parameters) {
                XMLNode paramNode = new XMLNode("arg");
                paramNode.addArgument("type", param.getType().getName());
                paramNode.addArgument("id", param.getName());
                node.addNode(paramNode);
            }
        }
        return node;
    }

    public static XMLNode createField(Field field, WithID obj) throws IllegalAccessException {
        field.setAccessible(true);
        XMLNode node = new XMLNode("field");

        String fieldType = field.getType().getName();
        Object value = field.get(obj);
        String fieldValue = value == null ? "null" : value.toString().replaceAll("\"", "\\\"");

        node.addArgument("type", fieldType);
        node.addArgument("id", field.getName());
        node.addArgument("value", fieldValue);

        if (value != null)
            if (fieldType.startsWith("Models.")) {
                node.addNode(createNode((WithID) value, fieldType));
            }

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

    public String serialize(int offset) {

        String offsetLine = new String(new char[offset]).replace("\0", " ");

        StringBuilder result = new StringBuilder(offsetLine);
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
                result.append(node.serialize(offset + XMLNode.offsetStep));
            }
            result.append(offsetLine);
            result.append("</" + name + ">\n");
        } else {
            result.append("/>\n");
        }
        return result.toString();
    }

    @Override
    public String toString() {
        return serialize(0);
    }
}




