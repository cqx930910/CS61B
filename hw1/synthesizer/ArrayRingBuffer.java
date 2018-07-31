package synthesizer;
import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;
    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        first =0;
        last =0;
        this.fillCount=0;
        this.capacity = capacity;
        this.rb = (T[])new Object[capacity];
    }
        public class subIterator implements Iterator<T>{
            private int index =0;
            public  subIterator(){
                index =first;
            }
            public boolean hasNext(){
                return index < last;
            }
            public T next(){
                T returnValue = rb[index];
                index++;
                return returnValue;
            }
        }
    public Iterator<T> iterator(){
        return new subIterator();
    }
    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (isFull()){
            throw new IllegalArgumentException("Ring Buffer Overflow");
        }
        rb[last] = x;
        last++;
        fillCount++;
        if (last == capacity()){
            last =0;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if (isEmpty()){
            throw new IllegalArgumentException( "Ring Buffer Underflow" );
        }
        T returnvalue = rb[first];
        first ++;
        fillCount--;
        if (first == capacity()){
            first =0;
        }
        return returnvalue;
    }

    /**
     * Return oldest item, but don't remove it.
     */

    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        if (fillCount ==0){
            throw new IllegalArgumentException("The list empty");
        }
        return rb[first];
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
    public static void main(String[] args) {
        ArrayRingBuffer <Integer> test= new ArrayRingBuffer(5);
        test.enqueue(1);
        test.enqueue(2);

    }

    }