package db;

import java.util.HashSet;

public class ColumnPair {
    private Column col1;
    private Column col2;
    private HashSet<IndexPair> pair = new HashSet<>();
    ColumnPair (Column col1, Column col2){
        this.col1 = col1;
        this.col2 = col2;
        for (int i =0; i<col1.getSize();i++){
            for (int j =0; j<col2.getSize();j++){
                if (col1.getitems().get(i).equals(col2.getitems().get(j))){
                    pair.add(new IndexPair(i,j));
                    break;
                }
            }
        }
    }
    public HashSet<IndexPair> getPair(){
        return pair;
    }
}
