package dataStructure;

public class HeapNode<T> implements Comparable<HeapNode<T>> {

    private T data;
    private double priority;

    public HeapNode(T data, double priority) {
        this.data = data;
        this.priority = priority;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return data + " (" + priority + ")";
    }

    @Override    
    public int compareTo(HeapNode<T> other) {
        return Double.compare(this.priority, other.priority);
    }
}