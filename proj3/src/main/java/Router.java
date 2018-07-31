import java.util.*;

/**
 * This class provides a shortestPath method for finding routes between two points
 * on the map. Start by using Dijkstra's, and if your code isn't fast enough for your
 * satisfaction (or the autograder), upgrade your implementation by switching it to A*.
 * Your code will probably not be fast enough to pass the autograder unless you use A*.
 * The difference between A* and Dijkstra's is only a couple of lines of code, and boils
 * down to the priority you use to order your vertices.
 */
public class Router {
    /**
     * Return a LinkedList of <code>Long</code>s representing the shortest path from st to dest, 
     * where the longs are node IDs.
     */
    static HashMap <Long, Double> bestDistance= new HashMap<>();
    static HashMap <Long, Long> bestParent = new HashMap<>();
    static PriorityQueue<Vertex> fringe = new PriorityQueue<>();
    public static LinkedList<Long> shortestPath(GraphDB g, double stlon, double stlat, double destlon, double destlat) {
        long start =  g.closest(stlon,stlat);
        long destination =g.closest(destlon,destlat);
        LinkedList<Long> toReturn = aStar(g,start,destination);
        Collections.reverse(toReturn);
        return toReturn ;

    }

    private static LinkedList<Long> aStar(GraphDB g,long start,long destination){
        for (long i: g.vertices()){
            bestDistance.put(i,g.distance(start,destination)*100000);
        }
        bestDistance.put(start,0.00000001);
        fringe.add(new Vertex(start,0.00000001));
        LinkedList<Long> toReturn = aStarthelper(g,destination);
        return toReturn;
    }
    private static LinkedList<Long> aStarthelper(GraphDB g, long destination) {
        if (fringe.size() < 1) {
            throw new RuntimeException("out of fringe");
        }
        long v = fringe.poll().id;
        while (v != destination) {
            for (long w : g.adjacent(v)) {
                if (bestDistance.get(v) + g.distance(v, w) < bestDistance.get(w)) {
                    bestDistance.put(w, bestDistance.get(v) + g.distance(v, w));
                    bestParent.put(w, v);
                    fringe.add(new Vertex(w, bestDistance.get(v) + g.distance(v, w) + g.distance(destination, w)));
                }
            }
            v = fringe.poll().id;
        }
        return sequence(v);
    }

    private static LinkedList<Long> sequence(long v){
        LinkedList<Long> toReturn = new LinkedList<>();
        toReturn.add(v);
        while (bestParent.get(v)!= null){
            toReturn.add(bestParent.get(v));
            v = bestParent.get(v);
        }
        return toReturn;
    }
    public static class Vertex implements Comparable<Vertex> {
        long id;
        double distance;
        Vertex(long id, double distance){
            this.id = id;
            this.distance = distance;
        }
        @Override
        public int compareTo(Vertex v1){
            return ( new Double ((this.distance-v1.distance)*10000000).intValue()) ;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "id=" + id +
                    ", distance=" + distance +
                    '}';
        }
    }
}
