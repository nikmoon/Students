package MyUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sa on 15.06.17.
 */
public class XMLNode {
    private XMLNode.NodeType nodeType;
    private String name;
    private Map<String, String> arguments;
    private List<XMLNode> nodes;

    private static int offsetStep = 4;

    private enum NodeType { CLASS, OBJECT, FIELD, METHOD, PARAM }


    public static XMLNode createNodeRecursive(Object obj) throws IllegalAccessException, InterruptedException {
        XMLNode node;
        if (obj instanceof Class) {
            node = new XMLNode(XMLNode.NodeType.CLASS);
            Class cl = (Class) obj;
            node.addArgument("id", cl.getName());
            for(Field field: cl.getDeclaredFields()) {
                field.setAccessible(true);
                XMLNode fieldNode;
                if (cl != field.getType()) {
                    fieldNode = createNodeRecursive(field);
                }
                else {
                    fieldNode = new XMLNode(XMLNode.NodeType.FIELD);
                    fieldNode.addArgument("type", field.getType().getName());
                    fieldNode.addArgument("id", field.getName());
                }
                node.addNode(fieldNode);
            }
        }
        else if (obj instanceof Field) {
            node = new XMLNode(XMLNode.NodeType.FIELD);
            Field field = (Field) obj;
            node.addArgument("type", field.getType().getName());
            node.addArgument("id", field.getName());
            if (field.getType().getName().startsWith("")) {
                XMLNode classNode = createNodeRecursive(field.getType());
                node.addNode(classNode);
            }
        }
        else if (obj instanceof Method) {
            node = new XMLNode(XMLNode.NodeType.METHOD);
            Method method = (Method) obj;
            node.addArgument("id", method.getName());
            node.addArgument("return", method.getReturnType().getName());
            Parameter[] parameters = method.getParameters();
            if (parameters.length > 0) {
                for (Parameter param : parameters) {
                    XMLNode paramNode = createNodeRecursive(param);
                    node.addNode(paramNode);
                }
            }
        }
        else if (obj instanceof Parameter) {
            node = new XMLNode(XMLNode.NodeType.PARAM);
            Parameter param = (Parameter) obj;
            node.addArgument("type", param.getType().getName());
            node.addArgument("id", param.getName());

        }
        else {
            node = new XMLNode(XMLNode.NodeType.OBJECT);
            node.addArgument("type", obj.getClass().getName());
            for(Field field: obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                XMLNode fieldNode = createNodeRecursive(field);
                fieldNode.addArgument("value", String.valueOf(field.get(obj)));
                node.addNode(fieldNode);
            }
            for(Method method: obj.getClass().getDeclaredMethods()) {
                method.setAccessible(true);
                XMLNode methodNode = createNodeRecursive(method);
                node.addNode(methodNode);
            }
        }
        return node;
    }

    private XMLNode(XMLNode.NodeType nodeType) {
        this.nodeType = nodeType;
        this.name = nodeType.name().toLowerCase();
        arguments = new HashMap<>();
        nodes = new ArrayList<>();
    }

    private void addArgument(String name, String value) {
        arguments.put(name, value);
    }

    private void addNode(XMLNode node) {
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
