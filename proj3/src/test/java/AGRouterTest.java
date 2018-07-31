import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class AGRouterTest extends AGMapTest {
    /**
     * Test the route-finding functionality by comparing the node id list item by item.
     * @throws Exception
     */
    @Test
    public void testShortestPath() throws Exception {
        for (TestParameters p : params) {
            LinkedList<Long> studentRouteResult = Router.shortestPath(graph,
            p.routeParams.get("start_lon"), p.routeParams.get("start_lat"),
            p.routeParams.get("end_lon"), p.routeParams.get("end_lat"));
            assertEquals(  p.routeResult, studentRouteResult);
        }
    }


    @Test
    public void testShortestPath3() throws Exception {
        LinkedList<Long> studentRouteResult = Router.shortestPath(graph,-122.23354274523257,
                37.87383979834944,-122.23307272570244,37.86020837234193);
        assertEquals(params.get(0).routeResult,studentRouteResult);
    }
}
