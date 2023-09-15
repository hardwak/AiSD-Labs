package lab5;

import sortCode.core.SortingAlgorithm;

import java.util.Comparator;
import java.util.List;


public class SortMinMaxChoice<T> extends SortingAlgorithm<T> {


    public SortMinMaxChoice(Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public List<T> sort(List<T> list) {
        int size = list.size();

        int minPos = 0;
        int maxPos = 0;

        for (int i = 0; i < (size + 1) / 2 ; i++) {
            T min = list.get(i);
            T max = list.get(size - 1 - i);

            for (int j = i; j < size - i ; j++) {
                if (compare(min, list.get(j)) >= 0) {
                    min = list.get(j);
                    minPos = j;
                }

                if (compare(max, list.get(j)) <= 0) {
                    max = list.get(j);
                    maxPos = j;
                }
            }
            if (minPos != i)
                swap(list, minPos, i);

            if (maxPos == i)
                maxPos = minPos;

            if (maxPos != size - 1 - i)
                swap(list, maxPos, size - 1 - i);
        }

        return list;
    }

}
