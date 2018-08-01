import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;
public class TestArrayDeque1B {
    @Test
    public void addLastTest(){
        OperationSequence fs = new OperationSequence();
        StudentArrayDeque<Integer> test1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> test2 = new ArrayDequeSolution<>();
        for (int i =0; i<10; i++){
            if (StdRandom.uniform()< 0.5){
                DequeOperation op = new DequeOperation("addFirst", i);
                test1.addFirst(i);
                test2.addFirst(i);
                fs.addOperation(op);
            }
            else{
                DequeOperation op = new DequeOperation("addLast", i);
                test1.addLast(i);
                test2.addLast(i);
                fs.addOperation(op);
            }
        }
        for (int i =0; i<10; i++){
            if (StdRandom.uniform()< 0.5){
//                assertEquals(test1.removeFirst(),test2.removeFirst());
            }
            else{
                DequeOperation op = new DequeOperation("removeLast");
                Integer actual = test1.removeLast();
                Integer expected = test2.removeLast();
                fs.addOperation(op);
                assertEquals(fs.toString()+"Oh noooo!\nThis is bad:\n   Random number " + actual
                                + " not equal to " + expected + "!",
                        expected, actual);

            }
        }
//        assertEquals(test1.size(),test2.size());

//        for (int i =0; i< test2.size();i++){
//            System.out.print (test1.get(i));
//            System.out.print (test2.get(i));
//            System.out.println();
//            assertEquals(test1.get(i),test2.get(i));
//        }

    }

}
