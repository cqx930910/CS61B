package db;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class Row<Value> implements Iterable<Value> {
    private List items = new ArrayList<>();

    public Iterator<Value> iterator(){
        return items.iterator();
    }
    public int size(){
        return items.size();
    }
    public Object get(int i){
        if (i>size()-1 || i<0){
            throw new ArrayIndexOutOfBoundsException("(Row) out of Bound");
        }
        return items.get(i);
    }
    public Row(String [] input, List<Column> Columns){
        for (int i =0; i<Columns.size(); i++){
            Object toadd =null;
            if (Columns.get(i).getType()== int.class) {
                toadd = Integer.parseInt (input[i]);
            }
            else {
                toadd = Columns.get(i).getType().cast(input[i]);
            }
            items.add(toadd );
            Columns.get(i).add(toadd);
        }
    }
    public Row(){
        List items = new ArrayList<>();
    }
    public void add(Object item){
        items.add(item);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Row<?> row = (Row<?>) o;

        return items.equals(row.items);
    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }
}
