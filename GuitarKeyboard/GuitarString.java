
/*************************************************************************
 * Name(s)      : 
 * NetID(s)     : 
 * Precept(s)   :
 *
 * Dependencies :
 * Description  : 
 *  
 *  This is a template file for GuitarString.java. It lists the constructors
 *  and methods you need, along with descriptions of what they're supposed
 *  to do.
 *  
 *  Note: it won't compile until you fill in the constructors and methods
 *        (or at least commment out the ones whose return type is non-void).
 *
 *****************************************************************************/

public class GuitarString {

    private RingBuffer buffer; // ring buffer
    private int time;
    private int N;
    

    // create guitar string of given frequency, using sampling rate of 44,100
    public GuitarString(double frequency) {
        N = 44100 / (int)Math.round(frequency);
        // System.out.println(N);
        buffer = new RingBuffer(N);
        while(!buffer.isFull()) {
            buffer.enqueue(0.0);
            // System.out.println(buffer.peek());
        }
        time = 0;
    } // end of first constructor

    
    // create a guitar string with size & initial values given by the array
    public GuitarString(double[] init) {
        N = init.length;
        buffer = new RingBuffer(N);
        for(int i=0; i < N; i++){
            buffer.enqueue(init[i]);
        }
        time = 0;
    } // end of second constructor

    
    // pluck the guitar string by replacing the buffer with white noise
    public void pluck() {
        for(int i=0; i < N; i++){
            buffer.dequeue();
            buffer.enqueue(Math.random() - .49);
        }
    }

    // advance the simulation one time step
    public void tic() {
        double average = (buffer.dequeue() + buffer.peek()) / 2;
        double decay = average * .996;
        buffer.enqueue(decay);
        time ++;
        
    }

    // return the current sample
    public double sample() {
        return buffer.peek();
    }

    // return number of times tic was called
    public int time() {
        return time;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };  
        GuitarString testString = new GuitarString(samples);
        for (int i = 0; i < N; i++) {
            int t = testString.time();
            double sample = testString.sample();
            System.out.printf("%6d %8.4f\n", t, sample);
            testString.tic();
        }
    }

}