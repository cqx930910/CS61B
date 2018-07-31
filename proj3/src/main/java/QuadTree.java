import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


public class QuadTree {
    private Node root;

    public Node divide (Node j){
        double middleLon = (j.lrlon+j.ullon)/2;
        double middleLat= (j.lrlat+j.ullat)/2;
        insert(j,j.ullon,j.ullat,middleLon,middleLat,j.title+ "1");
        insert(j,middleLon,j.ullat,j.lrlon,middleLat,j.title+"2");
        insert(j,j.ullon,middleLat,middleLon,j.lrlat,j.title+"3");
        insert(j,middleLon,middleLat,j.lrlon,j.lrlat,j.title+"4");
        return j;
    }
    public Node treeConstruct(Node j){
        if (j.title.length()<7){
            divide(j);
            j.ul=treeConstruct(j.ul);
            j.ur=treeConstruct(j.ur);
            j.ll=treeConstruct(j.ll);
            j.lr=treeConstruct(j.lr);
        }
        return j;
    }

    public void insert( double ullon, double ullat,double lrlon,double lrlat,String title) {
        root = insert(root,  ullon, ullat,lrlon,lrlat,title);
    }
    private Node insert(Node h,double ullon, double ullat,double lrlon,double lrlat,String title){
        if (h != null) {
            double midlon = (h.ullon + h.lrlon) / 2;
            double midlat = (h.ullat + h.lrlat) / 2;
            if (  (lrlon > midlon)&&(ullat > midlat))
                h.ur = insert(h.ur,  ullon,ullat,  lrlon, lrlat,title);
            else if ((ullon<midlon)&&(ullat>midlat))
                h.ul = insert(h.ul, ullon,ullat,  lrlon, lrlat,title);
            else if ((lrlon>midlon)&&(lrlat<midlat))
                h.lr = insert(h.lr, ullon,ullat,  lrlon, lrlat,title);
            else if ((ullon<midlon)&&(lrlat<midlat))
                h.ll = insert(h.ll, ullon,ullat,  lrlon, lrlat,title);
            return h;
        }
        else {
            return new Node(ullon,ullat,lrlon, lrlat,title);
        }
    }

    @Override
    public String toString() {
        return "title is "+ root.title +"\n"+
                "upper left ="+String.valueOf(root.ullon)+"\n"+
                "upper right ="+String.valueOf(root.ullat)+"\n"+
                "lower left ="+String.valueOf(root.lrlon)+"\n"+
                "lower right ="+String.valueOf(root.lrlat)+"\n";
    }
    public Node getRoot(){
        return root;
    }
    public Node getul(){
        return root.ul;
    }
    public Node getur(){
        return root.ur;
    }
    public Node getlr(){return root.lr;}
    public Node getll(){ return root.ll;}

    public PriorityQueue<Node> search (Node j,double lrlon,double ullon,double w, double ullat, double lrlat){
        Node target = new Node(ullon,ullat,lrlon,lrlat,"target");
        PriorityQueue<Node> track = new PriorityQueue<>();
        track = search(j,target,w,track);

        return track;
    }
    public PriorityQueue<Node> search (Node j, Node target, double w, PriorityQueue<Node> track){
        if (!Node.isoverlap(j, target)){
            return track;
        }
        else {
            if (((target.lrlon - target.ullon) / w <= (j.lrlon - j.ullon) / 256)&&j.ul!=null) {
                track = search(j.ul, target, w, track);
                track = search(j.ur, target, w, track);
                track = search(j.ll, target, w, track);
                track = search(j.lr, target, w, track);
            } else {
                if (Node.isoverlap(j, target)) track.add(j);
            }
        }
        return track;
    }

    public static Map<String, Object> tracktoResults(PriorityQueue<Node> track){
        Map<String, Object> results = new HashMap<>();
        int n = track.size();

        Node first = track.peek();
        Double raster_ul_lon = first.ullon;
        Double raster_ul_lat =first.ullat;
        int depth = first.title.length();
        if (first.title=="") first.title="root";
        PriorityQueue<Node> backup = new PriorityQueue<>();
        while (track.size()>1){
            backup.add(track.poll());
        }
        Double raster_lr_lon = track.peek().lrlon;
        Double raster_lr_lat = track.peek().lrlat;
        backup.add(track.poll());
        track = backup;
        boolean query_success = n>0;
        int width = (int)((raster_lr_lon- raster_ul_lon)/ (first.lrlon-first.ullon));
        int height =(int)((raster_ul_lat- raster_lr_lat)/ (first.ullat-first.lrlat));
        String[][] render_grid = new String[height][width];
        for (int j =0;j<height;j++){
            for (int i= 0;i<width;i++){
                render_grid[j][i]= "img/"+track.poll().title+".png";
            }
        }
        results.put("render_grid",render_grid);
        results.put("query_success",query_success);
        results.put("depth",depth);
        results.put("raster_ul_lon",raster_ul_lon);
        results.put("raster_lr_lon",raster_lr_lon);
        results.put("raster_lr_lat",raster_lr_lat);
        results.put("raster_ul_lat",raster_ul_lat);
        return results;
    }
    public static void main(String[] args) {
        QuadTree test = new QuadTree();
        test.insert(-100,100,100,-100,"layer1");
        test.insert(-100,100,-1,0,"layer2ul");
        test.insert(0,100,100,0,"layer2ur");
        test.insert(-100,-1,-1,-100,"layer2ll");
        test.insert(1,-1,100,-100,"layer2lr");
        System.out.println("root is "+test.root.title);
        System.out.println("upper left is "+ test.root.ul.title);
        System.out.println("upper right is "+ test.root.ur.title);
        System.out.println("lower left is "+ test.root.ll.title);
        System.out.println("lower right is "+ test.root.lr.title);
        System.out.println("root is" + test.toString());
    }
}

