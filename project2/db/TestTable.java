package db;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;
import db.Table;
import db.TableReader;
import java.util.Arrays;
public class TestTable {
    @Test
    public void writetest() {
        PrintStream output;
        try {
            output = new PrintStream("fans4.tbl");

        } catch (IOException e) {
            throw new Error("Wrong");
        }
    }
    @Test
    public void readTest() {
        BufferedReader input= null;
        try {
            input = new BufferedReader(new FileReader("records.tbl"));
            String header = input.readLine();
            System.out.println (header);
            String[] columnNames = header.split(",");
            String newHeader = input.readLine();
            for (String i : columnNames){
                System.out.println (i);
            }
            while (newHeader != null) {
                String[] row = newHeader.split(",");
                System.out.println(newHeader);
                System.out.println(Arrays.toString(row) );
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

    @Test
    public void readTableTest(){
        TableReader i = new TableReader();
        i.read("records.tbl");
        List<Column> target_columns = new ArrayList<>();
        target_columns.add(new Column("TeamName",String.class));
        target_columns.add(new Column("Season",int.class));
        target_columns.add(new Column("Wins",int.class));
        target_columns.add(new Column("Losses",int.class));
        target_columns.add(new Column("Ties",int.class));
        Table target = new Table (target_columns);
        assertEquals(target.getColumns()
                , i.TableGetter().getColumns());

    }
    @Test
    public void readTableRowTest(){
        TableReader i = new TableReader();
        i.read("records.tbl");
        List<Row> Target = new ArrayList<>();
        List<Column> target_columns = new ArrayList<>();
        target_columns.add(new Column("TeamNa   me",String.class));
        target_columns.add(new Column("Season",int.class));
        target_columns.add(new Column("Wins",int.class));
        target_columns.add(new Column("Losses",int.class));
        target_columns.add(new Column("Ties",int.class));
        String [] input1 = {"'Golden Bears'", "2016", "5", "7", "0"};
        String [] input2 ={"'Golden Bears'","2015","8","5","0"};
        String [] input3 ={"'Golden Bears'","2014","5","7","0"};
        Row inputRow1 = new Row(input1, target_columns);
        Row inputRow2 = new Row(input2, target_columns);
        Row inputRow3 = new Row(input3, target_columns);
        Target.add(inputRow1);
        Target.add(inputRow2);
        Target.add(inputRow3);
        assertEquals(Target,i.TableGetter().getRows());
        i.TableGetter().writeTable("lucasTest");
    }

    @Test
    public void columnTest(){
        TableReader i = new TableReader();
        i.read("records.tbl");
        List<Row> Target = new ArrayList<>();
        List<Column> target_columns = new ArrayList<>();
        target_columns.add(new Column("TeamName",String.class));
        target_columns.add(new Column("Season",int.class));
        target_columns.add(new Column("Wins",int.class));
        target_columns.add(new Column("Losses",int.class));
        target_columns.add(new Column("Ties",int.class));
        String [] input1 = {"'Golden Bears'", "2016", "5", "7", "0"};
        String [] input2 ={"'Golden Bears'","2015","8","5","0"};
        String [] input3 ={"'Golden Bears'","2014","5","7","0"};
        Row inputRow1 = new Row(input1, target_columns);
        Row inputRow2 = new Row(input2, target_columns);
        Row inputRow3 = new Row(input3, target_columns);
        Target.add(inputRow1);
        Target.add(inputRow2);
        Target.add(inputRow3);
        Table target = new Table(target_columns);
        assertEquals(target.getColumns(),i.TableGetter().getColumns());
    }
    @Test
    public void commonColTest(){
        TableReader grade1 = new TableReader();
        grade1.read("grade1.tbl");
        TableReader grade2 = new TableReader();
        grade2.read("grade2.tbl");
        List<Column> actual = grade1.TableGetter().commonColomn(grade2.TableGetter());
        List<Column> target= new ArrayList<>();
        Column Name = new Column("Name",String.class);
        Name.add("'Lucas'");
        Name.add("'Cindy'");
        target.add(Name);
        assertEquals(target,actual);

    }
    @Test
    public void pairTest(){
        TableReader grade1 = new TableReader();
        grade1.read("grade1.tbl");
        TableReader grade2 = new TableReader();
        grade2.read("grade2.tbl");
        HashSet<IndexPair> actual = grade1.TableGetter().findallpair (grade2.TableGetter());
        IndexPair pair = new IndexPair(0,0);
        HashSet<IndexPair> target = new HashSet<>();
        target.add(pair);
        assertEquals(target,actual);

    }
    @Test
    public void t1joint2(){
        TableReader t1 = new TableReader();
        t1.read("t1.tbl");
        TableReader t2 = new TableReader();
        t2.read("t2.tbl");
        List<Column> actual = t1.TableGetter().commonColomn(t2.TableGetter());
        Table t1t2 = new Table (actual);
        t1t2.writeTable("t1joint2.tbl");
    }

    @Test
    public void t4joint5(){
        TableReader t4 = new TableReader();
        t4.read("t4.tbl");
        TableReader t5 = new TableReader();
        t5.read("t5.tbl");
        List<Column> actual = t4.TableGetter().commonColomn(t5.TableGetter());
        Table t4t5 = new Table (actual);
        t4t5.writeTable("t4joint5.tbl");
    }

    @Test
    public void joinTest(){
        TableReader t4 = new TableReader();
        t4.read("t4.tbl");
        TableReader t5 = new TableReader();
        t5.read("t5.tbl");
        Table t4t5 = t4.TableGetter().join(t5.TableGetter());
        t4t5.writeTable("t4joint5.tbl");
    }

    @Test
    public void joinTest2(){
        TableReader t4 = new TableReader();
        t4.read("fans.tbl");
        TableReader t5 = new TableReader();
        t5.read("teams.tbl");
        Table t4t5 = t4.TableGetter().join(t5.TableGetter());
        t4t5.writeTable("fansjonteams.tbl");
    }
}
