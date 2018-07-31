import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

import static org.junit.Assert.*;
public class TestQuadTree {
    @Test
    public void mapConstructor(){
        QuadTree test = new QuadTree();
        test.insert(MapServer.ROOT_ULLON,MapServer.ROOT_ULLAT,MapServer.ROOT_LRLON,MapServer.ROOT_LRLAT,"");
        double middleLat = (test.getRoot().lrlat+test.getRoot().ullat)/2;
        double middleLon = (test.getRoot().lrlon+test.getRoot().ullon)/2;
//        System.out.println("lon ="+String.valueOf(middleLon ));
//        System.out.println("lat ="+String.valueOf(test.getRoot().ullat));
//        System.out.println("lon ="+String.valueOf(test.getRoot().lrlon) );
//        System.out.println("lat ="+String.valueOf(middleLat) );
//        test.insert(test.getRoot().ullon,test.getRoot().ullat,middleLon,middleLat,"1");
//        test.insert(middleLon,test.getRoot().ullat,test.getRoot().lrlon,middleLat,"2");
//        test.insert(test.getRoot().ullon,middleLat,middleLon,test.getRoot().lrlat,"3");
//        test.insert(middleLon,middleLat,test.getRoot().lrlon,test.getRoot().lrlat,"4");
        test.treeConstruct(test.getRoot());

        System.out.println(test.getul().ul.ur.ul.lr.toString());
    }

    @Test
    public void overlapTest(){
        Node n1 = new Node(10,40,40,20,"1");
        Node n2 = new Node(-30,40,10,10,"2");
        assertTrue(!Node.isoverlap(n1,n2));
    }
    @Test
    public void nodeCompareTest(){
        Node n1 = new Node (10,40,40,20,"1");
        Node n2 = new Node(10,40,40,20,"2");
        assertEquals(n1.compareTo(n2),0);
        Node n3 = new Node(0,0,20,-20,"2");
        Node n4 = new Node(-20,20,0,0,"2");
        assertEquals(n3.compareTo(n4),-1);
        assertEquals(n4.compareTo(n3),1);
        Node n5 = new Node(-20,0,0,-20,"2");
        assertEquals(n5.compareTo(n3),-1);
        assertEquals(n3.compareTo(n5),1);

    }
    @Test
    public void searchTest(){
        QuadTree test = new QuadTree();
        test.insert(MapServer.ROOT_ULLON,MapServer.ROOT_ULLAT,MapServer.ROOT_LRLON,MapServer.ROOT_LRLAT,"");
        test.treeConstruct(test.getRoot());
        PriorityQueue<Node> track =test.search(test.getRoot(),-122.20908713544797,-122.3027284165759,305.0,37.88708748276975,37.848731523430196);
        System.out.println(track);
    }
    @Test
    public void searchTest2(){
        QuadTree test = new QuadTree();
        test.insert(MapServer.ROOT_ULLON,MapServer.ROOT_ULLAT,MapServer.ROOT_LRLON,MapServer.ROOT_LRLAT,"");
        test.treeConstruct(test.getRoot());
        PriorityQueue<Node> track =test.search(test.getRoot(),-122.24053369025242,-122.24163047377972,892.0,37.87655856892288,37.87548268822065);
        System.out.println(track);
    }
    @Test
    public void tracktoResultsTest(){
        QuadTree test = new QuadTree();
        test.insert(MapServer.ROOT_ULLON,MapServer.ROOT_ULLAT,MapServer.ROOT_LRLON,MapServer.ROOT_LRLAT,"");
        test.treeConstruct(test.getRoot());
        PriorityQueue<Node> track =test.search(test.getRoot(),-122.24053369025242,-122.24163047377972,892.0,37.87655856892288,37.87548268822065);
        System.out.println(Arrays.deepToString((Object[])QuadTree.tracktoResults(track).get("render_grid")));
    }

}
