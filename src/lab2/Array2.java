package lab2;

import java.util.Arrays;
import java.util.Iterator;

public class Array2<T> implements Iterable<T>{

    @Override
    public Iterator<T> iterator() {
        return new Array2Iterator<>(this);
    }

    public Iterator<T> iterator(int n) {
        return new Array2SkipIterator<>(this, n);
    }

    private final T[][] array;
    private final int[] numOfElem;

    @SuppressWarnings("unchecked")
    public Array2(int[] numOfElem){
        T[][] array = (T[][])new Object[numOfElem.length][];
        for (int i = 0; i < numOfElem.length; i++) {

            array[i] = (T[]) new Object[numOfElem[i]];

        }
        this.array = array;
        this.numOfElem = numOfElem;
    }

    public T get(int i, int j){
        try {
            return array[i][j];
        }
        catch (ArrayIndexOutOfBoundsException e){
            e.getStackTrace();
        }
        return null;
    }

    public int[] getNumOfElem() {
        return numOfElem;
    }

    public void set(T newElem, int i, int j){
        try {
            array[i][j] = newElem;
        }
        catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }

    public String toString(){
        return Arrays.deepToString(array);
    }

}
