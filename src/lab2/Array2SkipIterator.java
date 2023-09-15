package lab2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Array2SkipIterator<T> implements Iterator<T> {
    private Array2 array;

    private int skip;
    private int ipos = 0;
    private int jpos;


    boolean isFinished = false;

    public Array2SkipIterator(Array2 array, int n) {
        this.array = array;
        skip = n;
        jpos = -n;
    }

    @Override
    public boolean hasNext() {
        jpos+= skip;
        while (array.get(ipos, jpos) == null && !isFinished){
            if(ipos < array.getNumOfElem().length) {
                jpos -= array.getNumOfElem()[ipos];
                ipos++;
            }
            else
                isFinished = true;
        }
        return ipos < array.getNumOfElem().length;
    }

    @Override
    public T next() throws NoSuchElementException {

        if (ipos < array.getNumOfElem().length) {
            return (T) array.get(ipos, jpos);
        }
        else
            throw new NoSuchElementException();
    }


    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
