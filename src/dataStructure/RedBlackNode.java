package dataStructure;

public class RedBlackNode<T extends Comparable<T>> {

    private T data;

    private RedBlackNode<T> left;
    private RedBlackNode<T> right;
    private RedBlackNode<T> parent;

    // true = RED
    // false = BLACK
    private boolean isRed;

    
    public RedBlackNode(T data) {

        this.data = data;

        this.isRed = true;

    }



    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public RedBlackNode<T> getLeft() {
        return left;
    }

    public void setLeft(RedBlackNode<T> left) {
        this.left = left;
    }

    public RedBlackNode<T> getRight() {
        return right;
    }

    public void setRight(RedBlackNode<T> right) {
        this.right = right;
    }

    public RedBlackNode<T> getParent() {
        return parent;
    }

    public void setParent(RedBlackNode<T> parent) {
        this.parent = parent;
    }

    public boolean isRed() {
        return isRed;
    }

    public boolean isBlack() {
        return !isRed;
    }

    public void setRed() {
        this.isRed = true;
    }

    public void setBlack() {
        this.isRed = false;
    }

    public void setColor(boolean isRed) {
        this.isRed = isRed;
    }

    @Override
    public String toString() {

        return data + (isRed ? "(R)" : "(B)");

    }
}