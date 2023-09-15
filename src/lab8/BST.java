package lab8;

import java.util.Comparator;

public class BST<T> {
    private class Node {
        private T key;
        private Node left, right;

        public Node(T item) {
            key = item;
            left = right = null;
        }
    }

    private Node root;

    private final Comparator<T> comparator;

    public BST(Comparator<T> comparator) {
        root = null;
        this.comparator = comparator;
    }

    public <R> void preOrderWalk(IExecutor<T, R> exec) {
        preOrderWalk(root, exec);
    }

    private <R> void preOrderWalk(Node root, IExecutor<T, R> exec) {
        if (root != null) {
            exec.execute(root.key);
            preOrderWalk(root.left, exec);
            preOrderWalk(root.right, exec);
        }
    }


    public T search(T key) {
        Node toReturn = searchRec(root, key);
        return toReturn == null ? null : toReturn.key;
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

/*    public void showOrder() {
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
    }*/

//    private void deleteIter(Node root, T key) {
//        Node parent = null;
//        boolean rightKid = false; //false - left kid, true - right kid
//
//        while (root != null && comparator.compare(key, root.key) != 0) {
//            if (comparator.compare(key, root.key) < 0) {
//                parent = root;
//                root = root.left;
//                rightKid = false;
//            }
//            else if (comparator.compare(key, root.key) > 0) {
//                parent = root;
//                root = root.right;
//                rightKid = true;
//            }
//        }
//        if (root != null){
//            if (root.left == null) {
//
//                root = root.right;
//            }
//            else if (root.right == null) {
//                root = root.left;
//
//
//            }
//        }
//    }

    public void delete(T key) {
        root = deleteRec(root, key);
    }

    private Node deleteRec(Node root, T key) {
        if (root == null)
            return null;


        if (comparator.compare(key, root.key) < 0)
            root.left = deleteRec(root.left, key);
        else if (comparator.compare(key, root.key) > 0)
            root.right = deleteRec(root.right, key);
        else {
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

    public T findNext(T key){
        Node current = root;
        Node next = null;

        while (current != null && comparator.compare(current.key, key) != 0){
            if (comparator.compare(current.key, key) < 0)
                current = current.right;
            else if (comparator.compare(current.key, key) > 0){
                next = current;
                current = current.left;
            }
        }

        if (current == null)
            return null;

        if (current.right != null)
            return minValueRec(current.right);

        return next == null ? null : next.key;
    }


    //-------WERSJE ITERACYJNE------------

 /*   private T maxValue(Node root) {
        T max = root.key;
        while (root.right != null) {
            max = root.right.key;
            root = root.right;
        }
        return max;
    }


    private T minValue(Node root) {
        T min = root.key;
        while (root.left != null) {
            min = root.left.key;
            root = root.left;
        }
        return min;
    }*/
}
