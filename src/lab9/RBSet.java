package lab9;

import java.util.TreeSet;

public class RBSet<T> extends TreeSet<T> implements ISet<T> {

    public RBSet(){
        super();
    }

    @Override
    public void insert(T element) {
        add(element);
    }
}
