package lab10;

public class ListDisjointSet {
    class Node{
        private Node represent;
        private Node next;
        private Node last;
        private int size;

        private Node(){
            represent = this;
            next = null;
            last = this;
            size = 1;
        }
    }



    public ListDisjointSet(){
    }

    public Node makeSet(){
        return new Node();
    }

    public boolean isInRelation(Node node1, Node node2){
        return node1.represent == node2.represent;
    }

    public Node findSet(Node node){
        if (node == null)
            throw new NullPointerException("Nie można szukać wartości null");
        return node.represent;
    }

    public void union(Node node1, Node node2){
        if (node1 == null || node2 == null)
            throw new NullPointerException("Nie można połaczyć wartości null");

        if (node1.represent == node2.represent){
            System.out.println("Elementy juz sa w jednym zbiorze");
            return;
        }

        if (node1.represent.size < node2.represent.size){
            Node temp = node1;
            node1 = node2;
            node2 = temp;
        }

        node1.represent.last.next = node2.represent;
        node1.represent.size += node2.represent.size;
        node1.represent.last = node2.represent.last;
        node2.represent.last = null;

        //zmiana referencji na reprezentanta
        node2 = node2.represent;
        while (node2 != null){
            node2.represent = node1.represent;
            node2 = node2.next;
        }
    }
}
