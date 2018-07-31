public class ArrayDeque<Item> implements Deque<Item> {
    private Item[]  items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int capacity = 4;
    public ArrayDeque(){
        items = (Item[]) new Object[capacity];
        nextFirst = capacity/2;
        nextLast = nextFirst+1;
        size = 0;
    }
    @Override
    public void addFirst(Item x){
        if (check_full()){
            resize(capacity*2);
        }
        items[nextFirst]= x;
        nextFirst =minusOne(nextFirst);
        size++;
    }
    public void addLast(Item x){
        if (check_full()){
            resize(capacity*2);
        }
        items[nextLast]= x;
        nextLast=plusOne(nextLast);
        size++;
    }
    private void resize(int new_capacity) {
        System.out.printf("Resizing from %5d to %5d\n", items.length, new_capacity);
        Item[] a = (Item[]) new Object[new_capacity];
        for (int i=1; i <= Math.min(capacity,new_capacity); i++){
            a[i-1]= items[minusOne(nextFirst+i)];
        }
        items =a;
        nextFirst= 0;
        nextLast = size+1;
        capacity= new_capacity;

    }
    private boolean check_full(){
        return (size == capacity-2);
    }



    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        for (int i= 0;i<size; i++){
            System.out.print (items[plusOne(nextFirst+i)]+" ");
        }
    }
    public Item removeFirst(){
        Item toRemove = items[plusOne(nextFirst)];
        items[plusOne(nextFirst)] = null;
        nextFirst = plusOne(nextFirst);
        size--;
        if (isSparse()){
            resize(capacity/2);
        }
        return toRemove;
    }
    public Item removeLast(){
        Item toRemove = items[minusOne(nextLast)];
        items[minusOne(nextLast)]= null;
        nextLast =minusOne(nextLast);
        size--;
        if (isSparse()){
            resize(capacity/2);
        }
        return toRemove;
    }
    public Item get(int index){
        if (index>size){
            return null;
        }
        return items[plusOne(index+nextFirst)];
    }

    public int minusOne(int index){
        return Math.floorMod(index-1,capacity);
    }
    private int plusOne(int index){
        return Math.floorMod(index+1, items.length);
    }
    private boolean isSparse(){
        return size()<items.length/4 &&items.length>16;
    }
    public int capacity() {
        return items.length;
    }
    public static void main(String[] args) {
        ArrayDeque<String > test = new <String>ArrayDeque();
        test.addFirst("1Lucas");
        test.addLast("2June");
        test.addFirst("3Josh");
        test.addLast("4Seth");
        test.addFirst("5Chao");
        test.addLast("6Cindy");
        test.printDeque();
        test.removeLast();
        test.removeLast();
        test.removeLast();


    }
}
