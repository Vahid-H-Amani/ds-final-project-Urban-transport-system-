package dataStructure;

@SuppressWarnings("unchecked")
public class MinHeap<T> {

    private HeapNode<T>[] heap;
    private int size;

    public MinHeap() {
        heap = (HeapNode<T>[]) new HeapNode[10];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return heap.length;
    }

    public void clear() {

        for (int i = 0; i < size; i++) {
            heap[i] = null;
        }

        size = 0;
    }

    public HeapNode<T> peek() {

        if (isEmpty()) {
            return null;
        }

        return heap[0];
    }

    public void print() {

        if (isEmpty()) {
            System.out.println("Heap is empty.");
            return;
        }

        for (int i = 0; i < size; i++) {
            System.out.println(heap[i]);
        }
    }

    private void resize() {

        HeapNode<T>[] newHeap =
                (HeapNode<T>[]) new HeapNode[heap.length * 2];

        for (int i = 0; i < size; i++) {
            newHeap[i] = heap[i];
        }

        heap = newHeap;
    }

    private void swap(int first, int second) {

        HeapNode<T> temp = heap[first];
        heap[first] = heap[second];
        heap[second] = temp;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return (2 * index) + 1;
    }

    private int rightChild(int index) {
        return (2 * index) + 2;
    }

    private boolean hasParent(int index) {
        return index > 0;
    }

    private boolean hasLeftChild(int index) {
        return leftChild(index) < size;
    }

    private boolean hasRightChild(int index) {
        return rightChild(index) < size;
    }







    public boolean insert(T data, double priority) {

        if (size == heap.length) {
            resize();
        }

        heap[size] = new HeapNode<>(data, priority);

        heapifyUp(size);

        size++;

        return true;
    }




    
    
    
    
    

    public HeapNode<T> removeMin() {

        if (isEmpty()) {
            return null;
        }

        HeapNode<T> min = heap[0];

        heap[0] = heap[size - 1];

        heap[size - 1] = null;

        size--;

        if (!isEmpty()) {
            heapifyDown(0);
        }

        return min;
    }







    private void heapifyUp(int index) {

        while (hasParent(index)) {

            int parentIndex = parent(index);

            if (heap[parentIndex].compareTo(heap[index]) <= 0) {
               break;
            }

            swap(parentIndex, index);

            index = parentIndex;
        }
    }



    private void heapifyDown(int index) {

        while (hasLeftChild(index)) {

            int smallerChild = leftChild(index);

            if (hasRightChild(index)
                && heap[rightChild(index)].compareTo(heap[leftChild(index)]) < 0) {

                smallerChild = rightChild(index);
            }

            if (heap[index].compareTo(heap[smallerChild]) <= 0) {
                break;
            }

            swap(index, smallerChild);

            index = smallerChild;
        }
    }




    

}