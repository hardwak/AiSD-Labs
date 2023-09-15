package lab5;

import sortCode.core.SortingAlgorithm;

import java.util.Comparator;
import java.util.List;

public class SortBinarySearch<T> extends SortingAlgorithm<T> {

    public SortBinarySearch(Comparator<? super T> comparator) {
        super(comparator);
    }



    @Override
    public List<T> sort(List<T> list) {
        int size = list.size();
        System.out.println("------------------------------");
        showList(list);


        for (int i = 1; i < size; i++){

            int sortedStart = 0;
            int sortedFinish = i - 1;
            boolean sorted = false;
            while (!sorted) {
                int middle = (sortedFinish + sortedStart)  / 2;

                if (sortedFinish == sortedStart){
                    T temp = list.get(i);
                    list.remove(i);

                    if (compare(list.get(middle), temp) <= 0){
                        middle++;
                    }

                    list.add(middle, temp);

                    sorted = true;
                    showList(list);
                }
                if (compare(list.get(middle), list.get(i)) > 0){
                    sortedFinish = middle;
                }
                else if (compare(list.get(middle), list.get(i)) < 0){
                    sortedStart = middle + 1;
                }
                else {
                    sortedStart = middle;
                    sortedFinish = middle;
                }
            }
        }
        return list;
    }

    private void showList(List<T> list){
        for (int i = 0; i < list.size(); i++) {
            System.out.print( list.get(i)+ " ");
        }
        System.out.println();
    }
}
