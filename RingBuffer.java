/**
 * This RingBuffer object . . .
 * 
 * @author  
 * @version 
 */
public class RingBuffer 
{
   ArrayQueue<Double> buffer = new ArrayQueue<>();
   int capacity;


    public RingBuffer(int capacity)
    {
        this.capacity = capacity;
    }

    public int size()
    {
        return buffer.size();
    }
    
    public boolean isEmpty()
    {
        return buffer.size() == 0;
    }
    
    public boolean isFull()
    {
        return buffer.size() == capacity;
    }
    
    public void add(double value)
    {
        if(isFull()){
            throw new QueueOverflowException("Queue is full");
        }
        buffer.add(value);
    }
    
    public double peek()
    {
        return buffer.peek();
    }
    
    public double remove()
    {
        return buffer.remove();
    }
    
    // a simple test of the constructor and methods in RingBuffer
    public static void main(String[] args) 
    {
        int capacity = 100;
        RingBuffer buffer = new RingBuffer(capacity);  
        for (int i = 1; i <= capacity; i++) 
            buffer.add(i);
      
        double t = buffer.remove();
        buffer.add(t);
        System.out.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) 
        {
            double x = buffer.remove();
            double y = buffer.remove();
            buffer.add(x + y);
        }
        System.out.println(buffer.peek());
    }

}
