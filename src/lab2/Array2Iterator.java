package lab2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Array2Iterator<T> implements Iterator<T> {
    private Array2 array;
    private int ipos = 0;
    private int jpos = 0;

    public Array2Iterator(Array2 array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        if(ipos < array.getNumOfElem().length && jpos >= array.getNumOfElem()[ipos]){
            ipos++;
            jpos = 0;
        }
        return ipos < array.getNumOfElem().length;
    }

    @Override
    public T next() throws NoSuchElementException{
        if (hasNext())
            return (T) array.get(ipos, jpos++);
        else
            throw new NoSuchElementException();
    }


    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
