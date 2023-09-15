package lab4;

import java.util.AbstractList;

public class TwoWayLinkedList<E> extends AbstractList<E> {


    private class Element{
        private E data;
        private Element next;
        private Element previous;

        Element(E data){
            this.data = data;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(Element next) {
            this.next = next;
        }

        public void setPrevious(Element previous) {
            this.previous = previous;
        }

        public Element getPrevious() {
            return previous;
        }

    }

    private Element getElement(int index){
        Element element = head;
        while (index > 0 && element != null){
            index--;
            element = element.getNext();
        }
        return element;
    }

    private Element head = null;
    private Element tail;


    public TwoWayLinkedList(){
    }

    public boolean isEmpty(){
        return head==null;
    }

    @Override
    public void clear() {
        head=null;
    }

    @Override
    public boolean add(E data) {
        Element element = new Element(data);
        if (head == null){
            head = element;
            tail = head;
            return true;
        }

        tail.setNext(element);
        element.setPrevious(tail);
        tail = element;
        return true;
    }

    @Override
     public void add(int index, E data) {
        if (index < 0) throw new IndexOutOfBoundsException();

        Element element = new Element(data);

        if (index == 0){
            element.setNext(head);
            head.setPrevious(element);
            head = element;
        }

        int size = size();

        if (index > size){
            throw new IndexOutOfBoundsException();
        }

        if((size - 1)/2 < index) {
            int steps = size - index;
            Element prevElement = tail;
            while (steps > 0){
                prevElement = prevElement.getPrevious();
                steps--;
            }

            if (prevElement == tail)
                add(data);
            else {
                Element nextElement = prevElement.getNext();

                element.setNext(nextElement);
                element.setPrevious(prevElement);

                prevElement.setNext(element);
                nextElement.setPrevious(element);
            }
        }
        else
        {
            Element prevElement = getElement(index - 1);
            Element nextElement = prevElement == null ? null : prevElement.getNext();

            if (prevElement == null) throw new IndexOutOfBoundsException();

            element.setNext(nextElement);
            element.setPrevious(prevElement);

            prevElement.setNext(element);
            if (prevElement != tail)
                nextElement.setPrevious(element);
            else
                tail = element;
        }
    }

    public int indexOf(Object data){
        int pos = 0;
        Element element = head;

        while (element != null){
            if (element.getData() == data)
                return pos;
            pos++;
            element = element.getNext();
        }

        return -1;
    }

    public boolean contains(Object data) {
        return indexOf(data)>=0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size()) return null;

        Element element = getElement(index);
        return element.getData();

        //return element == null ? null : element.getData();
    }

    public E set(int index, E data){
        if (index < 0 || index >= size()) return null;

        Element element = getElement(index);
        E dataToReturn = element.getData();
        element.setData(data);

        return dataToReturn;
    }

    public E remove(int index){

        if (head == null)
            return null;

        if (index == 0){
            E returnData = head.getData();

            if (head.getNext() != null)
                head.getNext().setPrevious(null);

            head = head.getNext();
            return returnData;
        }

        Element element = getElement(index);
        if (element == null) return null;

        Element nextElement = element.getNext();
        Element prevElement = element.getPrevious();

        prevElement.setNext(element.getNext());
        if (element != tail)
            nextElement.setPrevious(element.getPrevious());
        else
            tail = prevElement;

        return element.getData();
    }

    public boolean remove(Object data){
        if (head == null) return false;
        if ((head.getData() != null && head.getData().equals(data)) || (head.getData() == null && data == null)){
            if (head.getNext() != null)
                head.getNext().setPrevious(null);

            head = head.getNext();
            return true;
        }

        Element element = head;

        while (element != null && !((element.getData() != null && element.getData().equals(data)) || (element.getData() == null && data == null))){
            element = element.getNext();
        }

        if (element == null)
            return false;

        if (element != tail)
            element.getNext().setPrevious(element.getPrevious());
        else
            tail = element.getPrevious();

        element.getPrevious().setNext(element.getNext());

        return true;
    }

    @Override
    public int size() {
        int amount = 0;
        Element element = head;
        while (element != null){
            amount++;
            element = element.getNext();
        }
        return amount;

    }

    public E getLast(){
        return tail.getData();
    }

}
