package lab7;

import java.lang.reflect.Array;

public class ArrayDHeap<T extends Comparable<T>> {
    private T[] tab;

    private int kids;

    private int exactSize = 0;

    @SuppressWarnings("unchecked")
    public ArrayDHeap(int size, int kids) {
        if (kids < 1)
            throw new IllegalArgumentException();

        this.kids = kids;
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
                int parentIndex = (exactSize - 1) / kids;
                int childIndex = exactSize;
                do {
                    if (tab[parentIndex].compareTo(tab[childIndex]) > 0)
                        swap(parentIndex, childIndex);
                    childIndex = parentIndex;
                    parentIndex = (parentIndex - 1) / kids;
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
        int kidsIndexs[] = new int[kids];
        for (int i = 0; i < kids; i++) {
            kidsIndexs[i] = index*kids+i+1;
        }

        int min = index;

        for (int i = 0; i < kids; i++) {
            int comparingIndex = kidsIndexs[i];
            if (comparingIndex < exactSize && tab[comparingIndex].compareTo(tab[min]) < 0)
                min = comparingIndex;
        }

        if (min != index){
            swap(min, index);
            sink(min);
        }
    }

}
