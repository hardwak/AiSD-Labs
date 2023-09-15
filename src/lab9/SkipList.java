package lab9;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class SkipList<T> implements ISet<T>{

    private final Comparator<T> comparator;

    private final double p;

    private class Element {
        private T value;

        private Element[] nextElements;

        @SuppressWarnings("unchecked")
        Element(T value, int levels) {
            nextElements =  (Element[]) Array.newInstance(Element.class, levels);
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Element getNextElement(int level){
            return nextElements[level];
        }

        public void setNextElement(Element next ,int level){
            this.nextElements[level] = next;
        }

        public int getHighestLevel(){
            return nextElements.length - 1;
        }

    }

    public SkipList(Comparator<T> comparator, double p){
        this.comparator = comparator;
        if (p >= 1 || p < 0)
            throw new IllegalArgumentException();
        this.p = p;
    }

    private Element head = new Element(null, 1);

    @Override
    public void insert(T value) {
        if (value == null)
            throw new IllegalArgumentException();
        int levelNum = rand(1);
        visitedLevels = new boolean[levelNum];

        //increasing head levels
        if (levelNum > head.getHighestLevel() + 1){
            Element newHead = new Element(null, levelNum);

            for (int i = 0; i <= head.getHighestLevel(); i++) {
                newHead.setNextElement(head.getNextElement(i) , i);
            }

            head = newHead;
        }

        insertRec(value, head, head.getHighestLevel(), new Element(value, levelNum));
    }

    private boolean[] visitedLevels;
    private void insertRec(T value, Element currElement, int level, Element newElem){
        if (level >= 0 && currElement.getNextElement(level) != null && comparator.compare(currElement.getNextElement(level).getValue(), value) == 0)
            throw new KeyAlreadyExistsException();


        if (level >= 0){
            //goes till needed place
            if (currElement.getNextElement(level) == null || comparator.compare(value, currElement.getNextElement(level).getValue()) <= 0) {
                insertRec(value, currElement, level - 1, newElem);
                //zeby nie sprawdzalo nie istniejace referencje
                if (level < visitedLevels.length)
                    visitedLevels[level] = true;
            }
            else if (comparator.compare(value, currElement.getNextElement(level).getValue()) > 0 ){
                insertRec(value, currElement.getNextElement(level), level, newElem);
                if (level < visitedLevels.length)
                    visitedLevels[level] = false;
            }

            if (level < visitedLevels.length && visitedLevels[level]){
                visitedLevels[level] = false;

                newElem.setNextElement(currElement.getNextElement(level), level);
                currElement.setNextElement(newElem, level);
            }

        }
    }

    private int rand(int levels){
        if (new Random().nextDouble() <= p)
            return rand(++levels);
        return levels;
    }

    @Override
    public boolean contains(T element) {
        return containsRec(element, head, head.getHighestLevel());
    }

    private boolean containsRec(T value, Element currElement, int level){
        if (currElement != head && comparator.compare(value, currElement.getValue()) == 0)
            return true;

        if (level >= 0){
            if (currElement.getNextElement(level) == null || comparator.compare(value, currElement.getNextElement(level).getValue()) < 0)
                return containsRec(value, currElement, level - 1);
            if (comparator.compare(value, currElement.getNextElement(level).getValue()) >= 0 )
                return containsRec(value, currElement.getNextElement(level), level);
        }

        return false;
    }

    @Override
    public boolean remove(T element) {
        visitedLevels = new boolean[head.getHighestLevel() + 1];
        return removeRec(element, head, head.getHighestLevel());
    }

    private boolean removeRec(T value, Element currElement, int level){
        if (level == -1 && currElement.getNextElement(0) != null && comparator.compare(currElement.getNextElement(0).getValue(), value) == 0) {
            boolean[] visitedLevels = new boolean[currElement.getNextElement(0).getHighestLevel() + 1];
            System.arraycopy(this.visitedLevels, 0, visitedLevels, 0, visitedLevels.length);
            this.visitedLevels = visitedLevels;
            return true;
        }

        boolean isDeleted = false;

        if (level >= 0){
            if (currElement.getNextElement(level) == null || comparator.compare(value, currElement.getNextElement(level).getValue()) <= 0){
                isDeleted = removeRec(value, currElement, level - 1);
                if (level < visitedLevels.length)
                    visitedLevels[level] = true;
            }
            else if (comparator.compare(value, currElement.getNextElement(level).getValue()) > 0 ){
                isDeleted = removeRec(value, currElement.getNextElement(level), level);
                if (level < visitedLevels.length)
                    visitedLevels[level] = false;
            }

            if (level < visitedLevels.length && visitedLevels[level]){
                visitedLevels[level] = false;
                if (currElement.getNextElement(level) != null) //nie ma nastepnego elementu
                    currElement.setNextElement(currElement.getNextElement(level).getNextElement(level), level);
            }
        }
        return isDeleted;
    }

    public void printList(){
        Element currElement = head.getNextElement(0);
        StringBuilder elements = new StringBuilder();
        StringBuilder levelsString = new StringBuilder();
        ArrayList<Integer> levels = new ArrayList<>();

        while (currElement != null){
            elements.append(currElement.getValue()).append(", ");
            levels.add(currElement.getHighestLevel());
            levelsString.append(currElement.getHighestLevel()).append(", ");
            currElement = currElement.getNextElement(0);
        }

        for (int i = 0; i < head.getHighestLevel(); i++) {
            for (int j = 0; j < levels.size(); j++) {
                if (head.getHighestLevel() - i <= levels.get(j)){
                    System.out.print("□  ");
                }
                else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }

        System.out.println(elements);
        System.out.println(levelsString);

    }
}


































