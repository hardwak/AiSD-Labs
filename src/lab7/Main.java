package lab7;

public class Main {
    public static void main(String[] args){
        Array3Heap<Integer> tree = new Array3Heap<>(3);
        tree.add(9);
        tree.add(1);
        tree.add(11);
        tree.add(3);
        tree.add(2);
        tree.add(0);
        tree.add(8);
        tree.add(7);
        tree.add(10);
        tree.add(-1);
        tree.add(-2);
        try {
            tree.add(null);
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        System.out.println(tree.minimum());
        System.out.println(tree.minimum());
        System.out.println(tree.minimum());
        tree.clear();
        tree.add(10);

        ArrayDHeap<Integer> dTree = new ArrayDHeap<>(3, 4);
        dTree.add(9);
        dTree.add(1);
        dTree.add(11);
        dTree.add(3);
        dTree.add(2);
        dTree.add(0);
        dTree.add(8);
        dTree.add(7);
        dTree.add(10);
        dTree.add(-1);
        dTree.add(-2);
        System.out.println(dTree.minimum());

        System.out.println("finish");
    }
}
