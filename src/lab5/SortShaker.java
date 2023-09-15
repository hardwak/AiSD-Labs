package lab5;

import sortCode.core.SortingAlgorithm;

import java.util.Comparator;
import java.util.List;

public class SortShaker<T> extends SortingAlgorithm<T> {


    public SortShaker(Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public List<T> sort(List<T> list) {

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

            if (compare(list.get(i), list.get(i+1)) > 0) {
                swap(list, i, i + 1);
                swaps++;
            }

            if (increaseI)
                i++;
            else
                i--;
        }

        return list;
    }
}
