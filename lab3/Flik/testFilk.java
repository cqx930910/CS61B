import org.junit.Test;

import static org.junit.Assert.*;
public class testFilk {
    @Test
    public void testequal(){
        System.out.println(Flik.isSameNumber(128, 128));
        System.out.println(128==128);
//        assertTrue(Flik.isSameNumber(1, 1));

        assertTrue(Flik.isSameNumber(128, 128));
    }


    public static void main(String args[]){
        jh61b.junit.TestRunner.runTests("all", testFilk.class);
    }
}
