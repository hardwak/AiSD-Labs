package lab9;

import java.util.Comparator;

public class BSTSet<T> implements ISet<T> {

    private class Node {
        private T key;
        private Node left, right;

        public Node(T item) {
            key = item;
            left = right = null;
        }
    }

    private Node root;

    private Comparator<T> comparator;

    public BSTSet(Comparator<T> comparator) {
        root = null;
        this.comparator = comparator;
    }

    public void showOrder() {
        showOrderRec(root);
        System.out.println();
    }

    //preorder
    private void showOrderRec(Node root) {
        if (root != null) {
            System.out.print(root.key + ", ");
            showOrderRec(root.left);
            showOrderRec(root.right);
        }
    }


    public T search(T key) {
        Node toReturn = searchRec(root, key);
        return toReturn == null ? null : toReturn.key;
    }

    @Override
    public boolean contains(T key) {
        Node toReturn = searchRec(root, key);
        return toReturn != null;
    }

    private Node searchRec(Node root, T key) {
        Node searchingElement = null;

        if (root != null) {
            if (comparator.compare(root.key, key) > 0)
                searchingElement = searchRec(root.left, key);
            if (comparator.compare(root.key, key) < 0)
                searchingElement = searchRec(root.right, key);
            if (comparator.compare(root.key, key) == 0)
                searchingElement = root;
        }

        return searchingElement;
    }

    public void insert(T key) {
        insertKey(root, key);
    }

    private void insertKey(Node root, T key) {
        if (root == null) {
            this.root = new Node(key);
        } else {
            boolean inserted = false;
            while (!inserted) {

                if (comparator.compare(key, root.key) < 0) {
                    if (root.left == null) {
                        root.left = new Node(key);
                        inserted = true;
                    }
                    root = root.left;
                }

                if (comparator.compare(key, root.key) > 0) {
                    if (root.right == null) {
                        root.right = new Node(key);
                        inserted = true;
                    }
                    root = root.right;
                }

                if (comparator.compare(key, root.key) == 0)
                    inserted = true;
            }
        }
    }
    private boolean isDeleted;

    public boolean remove(T key){
        isDeleted = false;
        root = deleteRec(root, key);
        return isDeleted;
    }

    private Node deleteRec(Node root, T key) {
        if (root == null)
            return null;


        if (comparator.compare(key, root.key) < 0)
            root.left = deleteRec(root.left, key);
        else if (comparator.compare(key, root.key) > 0)
            root.right = deleteRec(root.right, key);
        else {
            isDeleted = true;

            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.key = minValueRec(root.right);

            root.right = deleteRec(root.right, root.key);
        }

        return root;
    }

    public T minimum() {
        return minValueRec(root);
    }

    public T maximum() {
        return maxValueRec(root);
    }

    private T maxValueRec(Node root) {
        if (root == null)
            return null;

        T max = root.key;
        if (root.right != null)
            max = maxValueRec(root.right);
        return max;
    }

    private T minValueRec(Node root) {
        if (root == null)
            return null;

        T min = root.key;
        if (root.left != null)
            min = minValueRec(root.left);
        return min;
    }
}
