package lab1;

import java.util.Arrays;
import java.util.Scanner;

public class ScarfAndArrays {

    static int[] numbers = {1, 2, 8, 11, 15, -3, -3, -4, 7, 10, 0};

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("wpisz dlugosc zatem szerokosc");

        drawZigZag(scanner.nextInt(), scanner.nextInt());

        System.out.println("Wpisz dlugosc zatem k ");

        drawScarf(scanner.nextInt(), scanner.nextInt());

        System.out.println(
                "Arrays: \n" +
                Arrays.deepToString(
                longestNondecreasingSubstrings(numbers))
        );

        System.out.println(longestNondecreasingSubstring(numbers).toString());
    }

    public static int[] getSortedArray(int[] tab, int endOfPrevArray){
        for (int j = 0; j < tab.length; j++) {
            tab[j] = numbers[j + endOfPrevArray];
        }
        return tab;
    }

    public static Substring longestNondecreasingSubstring(int[] numbers){
        Substring MaxString = new Substring(-1, -1);

        int endOfPrevArray = 0;

        for (int i = 1; i < numbers.length; i++) {


            if(numbers[i-1] > numbers[i]){

                if(i - endOfPrevArray > MaxString.getRange()) {
                    MaxString.setRange(i - endOfPrevArray);
                    MaxString.setPosition(endOfPrevArray);
                }

                endOfPrevArray = i;
            }

            if(i == numbers.length - 1 && i - endOfPrevArray > MaxString.getRange()){                //sprawdzenie ostatniego elementu
                MaxString.setRange(i - endOfPrevArray + 1);
                MaxString.setPosition(endOfPrevArray);
            }
        }

        return MaxString;
    }

    public static int[][] longestNondecreasingSubstrings(int[] numbers){
        int [][] sortedArrays = new int[numbers.length][];
///*
        //przejscie wiecej niz jeden raz???

        int currentArray = 0;
        int endOfPrevArray = 0;             //counters

        for (int i = 1; i < numbers.length; i++) {


            if(numbers[i-1] > numbers[i]){

                int[] tab = new int[i - endOfPrevArray]; //tablica potrzebnego wymiaru

                sortedArrays[currentArray] = getSortedArray(tab, endOfPrevArray);       //ustawiam w konieczna tablice

                endOfPrevArray = i;
                currentArray++;
            }

            if(i == numbers.length - 1){                //sprawdzenie ostatniego elementu
                int[] tab = new int[i - endOfPrevArray + 1];

                sortedArrays[currentArray] = getSortedArray(tab, endOfPrevArray);
            }
        }
//*/




        //nieefektywne wykorzystanie pamieci
/*
        int currentArray = 0;
        int currentPlace = 0;
        sortedArrays[0][0] = numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            currentPlace++;
            if(numbers[i-1] > numbers[i]){
                currentArray++;
                currentPlace = 0;
            }
            sortedArrays[currentArray][currentPlace] = numbers[i];
        }*/
        return sortedArrays;

    }


    public static void drawScarf(int n, int k){
        boolean increaseSpaces = true;
        boolean increaseO = true;
        int numOfSpaces = 0;
        int numOfXuntilO = k;

        for (int i = 0; i < n; i++) {
            drawSpacesTimesN(numOfSpaces);

            drawXtimesN(numOfXuntilO);
            drawOtimesN(2*k+1 - numOfXuntilO * 2);
            drawXtimesN(numOfXuntilO);

            System.out.println();

            //num of O
            if(increaseO)
                numOfXuntilO--;
            else
                numOfXuntilO++;

            if(numOfXuntilO == 0)
                increaseO = false;
            if (numOfXuntilO == k)
                increaseO = true;

            //num of spaces
            if (increaseSpaces)
                numOfSpaces++;
            else
                numOfSpaces--;

            if (numOfSpaces == 2*k + 1)
                increaseSpaces = false;

            if(numOfSpaces == 0)
                increaseSpaces = true;
        }
    }

    public static void drawZigZag(int n, int l){

        boolean increase = true;
        int numOfSpaces = 0;

        for (int i = 0; i < n; i++) {
            drawSpacesTimesN(numOfSpaces);
            drawXtimesN(l);
            System.out.println();

            if (increase)
                numOfSpaces++;
            else
                numOfSpaces--;


            if (numOfSpaces == l)
                increase = false;

            if(numOfSpaces == 0)
                increase = true;
        }
    }

    public static void drawOtimesN(int l){
        for (int i = 0; i < l; i++) {
            System.out.print("O");
        }
    }

    public static void drawXtimesN(int l){
        for (int i = 0; i < l; i++) {
            System.out.print("X");
        }
    }

    public static void drawSpacesTimesN(int n){
        for (int i = 0; i < n; i++) {
            System.out.print(" ");
        }
    }
}
