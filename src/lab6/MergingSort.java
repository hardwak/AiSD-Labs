package lab6;

import sortCode.core.SortingAlgorithm;

import java.util.*;

public class MergingSort<T> extends SortingAlgorithm<T> {
    public MergingSort(Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public List<T> sort(List<T> list){

        showList(list);

        int size = list.size();

        if (size < 2)
            return list;

        LinkedList<List<T>> queue = new LinkedList<>();

        for (T t : list) {
            List<T> currList = new ArrayList<>(1);
            currList.add(t);
            queue.addLast(currList);
        }

        while (queue.size() != 1){
            queue.addLast(merge(queue.removeFirst(), queue.removeFirst()));
        }

        List<T> returnList = queue.removeFirst();

        showList(returnList);
        System.out.println("-----------------------------");

        return returnList;

    }

    private void showList(List<T> list){
        for (int i = 0; i < list.size(); i++) {
            System.out.print( list.get(i)+ " ");
        }
        System.out.println();
    }


    @SuppressWarnings("unchecked")
    private List<T> merge(List<T> leftList, List<T> rightList){
        List<T> result = (List<T>) new ArrayList<Object>();
        Iterator<T> l = leftList.iterator();
        Iterator<T> r = rightList.iterator();

        T elemL = null ,elemR = null;

        boolean leftHasNext, rightHasNext;

        if (leftHasNext = l.hasNext())
            elemL = l.next();
        if (rightHasNext = r.hasNext())
            elemR = r.next();

        while (leftHasNext && rightHasNext){
            if (compare(elemL, elemR) <= 0){
                result.add(elemL);
                if (leftHasNext = l.hasNext())
                    elemL = l.next();
                else
                    result.add(elemR);
            }
            else {
                result.add(elemR);
                if (rightHasNext = r.hasNext())
                    elemR = r.next();
                else
                    result.add(elemL);
            }
        }

        while(l.hasNext())
            result.add(l.next());

        while(r.hasNext())
            result.add(r.next());

        return result;
    }
}
