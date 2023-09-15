package lab8;

import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        BST<Integer> tree = new BST<>(new IntegerComparator());

        System.out.println(tree.search(12));
        System.out.println(tree.maximum());
        System.out.println(tree.minimum());
        System.out.println(tree.findNext(12));
        tree.delete(2);

        tree.insert(20);
        tree.insert(7);
        tree.insert(10);
        tree.insert(25);
        tree.insert(4);
        tree.insert(1);
        tree.insert(2);
        tree.insert(12);
        tree.insert(30);
        tree.insert(34);
        tree.insert(32);
        tree.insert(33);
        tree.insert(35);
        tree.insert(24);
        tree.insert(24);

        IntegerToStringExec exec;

        System.out.println(tree.search(12));
        System.out.println(tree.search(50));

        System.out.println(tree.minimum());
        System.out.println(tree.maximum());

        exec = new IntegerToStringExec();
        tree.preOrderWalk(exec);
        System.out.println(exec.getResult());

        System.out.println(tree.findNext(20));
        System.out.println(tree.findNext(30));
        System.out.println(tree.findNext(2));
        System.out.println(tree.findNext(35));

        tree.delete(12);

        exec = new IntegerToStringExec();
        tree.preOrderWalk(exec);
        System.out.println(exec.getResult());

        tree.delete(2);

        exec = new IntegerToStringExec();
        tree.preOrderWalk(exec);
        System.out.println(exec.getResult());

        System.out.println(tree.minimum());

        tree.delete(20);
        tree.delete(20);

        exec = new IntegerToStringExec();
        tree.preOrderWalk(exec);
        System.out.println(exec.getResult());

    }

}

class IntegerComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer int1, Integer int2) {
        return int1 - int2;
    }

}



