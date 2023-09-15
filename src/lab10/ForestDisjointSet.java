package lab10;

public class ForestDisjointSet {
    class Node{
        private Node parent;
        private int rank;

        private Node(){
            parent = this;
            rank = 0;
        }
    }

    public Node makeSet(){
        return new Node();
    }

    public Node findSet(Node node){
        if (node == null)
            throw new NullPointerException("Nie można szukać wartości null");

        if (node != node.parent)
            node.parent = findSet(node.parent);
        return node.parent;
    }

    public void union(Node node1, Node node2){
        if (node1 == null || node2 == null)
            throw new NullPointerException("Nie można połaczyć wartości null");

        if (node1.parent == node2.parent){
            System.out.println("Elementy juz sa w jednym zbiorze");
            return;
        }

        link(findSet(node1), findSet(node2));
        System.out.println("Linked");
    }

    private void link(Node node1, Node node2){
        if (node1.rank > node2.rank)
            node2.parent = node1;
        else {
            node1.parent = node2;
            if (node1.rank == node2.rank)
                ++node2.rank;
        }
    }
}
