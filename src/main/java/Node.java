import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Node {

    public String name = "";
    public List<Node> children = new ArrayList<Node>();

    private static String s;
    private static Integer pos;
    private static Integer depth;

    private static Node parse() throws RuntimeException {
        Node node = new Node();
        while (s.length() > pos) {
            switch (s.charAt(pos++)) {
                case '(':
                    checkName(node);
                    depth++;
                    node.children.add(Node.parse());
                    break;
                case ')':
                    checkName(node);
                    depth--;
                    return node;
                case ',':
                    checkName(node);
                    node.children.add(Node.parse());
                    break;
                default:
                    node.name += s.charAt(pos - 1);
                    if (pos < s.length() && (s.charAt(pos) == ',' || s.charAt(pos) == ')')) { // :>
                        return node;
                    }
                    break;
            }

        }
        checkName(node);
        return node;
    }

    private static void checkName(Node node) {
        if (node.name.length() == 0) {
            throw new RuntimeException();
        }
    }

    public static String flipTree(String s) {
        Node.s = s;
        pos = depth = 0;
        Node root;
        try {
            root = parse();
            if (depth != 0 || pos < s.length() - 1) {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            return "ERROR";
        }
        return root.toString();
    }

    @Override
    public String toString() {
        String s = name;
        if (children.size() > 0) {
            s += "(";
            for (int i = children.size() - 1; i >= 0; i--) {
                s += children.get(i).toString() + ",";
            }
            s = s.substring(0, s.length() - 1) + ")";
        }
        return s;
    }

    public static void main(String... args) {
        if (args.length != 1) {
            System.out.println("you must specify input file path");
            System.exit(-1);
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"));
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            for (String line; (line = br.readLine()) != null; ) {
                bw.write(Node.flipTree(line));
                bw.newLine();
            }
            br.close();
            bw.close();
        } catch (Exception e) {
            System.out.println("something goes wrong");
            System.exit(-1);
        }
    }
}
