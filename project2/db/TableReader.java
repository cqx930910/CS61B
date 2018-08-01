package db;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TableReader{
    BufferedReader input;
    Table toSave;
    List<Column> ColumnList= new ArrayList<>();
    private static final HashMap PRIMITIVE_NAME_TYPE_MAP = new HashMap();
    /** Setup the primitives map. */
    static {
        PRIMITIVE_NAME_TYPE_MAP.put("boolean", boolean.class);
        PRIMITIVE_NAME_TYPE_MAP.put("byte", byte.class);
        PRIMITIVE_NAME_TYPE_MAP.put("char", char.class);
        PRIMITIVE_NAME_TYPE_MAP.put("short", short.class);
        PRIMITIVE_NAME_TYPE_MAP.put("int", int.class);
        PRIMITIVE_NAME_TYPE_MAP.put("long", long.class);
        PRIMITIVE_NAME_TYPE_MAP.put("float", float.class);
        PRIMITIVE_NAME_TYPE_MAP.put("double", double.class);
        PRIMITIVE_NAME_TYPE_MAP.put("string", String.class);
    }
    public Class getClass(String type){
        return (Class) PRIMITIVE_NAME_TYPE_MAP.get(type);
    }
    public TableReader(){
        toSave = null;
    }
    public Table TableGetter(){
        return toSave;
    }
    public void read(String fileName) {
        try {
            input = null;
            input = new BufferedReader(new FileReader(fileName));
            String header = input.readLine();
            String[] columnNames = header.split(",");
            String newHeader = input.readLine();
            for (String i : columnNames){
                String [] Columninfo = i.split(" ");
                Column toadd = new Column(Columninfo[0],getClass(Columninfo[1]));
                ColumnList.add(toadd);
            }
            toSave = new Table (ColumnList);
            while (newHeader != null) {
                String[] row = newHeader.split(",");
//                System.out.println(Arrays.toString(row) );
                Row newRow= new Row(row, ColumnList);
                toSave.addRow(newRow);
                newHeader = input.readLine();
            }

        } catch (FileNotFoundException e) {
            throw new Error("could not find ");
        }
        catch (IOException e) {
            throw new Error("problem reading from ");
        }
        finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    /* Ignore IOException */
                }
            }
        }
    }
}
