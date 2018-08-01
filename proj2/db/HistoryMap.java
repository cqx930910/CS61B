package db;

import java.util.HashMap;
import java.util.Stack;

public class HistoryMap<K, V> extends HashMap<K, V> {
    Stack<Operation> history= new Stack<>();
    public class Operation{
        boolean toDel;
        K key;
        V value;
        public Operation(K key, V value,boolean toDel ){
            this.key = key;
            this.value = value;
            this.toDel= toDel;
        }
    }
    @Override
    public V put(K key, V value) {
        super.put(key, value);
        if (super.containsKey(key)){
            Operation put = new Operation(key,super.get(key), super.containsKey(key));
            history.push(put);
        }
        else {
            Operation put = new Operation(key, value, super.containsKey(key));
            history.push(put);
        }
        return value;
    }

    @Override
    public V remove(Object key) {
        V toreturn = super.get(key);
        super.remove(key);
        Operation remove = new Operation((K) key,toreturn, false);
        history.push(remove);
        return toreturn;
    }

    public void undo() {
        if (history.empty()){
            return;
        }
        Operation last = history.pop();
        if(last.toDel==false){
            this.put(last.key,last.value);
        }
        else{
            this.remove(last.key);
        }

    }
    public static void main(String[] args) {
        HistoryMap<String, Integer> h = new HistoryMap<>();
        h.put("party", 1);
        h.put("parrot", 2);
        h.put("conga", 4);
        h.put("parrot", 3);
        h.undo();
        h.undo();
        System.out.println(h); // Output: {parrot=2, party=1}
        h.remove("party");
        h.undo();
        System.out.println(h); // Output: {parrot=2, party=1}
    }
}
