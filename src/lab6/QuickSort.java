package lab6;

import sortCode.core.SortingAlgorithm;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class QuickSort<T> extends SortingAlgorithm<T> {

    private boolean randomElement;

    public QuickSort(Comparator<? super T> comparator, boolean randomElement) {
        super(comparator);
        this.randomElement = randomElement;
    }

    @Override
    public List<T> sort(List<T> list) {
        //showList(list);

        quicksort(list, 0, list.size());

        //showList(list);
        //System.out.println("--------------------------------");
        return list;
    }

    private void quicksort(List<T> list, int startIndex, int endIndex){
        if (endIndex - startIndex > 1){
            int partition = partition(list, startIndex, endIndex);
            quicksort(list, startIndex, partition);
            quicksort(list, partition + 1, endIndex);
        }
    }

    private int partition(List<T> list, int startIndex, int endIndex){

        T value;

        if (randomElement) {
            int rnd = new Random().nextInt(startIndex, endIndex);
            value = list.get(rnd);
            swap(list, startIndex, rnd);
        }
        else {
            value = list.get(startIndex);
        }

        int biggerPoint = startIndex + 1;
        int lessPoint = endIndex - 1;

        do {
            while (biggerPoint <= lessPoint && compare(list.get(biggerPoint), value) <= 0)
                biggerPoint++;
            while (compare(list.get(lessPoint), value) > 0)
                lessPoint--;
            if (biggerPoint < lessPoint)
                swap(list, biggerPoint, lessPoint);
        } while (biggerPoint < lessPoint);

        swap(list, lessPoint, startIndex);

        return lessPoint;
    }

    private void showList(List<T> list){
        for (int i = 0; i < list.size(); i++) {
            System.out.print( list.get(i)+ " ");
        }
        System.out.println();
    }

}
