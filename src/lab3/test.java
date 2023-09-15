package lab3;

public class test {

    static OneWayLinkedListWithSentinel<String> listWithSentinel = new OneWayLinkedListWithSentinel<>();

    public static void main(String[] args){

        System.out.println("Is Empty: " + listWithSentinel.isEmpty());
        System.out.println(listWithSentinel.indexOf(null));
        System.out.println(listWithSentinel.contains(null));

        try {
            listWithSentinel.set(0, "5");
        }
        catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }

        try {
            listWithSentinel.get(-1000);
        }
        catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }

        try {
            listWithSentinel.add(5, "100");
        }
        catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }

        showListElem();

        listWithSentinel.add("5");

        showListElem();

        listWithSentinel.add("1");

        System.out.println("Is Empty: " + listWithSentinel.isEmpty());

        showListElem();

        listWithSentinel.add("2");
        listWithSentinel.add(null);
        listWithSentinel.add("4");
        listWithSentinel.add(null);

        showListElem();

        System.out.println("Contains \"5\": " + listWithSentinel.contains("5"));
        System.out.println("Contains null: " + listWithSentinel.contains(null));
        System.out.println(listWithSentinel.indexOf(null));

        listWithSentinel.remove(null);

        showListElem();

        listWithSentinel.add(1, "new");

        showListElem();

        listWithSentinel.set(5, "10");



        showListElem();
        System.out.println(listWithSentinel.isPalindrome());

        listWithSentinel.clear();
        System.out.println(listWithSentinel.isPalindrome());

        showListElem();

        listWithSentinel.add("1");
        listWithSentinel.add(null);
        listWithSentinel.add("3");
        listWithSentinel.add(null);
        listWithSentinel.add("1");

        System.out.println(listWithSentinel.isPalindrome());

    }

    public static void showListElem(){
        System.out.println("-----------------");
        for (int i = 0; i < listWithSentinel.size(); i++) {
            System.out.print(listWithSentinel.get(i) + " ");
        }
        System.out.println("\n-----------------");
    }
}
