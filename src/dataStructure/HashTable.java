package dataStructure;

@SuppressWarnings("unchecked")
public class HashTable<K, V> {

    private LinkedList<HashNode<K, V>>[] table;
    private int capacity;
    private int size;

    public HashTable(int capacity) {

        this.capacity = capacity;
        this.size = 0;

        table = new LinkedList[capacity];

        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void put(K key, V value) {

        int index = hash(key);

        LinkedList<HashNode<K, V>> bucket = table[index];

        for (int i = 0; i < bucket.getSize(); i++) {

            HashNode<K, V> node = bucket.get(i);

            if (node.getKey().equals(key)) {
                node.setValue(value);
                return;
            }
        }

        bucket.addLast(new HashNode<>(key, value));
        size++;
    }

    public V get(K key) {

        int index = hash(key);

        LinkedList<HashNode<K, V>> bucket = table[index];

        for (int i = 0; i < bucket.getSize(); i++) {

            HashNode<K, V> node = bucket.get(i);

            if (node.getKey().equals(key)) {
                return node.getValue();
            }
        }

        return null;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public boolean remove(K key) {

        int index = hash(key);

        LinkedList<HashNode<K, V>> bucket = table[index];

        for (int i = 0; i < bucket.getSize(); i++) {

            HashNode<K, V> node = bucket.get(i);

            if (node.getKey().equals(key)) {

                bucket.remove(node);
                size--;

                return true;
            }
        }

        return false;
    }

    public void clear() {

        for (int i = 0; i < capacity; i++) {
            table[i].clear();
        }

        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }
}