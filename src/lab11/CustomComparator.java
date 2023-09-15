package lab11;

public abstract class CustomComparator<T> {
    public CustomComparator(){};
    abstract int compare(T o1, T o2);
    abstract T maxValue();
}
