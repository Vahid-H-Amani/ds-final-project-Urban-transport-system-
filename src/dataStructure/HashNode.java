package dataStructure;

import java.util.Objects;

public class HashNode<K, V> {

    private K key;
    private V value;

    public HashNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        HashNode<?, ?> other = (HashNode<?, ?>) obj;

        return Objects.equals(key, other.key);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(key);
    }
    
}