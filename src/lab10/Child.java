package lab10;

public class Child {
    private String name;
    private ListDisjointSet listDisjointSet;
    private ListDisjointSet.Node childNode;

    public Child(String name){
        this.name = name;
        listDisjointSet = new ListDisjointSet();
        childNode = listDisjointSet.makeSet();
    }

    public void przedstawicDzieci(Child otherChild){
        listDisjointSet.union(childNode, otherChild.childNode);
    }

    public void czyZnaInnego(Child otherChild){
        System.out.println("Czy " + name + " zna " + otherChild.name + ": " + listDisjointSet.isInRelation(childNode, otherChild.childNode));
    }
}
