public class LinkedListDeque<item> implements Deque<item> {
    public class Link{
        item head;
        Link previous;
        Link after;

        public Link(){
            this.head = null;
            previous = null;
            after =null;
        }
        public Link(item x){
            this.head = x;
            previous = null;
            after = null;
        }
    }
    private Link sentinel;
    private int size;
    public LinkedListDeque(){
        size =0;
        sentinel = new Link ();
        sentinel.previous = sentinel;
        sentinel.after = sentinel;
    }
    @Override
    public void addLast(item x){
        Link new_link= new Link(x);
        new_link.previous = sentinel.previous;
        new_link.after = sentinel;
        sentinel.previous.after = new_link;
        sentinel.previous = new_link;
        size++;
    }
    @Override
    public void addFirst(item x){
        Link new_link = new Link(x);
        new_link.after = sentinel.after;
        sentinel.after.previous = new_link;
        sentinel.after = new_link;
        new_link.previous= sentinel;
        size++;
    }
    @Override
    public boolean isEmpty(){
        return size()==0;
    }
    @Override
    public int size(){
        return size;
    }
    @Override
    public void printDeque(){
        Link node = sentinel.after;
        for (int i=0; i< size; i++){
            System.out.print(node.head+" ");
            node = node.after;
        }

    }
    @Override
    public item removeFirst(){
        item temp;
        temp = sentinel.after.head;
        sentinel.after=sentinel.after.after;
        sentinel.after.previous = sentinel;
        size--;
        return temp;
    }
    @Override
    public item removeLast(){
        item temp;
        temp = sentinel.previous.head;
        sentinel.previous.previous.after = sentinel;
        sentinel.previous = sentinel.previous.previous;
        size--;
        return temp;
    }
    @Override
    public item get(int index){
        if (index >size()-1){
            return null;
        }
        Link curr = sentinel.after;
        while (index >0){
            curr = curr.after;
            index--;
        }
        return curr.head;
    }
    public item getRecursive(int index){
        return getRecursive (index+1, sentinel);
    }
    public item getRecursive(int index, Link curr){
        if (index==0){
            return curr.head;
        }
        else{
            index--;
            curr = curr.after;
            return getRecursive( index,  curr);
        }
    }

    public static void main(String[] args) {
        LinkedListDeque <String> test = new LinkedListDeque<>();
        test.addFirst("lucas");
        test.addFirst("Josh");
        test.addFirst("Chao");
        test.addFirst("June");
        System.out.println(test.getRecursive(0));
        System.out.println(test.getRecursive(2));

    }


}
