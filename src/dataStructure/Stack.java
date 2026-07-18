package dataStructure;

public class Stack<T> {

    private LinkedList<T> list;

    public Stack() {

        list = new LinkedList<>();

    }

    public void push(T data) {

        list.addFirst(data);

    }

    public T pop() {

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