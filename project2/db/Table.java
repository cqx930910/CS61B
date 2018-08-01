package db;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.io.PrintStream;
public class Table {
    private List<Row> rows = new ArrayList<>();
    private List<Column> columns = new ArrayList<>();
    Table(String[] columnNames, Class[] columnTypes) {
        for (int i = 0; i < columnNames.length; i++) {
            Column new_col = new Column(columnNames[i], columnTypes[i]);
            columns.add(new_col);
        }
    }
    Table (List<Column> Columns){
            for (Column i :Columns){
                columns.add(i);
            }
            for (int i =0; i<Columns.get(0).getSize();i++){
                Row toadd = new Row();
                for (int j =0;j<Columns.size();j++) {
                    toadd.add(Columns.get(j).getitems().get(i));
                }
                rows.add(toadd);
            }
    }

    public int rowsize(){return rows.size();}
    public int column_size(){return columns.size();}
    public void addRow(Row row) {
        rows.add(row);
    }

    public List<Row> getRows(){
        return rows;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void writeTable(String name){
        PrintStream output;
        output = null;
        try {
            String sep;
            sep = " ";
            output = new PrintStream(name + ".tbl");
            for (int k = 0; k < getColumns().size(); k++) {
                if (getColumns().get(k).getType()!=String.class){
                if (k != getColumns().size() - 1) {
                    output.print(getColumns().get(k).getName().toString()+ sep+
                            getColumns().get(k).getType().toString()+ ",");
                } else {
                    output.print(getColumns().get(k).getName().toString()+ sep+
                            getColumns().get(k).getType().toString());
                }
            }
            else{
                    if (k != getColumns().size() - 1) {
                        output.print(getColumns().get(k).getName().toString()+ sep+
                                "string"+ ",");
                    } else {
                        output.print(getColumns().get(k).getName().toString()+ sep+
                                "string");
                    }
                }
            }
            output.println();
            for (Row value: getRows()) {
                for (int i = 0; i < value.size(); i++) {
                    if (i != value.size() - 1) {
                        output.print(value.get(i) + ",");
                    } else {
                        output.print(value.get(i));
                    }
                } output.println();
            }
        } catch (IOException e) {
            throw new Error ("trouble writing to %s.db");
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }

    public Table join (Table tojoin){
        List<Column> joinresult = this.commonColomn(tojoin);
        return new Table (joinresult);
    }

    public List<Column> commonColomn(Table tojoin){
        List<Column> toReturn= new ArrayList<>();
        List<ColumnPair> Pairs = new ArrayList<>();
        for (Column col1:this.getColumns()){
            for (Column col2: tojoin.getColumns()){
                if (col1.getName().equals(col2.getName()) &&
                        col1.getType().equals(col2.getType())) {
                    ColumnPair newPair = new ColumnPair(col1, col2);
                    if (!newPair.getPair().isEmpty()) {
                        Pairs.add(newPair);
                    }
                }
            }
        }
        HashSet<IndexPair> CommomPairs = Pairs.get(0).getPair();
        for (int i =1; i< Pairs.size();i++){
            CommomPairs.retainAll(Pairs.get(i).getPair());
        }

        for (Column col1:this.getColumns()) {
            for (Column col2 : tojoin.getColumns()) {
                if (col1.getName().equals(col2.getName()) &&
                        col1.getType().equals(col2.getType())) {
                    Column newCol = new Column(col1.getName(),col1.getType());
                    for (IndexPair pair : CommomPairs){
                        newCol.add(col1.getitems().get(pair.getindex1()));
                    }
                    toReturn.add(newCol);
                }
            }
        }
        List<Column> toReturncopy = new ArrayList<>(toReturn);

        for (Column col1:this.getColumns()){
            boolean checkifexist= false;
            for (Column col2: toReturncopy){
                if (col1.getName().equals(col2.getName()) ==true) {
                    checkifexist =true;
                }
            }
            if (checkifexist==false){
                Column toadd = new Column(col1.getName(),col1.getType());
                for (IndexPair pair: CommomPairs){
                    toadd.add(col1.getitems().get(pair.getindex1()));
                }
                toReturn.add(toadd);
            }
        }

        for (Column col1:tojoin.getColumns()){
            boolean checkifexist= false;
            for (Column col2: toReturncopy){
                if (col1.getName().equals(col2.getName()) ==true) {
                    checkifexist =true;
                }
            }
            if (checkifexist==false){
                Column toadd = new Column(col1.getName(),col1.getType());
                for (IndexPair pair: CommomPairs){
                    toadd.add(col1.getitems().get(pair.getindex1()));
                }
                toReturn.add(toadd);
            }
        }
        return toReturn;
    }
    public List<Column> addCommonColumn(List<Column> toReturn,List<Column> toReturncopy, List<Column> reference,HashSet<IndexPair> CommomPairs){
        for (Column col1:reference){
            boolean checkifexist= false;
            for (Column col2: toReturncopy){
                if (col1.getName().equals(col2.getName()) ==true) {
                    checkifexist =true;
                }
            }
            if (checkifexist==false){
                Column toadd = new Column(col1.getName(),col1.getType());
                for (IndexPair pair: CommomPairs){
                    toadd.add(col1.getitems().get(pair.getindex1()));
                }
                toReturn.add(toadd);
            }
        }
        return toReturn;
    }
    public HashSet<IndexPair> findallpair(Table tojoin){
        List<ColumnPair> Pairs = new ArrayList<>();
        for (Column col1 : this.getColumns()) {
            for (Column col2 : tojoin.getColumns()) {
                if (col1.getName().equals(col2.getName()) &&
                        col1.getType().equals(col2.getType())) {
                    ColumnPair newPair = new ColumnPair(col1, col2);
                    if (!newPair.getPair().isEmpty()) {
                        Pairs.add(newPair);
                    }
                }
            }
        }

        HashSet<IndexPair> CommomPairs = Pairs.get(0).getPair();
        return CommomPairs;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return getColumns().equals(table.getColumns());
    }

    @Override
    public int hashCode() {
        return getColumns().hashCode();
    }
}
