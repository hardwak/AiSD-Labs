package lab7;

import java.lang.reflect.Array;

public class Array3Heap<T extends Comparable<T>>  {
    private T[] tab;

    private int exactSize = 0;

    @SuppressWarnings("unchecked")
    public Array3Heap(int size) {
        //chatGPT
        tab =(T[]) Array.newInstance(Comparable.class, size);
    }

    @SuppressWarnings("unchecked")
    public void add(T element) {
        if (element == null)
            throw new IllegalArgumentException();
        try {
            tab[exactSize] = element;

            if (exactSize != 0){
                int parentIndex = (exactSize - 1) / 3;
                int childIndex = exactSize;
                do {
                    if (tab[parentIndex].compareTo(tab[childIndex]) > 0)
                        swap(parentIndex, childIndex);
                    childIndex = parentIndex;
                    parentIndex = (parentIndex - 1) / 3;
                } while (childIndex != 0);
            }

            exactSize++;
        } catch (ArrayIndexOutOfBoundsException e){
            T[] newTab =(T[]) Array.newInstance(Comparable.class, tab.length * 2);
//            for (int i = 0; i < tab.length; i++) {
//                newTab[i] = tab[i];
//            }
            System.arraycopy(tab, 0, newTab, 0, tab.length);
            tab = newTab;
            add(element);
        }
    }

    private void swap(int i1, int i2){
        T temp = tab[i1];
        tab[i1] = tab[i2];
        tab[i2] = temp;
    }


    public void clear() {
        for (int i = 0; i < exactSize; i++) {
            tab[i] = null;
        }
        exactSize = 0;
    }

    public int size() {
        return exactSize;
    }

    public T minimum() {
        if (exactSize == 0)
            return null;

        if (exactSize == 1){
            T retValue = tab[0];
            tab[0] = null;
            exactSize--;
            return retValue;
        }

        T min = tab[0];

        swap(0, exactSize - 1);
        tab[exactSize - 1] = null;
        exactSize--;
        sink(0);

        return min;
    }

    private void sink(int index){
        int left = index*3+1;
        int middle = index*3+2;
        int right = index*3+3;

        int min = index;

        if (left < exactSize && tab[left].compareTo(tab[min]) < 0)
            min = left;
        if (middle < exactSize && tab[middle].compareTo(tab[min]) < 0)
            min = middle;
        if (right < exactSize && tab[right].compareTo(tab[min]) < 0)
            min = right;

        if (min != index){
            swap(min, index);
            sink(min);
        }
    }

}
