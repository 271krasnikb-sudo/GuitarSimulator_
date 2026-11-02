/**
 * This GuitarString object . . .
 * 
 * @author  
 * @version 
 */
public class GuitarString 
{
    private RingBuffer guitar;
   private int totalTic;
    public GuitarString(double frequency) 
    {
        guitar = new RingBuffer((int)(44100/frequency));
        for(int i = 0; i<guitar.size(); i++){
            guitar.add(0);
        }
        totalTic = 0;
    }

    public GuitarString(double[] array) 
    {
        guitar = new RingBuffer(array.length);
        for(int i = 0; i<guitar.size(); i++){
            guitar.add(array[i]);
        }
        totalTic = 0;

    }

    public void pluck() 
    {
        int temp = guitar.size();
        int capacity = 0;
        for(int i = 0; i < temp; i++){
            guitar.remove();
            capacity++;
        }

        for(int i = 0; i < capacity; i++){
            guitar.add(Math.random()-.5);
        }
    }

    // advance the simulation one time step
    public void tic() 
    {
        double value1 = guitar.remove();
        double value2 = guitar.peek();
        double average = (value1+value2)/2;
        guitar.add(average*.994);
    }

    // return the current sample
    public double sample() 
    {
        return guitar.peek();
    }

    // return number of times tic was called
    public int time() 
    {
        return totalTic;
    }

    public static void main(String[] args) 
    {
        double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };  
        GuitarString testString = new GuitarString(samples);
        for (int i = 0; i < 25; i++) 
        {
            int t = testString.time();
            double sample = testString.sample();
            System.out.printf("%6d %8.4f\n", t, sample);
            testString.tic();
        }
    }
}
