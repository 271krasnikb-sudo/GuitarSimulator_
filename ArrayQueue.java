@SuppressWarnings("unchecked")
public class ArrayQueue<T> implements QueueInterface<T> {

    private T[] elements = (T[]) new Object[4];
    private int size;
    private int head;
    private int tail;

    public int size() {
        return size;
    }

    public boolean empty() {
        return size == 0;
    }

    public T add(T item) {
        if (size == elements.length) {
            T[] newElements = (T[]) new Object[elements.length * 2];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[(head + i) % elements.length];
            }
            elements = newElements;
            head = 0;
            tail = size;
        }

        elements[tail] = item;
        tail = (tail + 1) % elements.length;
        size++;
        return item;
    }

    public T peek() throws QueueUnderflowException {
        if (empty()) {
            throw new QueueUnderflowException("Queue is empty");
        }
        return elements[head];
    }

    public T remove() throws QueueUnderflowException {
        if (empty()) {
            throw new QueueUnderflowException("Queue is empty");
        }

        T item = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;

        if (elements.length > 4 && size <= elements.length / 4) {
           T[] newElements = (T[]) new Object[elements.length / 2];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[(head + i) % elements.length];
            }
            elements = newElements;
            head = 0;
            tail = size;
        }
        return item;
    }

    public void clear() {
        elements = (T[]) new Object[4];
        size = 0;
        head = 0;
        tail = 0;
    }

    public boolean isFull() {
        return size == elements.length;
    }

    
}