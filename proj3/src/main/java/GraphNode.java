import java.util.ArrayList;
import java.util.List;

public class GraphNode {
    long id;
    double lon;
    double lat;
    String name;
    List<Long> adj = new ArrayList<>();
    GraphNode(String id,String lon,String lat){
        this.id = Long.parseLong (id) ;
        this.lon = Double.parseDouble(lon) ;
        this.lat = Double.parseDouble(lat) ;

    }
    double distance (GraphNode n){
        return Math.sqrt(Math.pow((this.lon-n.lon),2)+(Math.pow((this.lat-n.lat),2)));
    }
    double distance (double lon, double lat){
        return Math.sqrt(Math.pow((this.lon-lon),2)+(Math.pow((this.lat-lat),2)));
    }
}
