/*
Chandler Long
The purpose of this code is to recursively find, and then display the binary representation of a number.
 */

public class HW2 extends HW2_AbstractClass {

    /*
    This is the recursive method for finding the binary representation. There are two base cases,
    if n = 0 there will be no 1's and if n = 1 there will one be 1. Otherwise, the  recursive case
    is called which divides n by 2 and and adds it to the remainder.
     */
    public int countOfOnes(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return countOfOnes(n / 2) + countOfOnes(n % 2);
        }
    }
    /*
    The main method in this program first initializes the class and sets up the test values.
    Next it loops though the values of the array and carries out the binary representation.

    PRECONDITION: The values must be positive integers
     */
    public static void main(String[] args) {
        HW2 decimalConverter = new HW2();

        int[] numbers = new int[]{44, 22, 11, 5, 2, 1, 33, 16, 8, 6};
        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];

            System.out.println("The binary representation of " + num + " is: " + decimalConverter.countOfOnes(num));
        }
    }
}