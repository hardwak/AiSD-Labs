package lab11;
//STWORZYC KOPIEC DWUMIANOWY MAKSYMALNY (W GORZE WARTOSCI WIEKSZE)

public class Main{

    public static void main(String[] args){
        CustomIntegerComparator customIntegerComparator = new CustomIntegerComparator();
        BinomialHeap<Integer> heap = new BinomialHeap<>(customIntegerComparator);
        heap.insert(100);
        heap.insert(10);
        heap.insert(2);
        heap.insert(12);
        BinomialHeap<Integer>.Node node = heap.insertNode(7);
        heap.insert(1);
        heap.insert(45);
        heap.insert(4);

//        heap.insert(20);
//        heap.insert(20);
//        heap.insert(120);
//
//        heap.delete(node);
        System.out.println(heap.maximum());
        BinomialHeap<Integer> heap2 = new BinomialHeap<>(customIntegerComparator);

        try {
            BinomialHeap<Integer> heap3 = heap.union(heap, null);
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        heap.union(heap2);
        System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());
    }

}
