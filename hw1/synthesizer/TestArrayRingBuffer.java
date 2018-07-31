package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
    }
    @Test
    public void IterableTest(){
        ArrayRingBuffer<String> test = new ArrayRingBuffer<>(10);
        test.enqueue("a");
        test.enqueue("b");
        test.enqueue("c");
        test.enqueue("d");
        test.enqueue("e");
        for (String i :test){
            System.out.println(i);
        }
    }
    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
