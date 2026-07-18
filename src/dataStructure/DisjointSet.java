package dataStructure;

public class DisjointSet<T> {

    private HashTable<T, DisjointSetNode<T>> nodes;

    public DisjointSet() {
        nodes = new HashTable<>(100);
    }

    public void makeSet(T data) {

        if (nodes.containsKey(data)) {
            return;
        }

        DisjointSetNode<T> node = new DisjointSetNode<>(data);

        nodes.put(data, node);
    }

    public DisjointSetNode<T> findNode(T data) {

        DisjointSetNode<T> node = nodes.get(data);

        if (node == null) {
            return null;
        }

        if (node.getParent() != node) {
            node.setParent(findNode(node.getParent().getData()));
        }

        return node.getParent();
    }

    public T find(T data) {

        DisjointSetNode<T> root = findNode(data);

        if (root == null) {
            return null;
        }

        return root.getData();
    }

    public void union(T data1, T data2) {

        DisjointSetNode<T> root1 = findNode(data1);
        DisjointSetNode<T> root2 = findNode(data2);

        if (root1 == null || root2 == null) {
            return;
        }

        if (root1 == root2) {
            return;
        }

        if (root1.getRank() < root2.getRank()) {

            root1.setParent(root2);

        } else if (root1.getRank() > root2.getRank()) {

            root2.setParent(root1);

        } else {

            root2.setParent(root1);
            root1.increaseRank();
        }
    }

    public boolean isConnected(T data1, T data2) {

        DisjointSetNode<T> root1 = findNode(data1);
        DisjointSetNode<T> root2 = findNode(data2);

        if (root1 == null || root2 == null) {
            return false;
        }

        return root1 == root2;
    }

    public boolean contains(T data) {
        return nodes.containsKey(data);
    }

}