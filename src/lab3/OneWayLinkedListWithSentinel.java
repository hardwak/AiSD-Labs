package lab3;

import java.util.AbstractList;
import java.util.ArrayList;

public class OneWayLinkedListWithSentinel<E> extends AbstractList<E> {

    private class Element{
        private E data;
        private Element next;

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

        public void insertAfter(Element element){
            element.setNext(this.getNext());
            this.setNext(element);
        }

        public void remove(int index){
            if(index == 0)
                sentinel.setNext(this.getNext());
            else
                getElement(index - 1).setNext(this.getNext());
        }
    }

    private Element getElement(int index){
        Element element = sentinel.getNext();

        int counter = 0;

        while (element != sentinel && counter < index){
            counter++;
            element = element.getNext();
        }

        if (element == sentinel)
            throw new IndexOutOfBoundsException();
        return element;
    }

    private Element getElement(Object data){
        Element element = sentinel.getNext();

        while (element != sentinel && !data.equals(element.getData())){
            element = element.getNext();
        }

        if(element == sentinel)
            return sentinel;

        return element;
    }

    private Element sentinel = null;
    public OneWayLinkedListWithSentinel(){
        sentinel = new Element(null);
        sentinel.setNext(sentinel);
    }

    @Override
    public E get(int index) {
        Element element = getElement(index);
        return  element.getData();
    }

    public E set(int index, E data){
        Element element = getElement(index);
        E returnData = element.getData();
        element.setData(data);
        return returnData;
    }

    @Override
    public boolean add(E data) {
        Element newElement = new Element(data);

        if(size() == 0)
            sentinel.insertAfter(newElement);
        else
            getElement(size() - 1).insertAfter(newElement);

        return true;
    }

    @Override
    public void add(int index, E data){
        Element newElement = new Element(data);

        if(index == 0)
            sentinel.insertAfter(newElement);
        else
            getElement(index - 1).insertAfter(newElement);
    }

    @Override
    public boolean contains(Object data) {
        return indexOf(data) != -1;
    }

    public int indexOf(Object data){
        Element element = sentinel.getNext();

        int counter = 0;

        while (element != sentinel) {
            if (element.getData() == null) {
                if (data == null) {
                    return counter;
                } else {
                    counter++;
                    element = element.getNext();
                }
            } else {
                if (!element.getData().equals(data)) {
                    counter++;
                    element = element.getNext();
                }
                else return counter;
            }
        }

        if(element == sentinel)
            return -1;
        return counter;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.getNext()==sentinel;
    }

    @Override
    public void clear() {
        sentinel.setNext(sentinel);
    }

    public E remove(int index){
        Element element = getElement(index);

        element.remove(index);
        return element.getData();
    }

/*
    public boolean remove(E data){
        Element element = getElement(data);

        if(element == sentinel) return false;
        remove(indexOf(element));
        return true;
    }*/

    @Override
    public int size() {
        Element element = sentinel.getNext();

        int counter = 0;

        while (element != sentinel){
            counter++;
            element = element.getNext();
        }
        return counter;
    }

    public boolean isPalindrome(){
        int listSize = size();
        int counter = 0;
        if (listSize == 0)
            return true;

        Element element = sentinel.getNext();

        ArrayList<Element> elements = new ArrayList<>();

        while (counter < listSize / 2){
            elements.add(element);
            element = element.getNext();
            counter++;
        }

        counter--;
        if (listSize % 2 != 0)
            element = element.getNext();

        while (counter >= 0){
            if ((elements.get(counter).getData() != null && !elements.get(counter).getData().equals(element.getData())) ||
                    (elements.get(counter).getData() == null && elements.get(counter).getData() != element.getData())
            )
                return false;
            element = element.getNext();
            counter--;
        }



/*
        while (counter <= listSize / 2){
            if (element.getData() != null && !element.getData().equals(reverseElement.getData()))
                return false;
            else if (reverseElement.getData() != null && !reverseElement.getData().equals(element.getData()))
                return false;

            counter++;
            element = element.getNext();
            reverseElement = getElement(size() - 1 - counter);
        }
        */


        return true;
    }
}
