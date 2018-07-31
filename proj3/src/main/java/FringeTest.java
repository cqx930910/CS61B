import org.junit.Test;

import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;

public class FringeTest {
    @Test
    public void fringetest(){
        PriorityQueue<Router.Vertex> fringe = new PriorityQueue<>();
        fringe.add(new Router.Vertex(1,100.1));
        fringe.add(new Router.Vertex(2,99.1));
        fringe.add(new Router.Vertex(3,1.1));
        fringe.add(new Router.Vertex(4,2.1));
        System.out.println(fringe);
        assertEquals(fringe.poll().id,3);
        System.out.println(fringe);
        assertEquals(fringe.poll().id,4);
        System.out.println(fringe);
        assertEquals(fringe.poll().id,2);
        System.out.println(fringe);
        assertEquals(fringe.poll().id,1);
    }


}
