package db;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Arrays;

public  class Column<T> implements Iterable<T> {
    private String columnName;
    private Class  columnType;
    private List<T> items;
    private int size;
    public Column(String name, Class type){
        columnName =name;
        columnType = type;
        items = new ArrayList<T>();
        size =0;
    }

    public void add(T item){
        items.add(item);
        size++;
    }
    public String getName(){
        return columnName;
    }
    public Class getType(){
        return columnType;
    }
    public List<T> getitems(){
        return items;
    }
    public Iterator<T>  iterator(){
        return items.iterator();
    }

    public int getSize(){
        return size;
    }
    @Override
    public String toString(){
        return Arrays.toString(items.toArray());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Column<?> column = (Column<?>) o;

        if (!columnName.equals(column.columnName)) return false;
        if (!columnType.equals(column.columnType)) return false;
        return items != null ? items.equals(column.items) : column.items == null;
    }

    @Override
    public int hashCode() {
        int result = columnName.hashCode();
        result = 31 * result + columnType.hashCode();
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }
}
