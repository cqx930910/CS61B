public class Node implements Comparable<Node>{
    Node ul;
    Node ur;
    Node ll;
    Node lr;
    String title;
    double ullat;
    double ullon;
    double lrlat;
    double lrlon;

    Node( double ullon, double ullat, double lrlon, double lrlat,String title) {
        if (ullon>= lrlon||ullat<= lrlat){
            throw new RuntimeException("wrong coordinates");
        }
        this.title = title;
        this.ullat = ullat;
        this.ullon = ullon;
        this.lrlat = lrlat;
        this.lrlon = lrlon;
    }

    @Override
    public String toString() {
        return "Node{" +
                "title='" + title + '\'' +
                ", ullon=" + ullon +
                ", ullat=" + ullat +
                ", lrlon=" + lrlon +
                ", lrlat=" + lrlat +
                '}';
    }
    public static boolean isoverlap(Node a, Node b){
        if(a.lrlon<=b.ullon||b.lrlon<=a.ullon){
            return false;
        }
        if (a.lrlat>=b.ullat || b.lrlat >=a.ullat){
            return false;
        }
        return true;
    }
    @Override
    public int compareTo(Node t){
        if(this.ullat <t.ullat){
            return 1;
        }
        else if (this.ullat ==t.ullat) {
            if (this.ullon > t.ullon){
                return 1;
            }
            else if (this.ullon == t.ullon) {
                return 0;
            }
        }
        return -1;
    }

}
