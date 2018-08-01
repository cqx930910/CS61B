package db;

public class IndexPair {
    private final int x;
    private final int y;
    IndexPair(int x, int y) {this.x=x;this.y=y;}
    public int getindex1(){
        return x;
    }
    public int getindex2(){
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IndexPair indexPair = (IndexPair) o;

        if (x != indexPair.x) return false;
        return y == indexPair.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
