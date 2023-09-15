package lab10;

import java.util.ArrayList;

public class Main {
    static ArrayList<ListDisjointSet.Node> disjointNodes = new ArrayList<>();
    static ArrayList<ForestDisjointSet.Node> forestNodes = new ArrayList<>();

    static ListDisjointSet listDisjointSet = new ListDisjointSet();
    static ForestDisjointSet forestDisjointSet = new ForestDisjointSet();

    public static void main(String[] args){
        dzieci();
    }

    public static void dzieci(){
        Child tomek = new Child("tomek");
        Child olek = new Child("olek");
        Child janusz = new Child("janusz");
        Child wojciech = new Child("wojciech");
        Child kacper = new Child("kacper");

        tomek.przedstawicDzieci(olek);
        janusz.przedstawicDzieci(wojciech);

        olek.czyZnaInnego(tomek);
        janusz.czyZnaInnego(kacper);
        wojciech.czyZnaInnego(olek);

        kacper.przedstawicDzieci(janusz);

        kacper.czyZnaInnego(janusz);
        kacper.czyZnaInnego(wojciech);
    }

    public static void test(){
        disjointNodes.add(listDisjointSet.makeSet());
        disjointNodes.add(listDisjointSet.makeSet());
        disjointNodes.add(listDisjointSet.makeSet());
        disjointNodes.add(listDisjointSet.makeSet());
        listDisjointSet.union(disjointNodes.get(0), disjointNodes.get(1));
        listDisjointSet.union(disjointNodes.get(0), disjointNodes.get(2));
        ListDisjointSet.Node searchingDisjointNode1 = listDisjointSet.findSet(disjointNodes.get(0));
        listDisjointSet.union(disjointNodes.get(1), disjointNodes.get(2));
        ListDisjointSet.Node searchingDisjointNode2 = listDisjointSet.findSet(disjointNodes.get(3));
        listDisjointSet.union(disjointNodes.get(0), disjointNodes.get(1));
//        listDisjointSet.union(null, null);

        forestNodes.add(forestDisjointSet.makeSet());
        forestNodes.add(forestDisjointSet.makeSet());
        forestNodes.add(forestDisjointSet.makeSet());
        forestNodes.add(forestDisjointSet.makeSet());
        forestNodes.add(forestDisjointSet.makeSet());
        forestNodes.add(forestDisjointSet.makeSet());
        forestDisjointSet.union(forestNodes.get(0), forestNodes.get(1));
        forestDisjointSet.union(forestNodes.get(1), forestNodes.get(2));
        forestDisjointSet.union(forestNodes.get(3), forestNodes.get(3));
    }

}
