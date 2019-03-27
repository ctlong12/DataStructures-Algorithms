/*
Chandler Long

The purpose of this program is to implement two different algorithms to solve the famous selection problem.
Selection1 simply sorts the elements in decreasing order and returns the kth value.
Selection2 divides the array in half, sorts the first half, then swaps out elements larger than k before
returning the value for k.

Credit is due for Java Examples for helping my with the bubble sort in decreasing order.
https://www.java-examples.com/java-bubble-sort-descending-order-example
*/

import java.util.Scanner;

public class HW1 extends HW1_AbstractClass {

    static HW1 selectionAlgorithms = new HW1();

    public int selection1(int N) {
        int[] intArray = selectionAlgorithms.a;
        bubbleSort(intArray);
        int k = intArray.length / 2;

        return intArray[k];
    }

    public int selection2(int N) {
        int[] intArray = selectionAlgorithms.a;

        int arrayLength = intArray.length;
        int k = arrayLength / 2;
        int firstHalfLen = k + 1;
        int[] firstHalf = new int[firstHalfLen];

        //Read the first half of the elements into an array
        for (int i = 0; i < firstHalfLen; i++) {
            firstHalf[i] = intArray[i];
        }

        //Sort the first half of the elements in decreasing order
        bubbleSort(firstHalf);

        for (int i = firstHalfLen; i < arrayLength; i++) {
            int val = intArray[i];

            // If the new element to insert is >= the kth largest
            if (val > firstHalf[k]) {
                int pos = 0;
                // find index
                while ((pos < firstHalfLen) && (val <= firstHalf[pos])) {
                    pos++;
                }
                // Swap
                for (int j = k; j > pos; j--) {
                    firstHalf[j] = firstHalf[j - 1];
                }
                firstHalf[pos] = val;

            }
        }
        return firstHalf[k];
    }

    /*
    PRECAUTION: Please enter a non-negative integer
    */

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.print("How many elements would you like in your array?: ");

        int numberOfElements = scnr.nextInt();
        int[] array = new int[numberOfElements];

        selectionAlgorithms.a = fillArray(array, numberOfElements);

        //Algorithm One
        long algorithmOneStartTime = System.currentTimeMillis();
        System.out.println("Selection Algorithm One: " + selectionAlgorithms.selection1(numberOfElements));
        long algorithmOneEndTime = System.currentTimeMillis();

        System.out.println("Algorithm One Took " + (algorithmOneEndTime - algorithmOneStartTime) / 1000.0 + " seconds\n");


        //Algorithm Two
        long AlgorithmTwoStartTime = System.currentTimeMillis();
        System.out.println("Selection Algorithm Two: " + selectionAlgorithms.selection2(numberOfElements));
        long AlgorithmTwoEndTime = System.currentTimeMillis();

        System.out.println("Algorithm One Took " + (AlgorithmTwoEndTime - AlgorithmTwoStartTime) / 1000.0 + " seconds");


    }

    //Fill array with random numbers
    public static int[] fillArray(int[] arr, int numOfElements) {
        for (int i = 0; i < numOfElements; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        return arr;
    }

    //Bubble sort algorithm for decreasing order
    public int[] bubbleSort(int[] arr) {
        int n = arr.length;
        int temp = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (arr[j - 1] < arr[j]) {
                    //swap the elements
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }
}