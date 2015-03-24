public class RingBuffer {
    private double[] rb;          // items in the buffer
    private int first;            // index for the next dequeue or peek
    private int last;             // index for the next enqueue
    private int size;             // number of items in the buffer

    // create an empty ring buffer, with given max capacity
    public RingBuffer(int capacity) {
        rb = new double[capacity];
        size = 0;
        first = 0;
        last = 0;      
    }

    // return number of items currently in the buffer
    public int size() {
       return size;
    }

    // is the buffer empty (size equals zero)?
    public boolean isEmpty() {
       if (size == 0) return true;
       else return false;
    }

    // is the buffer full (size equals array capacity)?
    public boolean isFull() {
       if (size == rb.length) return true;
       else return false;
    }

    
    // add item x to the end
    public void enqueue(double x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        else{
            if (size == 0 ) 
                rb[last % rb.length] = x;
            else{
                rb[(last+1) % rb.length] = x;
                last++;
            }
            size ++;
        }
    }

    
    /*public void toPrint(){
        for( int i=0; i < rb.length; i++){
           System.out.print(rb[i] + " ");
           
        }  
        System.out.println();
        System.out.println(first % rb.length + "");
        System.out.println(last % rb.length + "");
        
    } */   
        
    // delete and return item from the front
    public double dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        else{
            double temp = rb[first];
            size --;
            
            //rb[first] = rb[first % rb.length   
            if (rb.length-1 == first) first = 0;
            else first ++; 
            
            return temp;
        }
    } // end of deque
    

    // return (but do not delete) item from the front
    public double peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        else return rb[last % rb.length];
    }

    // a simple test of the constructor and methods in RingBuffer
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        RingBuffer buffer = new RingBuffer(N);
        for (int i = 1; i <= N; i++) {
            buffer.enqueue(i);
            // buffer.toPrint();
        }
        double t = buffer.dequeue();
        // buffer.toPrint();
        buffer.enqueue(t);
        // buffer.toPrint();
        System.out.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) {
            double x = buffer.dequeue();
            double y = buffer.dequeue();
            buffer.enqueue(x + y);
            // buffer.toPrint();
        }
        System.out.println(buffer.peek());
    }

}