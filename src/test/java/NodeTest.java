import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NodeTest {

    @Test
    public void flippingTest() {
        assertEquals(Node.flipTree("a"), "a");
        assertEquals(Node.flipTree("aaa"), "aaa");
        assertEquals(Node.flipTree("aaa(bbb,ccc)"), "aaa(ccc,bbb)");
        assertEquals(Node.flipTree("aaa(bbb(sdf(1,2),ooo(4,5,fgh(6))),456)"), "aaa(456,bbb(ooo(fgh(6),5,4),sdf(2,1)))");
        assertEquals(Node.flipTree("n(o,o,n,ra(t,t t))"), "n(ra(t t,t),n,o,o)");

        assertEquals(Node.flipTree(""), "ERROR");
        assertEquals(Node.flipTree("()"), "ERROR");
        assertEquals(Node.flipTree("a()"), "ERROR");
        assertEquals(Node.flipTree("a("), "ERROR");
        assertEquals(Node.flipTree("a(b(x,,z))"), "ERROR");
        assertEquals(Node.flipTree("a(b,c((d)"), "ERROR");
        assertEquals(Node.flipTree("a(c),b(d)"), "ERROR");
    }
}
