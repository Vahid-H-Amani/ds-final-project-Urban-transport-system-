package dataStructure;

import graph.Station;

public class RedBlackTree<T extends Comparable<T>> {

    private final RedBlackNode<T> NIL;

    private RedBlackNode<T> root;

    private int size;



    public RedBlackTree() {

        NIL = new RedBlackNode<>(null);

        NIL.setBlack();

        NIL.setLeft(NIL);
        NIL.setRight(NIL);
        NIL.setParent(NIL);

        root = NIL;

        size = 0;
    }



    public RedBlackNode<T> getRoot() {

        if (root == NIL) return null;
        
        return root;    
    
    }



    public boolean isEmpty() {
        return root == NIL;
    }



    public int getSize() {
        return size;
    }



    public void clear() {

        root = NIL;

        size = 0;

    }



    private RedBlackNode<T> searchNode(T data) {

        RedBlackNode<T> current = root;

        while (current != NIL) {

            int compare = data.compareTo(current.getData());

            if (compare == 0) {
                return current;
            }

            if (compare < 0) {
                current = current.getLeft();
            }
            else {
                current = current.getRight();
            }

        }

        return NIL;

    }



    public boolean contains(T data) {

        return searchNode(data) != NIL;

    }



    public T get(T data) {

        RedBlackNode<T> node = searchNode(data);

        if (node == NIL) {
            return null;
        }

        return node.getData();

    }



    private RedBlackNode<T> minimum(RedBlackNode<T> node) {

        if (node == NIL) {
            return NIL;
        }

        while (node.getLeft() != NIL) {
            node = node.getLeft();
        }

        return node;

    }
    

    private void leftRotate(RedBlackNode<T> x) {

        RedBlackNode<T> y = x.getRight();

        x.setRight(y.getLeft());

        if (y.getLeft() != NIL) {
            y.getLeft().setParent(x);
        }

        y.setParent(x.getParent());

        if (x.getParent() == NIL) {

            root = y;

        }
        else if (x == x.getParent().getLeft()) {

            x.getParent().setLeft(y);

        }
        else {

            x.getParent().setRight(y);

        }

        y.setLeft(x);

        x.setParent(y);

    }


    private void rightRotate(RedBlackNode<T> y) {

        RedBlackNode<T> x = y.getLeft();

        y.setLeft(x.getRight());

        if (x.getRight() != NIL) {
            x.getRight().setParent(y);
        }

        x.setParent(y.getParent());

        if (y.getParent() == NIL) {

            root = x;

        }
        else if (y == y.getParent().getLeft()) {

            y.getParent().setLeft(x);

        }
        else {

            y.getParent().setRight(x);

        }

        x.setRight(y);

        y.setParent(x);

    }









//insert

    public boolean insert(T data) {

        RedBlackNode<T> node = new RedBlackNode<>(data);

        node.setLeft(NIL);
        node.setRight(NIL);
        node.setParent(NIL);

        RedBlackNode<T> current = root;
        RedBlackNode<T> parent = NIL;

        while (current != NIL) {

            parent = current;

            int compare = data.compareTo(current.getData());

            if (compare == 0) {
                return false;
            }

            if (compare < 0) {

                current = current.getLeft();

            } else {

                current = current.getRight();

            }

        }

        node.setParent(parent);

        if (parent == NIL) {

            root = node;

        }
        else if (data.compareTo(parent.getData()) < 0) {

            parent.setLeft(node);

        }
        else {

            parent.setRight(node);

        }

        size++;

        insertFix(node);

        return true;

    }


    private void insertFix(RedBlackNode<T> node) {

        while (node.getParent().isRed()) {

            RedBlackNode<T> parent = node.getParent();
            RedBlackNode<T> grandParent = parent.getParent();

            if (parent == grandParent.getLeft()) {

                RedBlackNode<T> uncle = grandParent.getRight();

                // Case 1
                if (uncle.isRed()) {

                    parent.setBlack();
                    uncle.setBlack();
                    grandParent.setRed();

                    node = grandParent;

                }
                else {

                    // Case 2
                    if (node == parent.getRight()) {

                        node = parent;

                        leftRotate(node);

                        parent = node.getParent();
                        grandParent = parent.getParent();

                    }

                    // Case 3
                    parent.setBlack();
                    grandParent.setRed();

                    rightRotate(grandParent);

                }

            }
            else {

                RedBlackNode<T> uncle = grandParent.getLeft();

                // Case 1
                if (uncle.isRed()) {

                    parent.setBlack();
                    uncle.setBlack();
                    grandParent.setRed();

                    node = grandParent;

                }
                else {

                    // Case 2
                    if (node == parent.getLeft()) {

                        node = parent;

                        rightRotate(node);

                        parent = node.getParent();
                        grandParent = parent.getParent();

                    }

                    // Case 3
                    parent.setBlack();
                    grandParent.setRed();

                    leftRotate(grandParent);

                }

            }

        }

        root.setBlack();

    }




//delete

    private void transplant(RedBlackNode<T> u,
                            RedBlackNode<T> v) {

        if (u.getParent() == NIL) {

            root = v;

        }
        else if (u == u.getParent().getLeft()) {

            u.getParent().setLeft(v);

        }
        else {

            u.getParent().setRight(v);

        }

        v.setParent(u.getParent());

    }



    public boolean delete(T data) {

        RedBlackNode<T> z = searchNode(data);

        if (z == NIL) {
            return false;
        }

        RedBlackNode<T> y = z;

        boolean originalColor = y.isBlack();

        RedBlackNode<T> x;

        if (z.getLeft() == NIL) {

            x = z.getRight();

            transplant(z, z.getRight());

        }
        else if (z.getRight() == NIL) {

            x = z.getLeft();

            transplant(z, z.getLeft());

        }
        else {

            y = minimum(z.getRight());

            originalColor = y.isBlack();

            x = y.getRight();

            if (y.getParent() == z) {

                x.setParent(y);

            }
            else {

                transplant(y, y.getRight());

                y.setRight(z.getRight());

                y.getRight().setParent(y);

            }

            transplant(z, y);

            y.setLeft(z.getLeft());

            y.getLeft().setParent(y);

            y.setColor(z.isRed());

        }

        if (originalColor) {

            deleteFix(x);

        }

        size--;

        return true;

    }

        
    private void deleteFix(RedBlackNode<T> x) {

        while (x != root && x.isBlack()) {

            if (x == x.getParent().getLeft()) {

                RedBlackNode<T> w = x.getParent().getRight();

                // Case 1
                if (w.isRed()) {

                    w.setBlack();

                    x.getParent().setRed();

                    leftRotate(x.getParent());

                    w = x.getParent().getRight();

                }

                // Case 2
                if (w.getLeft().isBlack() &&
                    w.getRight().isBlack()) {

                    w.setRed();

                    x = x.getParent();

                }
                else {

                    // Case 3
                    if (w.getRight().isBlack()) {

                        w.getLeft().setBlack();

                        w.setRed();

                        rightRotate(w);

                        w = x.getParent().getRight();

                    }

                    // Case 4
                    w.setColor(x.getParent().isRed());

                    x.getParent().setBlack();

                    w.getRight().setBlack();

                    leftRotate(x.getParent());

                    x = root;

                }

            }
            else {

                RedBlackNode<T> w = x.getParent().getLeft();

                // Case 1
                if (w.isRed()) {

                    w.setBlack();

                    x.getParent().setRed();

                    rightRotate(x.getParent());

                    w = x.getParent().getLeft();

                }

                // Case 2
                if (w.getRight().isBlack() &&
                    w.getLeft().isBlack()) {

                    w.setRed();

                    x = x.getParent();

                }
                else {

                    // Case 3
                    if (w.getLeft().isBlack()) {

                        w.getRight().setBlack();

                        w.setRed();

                        leftRotate(w);

                        w = x.getParent().getLeft();

                    }

                    // Case 4
                    
                    w.setColor(x.getParent().isRed());

                    x.getParent().setBlack();

                    w.getLeft().setBlack();

                    rightRotate(x.getParent());

                    x = root;

                }

            }

        }

        x.setBlack();

    }





//print

    public void printInOrder() {

        if (root == NIL) {

            System.out.println("Tree is empty.");

            return;

        }

        printInOrder(root);

        System.out.println();

    }

    private void printInOrder(RedBlackNode<T> node) {

        if (node == NIL) {
            return;
        }

        printInOrder(node.getLeft());

        System.out.print(node + " ");

        printInOrder(node.getRight());

    }





    public void printTree() {

        if (root == NIL) {

            System.out.println("Tree is empty.");

            return;

        }

        printTree(root, "", true);

    }

    private void printTree(RedBlackNode<T> node,
                        String prefix,
                        boolean isTail) {

        if (node == NIL) {
            return;
        }

        System.out.println(
                prefix +
                (isTail ? "└── " : "├── ") +
                node
        );

        if (node.getLeft() != NIL || node.getRight() != NIL) {

            printTree(
                    node.getLeft(),
                    prefix + (isTail ? "    " : "│   "),
                    false
            );

            printTree(
                    node.getRight(),
                    prefix + (isTail ? "    " : "│   "),
                    true
            );

        }

    }



//search

    public LinkedList<T> searchPrefix(String prefix) {

        LinkedList<T> result = new LinkedList<>();

        searchPrefix(root, prefix, result);

        return result;

    }


    private void searchPrefix(RedBlackNode<T> node,
                            String prefix,
                            LinkedList<T> result) {

        if (node == NIL) {
            return;
        }

        String temp = prefix.toUpperCase();

        Station station = (Station) node.getData();

        String key = station.getName().toUpperCase();

        if (key.startsWith(temp)) {

            searchPrefix(node.getLeft(), temp, result);

            result.addLast(node.getData());

            searchPrefix(node.getRight(), temp, result);

        }

        else if (key.compareTo(temp) == 0){

            searchPrefix(node.getLeft() , temp , result );
            
            result.addLast(node.getData());

        }

        else if (key.compareTo(temp) < 0) {

            searchPrefix(node.getRight(), temp , result);

        }

        else {

            searchPrefix(node.getLeft(), temp , result);

        }

    }











//print

    public void printPrefixSearch(String prefix) {

        LinkedList<T> result = searchPrefix(prefix);

        if (result.isEmpty()) {

            System.out.println("No Station Found.");

            return;

        }

        for (int i = 0; i < result.getSize(); i++) {

            Station station = (Station) result.get(i);

            System.out.println(station.getName());

        }

    }


}