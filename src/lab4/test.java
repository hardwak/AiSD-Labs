package lab4;

public class test {

    static TwoWayLinkedList<Integer> twoWayLinkedList = new TwoWayLinkedList<>();

    public static void main(String[] args){

        System.out.println(twoWayLinkedList.size());
        System.out.println(twoWayLinkedList.isEmpty());
        System.out.println(twoWayLinkedList.contains(10));

        try {
            twoWayLinkedList.add(3, 5);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        twoWayLinkedList.set(5, 5);

        showElem();


        for (int i = 0; i < 6; i++) {
            twoWayLinkedList.add(i*2);
        }

        twoWayLinkedList.set(3, 45);

        showElem();

        twoWayLinkedList.set(3, null);

        showElem();

        System.out.println(twoWayLinkedList.size());
        System.out.println(twoWayLinkedList.isEmpty());
        System.out.println(twoWayLinkedList.contains(2));

        System.out.println(twoWayLinkedList.indexOf(null));
        System.out.println(twoWayLinkedList.getLast());

        twoWayLinkedList.remove(null);
        twoWayLinkedList.remove(5);
        showElem();

        twoWayLinkedList.add(3, 73);
        twoWayLinkedList.remove(5);
        showElem();
        System.out.println(twoWayLinkedList.getLast());

        twoWayLinkedList.remove((Integer) 8);
        showElem();
        System.out.println(twoWayLinkedList.getLast());
        twoWayLinkedList.add(4, 10);
        twoWayLinkedList.add(5, 100);
        showElem();
        System.out.println(twoWayLinkedList.getLast());

        twoWayLinkedList.clear();
        showElem();
    }

    public static void showElem(){
        System.out.println("-------------------");

        for (Integer integer : twoWayLinkedList) {
            System.out.print(integer + " ");
        }

        System.out.println("\n-------------------");
    }
}
