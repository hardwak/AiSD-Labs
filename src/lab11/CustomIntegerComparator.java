package lab11;

public class CustomIntegerComparator extends CustomComparator<Integer> {
    public CustomIntegerComparator(){
        super();
    }

    public int compare(Integer i1, Integer i2) {
        return i1 - i2;
    }

    public Integer maxValue(){
        return Integer.MAX_VALUE;
    }

}
