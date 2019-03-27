/**
 * @author Chandler Long
 * Data Structures and Algorithms
 * The purpose of this homework assignment is to implement and test different methods
 * that can be used to navigate a double linked list. Methods such as append, insert, isEmpty,
 * currPos are amongst the many methods that will be implemented and tested.
 */


public class HW3 extends DoublyLinkedList {

    static HW3 doubleLinkedList = new HW5();

    public static void main(String args[]) {

        //Add method
        doubleLinkedList.insert(25);
        doubleLinkedList.insert(20);
        doubleLinkedList.insert(15);
        doubleLinkedList.insert(10);
        doubleLinkedList.insert(5);
        doubleLinkedList.insert(0);

        //Append method
        doubleLinkedList.append(30);

        //To string method
        System.out.println(doubleLinkedList.toString());

        //Remove method
        System.out.println("Current Position: " + doubleLinkedList.currPos());
        doubleLinkedList.remove();
        System.out.println("List after removal method...");

        System.out.println(doubleLinkedList.toString());

        //Move to start method
        doubleLinkedList.moveToStart();
        System.out.println("Current position (move to start): " + doubleLinkedList.currPos());

        //Move to end method
        doubleLinkedList.moveToEnd();
        System.out.println("Current Position: (move to end) " + doubleLinkedList.currPos());

        //Prev method
        doubleLinkedList.prev();
        System.out.println("Current Position: (previous) " + doubleLinkedList.currPos());

        //Next method
        doubleLinkedList.next();
        System.out.println("Current Position: (next) " + doubleLinkedList.currPos());

        //Length method
        System.out.println("Length of List: " + doubleLinkedList.length());

        //Move to position method
        doubleLinkedList.moveToPos(3);
        System.out.println("Current Position: (move to position) " + doubleLinkedList.currPos());

        //Is at end method
        doubleLinkedList.isAtEnd();
        System.out.println("Current Position: (is at end) " + doubleLinkedList.isAtEnd());

        //Move to end method
        doubleLinkedList.moveToEnd();
        System.out.println("Current Position: (is at end) " + doubleLinkedList.isAtEnd());

        //Get value method
        System.out.println("Get the current element: " + doubleLinkedList.getValue());

        //Is empty method & clear method
        System.out.println(doubleLinkedList.isEmpty());
        doubleLinkedList.clear();
        System.out.print(doubleLinkedList.isEmpty());

        System.out.println(doubleLinkedList.toString());

    }
}
