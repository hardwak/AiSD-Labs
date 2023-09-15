package lab2;

import java.util.Iterator;

public class test {

    static Integer[][] tab = {
            {2,3,4},
            {1,1,1,1},
            {7,6,5},
            {0},
            {345,27,5,5,5,5}
    };

    static int[] numOfElem = {3, 4, 3, 1, 6};

    public static void main(String[] args){
        Array2 array2 = new Array2<Integer>(numOfElem);


        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                array2.set(tab[i][j] , i, j);
            }
        }
        //array2.set("String", 0, 0);

        System.out.println(array2.toString());

        /*
        Iterator<Array2> iterator = array2.iterator();

        int ipos = 0, jpos = 0;
        while (iterator.hasNext()) {

            if(jpos >= array2.getNumOfElem()[ipos]){
                ipos++;
                jpos = 0;
                System.out.println();
            }

            System.out.print(iterator.next() + " ");

            jpos++;
        }

        for (Object arrayItem: array2) {
            System.out.print(arrayItem + " ");
        }*/

        Iterator<Array2> iterator2 = array2.iterator(8);

        while (iterator2.hasNext()){
            System.out.print(iterator2.next() + " ");
        }
    }
}