package dataStructure;


public class Queue<T> {

    private LinkedList<T> list;


    public Queue() {
        list = new LinkedList<>();
    }


    
    public void enqueue(T data) {
        list.addLast(data);
    }

    public T dequeue() {
        return list.removeFirst();
    }

    public T peek() {
        return list.get(0);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.getSize();
    }

    public void clear() {
        list.clear();
    }

}