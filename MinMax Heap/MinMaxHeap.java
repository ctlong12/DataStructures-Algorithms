/*
 * Chandler Long
 * HW9, Spring 2019
 * Creates and populates a min-max heap, then finds and removes both the max and min values
 */

import java.lang.reflect.Array;
import java.util.Arrays;
import javafx.util.Pair;

public class MinMaxHeap<E extends Comparable<? super E>> extends HW8_AbstractClass<E> {
	
	public MinMaxHeap (int maxSize) {
		n = 0;
		capacity = maxSize;
		MMHeap = (E[]) new Comparable[maxSize];
	}
	
	/**
	 * Builds the heap starting at the 2nd to last row, calling the siftdown method
	 * for each item on the second to last level or above
	 */
	public void buildheap() {
		for (int i=n/2-1; i>=0; i--)
			siftdown(i);
	}
	
	/**
	 * Returns the capacity of the heap
	 * @return Returns the capacity of the heap (int)
	 */
	public int capacity() {
		return capacity;
	}
	
	/**
	 * boolean indicating whether or not the value at pos is a leaf
	 * @param pos
	 * @return boolean indicating whether or not the value at pos is a leaf
	 */
	boolean isLeaf(int pos) {
		return (pos >= n/2) && (pos < n);
	}
	
	/**
	 * Return position of parent of child at pos
	 * @param pos
	 * @return position of parent (int)
	 */
	private int parent(int pos) { 
        return (pos-1)/ 2;
    }

	public E findMin() {
		if (n < 1)
			return null;
		return MMHeap[0];
	}

	public E findMax() {
		if (n < 1)
			return null;
		if (n == 1)
			return MMHeap[0];
		else if (n == 2)
			return MMHeap[1];
		else if (n >= 3) {
			if (MMHeap[1].compareTo(MMHeap[2]) > 0)
				return MMHeap[1];
			else
				return MMHeap[2];
		}
		return null;
	}

	public void deleteMin() {
		
	}

	public void deleteMax() {
		
	}

	/**
	 * inserts new element at the end of the heap, then bubbles up
	 * @param key
	 */
	public void insert(E key) {
		if (capacity == n)
			return;
		MMHeap[n] = key;
		bubbleUp(n);
		n++;
	}

	
	//******************** SIFTDOWN ********************//
	/**
	 * chooses the max or min helper depending on what level the item at pos is on
	 * @param pos
	 */
	void siftdown(int pos) {
	    if (onMinLevel(pos))
	    	siftdownMin(pos);
	    else
	    	siftdownMax(pos);
	}
	
	/**
	 * Helper method for siftdown that's used when the item is on a min level
	 * @param pos
	 */
	void siftdownMin(int pos) {
		if (hasChildren(pos)) {
			int m = smallestChildOrGrandChild(pos);
			if (isGrandChild(pos, m)) {
				if (MMHeap[m].compareTo(MMHeap[pos]) < 0) {
					swap(m, pos);
					if (MMHeap[m].compareTo(MMHeap[parent(m)]) > 0) {
						swap(m, parent(m));
					}
					siftdownMin(m);
				}
			}
			else {
				if (MMHeap[m].compareTo(MMHeap[pos]) < 0)
					swap(m, pos);
			}
		}
	}
	
	/**
	 * Helper method for siftdown that's used when the item is on a max level
	 * @param pos
	 */
	void siftdownMax(int pos) {
		if (hasChildren(pos)) {
			int m = largestChildOrGrandChild(pos);
			if (isGrandChild(pos, m)) {
				if (MMHeap[m].compareTo(MMHeap[pos]) > 0) {
					swap(m, pos);
					if (MMHeap[m].compareTo(MMHeap[parent(m)]) < 0) {
						swap(m, parent(m));
					}
					siftdownMax(m);
				}
			}
			else {
				if (MMHeap[m].compareTo(MMHeap[pos]) > 0)
					swap(m, pos);
			}
		}
	}
	//******************** SIFTDOWN ********************//

	
	
	//******************** BUBBLE UP ********************//
	/**
	 * 'bubbles up' the item at the position indicated, swapping it with any parents it might have if the parent is smaller
	 * @param pos
	 */
	void bubbleUp(int pos) {
		if (onMinLevel(pos)) {
			if (hasParent(pos)) {
				//if item at pos is bigger than the parent, swap and call bubbleupmax
				if (MMHeap[pos].compareTo(MMHeap[parent(pos)]) > 0) {
					swap(pos, parent(pos));
					bubbleUpMax(parent(pos));
				}
				else
					bubbleUpMin(pos);
			}
		}
		else {
			if (hasParent(pos)) {
				//if item at pos is smaller than its parent (which is on min level), swap and call bubbleupmin
				if (MMHeap[pos].compareTo(MMHeap[parent(pos)]) < 0) {
					swap(pos, parent(pos));
					bubbleUpMin(parent(pos));
				}
				else
					bubbleUpMax(pos);
			}
		}
	}
	
	/**
	 * Helper method for bubble up that's used when the item is on a min level
	 * @param pos
	 */
	void bubbleUpMin(int i) {
		int grandparent = parent(parent(i));
		if (hasGrandParent(i)) {
			if (MMHeap[i].compareTo(MMHeap[grandparent]) < 0) {
				swap (i, grandparent);
				bubbleUpMin(grandparent);
			}
		}
	}
	
	/**
	 * Helper method for bubble up that's used when the item is on a max level
	 * @param pos
	 */
	void bubbleUpMax(int pos) {
		int grandparent = parent(parent(pos));
		if (hasGrandParent(pos)) {
			if (MMHeap[pos].compareTo(MMHeap[grandparent]) > 0) {
				swap (pos, grandparent);
				bubbleUpMax(grandparent);
			}
		}
	}
	//******************** BUBBLE UP ********************//
	
	
	//******************** LARGEST/SMALLEST CHILD/GRANDCHILD ********************//
	/**
	 * finds and returns the index of the child/grandchild with the smallest value
	 * @param pos
	 * @return returns the index of the child/grandchild with the smallest value
	 */
	int smallestChildOrGrandChild(int pos) {
		int smallestValueIndex = 0;
		if (hasChildren(pos)) {
			smallestValueIndex = 2*pos+1;
			if (MMHeap[2*pos+2].compareTo(MMHeap[2*pos+1]) < 0)
				smallestValueIndex = 2*pos+2;
			if (hasChildren(2*pos+1)) {
				int cpos = 2*pos+1;
				if (MMHeap[2*cpos+1].compareTo(MMHeap[smallestValueIndex]) < 0)
					smallestValueIndex = 2*cpos+1;
				if (MMHeap[2*cpos+2].compareTo(MMHeap[smallestValueIndex]) < 0)
					smallestValueIndex = 2*cpos+2;
			}
			if (hasChildren(2*pos+2)) {
				int cpos = 2*pos+2;
				if (MMHeap[2*cpos+1].compareTo(MMHeap[smallestValueIndex]) < 0)
					smallestValueIndex = 2*cpos+1;
				if (MMHeap[2*cpos+2].compareTo(MMHeap[smallestValueIndex]) < 0)
					smallestValueIndex = 2*cpos+2;
			}
		}
		return smallestValueIndex;
	}
	
	/**
	 * finds and returns the index of the child/grandchild with the largest value
	 * @param pos
	 * @return returns the index of the child/grandchild with the largest value
	 */
	int largestChildOrGrandChild(int pos) {
		int largestValueIndex = 0;
		if (hasChildren(pos)) {
			largestValueIndex = 2*pos+1;
			if (MMHeap[2*pos+2].compareTo(MMHeap[2*pos+1]) > 0)
				largestValueIndex = 2*pos+2;
			if (hasChildren(2*pos+1)) {
				int cpos = 2*pos+1;
				if (MMHeap[2*cpos+1].compareTo(MMHeap[largestValueIndex]) > 0) 
					largestValueIndex = 2*cpos+1;
				if (MMHeap[2*cpos+2].compareTo(MMHeap[largestValueIndex]) > 0)
					largestValueIndex = 2*cpos+2;
			}
			if (hasChildren(2*pos+2)) {
				int cpos = 2*pos+2;
				if (MMHeap[2*cpos+1].compareTo(MMHeap[largestValueIndex]) > 0)
					largestValueIndex = 2*cpos+1;
				if (MMHeap[2*cpos+2].compareTo(MMHeap[largestValueIndex]) > 0)
					largestValueIndex = 2*cpos+2;
			}
		}
		return largestValueIndex;
	}
	//******************** LARGEST/SMALLEST CHILD/GRANDCHILD ********************//
		

	
	
	//******************** RELATIONSHIP BOOLEANS ********************//
	/**
	 * returns true or false depending on whether or not the item at pos has a parent
	 * @param pos
	 * @return returns true or false depending on whether or not the item at pos has a parent
	 */
	boolean hasParent(int pos) {
		if (pos > 0)
			return true;
		return false;		
	}
	/**
	 * returns true or false depending on whether or not the item at pos has a grandparent
	 * @param pos
	 * @return returns true or false depending on whether or not the item at pos has a grandparent
	 */
	boolean hasGrandParent(int pos) {
		if (pos > 2)
			return true;
		return false;
	}
	/**
	 * returns true or false depending on whether or not the item at pos has children
	 * @param pos
	 * @return returns true or false depending on whether or not the item at pos has children
	 */
	boolean hasChildren(int pos) {
		if ((2*pos+1 < n) || (2*pos+2 < n)) {
			return true;
		}
		return false;
	}
	/**
	 * returns true or false depending on whether or not the item at pos has grandchildren
	 * @param pos
	 * @return returns true or false depending on whether or not the item at pos has grandchildren
	 */
	boolean hasGrandChildren(int pos) {
		if (hasChildren(2*pos+1) || hasChildren(2*pos+2))
			return true;
		return false;
	}
	/**
	 * returns true or false depending on whether or not the item at pos is a grandchild
	 * @param parent
	 * @param grandchild
	 * @return returns true or false depending on whether or not the item at pos is a grandchild
	 */
	boolean isGrandChild(int parent, int grandchild) {
		if (hasGrandChildren(parent))
			if (grandchild < n)
				return true;
		return false;
	}
	//******************** RELATIONSHIP BOOLEANS ********************//
	
	
	
	
	//******************** ON LEVEL METHODS ********************//
	/**
	 * checks whether or not the item at pos is on a min level
	 * @param pos
	 * @return true or false based on whether or not the item at pos is on a min level
	 */
	boolean onMinLevel(int pos) {
		int level = onLevel(pos+1);
		if (level == 1)
			return true;
		if (level % 2 == 0)
			return false;
		return true;
	}
	int onLevel(int pos) {
		return (int) Math.floor(Math.log(pos)/Math.log(2))+1;
	}
	//******************** ON LEVEL METHODS ********************//
	
	
	/**
	 * Swaps the elements at the positions indicated by pos and j
	 * @param pos
	 * @param j
	 */
	private void swap(int pos, int j) {
		E temp = MMHeap[pos];
		MMHeap[pos] = MMHeap[j];
		MMHeap[j] = temp;
	}
	
	/**
	 * Prints the contents of the heap
	 */
	void printHeap() {
		for (int i = 0; i < n; i++) {
			System.out.print(MMHeap[i]+", ");
		}
	}
	
	public static void main(String[] args) {
		//int[] anArray = new int[] {1, 18, 5, -19, -5, 6, 43, -8, 60, 40, 85, -100, 2, 7, -20};
		//int[] anArray = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		int[] anArray = new int[] {15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
		MinMaxHeap heap = new MinMaxHeap(20);
		for (int i = 0; i < heap.capacity() && i < anArray.length; i++)
			heap.insert(anArray[i]);
		System.out.println("Printing First Heap...");
		heap.printHeap();
		heap.buildheap();
		System.out.println();
		heap.printHeap();
	}
}
