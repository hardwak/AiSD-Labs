package lab11;

public class BinomialHeap<T> {

    class Node{
        private Node parent = null;

        private T value;

        private int degree = 0;

        private Node child = null;

        private Node sibling = null;

        private Node(T value){
            this.value = value;
        }
    }

    private final CustomComparator<T> comparator;

    private Node head = null;

    public BinomialHeap(CustomComparator<T> comparator){
        this.comparator = comparator;
    }

    public T maximum(){
        if  (head == null)
            throw new IllegalStateException("Heap is empty");
        if (head.sibling == null)
            return head.value;
        Node preMaxNode = preMaxNode();
        return preMaxNode.sibling.value;
    }

    public void union(BinomialHeap<T> heap){
        head = union(this, heap).head;
    }
    public BinomialHeap<T> union(BinomialHeap<T> heap1, BinomialHeap<T> heap2){
        if (heap1 == heap2)
            throw new IllegalArgumentException("Cannot union same heaps");
        if (heap1 == null || heap2 == null)
            throw new NullPointerException("Cannot union heap with null");
        BinomialHeap<T> heap = new BinomialHeap<>(comparator);
        heap.head = heapMerge(heap1.head, heap2.head);

        if (heap1.head == null)
            return heap2;
        if (heap2.head == null)
            return heap1;

        if (heap.head == null)
            return null;

        Node prev = null;
        Node curr = heap.head;
        Node next = curr.sibling;

        while (next != null){
            if (curr.degree != next.degree || (next.sibling != null && next.sibling.degree == curr.degree)){
                prev = curr;
                curr = next;
            }
            else {
                if (comparator.compare(curr.value, next.value) >= 0){
                    curr.sibling = next.sibling;
                    link(next, curr);
                }
                else {
                    if (prev == null)
                        heap.head = next;
                    else
                        prev.sibling = next;
                    link(curr, next);
                    curr = next;
                }
            }
            next = curr.sibling;
        }
        return heap;
    }

    public void insert(T value){
        BinomialHeap<T> newHeap = new BinomialHeap<>(comparator);
        newHeap.head = new Node(value);
        head = union(this, newHeap).head;
    }

    public Node insertNode(T value){
        BinomialHeap<T> newHeap = new BinomialHeap<>(comparator);
        Node newNode = new Node(value);
        newHeap.head = newNode;
        head = union(this, newHeap).head;
        return newNode;
    }

    public T extractMax(){
        BinomialHeap<T> newHeap = new BinomialHeap<>(comparator);

        Node prev = preMaxNode();
        Node maxNode = prev.sibling;

        if (head.sibling == null){
            maxNode = prev;
            head = null;
        }else
            prev.sibling = maxNode.sibling;

        Node sibling = maxNode.child;
        while (sibling != null){
            prev = sibling;
            sibling = sibling.sibling;
            prev.sibling = null;
            prev.parent = null;
            if (newHeap.head != null)
                prev.sibling = newHeap.head;
            newHeap.head = prev;
        }

        union(newHeap);
        return maxNode.value;
    }

    public void delete(Node node){
        increaseValue(node);
        extractMax();
    }

    private void increaseValue(Node node){
        node.value = comparator.maxValue();
        Node lowerNode = node;
        Node higherNode = lowerNode.parent;
        while (higherNode != null && comparator.compare(lowerNode.value, higherNode.value) > 0){

            T tempValue = lowerNode.value;
            lowerNode.value = higherNode.value;
            higherNode.value = tempValue;

            lowerNode = higherNode;
            higherNode = higherNode.parent;
        }
    }

    private Node heapMerge(Node heap1, Node heap2){
        Node head, tail;

        if (heap1 == null)
            return heap2;
        if (heap2 == null)
            return heap1;

        //pierwszy mniejszy
        if (heap1.degree <= heap2.degree){
            head = tail = heap1;
            heap1 = heap1.sibling;
        }
        else {
            head = tail = heap2;
            heap2 = heap2.sibling;
        }

        while (heap1 != null && heap2 != null){
            if (heap1.degree <= heap2.degree){
                tail.sibling = heap1;
                tail = heap1;
                heap1 = heap1.sibling;
            }
            else {
                tail.sibling = heap2;
                tail = heap2;
                heap2 = heap2.sibling;
            }
        }

        //dodaje reszte
        if (heap1 != null)
            tail.sibling = heap1;
        if (heap2 != null)
            tail.sibling = heap2;


        return head;
    }

    private Node preMaxNode(){
        if (head == null) {
            throw new IllegalStateException("Heap is empty");
        }
        if (head.sibling == null)
            return head;

        Node currNode = head;
        Node preMaxNode = head;
        while (currNode.sibling != null){
            if (comparator.compare(preMaxNode.value, currNode.sibling.value) < 0)
                preMaxNode = currNode;

            currNode = currNode.sibling;
        }
        return preMaxNode;
    }

    private void link(Node lower, Node higher){
        if (comparator.compare(lower.value, higher.value) > 0) {
            Node temp = lower;
            lower = higher;
            higher = temp;
        }

        lower.parent = higher;
        lower.sibling = higher.child;
        higher.child = lower;
        higher.degree++;
    }


}
