package dataStructure;


public class DisjointSetNode<T> {

    private T data;
    private DisjointSetNode<T> parent;
    private int rank;

    public DisjointSetNode(T data) {

        this.data = data;
        this.parent = this;
        this.rank = 0;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public DisjointSetNode<T> getParent() {
        return parent;
    }

    public void setParent(DisjointSetNode<T> parent) {
        this.parent = parent;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void increaseRank() {
        rank++;
    }

    @Override
    public String toString() {
        return data.toString();
    }
    
}