/**
 * Chandler Long
 * Data Structures and Algorithms HW7
 * The purpose of this assignment is to implement new methods for a binary search tree class.
 * The first method counts the number of leaf nodes in the tree. The second calculates the height of
 * the tree, and the third method calculates the depth of a particular node.
 */

public class HW7 extends BST {

    public static BST binarySearchTree = new BST();

    public static void main(String args[]) {

        binarySearchTree.insert(20);
        binarySearchTree.insert(10);
        binarySearchTree.insert(30);
        binarySearchTree.insert(5);
        binarySearchTree.insert(12);
        binarySearchTree.insert(24);
        binarySearchTree.insert(45);
        binarySearchTree.insert(2);
        binarySearchTree.insert(6);
        binarySearchTree.insert(100);


        System.out.println(binarySearchTree.size());
        System.out.println(binarySearchTree.countLeafNodes());
        System.out.println(binarySearchTree.height());

        System.out.println(binarySearchTree.depthOf(45));

    }
}
