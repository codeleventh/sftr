import java.util.ArrayList;
import java.util.List;

public class Node {

    String name = "";
    List<Node> children = new ArrayList<Node>();

    private static String s;
    private static Integer pos;
    private static Integer depth;

    static Node build(String s) throws RuntimeException {
        System.out.println(s);
        Node.s = s;
        pos = depth = 0;
        return parse();
    }

    static Node parse() throws RuntimeException {
        Node node = new Node();
        while (s.length() > pos) {
            switch (s.charAt(pos)) {
                case ' ':
                    pos++;
                    break;
                case '(':
                    pos++;
                    depth++;
                    node.children.add(Node.parse());
                    // пропуск всего до )
                    break;
                case ')':
                    pos++;
                    depth--;
                    return node;
                case ',':
                    pos++;
                    node.children.add(Node.parse());
                    break;
                //                    }
                //                break;
                default:
                    node.name += s.charAt(pos);
                    pos++;
                    if (pos < s.length()) {
                        char a = s.charAt(pos);
                        if (a == ',' || a == ')') { // :>
                            return node;
                        }

                    }
            }
        }
        return node;
    }

    @Override
    public String toString() {
        // R to L
        String s = name;
        if (children.size() > 0) {
            s += "(";
            for (Node n : children) {
                s += n.toString() + ",";
            }
            s = s.substring(0, s.length() - 1);
            s += ")";
        }
        return s;
    }

    public static void main(String... s) {
        System.out.println(Node.build("aaa(bbb,ccc)"));
        System.out.println(Node.build("aaa(bbb,ccc(dda))"));
        System.out.println(Node.build("test"));
        System.out.println(Node.build("aaa(bbb(sdf(1,2),ooo(4,5,fgh(6))),456)"));
        System.out.println(Node.build("aaa(456,bbb(ooo(fgh(6),5,4),sdf(2,1)))"));
    }
}

// TODO:
// test!
// file IO
// назовки класс каким-нибудь NodeBuilder
// строку-то можешь и не передавать

// постпроверка на то, что вся строка съедена, глубина ноль
// и лови экцепшны