package lab5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Test {
    public static void main(String[] args){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Random().nextInt(0, 100));
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();


        sortShaker(list);


        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }

    public static void sortShaker(ArrayList<Integer> list){
        int swaps = 0;
        int size = list.size();
        int i = 0;
        boolean sorted = false;
        boolean increaseI = true;

        while (!sorted){

            if (i == -1 || i == size - 1) {
                if (swaps == 0)
                    sorted = true;
                swaps = 0;
            }

            if (i == size - 1){
                i-=2;
                increaseI = false;

            }
            if (i == -1){
                i+=2;
                increaseI = true;
            }

            if (list.get(i) > list.get(i+1)) {
                Collections.swap(list, i, i + 1);
                swaps++;
            }

            if (increaseI)
                i++;
            else
                i--;
        }
    }

    public static void sortChoiceTest(ArrayList<Integer> list){
        int size = list.size();

        int minPos = 0;
        int maxPos = 0;

        for (int i = 0; i < (size + 1) / 2; i++) {
            Integer min = list.get(i);
            Integer max = list.get(size - 1 - i);

            for (int j = i; j < size - i; j++) {
                if (min >  list.get(j)) {
                    min = list.get(j);
                    minPos = j;
                }

                if (max < list.get(j)) {
                    max = list.get(j);
                    maxPos = j;
                }
            }

                Collections.swap(list, minPos, i);

            if (maxPos == i)
                maxPos = minPos;


            Collections.swap(list, maxPos, size - 1 - i);
        }
    }
}
