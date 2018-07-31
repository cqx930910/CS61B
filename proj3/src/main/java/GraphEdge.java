import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphEdge {
    int maxspeed;
    String type;
    boolean flag;
    String name;
    Queue<Long> sequence = new LinkedList<>();
    ArrayList<GraphNode> nodes = new ArrayList<>();
    long id;
    GraphEdge(int maxspeed,String type,boolean flag, String name){
        this.maxspeed=maxspeed;
        this.type = type;
        this.flag = flag;
        this.name = name;
    }
    GraphEdge(String id){
        this.id = Long.parseLong(id);
    }
    GraphEdge(){}
    void addNode(String id){
        sequence.add(Long.parseLong(id));
    }

}
