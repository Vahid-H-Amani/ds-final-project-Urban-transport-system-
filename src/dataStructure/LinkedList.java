package dataStructure;

public class LinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);

        if (this.isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }

        size++;
    }

    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);

        if (this.isEmpty()) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }

        size++;
    }

    public T removeFirst() {
        if (isEmpty())
            return null;

        T data = head.getData();
        head = head.getNext();

        if (head == null)
            tail = null;

        size--;

        return data;
    }

    public T removeLast() {
        if (isEmpty())
            return null;

        if (head == tail) {
            T data = head.getData();
            head = tail = null;
            size--;
            return data;
        }

        Node<T> current = head;

        while (current.getNext() != tail) {
            current = current.getNext();
        }

        T data = tail.getData();
        tail = current;
        tail.setNext(null);

        size--;

        return data;
    }

    public boolean contains(T data) {
        Node<T> current = head;

        while (current != null) {
            if (current.getData().equals(data))
                return true;

            current = current.getNext();
        }

        return false;
    }

    public T get(int index) {

        if (index < 0 || index >= size)
            return null;

        Node<T> current = head;

        for (int i = 0; i < index; i++)
            current = current.getNext();

        return current.getData();
    }

    public boolean remove(T data) {

        if (isEmpty())
            return false;

        if (head.getData().equals(data)) {
            removeFirst();
            return true;
        }

        Node<T> current = head;

        while (current.getNext() != null) {

            if (current.getNext().getData().equals(data)) {

                if (current.getNext() == tail){
                    tail = current;
                }
                current.setNext(current.getNext().getNext());

                size--;

                return true;
            }

            current = current.getNext();
        }

        return false;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void print() {

        Node<T> current = head;

        while (current != null) {

            System.out.print(current.getData());

            if (current.getNext() != null)
                System.out.print(" -> ");

            current = current.getNext();
        }

        System.out.println();
    }
}