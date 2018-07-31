import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.*;

/**
 * Graph for storing all of the intersection (vertex) and road (edge) information.
 * Uses your GraphBuildingHandler to convert the XML files into a graph. Your
 * code must include the vertices, adjacent, distance, closest, lat, and lon
 * methods. You'll also need to include instance variables and methods for
 * modifying the graph (e.g. addNode and addEdge).
 *
 * @author Alan Yao, Josh Hug
 */
public class GraphDB {
    /** Your instance variables for storing the graph. You should consider
     * creating helper classes, e.g. Node, Edge, etc. */

    /**
     * Example constructor shows how to create and start an XML parser.
     * You do not need to modify this constructor, but you're welcome to do so.
     * @param dbPath Path to the XML file to be parsed.
     */
    HashMap<Long,GraphNode> nodeMap = new HashMap<>();
    HashMap<Long,GraphEdge> edgeMap= new HashMap<>();
    TrieST<Location> locationTrie = new TrieST<>();
    public GraphDB(String dbPath) {
        try {
            File inputFile = new File(dbPath);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            GraphBuildingHandler gbh = new GraphBuildingHandler(this);
            saxParser.parse(inputFile, gbh);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        clean();
    }

    /**
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

    /**
     *  Remove nodes with no connections from the graph.
     *  While this does not guarantee that any two nodes in the remaining graph are connected,
     *  we can reasonably assume this since typically roads are connected.
     */
    private void clean() {
        // TODO: Your code here.
        HashSet<Long> removeItemIDs = new HashSet<>();
        for (long i: this.vertices()){
            if (nodeMap.get(i).adj.size()==0){
                removeItemIDs.add(i);
            }
        }
        nodeMap.keySet().removeAll(removeItemIDs);
    }

    /** Returns an iterable of all vertex IDs in the graph. */
    Iterable<Long> vertices() {
        //YOUR CODE HERE, this currently returns only an empty list.
        return new keyInteratable(nodeMap.keySet());
    }
    class keyInteratable implements Iterable<Long> {
        Set<Long> keyset;
        keyInteratable(Set<Long> keyset){
            this.keyset= keyset;
        }
        public Iterator<Long> iterator(){
            return new keyInterator(keyset);
        }
        class keyInterator implements Iterator<Long> {
            private Queue<Long> keyQueue;
            keyInterator(Set<Long> keyset) {
                keyQueue = new LinkedList<>(keyset);
            }

            @Override
            public boolean hasNext() {
                return keyQueue.size() > 0;
            }

            @Override
            public Long next() {
                return keyQueue.poll();
        }
    }
    }
    /** Returns ids of all vertices adjacent to v. */
    Iterable<Long> adjacent(long v) {
        return nodeMap.get(v).adj;
    }

    /** Returns the Euclidean distance between vertices v and w, where Euclidean distance
     *  is defined as sqrt( (lonV - lonV)^2 + (latV - latV)^2 ). */
    double distance(long v, long w) {
        return nodeMap.get(v).distance(nodeMap.get(w));
    }

    /** Returns the vertex id closest to the given longitude and latitude. */
    long closest(double lon, double lat) {
        clean();
        if(nodeMap.keySet().size()==0){
            throw new RuntimeException("Nothing inside the Graph, cannot find the closet vertex");
        }
        GraphNode minNode= new ArrayList<GraphNode>(nodeMap.values()).get(0) ;
        double min=minNode.distance(lon,lat) ;

        for (long n: this.vertices()){
            if(nodeMap.get(n).distance(lon,lat)<min){
                min = nodeMap.get(n).distance(lon,lat);
                minNode = nodeMap.get(n);
            }
        }
        return minNode.id;
    }

    /** Longitude of vertex v. */
    double lon(long v) {
        return nodeMap.get(v).lon;
    }

    /** Latitude of vertex v. */
    double lat(long v) {
        return nodeMap.get(v).lat;
    }
    void addNode(GraphNode n){
        nodeMap.put(n.id,n);
    }
    void addEdge(GraphEdge e){
        if (e.sequence.size()<2){
            return;
        }
        edgeMap.put(e.id,e);
        GraphNode previous = nodeMap.get(e.sequence.poll()) ;
        while (e.sequence.size()>0) {
            GraphNode next = nodeMap.get(e.sequence.poll());
            previous.adj.add(next.id);
            next.adj.add(previous.id);
            e.nodes.add(previous);
            previous = next;
        }
        e.nodes.add(previous);
    }
}
