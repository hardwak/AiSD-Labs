package lab9;

import sortCode.measuring.Timer;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Main {

    static SkipList<Integer> skipList;

    static BSTSet<Integer> bstTree;

    static RBSet<Integer> rbTree;

    static sortCode.measuring.Timer timer = new Timer();

    static Random random = new Random();

    public static void main(String[] args){
        test();
//        int size = 500;
//        statisticInsert(size, 0.25);
//        statisticContains(size);
//        statisticRemove(size, true);
    }

    public static void test(){
        skipList = new SkipList<>(new IntegerComparator(), 0.5);

        skipList.insert(4);
        skipList.insert(5);
        skipList.insert(6);
        skipList.insert(1);
        skipList.insert(3);
        skipList.insert(2);
        skipList.insert(9);
        skipList.insert(0);
        skipList.insert(8);
        skipList.insert(7);
        skipList.printList();

//        try {
//            skipList.insert(4);
//        }
//        catch (KeyAlreadyExistsException e){
//            e.printStackTrace();
//        }

        System.out.println(skipList.contains(4));
        System.out.println(skipList.contains(-1));

        System.out.println(skipList.remove(4));
        System.out.println(skipList.remove(6));
        System.out.println(skipList.remove(-2));
        skipList.printList();
        System.out.println("test end");
    }

    public static void statisticInsert(int size, double p){
        skipList = new SkipList<>(new IntegerComparator(), p);
        rbTree = new RBSet<>();
        bstTree = new BSTSet<>(new IntegerComparator());

        int[] testArray = generateRandomArray(size);

        System.out.println("Insertion time in ms for size: " + size);

        timer.start();
        for (int i = 0; i < size; i++) {
            skipList.insert(testArray[i]);
        }
        timer.stop();
        System.out.println("Skip list: " + timer.durationMillis());
        timer.reset();


        timer.start();
        for (int i = 0; i < size; i++) {
            bstTree.insert(testArray[i]);
        }
        timer.stop();
        System.out.println("BST Tree: " + timer.durationMillis());
        timer.reset();


        timer.start();
        for (int i = 0; i < size; i++) {
            rbTree.insert(testArray[i]);
        }
        timer.stop();
        System.out.println("RB Tree: " + timer.durationMillis());
        timer.reset();
    }

    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < size; i++) {
            int num = random.nextInt() + 1;

            // Check if the number already exists in the set
            while (set.contains(num)) {
                num = random.nextInt() + 1;
            }

            array[i] = num;
            set.add(num);
        }

        return array;
    }

    public static void statisticContains(int size){
        int[] testArray = generateRandomArray(size);

        System.out.println("Contains time in ms for size: " + size);

        timer.start();
        for (int i = 0; i < size; i++) {
            skipList.contains(testArray[i]);
        }
        timer.stop();
        System.out.println("Skip list: " + timer.durationMillis());
        timer.reset();


        timer.start();
        for (int i = 0; i < size; i++) {
            bstTree.contains(testArray[i]);
        }
        timer.stop();
        System.out.println("BST Tree: " + timer.durationMillis());
        timer.reset();


        timer.start();
        for (int i = 0; i < size; i++) {
            rbTree.contains(testArray[i]);
        }
        timer.stop();
        System.out.println("RB Tree: " + timer.durationMillis());
        timer.reset();
    }

    public static void statisticRemove(int size, boolean doskip){
        int[] testArray = generateRandomArray(size);

        System.out.println("Remove time in ms for size: " + size);

        if (doskip) {
            timer.start();
            for (int i = 0; i < size; i++) {
                skipList.remove(testArray[i]);
            }
            timer.stop();
            System.out.println("Skip list: " + timer.durationMillis());
            timer.reset();
        }

        timer.start();
        for (int i = 0; i < size; i++) {
            bstTree.remove(testArray[i]);
        }
        timer.stop();
        System.out.println("BST Tree: " + timer.durationMillis());
        timer.reset();


        timer.start();
        for (int i = 0; i < size; i++) {
            rbTree.remove(testArray[i]);
        }
        timer.stop();
        System.out.println("RB Tree: " + timer.durationMillis());
        timer.reset();
    }
}

class IntegerComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer int1, Integer int2) {
        return int1 - int2;
    }

}
